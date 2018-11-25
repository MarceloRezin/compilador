package exceptions;

public class AnaliseSemanticaException extends Exception{

    public AnaliseSemanticaException(String s) {
        super("Análise semântica [ERRO]: " + s);
    }
}
