package arquivo;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class Arquivo {

    public static void gravar(String dir, String texto) throws IOException {
        Path caminho = Paths.get(dir);
        Files.write(caminho, texto.getBytes());
    }

    public static InputStream ler(String dir) throws IOException {
        return new FileInputStream(dir);
    }

    public static String convert(InputStream inputStream) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, Charset.defaultCharset()))) {
            return br.lines().collect(Collectors.joining(System.lineSeparator()));
        }
    }

}
