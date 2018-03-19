import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    private static List<String> lines;

    public static void main(String[] args) throws IOException {
        getFile();
        for (String s:lines) {
            System.out.println(s);
            System.out.println();
        }
    }

    private static void getFile() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            Path path = Paths.get("C:\\Users\\u1\\Desktop\\abcd.txt");
            lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("File not found or Can't read file");
        }

        reader.close();
    }
}
