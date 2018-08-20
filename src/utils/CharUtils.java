package utils;


public final class CharUtils {

    //LETRAS: A-Za-z
    public static boolean isLetra(Integer caracter){
        return  (caracter > 64 && caracter < 91) || (caracter > 96 && caracter < 123);
    }

    //DIGITOS: 0-9
    public static boolean isDigito(Integer caracter){
        return caracter > 47 && caracter < 58;
    }

    //DELIMITADORES: $ '-/ :-> [ ]
    public static boolean isOperador(Integer caracter){
        return caracter == 36 || (caracter > 42 && caracter < 48) || (caracter > 57 && caracter < 63) || caracter == 91 || caracter == 93;
    }

    //ESPACO
    public static boolean isEspaco(Integer caracter){
        return caracter == 32 || caracter == 9 || caracter == 10;
    }
}
