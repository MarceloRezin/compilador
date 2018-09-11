package enuns;

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
            if(codigo.getCodigo() > 51){
                codigo = null;
            }

        }catch (IllegalArgumentException e){}

        if(codigo == null){
            return IDENTIFICADOR;
        }

        return codigo;
    }

    public String getCaracter() {
        return caracter;
    }

    public static Codigo getByOperador(String palavra){
        return delimitadores.get(palavra);
    }
}
