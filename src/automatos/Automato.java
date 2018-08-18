package automatos;


import enuns.Codigo;
import enuns.Tipo;
import token.Token;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

public class Automato {

    private Estado estadoInicial;
    private Set<Estado> estadoFinais = new HashSet<>();

    public Automato(Estado estadoInicial, Estado ... estadoFinais) {
        this.estadoInicial = estadoInicial;

        for (int i=0; i<estadoFinais.length; i++){
            this.estadoFinais.add(estadoFinais[i]);
        }
    }

    public Token executar(InputStream inputStream) throws IOException{

        Estado estadoAtual = estadoInicial;

        Integer caracterAtual = inputStream.read();

        StringBuilder builderPalavra = new StringBuilder();

        while (true) {
            Tipo tipo = Tipo.valueOfBy(caracterAtual);

            Estado estado = estadoAtual.getEstado(tipo);

            if(estado != null){
                estadoAtual = estado;

                if(estadoFinais.contains(estadoAtual)){
                    break;
                }

                builderPalavra.append((char) caracterAtual.intValue());
            }

            caracterAtual = inputStream.read();
        }

        return retorno(estadoAtual, builderPalavra.toString());
    }

    private Token retorno(Estado estadoFinal, String palavra){
        Tipo tipo = estadoFinal.getRetornoEspecifico();

        if(tipo != null){
            if(tipo == Tipo.IDENTIFICADOR){
                return new Token(palavra, Codigo.IDENTIFICADOR);
            }

            if(tipo == Tipo.KEYWORD){
                return new Token(palavra, Codigo.valueOf(palavra.toUpperCase()));
            }

            if(tipo == Tipo.OPERADOR){
                return new Token(palavra, Codigo.getByOperador(palavra));
            }
        }

        return new Token(palavra);
    }

    //    private void analiseLexica(InputStream inputstream) throws Exception {
//
//        int caracterLido = inputstream.read();
//        int caracterProximo = inputstream.read();
//
//        Stack<Token> tokens = new Stack<>();
//        while (caracterLido != -1) {
//
//            if(isEspaco(caracterLido)){
//                continue;
//            }
//
//            boolean isCaracter = isCaracter(caracterLido);
//            boolean isDigito = isDigito(caracterLido);
//            boolean isDelimitador = isDelimitador(caracterLido);
//
//            StringBuilder buiderPalavra = new StringBuilder();
//
//            if (isCaracter) { //CARACTERES
//                boolean temDigito = isDigito;
//
//                while(isCaracter || isDigito){
//                    buiderPalavra.append((char) caracterLido);
//
//                    if(!temDigito && isDigito){
//                        temDigito = true;
//                    }
//
//                    caracterLido = caracterProximo;
//                    caracterProximo = inputstream.read();
//
//                    isCaracter = isCaracter(caracterLido);
//                    isDigito = isDigito(caracterLido);
//                }
//
//                String palavra = buiderPalavra.toString();
//
//                if(temDigito){ //Se tem digito sigifica que é identificador
//                    tokens.push(new Token(palavra, Codigo.IDENTIFICADOR));
//                }else{
//                    tokens.push(new Token(palavra));
//                }
//
//            } else if (isDigito) { //DIGITOS
//
//                while (isDigito){
//                    buiderPalavra.append(caracterLido);
//
//                    caracterLido = caracterProximo;
//                    caracterProximo = inputstream.read();
//                    isDigito = isDigito(caracterLido);
//
//                    if(isCaracter(caracterProximo)){
//                        throw new Exception("NUmero invalido");
//                    }
//                }
//
//                tokens.push(new Token(buiderPalavra.toString(), Codigo.INTEIRO));
//
//            } else if (isDelimitador) { //DELIMITADORES
//                while (isDelimitador){
//                    buiderPalavra.append(caracterLido);
//                    if(caracterLido == 39){ // '
//
//                        do{
//
//                        } while (caracterProximo != 39){
//                            buiderPalavra.append(caracterLido);
//
//                            caracterLido = caracterProximo;
//                            caracterProximo = inputstream.read();
//                        }
//
//                        tokens.push(new Token(buiderPalavra.toString(), Codigo.LITERAL));
//                        tokens.push(new Token(Codigo.));
//
//                    }else if(caracterLido == 40){ // (
//
//                    }else{
//                        if(!isDelimitador(caracterProximo)){
//                            tokens.push(new Token(buiderPalavra.toString()));
//                            break;
//                        }
//                    }
//
//                    caracterLido = caracterProximo;
//                    caracterProximo = inputstream.read();
//                    isDelimitador = isDelimitador(caracterLido);
//                }
//            }
//
//            caracterLido = caracterProximo;
//            caracterProximo = inputstream.read();
//        }
//    }
}
