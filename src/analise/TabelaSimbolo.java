package analise;

import enuns.Categoria;
import enuns.Codigo;

public class TabelaSimbolo {

    private String nome;
    private Categoria categoria;
    private Codigo tipo;
    private int nivel;

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

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
}
