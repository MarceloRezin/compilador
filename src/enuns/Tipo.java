package enuns;

import utils.CharUtils;

public enum Tipo {

    CARACTER,
    DIGITO,
    OPERADOR,
    ESPACO,

    IDENTIFICADOR,
    KEYWORD,

    FIM; //Representa o fim do arquivo

    public static Tipo valueOfBy(Integer caracter){
        if(caracter == -1){
            return FIM;
        }

        if(CharUtils.isCaracter(caracter)){
            return CARACTER;
        }

        if(CharUtils.isDigito(caracter)){
            return DIGITO;
        }

        if(CharUtils.isDelimitador(caracter)){
            return OPERADOR;
        }

        return ESPACO;
    }
}
