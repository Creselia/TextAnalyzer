import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
        List<String> mainList = new ArrayList<>();
        HashMap<String, Integer> uniqueWords = new HashMap<>();
        List<String> topTenWords = new ArrayList<>();
        Map<String, Integer> sortedUniqueWords;

        AnalyzeText analyzer = new AnalyzeText();
        analyzer.getFileWithText();
        analyzer.deleteSpecialCharacters(analyzer.getFileStringList(), mainList);
        analyzer.uniqueWordsCount(mainList, uniqueWords);
        analyzer.deleteExcludedWords(uniqueWords);
        sortedUniqueWords = analyzer.sortMap(uniqueWords);
        analyzer.getTopTenWords(sortedUniqueWords, topTenWords);

        for (String s : topTenWords) {
            System.out.println(s);
        }
    }
}