package automatos;

import enuns.Codigo;
import token.Token;

import java.io.IOException;
import java.io.InputStream;
import java.util.Stack;

public class Automatos {

    private void analiseLexica(InputStream inputstream) throws Exception {

        int caracterLido = inputstream.read();
        int caracterProximo = inputstream.read();

        Stack<Token> tokens = new Stack<>();
        while (caracterLido != -1) {

            if(isEspaco(caracterLido)){
                continue;
            }

            boolean isCaracter = isCaracter(caracterLido);
            boolean isDigito = isDigito(caracterLido);
            boolean isDelimitador = isDelimitador(caracterLido);

            StringBuilder buiderPalavra = new StringBuilder();

            if (isCaracter) { //CARACTERES
                boolean temDigito = isDigito;

                while(isCaracter || isDigito){
                    buiderPalavra.append((char) caracterLido);

                    if(!temDigito && isDigito){
                        temDigito = true;
                    }

                    caracterLido = caracterProximo;
                    caracterProximo = inputstream.read();

                    isCaracter = isCaracter(caracterLido);
                    isDigito = isDigito(caracterLido);
                }

                String palavra = buiderPalavra.toString();

                if(temDigito){ //Se tem digito sigifica que é identificador
                    tokens.push(new Token(palavra, Codigo.IDENTIFICADOR));
                }else{
                    tokens.push(new Token(palavra));
                }

            } else if (isDigito) { //DIGITOS

                while (isDigito){
                    buiderPalavra.append(caracterLido);

                    caracterLido = caracterProximo;
                    caracterProximo = inputstream.read();
                    isDigito = isDigito(caracterLido);

                    if(isCaracter(caracterProximo)){
                        throw new Exception("NUmero invalido");
                    }
                }

                tokens.push(new Token(buiderPalavra.toString(), Codigo.INTEIRO));

            } else if (isDelimitador) { //DELIMITADORES
                while (isDelimitador){
                    buiderPalavra.append(caracterLido);
                    if(caracterLido == 39){ // '

                        do{

                        } while (caracterProximo != 39){
                            buiderPalavra.append(caracterLido);

                            caracterLido = caracterProximo;
                            caracterProximo = inputstream.read();
                        }

                        tokens.push(new Token(buiderPalavra.toString(), Codigo.LITERAL));
                        tokens.push(new Token(Codigo.));

                    }else if(caracterLido == 40){ // (

                    }else{
                        if(!isDelimitador(caracterProximo)){
                            tokens.push(new Token(buiderPalavra.toString()));
                            break;
                        }
                    }

                    caracterLido = caracterProximo;
                    caracterProximo = inputstream.read();
                    isDelimitador = isDelimitador(caracterLido);
                }
            }

            caracterLido = caracterProximo;
            caracterProximo = inputstream.read();
        }
    }

    //LETRAS: A-Za-z
    private boolean isCaracter(int caracter){
        return  (caracter > 64 && caracter < 90) || (caracter > 96 && caracter < 123);
    }

    //DIGITOS: 0-9
    private boolean isDigito(int caracter){
        return caracter > 47 && caracter < 58;
    }

    //DELIMITADORES: $ (-/ :-> [ ]
    private boolean isDelimitador(int caracter){
        return caracter == 36 || (caracter > 38 && caracter < 48) || (caracter > 57 && caracter < 63) || caracter == 91 || caracter == 93;
    }

    //ESPACO
    private boolean isEspaco(int caracter){
        return caracter == 32;
    }
}
