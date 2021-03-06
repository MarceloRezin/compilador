package automatos;

import arquivo.Leitor;
import enuns.Codigo;
import enuns.TipoEntrada;
import enuns.TipoRetorno;
import exceptions.AnaliseLexicaException;
import token.Token;
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

    public Token executar(Leitor leitor) throws AnaliseLexicaException{

        Estado estadoAtual = estadoInicial;

        StringBuilder builderPalavra = new StringBuilder();
        int cont = 0;
        while (true) {
            char caracterAtual = leitor.getCaracterLido();

            Object entrada = TipoEntrada.valueOfByCaracter(caracterAtual);

            if(entrada == null){
                entrada = Character.toString(caracterAtual);
            }

            Estado estado = estadoAtual.getEstado(entrada);

            if(estado != null){
                estadoAtual = estado;

                if(estadoFinais.contains(estadoAtual)){
                    break;
                }

                builderPalavra.append(caracterAtual);
            }

            leitor.lerProximo();

            //Caso tenha entrado em loop
            if(cont > leitor.getLenght()){
                break;
            }

            cont ++;
        }

        if(!estadoFinais.contains(estadoAtual)){
            throw new AnaliseLexicaException("Um bloco não foi fechado!");
        }

        Token token = retorno(estadoAtual, builderPalavra.toString(), leitor);
        token.setPosicao(leitor.getLinha(), leitor.getColuna());
        return token;
    }

    private Token retorno(Estado estadoFinal, String palavra, Leitor leitor) throws AnaliseLexicaException{
        TipoRetorno tipo = estadoFinal.getRetornoEspecifico();

        if(tipo != null){
            if(tipo == TipoRetorno.IGNORAR){
                return Token.tokenIgnorado();
            }

            if(tipo == TipoRetorno.IDENTIFICADOR){
                return new Token(palavra, Codigo.IDENTIFICADOR);
            }

            if(tipo == TipoRetorno.OPERADOR){
                return new Token(palavra, Codigo.getByOperador(palavra));
            }

            if(tipo == TipoRetorno.INTEIRO){
                return new Token(palavra, Codigo.INTEIRO);
            }

            if(tipo == TipoRetorno.LITERAL){
                return new Token(palavra, Codigo.LITERAL);
            }

            if(tipo == TipoRetorno.ERRO){
                throw new AnaliseLexicaException("Número inválido na posição: " + leitor.getLinha() + " | " + leitor.getColuna());
            }
        }

        return new Token(palavra);
    }
}
