package main;

import arquivo.Arquivo;
import automatos.Automato;
import automatos.Estado;
import enuns.Tipo;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

//        try {
//            Arquivo.gravar("texto.txt", "ACAD");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        Estado e1 = new Estado();
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


        Automato automato = new Automato(e1, e3, e5);

        System.out.println(automato.executar(Arquivo.ler("texto.txt")));
    }
}
