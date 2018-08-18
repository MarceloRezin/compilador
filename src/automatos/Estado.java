package automatos;

import enuns.Tipo;

import java.util.HashMap;
import java.util.Map;

public class Estado {
    private Map<Object, Estado> transicoes = new HashMap<>();
    private Tipo retornoEspecifico;

    public Estado() {}

    //Utilizado em estados finais com retorno predefinido
    public Estado(Tipo retornoEspecifico) {
        this.retornoEspecifico = retornoEspecifico;
    }

    public void addTransicao(Object entrada, Estado estado){
        transicoes.put(entrada, estado);
    }

    public Estado getEstado(Object entrada){
        return transicoes.get(entrada);
    }

    public Tipo getRetornoEspecifico() {
        return retornoEspecifico;
    }
}
