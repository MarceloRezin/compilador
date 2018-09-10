package token;

import enuns.CodigoTerminal;

public class Token {

    private String palavra;
    private CodigoTerminal codigoTerminal;

    public Token(String palavra) {
        this.codigoTerminal = CodigoTerminal.valueOfByPalavra(palavra);
        this.palavra = palavra;
    }

    public Token(String palavra, CodigoTerminal codigoTerminal) {
        this.palavra = palavra;
        this.codigoTerminal = codigoTerminal;
    }

    //Utilizar apanas para delimitadores
    public Token(CodigoTerminal codigoTerminal) {
        this.codigoTerminal = codigoTerminal;
        String caracter = codigoTerminal.getCaracter();
        if(caracter == null){
            throw new NullPointerException("Não foi definido uma caracter para o código especificado");
        }
        this.palavra = caracter;
    }

    public String getPalavra() {
        return palavra;
    }

    public void setPalavra(String palavra) {
        this.palavra = palavra;
    }

    public CodigoTerminal getCodigoTerminal() {
        return codigoTerminal;
    }

    public void setCodigoTerminal(CodigoTerminal codigoTerminal) {
        this.codigoTerminal = codigoTerminal;
    }

    @Override
    public String toString() {
        return codigoTerminal.toString() + " " + palavra;
    }

    public static Token tokenIgnorado(){
        return new Token(null, null);
    }

    public boolean isIgnorado(){
        return palavra == null && codigoTerminal == null;
    }
}
