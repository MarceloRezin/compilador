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

        if(posicaoLeitura < arquivo.length){
            proximoCaracter = arquivo[posicaoLeitura];
        }else{ //Fim do arquivo
            proximoCaracter = (char)-1;
        }
    }

    public char getCaracterLido() {
        return caracterLido;
    }

    public char getProximoCaracter() {
        return proximoCaracter;
    }

    public boolean hasNext(){
        return posicaoLeitura < getLenght();
    }

    private void proximaLeitura(){
        if(hasNext()){
            posicaoLeitura ++;
        }
    }

    public int getLenght(){
        return arquivo.length + 1;
    }
}
