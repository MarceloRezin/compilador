package analise;

import arquivo.Leitor;
import automatos.Automato;
import automatos.Estado;
import enuns.Tipo;
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
                tokens.push(token);
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

    /*
    Possibilidades:
        CARACTER
        OPERADOR
        ESPACO
        DIGITO
        FIM
        LITERAL
     */
    private static Automato getAutomato(){

        Estado e1 = new Estado();

        //Identifica espacos
        Estado e14 = new Estado(Tipo.ESPACO);
        e1.addTransicao(Tipo.ESPACO, e14);

        //Identificacao de caracteres e palavras chave
        Estado e2 = new Estado();
        Estado e3 = new Estado();
        Estado e4 = new Estado();
        Estado e5 = new Estado(Tipo.IDENTIFICADOR);

        e1.addTransicao(Tipo.CARACTER, e2);
        e2.addTransicao(Tipo.CARACTER, e2);
        e2.addTransicao(Tipo.OPERADOR, e3);
        e2.addTransicao(Tipo.ESPACO, e3);
        e2.addTransicao(Tipo.FIM, e3);
        e2.addTransicao(Tipo.DIGITO, e4);
        e4.addTransicao(Tipo.DIGITO, e4);
        e4.addTransicao(Tipo.CARACTER, e4);
        e4.addTransicao(Tipo.OPERADOR, e5);
        e4.addTransicao(Tipo.ESPACO, e5);
        e4.addTransicao(Tipo.FIM, e5);

        //Identificacao de inteiros
        Estado e6 = new Estado();
        Estado e7 = new Estado(Tipo.DIGITO);
        Estado e8 = new Estado(Tipo.ERRO);

        e1.addTransicao(Tipo.DIGITO, e6);
        e6.addTransicao(Tipo.DIGITO, e6);
        e6.addTransicao(Tipo.OPERADOR, e7);
        e6.addTransicao(Tipo.ESPACO, e7);
        e6.addTransicao(Tipo.FIM, e7);
        e6.addTransicao(Tipo.CARACTER, e8);

        //Identificacao de operadores, literais e comentarios
        Estado e9 = new Estado();
        Estado e10 = new Estado();
        Estado e11 = new Estado(Tipo.LITERAL);
        Estado e12 = new Estado();
        Estado e13 = new Estado(Tipo.OPERADOR);

        e1.addTransicao(Tipo.LITERAL, e9);
        e1.addTransicao(Tipo.OPERADOR, e12);
        e9.addTransicao(Tipo.CARACTER, e9);
        e9.addTransicao(Tipo.DIGITO, e9);
        e9.addTransicao(Tipo.ESPACO, e9);
        e9.addTransicao(Tipo.OPERADOR, e9);
        e9.addTransicao(Tipo.LITERAL, e10);
        e10.addTransicao(Tipo.LITERAL, e11);
        e10.addTransicao(Tipo.OPERADOR, e11);
        e10.addTransicao(Tipo.CARACTER, e11);
        e10.addTransicao(Tipo.DIGITO, e11);
        e10.addTransicao(Tipo.ESPACO, e11);
        e10.addTransicao(Tipo.FIM, e11);

        e12.addTransicao(Tipo.LITERAL, e13);
        e12.addTransicao(Tipo.OPERADOR, e13);
        e12.addTransicao(Tipo.CARACTER, e13);
        e12.addTransicao(Tipo.DIGITO, e13);
        e12.addTransicao(Tipo.ESPACO, e13);
        e12.addTransicao(Tipo.FIM, e13);

       return new Automato(e1, e3, e5, e7, e8, e11, e13, e14);
    }
}
