package utils;


public final class CharUtils {

    //LETRAS: A-Za-z
    public static boolean isLetra(char caracter){
        return  (caracter > 64 && caracter < 91) || (caracter > 96 && caracter < 123);
    }

    //DIGITOS: 0-9
    public static boolean isDigito(char caracter){
        return caracter > 47 && caracter < 58;
    }

    //DELIMITADORES
    public static boolean isOperador(char caracter){
        return caracter == 36 || (caracter > 42 && caracter < 45) || caracter == 47 || caracter == 59 || caracter == 91 || caracter == 93;
    }

    //ESPACO
    public static boolean isEspaco(char caracter){
        return caracter == 32 || caracter == 9 || caracter == 10;
    }
}
