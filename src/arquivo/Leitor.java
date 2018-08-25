package arquivo;

public class Leitor {

    private char[] arquivo;
    private char caracterLido;
    private char proximoCaracter;
    private int posicaoLeitura = 0;

    public Leitor(char[] arquivo){
        this.arquivo = arquivo;
        this.caracterLido = arquivo[posicaoLeitura];
        proximaLeitura();
        this.proximoCaracter = arquivo[posicaoLeitura];
    }

    public void lerProximo(){
        proximaLeitura();
        caracterLido = proximoCaracter;
        proximoCaracter = arquivo[posicaoLeitura];
    }

    public char getCaracterLido() {
        return caracterLido;
    }

    public char getProximoCaracter() {
        return proximoCaracter;
    }

    public boolean hasNext(){
        return posicaoLeitura < arquivo.length -1;
    }

    private void proximaLeitura(){
        if(hasNext()){
            posicaoLeitura ++;
        }
    }
}
