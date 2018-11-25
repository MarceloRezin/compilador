package exceptions;

public class AnaliseSemanticaException extends Exception{

    private static final String PREFIXO = "Análise semântica [ERRO]: ";

    public AnaliseSemanticaException(String s) {
        super(s);
    }

    public AnaliseSemanticaException(String s, boolean prefixo) {
        super(prefixo ? PREFIXO + s : s);
    }
}
