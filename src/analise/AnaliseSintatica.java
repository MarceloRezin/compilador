package analise;

import enuns.Codigo;
import token.Token;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;

public class AnaliseSintatica {

    public static Stack<Token> getPilhaParsingInicial() {
        Stack<Token> parsing = new Stack<>();
        parsing.add(new Token(Codigo.PROGRAMA));
        return parsing;
    }

    public static void analisar(Stack<Token> derivacoes, Stack<Token> parsing) throws Exception {
        Codigo x = parsing.peek().getCodigo();
        Codigo a = derivacoes.peek().getCodigo();

        if(x.isTerminal()){
            if(x.equals(a)){
                derivacoes.pop();
                parsing.pop();

                return;
            }
        }else{
            Map<Codigo, List<Codigo>> y = Codigo.tabelaParsing.get(x);
            if(y != null){
                List<Codigo> yk = y.get(a);
                if(yk != null){
                    parsing.pop();
                    parsing.addAll(yk.stream().map( c -> new Token(c)).collect(Collectors.toList()));
                    return;
                }
            }
        }

        throw new Exception("Erro");
    }
}
