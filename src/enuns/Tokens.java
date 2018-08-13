package enuns;

public enum Tokens {

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
    OP_SOMA(30),
    OP_SUB(31),
    OP_MULT(32),
    OP_DIV(33),
    OP_COLCHETE_ABRE(34),
    OP_COLCHETE_FECHA(35),
    OP_PARENTESE_ABRE(36),
    OP_PARENTESE_FECHA(37),
    OP_RECEBE(38),
    OP_TIPAGEM(39),
    OP_IGUAL(40),
    OP_MAIOR(41),
    OP_MAIOR_OU_IGUAL(42),
    OP_MENOR(43),
    OP_MENOR_OU_IGUA(44),
    OP_DIFERENTE(45),
    OP_VIRGULA(46),
    OP_PORNTO_VIRGULA(47),
    LITERAL(48),
    OP_PONTO(49),
    OP_PONTO_PONTO(50),
    OP_CIFRAO(51);

    private int codigo;

    Tokens(int codigo){
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }
}
