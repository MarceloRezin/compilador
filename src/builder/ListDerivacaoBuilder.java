package builder;

import enuns.Codigo;

import java.util.*;

public final class ListDerivacaoBuilder {

    private DerivacaoBuilder derivacaoBuilder;
    private Codigo codigo;

    private ListDerivacaoBuilder(){}

    //Contrutor visivel apenas no pacote
    ListDerivacaoBuilder(DerivacaoBuilder derivacaoBuilder, Codigo codigo) {
        this.derivacaoBuilder = derivacaoBuilder;
        this.codigo = codigo;
    }

    public DerivacaoBuilder derivarEm(Codigo... derivacoes){
        derivacaoBuilder.getDerivacoes().put(codigo , Arrays.asList(derivacoes));

        return derivacaoBuilder;
    }

    public DerivacaoBuilder derivarEm(){
        derivacaoBuilder.getDerivacoes().put(codigo , Collections.emptyList());

        return derivacaoBuilder;
    }
}
