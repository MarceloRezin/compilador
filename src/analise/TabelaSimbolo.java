package analise;

import enuns.Categoria;
import enuns.Codigo;
import token.Token;

import java.util.List;
import java.util.Objects;

public class TabelaSimbolo {

    private String nome;
    private Categoria categoria;
    private Codigo tipo;

    public TabelaSimbolo(String nome, Categoria categoria, Codigo tipo) {
        this.nome = nome;
        this.categoria = categoria;
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Codigo getTipo() {
        return tipo;
    }

    public void setTipo(Codigo tipo) {
        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == this) return true;
        if (!(obj instanceof TabelaSimbolo)) {
            return false;
        }
        TabelaSimbolo tabelaSimbolo = (TabelaSimbolo) obj;
        return  Objects.equals(nome, tabelaSimbolo.nome);
    }

    //    public static TabelaSimbolo toTabelaSimbolo(Token token, Categoria categoria, Codigo tipo){
//        TabelaSimbolo tabelaSimbolo = new TabelaSimbolo();
//        tabelaSimbolo.setNome(token.getPalavra());
//        tabelaSimbolo.setCategoria(categoria);
//        tabelaSimbolo.setTipo(tipo);
//
//        return tabelaSimbolo;
//    }
}
