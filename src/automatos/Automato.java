package automatos;

import arquivo.Leitor;
import enuns.Codigo;
import enuns.TipoEntrada;
import enuns.TipoRetorno;
import exceptions.AnaliseLexicaException;
import token.Token;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Automato {

    private Estado estadoInicial;
    private Set<Estado> estadoFinais = new HashSet<>();

    public Automato(Estado estadoInicial, Estado... estadoFinais) {
        this.estadoInicial = estadoInicial;

        for (int i=0; i<estadoFinais.length; i++){
            this.estadoFinais.add(estadoFinais[i]);
        }
    }

    public Token executar(Leitor leitor) throws IOException, AnaliseLexicaException{

        Estado estadoAtual = estadoInicial;

        StringBuilder builderPalavra = new StringBuilder();

        while (true) {
            Integer caracterAtual = leitor.getCaracterLido();
            char entradaChar = (char) caracterAtual.intValue();

            Object entrada = TipoEntrada.valueOfByCaracter(caracterAtual);

            if(entrada == null){
                entrada = Character.toString(entradaChar);
            }

            Estado estado = estadoAtual.getEstado(entrada);

            if(estado != null){
                estadoAtual = estado;

                if(estadoFinais.contains(estadoAtual)){
                    break;
                }

                builderPalavra.append(entradaChar);
            }

            leitor.lerProximo();
        }

        return retorno(estadoAtual, builderPalavra.toString());
    }

    private Token retorno(Estado estadoFinal, String palavra) throws AnaliseLexicaException{
        TipoRetorno tipo = estadoFinal.getRetornoEspecifico();

        if(tipo != null){
            if(tipo == TipoRetorno.IGNORAR){
                return Token.tokenIgnorado();
            }

            if(tipo == TipoRetorno.IDENTIFICADOR){
                return new Token(palavra, Codigo.IDENTIFICADOR);
            }

//            if(tipo == Tipo.KEYWORD){
//                return new Token(palavra, Codigo.valueOf(palavra.toUpperCase()));
//            }

            if(tipo == TipoRetorno.OPERADOR){
                return new Token(palavra, Codigo.getByOperador(palavra));
            }

            if(tipo == TipoRetorno.INTEIRO){
                return new Token(palavra, Codigo.INTEIRO);
            }

            if(tipo == TipoRetorno.LITERAL){
                return new Token(palavra, Codigo.LITERAL);
            }
//
            if(tipo == TipoRetorno.ERRO){
                throw new AnaliseLexicaException("Número inválido!");
            }
//
//            if(tipo == Tipo.ESPACO){
//                return null;
//            }
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
//            boolean isLetra = isLetra(caracterLido);
//            boolean isDigito = isDigito(caracterLido);
//            boolean isOperador = isOperador(caracterLido);
//
//            StringBuilder buiderPalavra = new StringBuilder();
//
//            if (isLetra) { //CARACTERES
//                boolean temDigito = isDigito;
//
//                while(isLetra || isDigito){
//                    buiderPalavra.append((char) caracterLido);
//
//                    if(!temDigito && isDigito){
//                        temDigito = true;
//                    }
//
//                    caracterLido = caracterProximo;
//                    caracterProximo = inputstream.read();
//
//                    isLetra = isLetra(caracterLido);
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
//                    if(isLetra(caracterProximo)){
//                        throw new Exception("NUmero invalido");
//                    }
//                }
//
//                tokens.push(new Token(buiderPalavra.toString(), Codigo.INTEIRO));
//
//            } else if (isOperador) { //DELIMITADORES
//                while (isOperador){
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
//                        if(!isOperador(caracterProximo)){
//                            tokens.push(new Token(buiderPalavra.toString()));
//                            break;
//                        }
//                    }
//
//                    caracterLido = caracterProximo;
//                    caracterProximo = inputstream.read();
//                    isOperador = isOperador(caracterLido);
//                }
//            }
//
//            caracterLido = caracterProximo;
//            caracterProximo = inputstream.read();
//        }
//    }
}
