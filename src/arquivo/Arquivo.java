package arquivo;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Arquivo {

    public static void gravar(String dir, String texto) throws IOException {
        Path caminho = Paths.get(dir);
        Files.write(caminho, texto.getBytes());
    }

    public static InputStream ler(String dir) throws IOException {
        return new FileInputStream(dir);
    }
}
