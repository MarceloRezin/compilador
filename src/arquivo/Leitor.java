package arquivo;

public class Leitor {

    private char[] arquivo;
    private char caracterLido;
    private char proximoCaracter;
    private int posicaoLeitura = 0;
    private int linha = 1;
    private int coluna = 1;
    private int colunaReal = 0;

    public Leitor(char[] arquivo){
        this.arquivo = arquivo;
        this.caracterLido = arquivo[posicaoLeitura];
        countCaracter();
        proximaLeitura();
        this.proximoCaracter = arquivo[posicaoLeitura];
    }

    public void lerProximo(){
        proximaLeitura();
        caracterLido = proximoCaracter;

        countCaracter();

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

    public void countCaracter(){
        if(caracterLido == 10){ //A cada quebra de linha conta
            linha ++;
            colunaReal = 0;
        }else{
            colunaReal ++;
            if(caracterLido == 32 || caracterLido == 9){
                coluna = colunaReal+1;
            }
        }
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }
}
