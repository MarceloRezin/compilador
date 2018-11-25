package analise;

import enuns.Categoria;
import enuns.Codigo;
import exceptions.AnaliseSintaticaException;
import token.Token;

import java.util.*;

public class AnaliseSemantica {
    private static int nivel = 0;
    private static List<Token> tokensTmp = new ArrayList<>();
    //Nivel , Tabela
    private static Map<Integer, List<TabelaSimbolo>> tabelasSimbolos = new HashMap<>();
    private static Codigo codigoAnterior;

    //Flags
    private static boolean program = false;

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

    public static void insertSimbolo(TabelaSimbolo... tabelaSimbolo) throws AnaliseSintaticaException{
        List<TabelaSimbolo> tabelasAInserir = new ArrayList<>(Arrays.asList(tabelaSimbolo));

        for(TabelaSimbolo ts: tabelasAInserir){
            if(isIdentificadorExistente(ts)){
                throw new AnaliseSintaticaException("Identificador já existente");
            }
        }

        List<TabelaSimbolo> simbolos = tabelasSimbolos.get(nivel);
        if (simbolos == null) {
            tabelasSimbolos.put(nivel, tabelasAInserir);
        }else{
            simbolos.addAll(tabelasAInserir);
        }
    }

    public static boolean isIdentificadorExistente(TabelaSimbolo tabelaSimbolo){
        int aux = nivel;

        for(int i=aux; i>-1; i--){
            List<TabelaSimbolo> simboloList = tabelasSimbolos.get(i);

            for(TabelaSimbolo ts: simboloList){
                if(ts.equals(tabelaSimbolo)){
                    return true;
                }
            }
        }

        return false;
    }

    public static void classificarIdentificador(Codigo codigo, Token token) throws AnaliseSintaticaException{

        if(codigo == Codigo.PROGRAM){
            program = true;
        }

        controlarNivel(codigo);

        if(codigo == Codigo.IDENTIFICADOR){
           if(program){
              insertSimbolo(new TabelaSimbolo(token.getPalavra(), Categoria.VARIAVEL, Codigo.PROGRAM));
              program = false;
           }
        }

        codigoAnterior = codigo;
    }

}
