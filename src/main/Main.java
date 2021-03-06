package main;

import analise.AnaliseLexica;
import arquivo.Arquivo;
import exceptions.AnaliseLexicaException;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

//        try {
//            Arquivo.gravar("texto.txt", "ACAD");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        try {
            AnaliseLexica.analisar(Arquivo.convert(Arquivo.ler("texto.lms")).toCharArray());
        } catch (AnaliseLexicaException e) {
            e.printStackTrace();
        }
    }
}
