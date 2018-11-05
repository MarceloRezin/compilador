package analise;

import enuns.Codigo;
import token.Token;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnaliseSemantica {
    private static int nivel = 0;
    private static List<Token> tokensTmp = new ArrayList<>();
    //Nivel , Tabela
    private static Map<Integer, List<TabelaSimbolo>> tabelasSimbolos = new HashMap<>();
    private static Codigo codigoAnterior;

    public static int getNivel() {
        return nivel;
    }

    public static void setNivel(int nivel) {
        AnaliseSemantica.nivel = nivel;
    }

    public static int addNivel() {
        return nivel++;
    }

    public static int subNivel() {
        return nivel--;
    }

    public static void controlarNivel(Codigo codigo) {
        if(codigo == Codigo.BLOCO){
            addNivel();
        }else if(codigo == Codigo.END) {
            subNivel();
        }
    }

    public static void classificarIdentificador(Codigo codigo, Token token) {


        if(codigo == Codigo.IDENTIFICADOR){
            if(codigoAnterior == Codigo.PROGRAM){

            }
            tokensTmp.add(token);
        }
        else if(codigo == Codigo.OP_PONTO_VIRGULA && !tokensTmp.isEmpty()){

        }

        codigoAnterior = codigo;
    }

}
