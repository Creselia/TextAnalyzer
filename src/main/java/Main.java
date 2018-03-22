import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        List<String> mainList = new ArrayList<>();
        HashMap<String, Integer> uniqueWords = new HashMap<>();
        List<String> topTenWords = new ArrayList<>();

        AnalyzeText analyzer = new AnalyzeText();
        analyzer.getFileWithText();
        analyzer.deleteSpecialCharacters(analyzer.getFileStringList(), mainList);
        analyzer.uniqueWordsCount(mainList, uniqueWords);
        analyzer.deleteExcludedWords(uniqueWords);
        analyzer.getTopTenWords(analyzer.sortMap(uniqueWords), topTenWords);

        for (String s : topTenWords) {
            System.out.println(s);
        }


        CheckBrackets brackets = new CheckBrackets();
        brackets.getFileWithBrackets();
        boolean isCorrect = brackets.CheckPairOfBrackets(brackets.getFilledList());
        System.out.println("Brackets are " + (isCorrect ? "" : "not ") + "correct.");

    }
}
