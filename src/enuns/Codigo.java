package enuns;

import builder.DerivacaoBuilder;

import java.util.*;

public enum Codigo {

    /*
    TERMINAIS
     */
    PROGRAM(1),
    LABEL(2),
    CONST(3),
    VAR(4),
    PROCEDURE(5),
    BEGIN(6),
    END(7),
    INTEGER(8),
    ARRAY(9),
    OF(10),
    CALL(11),
    GOTO(12),
    IF(13),
    THEN(14),
    ELSE(15),
    WHILE(16),
    DO(17),
    REPEAT(18),
    UNTIL(19),
    READLN(20),
    WRITELN(21),
    OR(22),
    AND(23),
    NOT(24),
    IDENTIFICADOR(25),
    INTEIRO(26),
    FOR(27),
    TO(28),
    CASE(29),
    LITERAL(48),

    //Simbolos especiais
    OP_SOMA(30, "+"),
    OP_SUB(31, "-"),
    OP_MULT(32, "*"),
    OP_DIV(33, "/"),
    OP_COLCHETE_ABRE(34, "["),
    OP_COLCHETE_FECHA(35, "]"),
    OP_PARENTESE_ABRE(36, "("),
    OP_PARENTESE_FECHA(37, ")"),
    OP_RECEBE(38, ":="),
    OP_TIPAGEM(39, ":"),
    OP_IGUAL(40, "="),
    OP_MAIOR(41, ">"),
    OP_MAIOR_OU_IGUAL(42, ">="),
    OP_MENOR(43, "<"),
    OP_MENOR_OU_IGUAL(44, "<="),
    OP_DIFERENTE(45, "<>"),
    OP_VIRGULA(46, ","),
    OP_PONTO_VIRGULA(47, ";"),
    OP_PONTO(49, "."),
    OP_PONTO_PONTO(50, ".."),
    OP_CIFRAO(51, "$"),


    /*
    NAO TERMINAIS
     */
    PROGRAMA(52),
    BLOCO(53),
    DCLROT(54),
    LID(55),
    REPIDENT(56),
    DCLCONST(57),
    LDCONST(58),
    DCLVAR(59),
    LDVAR(60),
    TIPO(61),
    DCLPROC(62),
    DEFPAR(63),
    CORPO(64),
    REPCOMANDO(65),
    COMANDO(66),
    RCOMID(67),
    RVAR(68),
    PARAMETROS(69),
    REPPAR(70),
    ELSEPARTE(71),
    VARIAVEL(72),
    VARIAVEL1(73),
    REPVARIAVEL(74),
    ITEMSAIDA(75),
    REPITEM(76),
    EXPRESSAO(77),
    REPEXPSIMP(78),
    EXPSIMP(79),
    REPEXP(80),
    TERMO(81),
    REPTERMO(82),
    FATOR(83),
    CONDCASE(84),
    CONTCASE(85),
    RPINTEIRO(86),
    SEMEFEITO(87);


    private int codigo;
    String caracter;

    //Otimização para encontrar operadores especiais
    private static final Map<String, Codigo> delimitadores;
    static {
        HashMap<String, Codigo> tmp = new HashMap<>();
        tmp.put(OP_SOMA.caracter, OP_SOMA);
        tmp.put(OP_SUB.caracter, OP_SUB);
        tmp.put(OP_MULT.caracter, OP_MULT);
        tmp.put(OP_DIV.caracter, OP_DIV);
        tmp.put(OP_COLCHETE_ABRE.caracter, OP_COLCHETE_ABRE);
        tmp.put(OP_COLCHETE_FECHA.caracter, OP_COLCHETE_FECHA);
        tmp.put(OP_PARENTESE_ABRE.caracter, OP_PARENTESE_ABRE);
        tmp.put(OP_PARENTESE_FECHA.caracter, OP_PARENTESE_FECHA);
        tmp.put(OP_RECEBE.caracter, OP_RECEBE);
        tmp.put(OP_TIPAGEM.caracter, OP_TIPAGEM);
        tmp.put(OP_IGUAL.caracter, OP_IGUAL);
        tmp.put(OP_MAIOR.caracter, OP_MAIOR);
        tmp.put(OP_MAIOR_OU_IGUAL.caracter, OP_MAIOR_OU_IGUAL);
        tmp.put(OP_MENOR.caracter, OP_MENOR);
        tmp.put(OP_MENOR_OU_IGUAL.caracter, OP_MENOR_OU_IGUAL);
        tmp.put(OP_DIFERENTE.caracter, OP_DIFERENTE);
        tmp.put(OP_VIRGULA.caracter, OP_VIRGULA);
        tmp.put(OP_PONTO_VIRGULA.caracter, OP_PONTO_VIRGULA);
        tmp.put(OP_PONTO.caracter, OP_PONTO);
        tmp.put(OP_PONTO_PONTO.caracter, OP_PONTO_PONTO);
        tmp.put(OP_CIFRAO.caracter, OP_CIFRAO);

        delimitadores = Collections.unmodifiableMap(tmp);
    }

//    private static final Set<Codigo> codigosTerminais;
//    static {
//        Set<Codigo> tmp = new HashSet<>();
//        tmp.add(PROGRAM);
//        tmp.add(LABEL);
//        tmp.add(CONST);
//        tmp.add(VAR);
//        tmp.add(PROCEDURE);
//        tmp.add(BEGIN);
//        tmp.add(END);
//        tmp.add(INTEGER);
//        tmp.add(ARRAY);
//        tmp.add(OF);
//        tmp.add(CALL);
//        tmp.add(GOTO);
//        tmp.add(IF);
//        tmp.add(THEN);
//        tmp.add(ELSE);
//        tmp.add(WHILE);
//        tmp.add(DO);
//        tmp.add(REPEAT);
//        tmp.add(UNTIL);
//        tmp.add(READLN);
//        tmp.add(WRITELN);
//        tmp.add(OR);
//        tmp.add(AND);
//        tmp.add(NOT);
//        tmp.add(IDENTIFICADOR);
//        tmp.add(INTEIRO);
//        tmp.add(FOR);
//        tmp.add(TO);
//        tmp.add(CASE);
//
//        codigosTerminais = Collections.unmodifiableSet(tmp);
//    }

    public static final Map<Codigo, Map<Codigo, List<Codigo>>> tabelaParsing;
    static {
        HashMap<Codigo, Map<Codigo, List<Codigo>>> tmp = new HashMap<>();

        tmp.put(PROGRAMA, new DerivacaoBuilder()
                .quando(PROGRAM).derivarEm(PROGRAM, IDENTIFICADOR, OP_PONTO_VIRGULA, BLOCO, OP_PONTO)
                .toMap());

        tmp.put(BLOCO, new DerivacaoBuilder()
                .quando(LABEL).derivarEm(DCLROT, DCLCONST, DCLVAR, DCLPROC, CORPO)
                .quando(CONST).derivarEm(DCLROT, DCLCONST, DCLVAR, DCLPROC, CORPO)
                .quando(VAR).derivarEm(DCLROT, DCLCONST, DCLVAR, DCLPROC, CORPO)
                .quando(PROCEDURE).derivarEm(DCLROT, DCLCONST, DCLVAR, DCLPROC, CORPO)
                .quando(BEGIN).derivarEm(DCLROT,DCLCONST,DCLVAR,DCLPROC,CORPO)
                .toMap());

        tmp.put(DCLROT, new DerivacaoBuilder()
                .quando(LABEL).derivarEm(LABEL, LID, OP_PONTO_VIRGULA)
                .quando(CONST).derivarEm()
                .quando(VAR).derivarEm()
                .quando(PROCEDURE).derivarEm()
                .quando(BEGIN).derivarEm().toMap());

        tmp.put(LID, new DerivacaoBuilder()
                .quando(IDENTIFICADOR).derivarEm(IDENTIFICADOR, REPIDENT)
                .toMap());

        tmp.put(REPIDENT, new DerivacaoBuilder()
                .quando(OP_TIPAGEM).derivarEm()
                .quando(OP_VIRGULA).derivarEm(OP_VIRGULA, IDENTIFICADOR, REPIDENT)
                .quando(OP_PONTO_VIRGULA).derivarEm().toMap());

        tmp.put(DCLCONST, new DerivacaoBuilder()
                .quando(CONST).derivarEm(CONST, IDENTIFICADOR, OP_IGUAL, INTEIRO, OP_PONTO_VIRGULA, LDCONST)
                .quando(VAR).derivarEm()
                .quando(PROCEDURE).derivarEm()
                .quando(BEGIN).derivarEm().toMap());

        tmp.put(LDCONST, new DerivacaoBuilder()
                .quando(VAR).derivarEm()
                .quando(PROCEDURE).derivarEm()
                .quando(BEGIN).derivarEm()
                .quando(IDENTIFICADOR).derivarEm(IDENTIFICADOR, OP_IGUAL, INTEIRO, OP_PONTO_VIRGULA, LDCONST).toMap());

        tmp.put(DCLVAR, new DerivacaoBuilder()
                .quando(VAR).derivarEm(VAR, LID, OP_TIPAGEM, TIPO, OP_PONTO_VIRGULA, LDVAR)
                .quando(PROCEDURE).derivarEm()
                .quando(BEGIN).derivarEm().toMap());

        tmp.put(LDVAR, new DerivacaoBuilder()
                .quando(PROCEDURE).derivarEm()
                .quando(BEGIN).derivarEm()
                .quando(IDENTIFICADOR).derivarEm(LID, OP_TIPAGEM, TIPO, OP_PONTO_VIRGULA, LDVAR).toMap());

        tmp.put(TIPO, new DerivacaoBuilder()
                .quando(INTEGER).derivarEm(INTEGER)
                .quando(ARRAY).derivarEm(ARRAY, OP_COLCHETE_ABRE, INTEIRO, OP_PONTO_PONTO, INTEIRO, OP_COLCHETE_FECHA, OF,INTEGER).toMap());

        tmp.put(DCLPROC, new DerivacaoBuilder()
                .quando(PROCEDURE).derivarEm(PROCEDURE, IDENTIFICADOR, DEFPAR, OP_PONTO_VIRGULA,BLOCO, OP_PONTO_VIRGULA, DCLPROC)
                .quando(BEGIN).derivarEm().toMap());

        tmp.put(DEFPAR, new DerivacaoBuilder()
                .quando(OP_PARENTESE_ABRE).derivarEm(OP_PARENTESE_ABRE, LID, OP_TIPAGEM, INTEGER, OP_PARENTESE_FECHA)
                .quando(OP_TIPAGEM).derivarEm().toMap());

        tmp.put(CORPO, new DerivacaoBuilder()
                .quando(BEGIN).derivarEm(BEGIN, COMANDO, REPCOMANDO, END).toMap());

        tmp.put(REPCOMANDO, new DerivacaoBuilder()
                .quando(END).derivarEm()
                .quando(OP_PONTO_VIRGULA).derivarEm(OP_PONTO_VIRGULA, COMANDO, REPCOMANDO).toMap());

        tmp.put(COMANDO, new DerivacaoBuilder()
                .quando(BEGIN).derivarEm(CORPO)
                .quando(END).derivarEm()
                .quando(CALL).derivarEm(CALL, IDENTIFICADOR, PARAMETROS)
                .quando(GOTO).derivarEm(GOTO, IDENTIFICADOR)
                .quando(IF).derivarEm(IF, EXPRESSAO, THEN, COMANDO, ELSEPARTE)
                .quando(ELSE).derivarEm()
                .quando(WHILE).derivarEm(WHILE, EXPRESSAO, DO, COMANDO)
                .quando(REPEAT).derivarEm(REPEAT, COMANDO, UNTIL, EXPRESSAO)
                .quando(UNTIL).derivarEm()
                .quando(READLN).derivarEm(READLN, OP_PARENTESE_ABRE, VARIAVEL, REPVARIAVEL, OP_PARENTESE_FECHA)
                .quando(WRITELN).derivarEm(WRITELN, OP_PARENTESE_ABRE, ITEMSAIDA, REPITEM, OP_PARENTESE_FECHA)
                .quando(IDENTIFICADOR).derivarEm(IDENTIFICADOR, RCOMID)
                .quando(FOR).derivarEm(FOR, IDENTIFICADOR, OP_RECEBE, EXPRESSAO, TO, EXPRESSAO, DO, COMANDO)
                .quando(CASE).derivarEm(CASE, EXPRESSAO, OF, CONDCASE, END)
                .quando(OP_PONTO_VIRGULA).derivarEm().toMap());

        tmp.put(RCOMID, new DerivacaoBuilder()
                .quando(OP_COLCHETE_ABRE).derivarEm(RVAR, OP_RECEBE, EXPRESSAO)
                .quando(OP_RECEBE).derivarEm(RVAR, OP_RECEBE, EXPRESSAO)
                .quando(OP_TIPAGEM).derivarEm(OP_TIPAGEM, COMANDO).toMap());

        tmp.put(RVAR, new DerivacaoBuilder()
                .quando(OP_COLCHETE_ABRE).derivarEm(OP_COLCHETE_ABRE, EXPRESSAO, OP_COLCHETE_FECHA)
                .quando(OP_RECEBE).derivarEm().toMap());

        tmp.put(PARAMETROS, new DerivacaoBuilder()
                .quando(END).derivarEm()
                .quando(ELSE).derivarEm()
                .quando(UNTIL).derivarEm()
                .quando(OP_PARENTESE_ABRE).derivarEm(OP_PARENTESE_ABRE, EXPRESSAO, REPPAR, OP_PARENTESE_FECHA)
                .quando(OP_PONTO_VIRGULA).derivarEm().toMap());

        tmp.put(REPPAR, new DerivacaoBuilder()
                .quando(OP_PARENTESE_FECHA).derivarEm()
                .quando(OP_VIRGULA).derivarEm(OP_VIRGULA, EXPRESSAO, REPPAR).toMap());

        tmp.put(ELSEPARTE, new DerivacaoBuilder()
                .quando(END).derivarEm()
                .quando(ELSE).derivarEm(ELSE, COMANDO)
                .quando(UNTIL).derivarEm()
                .quando(OP_PONTO_VIRGULA).derivarEm().toMap());

        tmp.put(VARIAVEL, new DerivacaoBuilder()
                .quando(IDENTIFICADOR).derivarEm(IDENTIFICADOR, VARIAVEL1).toMap());

        tmp.put(VARIAVEL1, new DerivacaoBuilder()
                .quando(END).derivarEm()
                .quando(OF).derivarEm()
                .quando(THEN).derivarEm()
                .quando(ELSE).derivarEm()
                .quando(DO).derivarEm()
                .quando(UNTIL).derivarEm()
                .quando(OR).derivarEm()
                .quando(AND).derivarEm()
                .quando(TO).derivarEm()
                .quando(OP_SOMA).derivarEm()
                .quando(OP_SUB).derivarEm()
                .quando(OP_MULT).derivarEm()
                .quando(OP_DIV).derivarEm()
                .quando(OP_COLCHETE_ABRE).derivarEm(OP_COLCHETE_ABRE, EXPRESSAO, OP_COLCHETE_FECHA)
                .quando(OP_COLCHETE_FECHA).derivarEm()
                .quando(OP_PARENTESE_FECHA).derivarEm()
                .quando(OP_IGUAL).derivarEm()
                .quando(OP_MAIOR).derivarEm()
                .quando(OP_MAIOR_OU_IGUAL).derivarEm()
                .quando(OP_MENOR).derivarEm()
                .quando(OP_MENOR_OU_IGUAL).derivarEm()
                .quando(OP_DIFERENTE).derivarEm()
                .quando(OP_VIRGULA).derivarEm()
                .quando(OP_PONTO_VIRGULA).derivarEm().toMap());

        tmp.put(REPVARIAVEL, new DerivacaoBuilder()
                .quando(OP_PARENTESE_FECHA).derivarEm()
                .quando(OP_VIRGULA).derivarEm(OP_VIRGULA, VARIAVEL, REPVARIAVEL).toMap());

        tmp.put(ITEMSAIDA, new DerivacaoBuilder()
                .quando(NOT).derivarEm(EXPRESSAO)
                .quando(IDENTIFICADOR).derivarEm(EXPRESSAO)
                .quando(INTEIRO).derivarEm(EXPRESSAO)
                .quando(OP_SOMA).derivarEm(EXPRESSAO)
                .quando(OP_SUB).derivarEm(EXPRESSAO)
                .quando(OP_PARENTESE_ABRE).derivarEm(EXPRESSAO)
                .quando(LITERAL).derivarEm(LITERAL).toMap());

        tmp.put(REPITEM, new DerivacaoBuilder()
                .quando(OP_PARENTESE_FECHA).derivarEm()
                .quando(OP_VIRGULA).derivarEm(OP_VIRGULA, ITEMSAIDA, REPITEM).toMap());

        tmp.put(EXPRESSAO, new DerivacaoBuilder()
                .quando(NOT).derivarEm(EXPSIMP, REPEXPSIMP)
                .quando(IDENTIFICADOR).derivarEm(EXPSIMP, REPEXPSIMP)
                .quando(INTEIRO).derivarEm(EXPSIMP, REPEXPSIMP)
                .quando(OP_SOMA).derivarEm(EXPSIMP, REPEXPSIMP)
                .quando(OP_SUB).derivarEm(EXPSIMP, REPEXPSIMP)
                .quando(OP_PARENTESE_ABRE).derivarEm(EXPSIMP, REPEXPSIMP)
                .toMap());

        tmp.put(REPEXPSIMP, new DerivacaoBuilder()
                .quando(END).derivarEm()
                .quando(OF).derivarEm()
                .quando(THEN).derivarEm()
                .quando(ELSE).derivarEm()
                .quando(DO).derivarEm()
                .quando(UNTIL).derivarEm()
                .quando(TO).derivarEm()
                .quando(OP_COLCHETE_FECHA).derivarEm()
                .quando(OP_PARENTESE_FECHA).derivarEm()
                .quando(OP_IGUAL).derivarEm(OP_IGUAL, EXPSIMP)
                .quando(OP_MAIOR).derivarEm(OP_MAIOR, EXPSIMP)
                .quando(OP_MAIOR_OU_IGUAL).derivarEm(OP_MAIOR_OU_IGUAL, EXPSIMP)
                .quando(OP_MENOR).derivarEm(OP_MENOR, EXPSIMP)
                .quando(OP_MENOR_OU_IGUAL).derivarEm(OP_MAIOR_OU_IGUAL, EXPSIMP)
                .quando(OP_DIFERENTE).derivarEm(OP_DIFERENTE, EXPSIMP)
                .quando(OP_VIRGULA).derivarEm()
                .quando(OP_PONTO_VIRGULA).derivarEm().toMap());

        tmp.put(EXPSIMP, new DerivacaoBuilder()
                .quando(NOT).derivarEm(TERMO, REPEXP)
                .quando(IDENTIFICADOR).derivarEm(TERMO, REPEXP)
                .quando(INTEIRO).derivarEm(TERMO, REPEXP)
                .quando(OP_SOMA).derivarEm(OP_SOMA, TERMO, REPEXP)
                .quando(OP_SUB).derivarEm(OP_SUB, TERMO, REPEXP)
                .quando(OP_PARENTESE_ABRE).derivarEm(TERMO, REPEXP).toMap());

        tmp.put(REPEXP, new DerivacaoBuilder()
                .quando(END).derivarEm()
                .quando(OF).derivarEm()
                .quando(THEN).derivarEm()
                .quando(ELSE).derivarEm()
                .quando(DO).derivarEm()
                .quando(UNTIL).derivarEm()
                .quando(OR).derivarEm(OR, TERMO, REPEXP)
                .quando(TO).derivarEm()
                .quando(OP_SOMA).derivarEm(OP_SOMA, TERMO, REPEXP)
                .quando(OP_SUB).derivarEm(OP_SUB, TERMO, REPEXP)
                .quando(OP_COLCHETE_FECHA).derivarEm()
                .quando(OP_PARENTESE_FECHA).derivarEm()
                .quando(OP_IGUAL).derivarEm()
                .quando(OP_MAIOR).derivarEm()
                .quando(OP_MAIOR_OU_IGUAL).derivarEm()
                .quando(OP_MENOR).derivarEm()
                .quando(OP_MENOR_OU_IGUAL).derivarEm()
                .quando(OP_DIFERENTE).derivarEm()
                .quando(OP_VIRGULA).derivarEm()
                .quando(OP_PONTO_VIRGULA).derivarEm().toMap());

        tmp.put(TERMO, new DerivacaoBuilder()
                .quando(NOT).derivarEm(FATOR, REPTERMO)
                .quando(IDENTIFICADOR).derivarEm(FATOR, REPTERMO)
                .quando(INTEIRO).derivarEm(FATOR, REPTERMO)
                .quando(OP_PARENTESE_ABRE).derivarEm(FATOR, REPTERMO).toMap());

        tmp.put(REPTERMO, new DerivacaoBuilder()
                .quando(END).derivarEm()
                .quando(OF).derivarEm()
                .quando(THEN).derivarEm()
                .quando(ELSE).derivarEm()
                .quando(DO).derivarEm()
                .quando(UNTIL).derivarEm()
                .quando(OR).derivarEm()
                .quando(AND).derivarEm(AND, FATOR, REPTERMO)
                .quando(TO).derivarEm()
                .quando(OP_SOMA).derivarEm()
                .quando(OP_SUB).derivarEm()
                .quando(OP_MULT).derivarEm(OP_MULT, FATOR, REPTERMO)
                .quando(OP_DIV).derivarEm(OP_DIV, FATOR, REPTERMO)
                .quando(OP_COLCHETE_FECHA).derivarEm()
                .quando(OP_PARENTESE_FECHA).derivarEm()
                .quando(OP_IGUAL).derivarEm()
                .quando(OP_MAIOR).derivarEm()
                .quando(OP_MAIOR_OU_IGUAL).derivarEm()
                .quando(OP_MENOR).derivarEm()
                .quando(OP_MENOR_OU_IGUAL).derivarEm()
                .quando(OP_DIFERENTE).derivarEm()
                .quando(OP_VIRGULA).derivarEm()
                .quando(OP_PONTO_VIRGULA).derivarEm().toMap());

        tmp.put(FATOR, new DerivacaoBuilder()
                .quando(NOT).derivarEm(NOT, FATOR)
                .quando(IDENTIFICADOR).derivarEm(VARIAVEL)
                .quando(INTEIRO).derivarEm(INTEIRO)
                .quando(OP_PARENTESE_ABRE).derivarEm(OP_PARENTESE_ABRE, EXPRESSAO, OP_PARENTESE_FECHA).toMap());

        tmp.put(CONDCASE, new DerivacaoBuilder()
                .quando(INTEIRO).derivarEm(INTEIRO, RPINTEIRO, OP_TIPAGEM, COMANDO, CONDCASE).toMap());

        tmp.put(CONTCASE, new DerivacaoBuilder()
                .quando(END).derivarEm()
                .quando(OP_PONTO_VIRGULA).derivarEm(OP_PONTO_VIRGULA, CONDCASE).toMap());

        tmp.put(RPINTEIRO, new DerivacaoBuilder()
                .quando(OP_TIPAGEM).derivarEm()
                .quando(OP_VIRGULA).derivarEm(OP_VIRGULA, INTEIRO, RPINTEIRO).toMap());

        tabelaParsing = Collections.unmodifiableMap(tmp);
    }

    Codigo(int codigo, String caracter){
        this.codigo = codigo;
        this.caracter = caracter;
    }

    Codigo(int codigo){
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public static Codigo valueOfByPalavra(String token) {
        String tokenUpper = token.toUpperCase();

        //Procura por delimitadores
        Codigo codigo = getByOperador(tokenUpper);

        if(codigo != null){
            return codigo;
        }

        //Procura por palavras reservadas
        try {
            codigo = valueOf(tokenUpper);
            if(!codigo.isTerminal()){
                codigo = null;
            }

        }catch (IllegalArgumentException e){}

        if(codigo == null){
            return IDENTIFICADOR;
        }

        return codigo;
    }

    public String getCaracter() {
        if(caracter == null){
            return this.toString();
        }
        return caracter;
    }

    public static Codigo getByOperador(String palavra){
        return delimitadores.get(palavra);
    }

    public boolean isTerminal  (){
        return getCodigo() < 52;
    }
}
