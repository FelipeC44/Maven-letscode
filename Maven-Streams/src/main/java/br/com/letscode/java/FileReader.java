package br.com.letscode.java;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileReader {

    private final List<Oscar> oscarList;

    public FileReader(String fileName) throws IOException {
        this.oscarList = this.prepararLeituraArquivoCsv(fileName);

    }
        private List<Oscar> prepararLeituraArquivoCsv(String fileName) throws IOException {
            String filepath = getFilepathFromResourceAsStream(fileName);
            try (Stream<String> lines = Files.lines(Path.of(filepath))) {
                return lines.skip(1)
                        .map(Oscar::fromLine)
                        .collect(Collectors.toList());
            }
        }
    private String getFilepathFromResourceAsStream(String fileName) {
        URL url = getClass().getClassLoader().getResource(fileName);
        File file = new File(url.getFile());
        return file.getPath();
    }
        public List<Oscar> getOscarList() {
            return oscarList;
        }
}
