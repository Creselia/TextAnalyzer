

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

public class Main {

    public static void main(String[] args) throws IOException, URISyntaxException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        AnalyzeText analyzer = new AnalyzeText();
        analyzer.printTopTenWords(reader);

        CheckBrackets brackets = new CheckBrackets();
        brackets.printBracketResult(reader);

        reader.close();
    }
}
