package enuns;

import utils.CharUtils;

public enum Tipo {

    CARACTER,
    DIGITO,
    OPERADOR,
    ESPACO,

    COMENTARIO,
    LITERAL,

    IDENTIFICADOR,
    KEYWORD,

    FIM, //Representa o fim do arquivo
    ERRO;//Algum erro pego pela analise lexica

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

        if(CharUtils.isAspaSimples(caracter)){
            return LITERAL;
        }

        if(CharUtils.isEspaco(caracter)){
            return ESPACO;
        }

        return null;
    }
}
