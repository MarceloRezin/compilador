package enuns;

import utils.CharUtils;

public enum TipoEntrada {

    ESPACO,
    QUALQUER,
    LETRA,
    FIM,
    DIGITO,
    OPERADOR;

    public static TipoEntrada valueOfByCaracter(char caracter){
        if(caracter == -1){
            return FIM;
        }

        if(CharUtils.isEspaco(caracter)){
            return ESPACO;
        }

        if(CharUtils.isLetra(caracter)){
            return LETRA;
        }

        if(CharUtils.isDigito(caracter)){
            return DIGITO;
        }

        if(CharUtils.isOperador(caracter)){
            return OPERADOR;
        }

        return null;
    }
}
