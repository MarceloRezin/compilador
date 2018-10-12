package exceptions;

public class AnaliseLexicaException extends Exception{

    public AnaliseLexicaException(String s) {
        super("Análise léxica [ERRO]: " + s);
    }
}
