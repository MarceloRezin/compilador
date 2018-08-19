package arquivo;

import java.io.IOException;
import java.io.InputStream;

public class Leitor {

    private InputStream inputStream;
    private Integer caracterLido;
    private Integer proximoCaracter;

    public Leitor(InputStream inputStream) throws IOException {
        this.inputStream = inputStream;
        this.caracterLido = inputStream.read();
        this.proximoCaracter = inputStream.read();
    }

    public void lerProximo() throws IOException{
        caracterLido = proximoCaracter;
        proximoCaracter = inputStream.read();
    }

    public Integer getCaracterLido() {
        return caracterLido;
    }

    public Integer getProximoCaracter() {
        return proximoCaracter;
    }
}
