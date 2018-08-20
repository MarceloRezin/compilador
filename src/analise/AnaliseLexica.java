package analise;

import arquivo.Leitor;
import automatos.Automato;
import automatos.Estado;
import enuns.TipoEntrada;
import enuns.TipoRetorno;
import exceptions.AnaliseLexicaException;
import token.Token;

import java.io.IOException;
import java.io.InputStream;
import java.util.Stack;

public class AnaliseLexica {

    public static Stack<Token> analisar(InputStream inputStream) throws IOException, AnaliseLexicaException {
        Leitor leitor = new Leitor(inputStream);

        Stack<Token> tokens = new Stack<>();

        Automato automato = getAutomato();

        while (leitor.getCaracterLido() > 0){
            Token token = automato.executar(leitor);
            if(token != null){
                if(!token.isIgnorado()){
                    tokens.push(token);
                }
            }else{
                leitor.lerProximo();
            }
        }

        Stack<Token> pilhaInvertida = new Stack<>();

        while (tokens.size() > 0){
            pilhaInvertida.push(tokens.pop());
        }

        while (pilhaInvertida.size() > 0){
            System.out.println(pilhaInvertida.pop());
        }

        return tokens;
    }

    private static Automato getAutomato(){

        Estado e1 = new Estado();

        //Identifica espacos
        Estado e2 = new Estado();
        Estado e3 = new Estado(TipoRetorno.IGNORAR);
        e1.addTransicao(TipoEntrada.ESPACO, e2);
        e2.addTransicao(TipoEntrada.QUALQUER, e3);

        //Identificacao de caracteres e palavras chave
        Estado e4 = new Estado();
        Estado e5 = new Estado();
        Estado e6 = new Estado();
        Estado e7 = new Estado(TipoRetorno.IDENTIFICADOR);

        e1.addTransicao(TipoEntrada.LETRA, e4);
        e4.addTransicao(TipoEntrada.LETRA, e4);
        e4.setExcecao(e5);
        e4.addTransicao(TipoEntrada.DIGITO, e6);
        e6.addTransicao(TipoEntrada.DIGITO, e6);
        e6.addTransicao(TipoEntrada.LETRA, e6);
        e6.setExcecao(e7);

        //Identificacao de inteiros
        Estado e8 = new Estado();
        Estado e9 = new Estado(TipoRetorno.INTEIRO);
        Estado e10 = new Estado(TipoRetorno.ERRO);

        e1.addTransicao(TipoEntrada.DIGITO, e8);
        e8.addTransicao(TipoEntrada.DIGITO, e8);
        e8.setExcecao(e9);
        e8.addTransicao(TipoEntrada.LETRA, e10);

        //Identificacao de operadores
        Estado e11 = new Estado();
        Estado e12 = new Estado(TipoRetorno.OPERADOR);
//
        e1.addTransicao(TipoEntrada.OPERADOR, e11);
        e1.addTransicao(")", e11);
        e1.addTransicao("*", e11);
        e11.addTransicao(TipoEntrada.QUALQUER, e12);

        //Identificacao de literias
        Estado e13 = new Estado();
        Estado e14 = new Estado();
        Estado e15 = new Estado(TipoRetorno.LITERAL);

        e1.addTransicao("'", e13);
        e13.setExcecao(e13);
        e13.addTransicao("'",   e14);
        e14.addTransicao(TipoEntrada.QUALQUER, e15);

        //Identificacao de comentarios
        Estado e16 = new Estado();
        Estado e17 = new Estado();
        Estado e18 = new Estado();
        Estado e19 = new Estado();
        Estado e20 = new Estado(TipoRetorno.IGNORAR);

        e1.addTransicao("(", e16);
        e16.setExcecao(e12);
        e16.addTransicao("*", e17);
        e17.setExcecao(e17);
        e17.addTransicao("*", e18);
        e18.addTransicao(")", e19);
        e18.setExcecao(e17);
        e19.addTransicao(TipoEntrada.QUALQUER, e20);

       return new Automato(e1, e3, e5, e7, e9, e10, e12, e15, e20);
    }
}
