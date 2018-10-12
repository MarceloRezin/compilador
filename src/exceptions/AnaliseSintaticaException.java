package exceptions;

public class AnaliseSintaticaException extends Exception{

    public AnaliseSintaticaException(String s) {
        super("Análise sintática [ERRO]: " + s);
    }
}
