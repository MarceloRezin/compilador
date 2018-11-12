package analise;

import enuns.Categoria;
import enuns.Codigo;
import token.Token;

import java.util.*;

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

    public static Map<Integer, List<TabelaSimbolo>> getTabelasSimbolos() {
        return tabelasSimbolos;
    }

    public static int addNivel() {
        return nivel++;
    }

    public static int subNivel() {
        return nivel--;
    }

    public static void controlarNivel(Codigo codigo) {
        if(codigo == Codigo.BEGIN){
            addNivel();
        }else if(codigo == Codigo.END) {
            subNivel();
        }
    }

    public static void insertSimbolo(TabelaSimbolo... tabelaSimbolo){
        List<TabelaSimbolo> simbolos = tabelasSimbolos.get(nivel);

        if (simbolos == null) {
            tabelasSimbolos.put(nivel, new ArrayList<>(Arrays.asList(tabelaSimbolo)));
        }else{
            simbolos.addAll(new ArrayList<>(Arrays.asList(tabelaSimbolo)));
        }
    }

    public static void isIdentificadorExistente(Token token){
        int aux = nivel;

        for(int i=aux; i>-1; i--){
            
        }
    }

    public static void classificarIdentificador(Codigo codigo, Token token) {
        if(codigo == Codigo.IDENTIFICADOR){
            if(codigoAnterior == Codigo.PROGRAM){
                insertSimbolo(new TabelaSimbolo(token.getPalavra(), Categoria.VARIAVEL, Codigo.PROGRAM));
            }else if(codigoAnterior == Codigo.VAR || !tokensTmp.isEmpty()){
                tokensTmp.add(token);
            }else if(codigoAnterior == Codigo.PROCEDURE){
                insertSimbolo(new TabelaSimbolo(token.getPalavra(), Categoria.PROCEDURE, Codigo.PROCEDURE));
                addNivel();
            }else{
                tokensTmp.add(token);
            }
        }else if(codigo == Codigo.OP_PONTO_VIRGULA && !tokensTmp.isEmpty()){

        }else if(codigo == Codigo.OP_TIPAGEM){

        }

        else if(codigo == Codigo.INTEGER){
            TabelaSimbolo[] simbolos = new TabelaSimbolo[tokensTmp.size()];

            for(int i=0; i<tokensTmp.size(); i++){
                simbolos[i] = new TabelaSimbolo(tokensTmp.get(i).getPalavra(), Categoria.VARIAVEL, Codigo.INTEGER);
            }

            insertSimbolo(simbolos);

            tokensTmp.clear();
        }

        codigoAnterior = codigo;
    }

}
