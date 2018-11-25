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

    //Flags
    private static boolean program = false;
    private static boolean label = false;
    private static boolean constant = false;
    private static boolean var = false;
    private static boolean procedure = false;
    private static boolean parametro = false;

    private static TabelaSimbolo varUtilizada;

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
        if(codigo == Codigo.BEGIN && nivel != 1){
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

    private static boolean isIdentificadorExistente(TabelaSimbolo tabelaSimbolo){
        int aux = nivel;

        for(int i=aux; i>-1; i--){
            List<TabelaSimbolo> simboloList = tabelasSimbolos.get(i);

            if(simboloList == null){
                continue;
            }

            for(TabelaSimbolo ts: simboloList){
                if(ts.equals(tabelaSimbolo)){
                    return true;
                }
            }
        }

        return false;
    }

    private static TabelaSimbolo getByPalavra(String palavra){
        int aux = nivel;

        for(int i=aux; i>-1; i--){
            List<TabelaSimbolo> simboloList = tabelasSimbolos.get(i);

            if(simboloList == null){
                continue;
            }

            for(TabelaSimbolo ts: simboloList){
                if(ts.getNome().toUpperCase().equals(palavra.toUpperCase())){
                    return ts;
                }
            }
        }

        return null;
    }

    public static void classificarIdentificador(Codigo codigo, Token token) throws AnaliseSintaticaException{

        switch (codigo){
            case PROGRAM:
                program = true;
                break;

            case LABEL:
                label = true;
                constant = false;
                var = false;
                break;

            case CONST:
                constant = true;
                var = false;
                break;

            case VAR:
                var = true;
                constant = false;
                break;

            case PROCEDURE:
                procedure = true;
                constant = false;
                var = false;
                break;

        }

        controlarNivel(codigo);

        if(codigo == Codigo.IDENTIFICADOR){
           if(program){
              insertSimbolo(new TabelaSimbolo(token.getPalavra(), Categoria.VARIAVEL, Codigo.PROGRAM));
              program = false;
           }else if(label){
               insertSimbolo(new TabelaSimbolo(token.getPalavra(), Categoria.ROTULO, Codigo.LABEL));
           }else if(constant){
               insertSimbolo(new TabelaSimbolo(token.getPalavra(), Categoria.CONSTANTE, Codigo.CONST));
           }else if(var){
               tokensTmp.add(token);
           }else if(procedure){
               insertSimbolo(new TabelaSimbolo(token.getPalavra(), Categoria.PROCEDURE, Codigo.PROCEDURE));
               procedure = false;
               parametro = true;
               addNivel();
           }else if(parametro){
               insertSimbolo(new TabelaSimbolo(token.getPalavra(), Categoria.PARAMETRO, Codigo.INTEGER));
           }else{
               TabelaSimbolo ts = getByPalavra(token.getPalavra());

               if(ts == null){
                   throw new AnaliseSintaticaException("Identificador " + token.getPalavra() + " não declarado");
               }

               if(varUtilizada == null){
                   varUtilizada = ts;
               }else{
                   if(ts.getCategoria() != varUtilizada.getCategoria()){
                       throw new AnaliseSintaticaException("Atribuição inválida -> Esperado: " + token.getCodigo() + " Encontrado: " + varUtilizada.getTipo());
                   }
               }
           }
        }else if(codigo == Codigo.OP_PONTO_VIRGULA){
            if(label){
                label = false;
            }else if(parametro){
                parametro = false;
            }else if(varUtilizada != null){
                varUtilizada = null;
            }
        }else if(codigo == Codigo.INTEGER){
            if(var){
                for(Token t: tokensTmp){
                    insertSimbolo(new TabelaSimbolo(t.getPalavra(), Categoria.VARIAVEL, Codigo.INTEGER));
                }

                tokensTmp.clear();
            }
        }else if(codigo == Codigo.ARRAY){
            if(var){
                for(Token t: tokensTmp){
                    insertSimbolo(new TabelaSimbolo(t.getPalavra(), Categoria.VARIAVEL, Codigo.ARRAY));
                }

                tokensTmp.clear();
            }
        }
    }

    public static void resetAll(){
        nivel = 0;
        tokensTmp.clear();
        tabelasSimbolos.clear();
        program = false;
        label = false;
        constant = false;
        var = false;
        procedure = false;
        parametro = false;
        varUtilizada = null;
    }
}
