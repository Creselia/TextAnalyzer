package main.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, URISyntaxException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<String> mainList = new ArrayList<>();
        HashMap<String, Integer> uniqueWords = new HashMap<>();
        List<String> topTenWords = new ArrayList<>();

        AnalyzeText analyzer = new AnalyzeText();
        analyzer.getFileWithText(reader);
        analyzer.deleteSpecialCharacters(analyzer.getFileStringList(), mainList);
        analyzer.uniqueWordsCount(mainList, uniqueWords);
        analyzer.deleteExcludedWords(uniqueWords);
        analyzer.getTopTenWords(analyzer.sortMap(uniqueWords), topTenWords);
        for (String s : topTenWords) {
            System.out.println(s);
        }

        CheckBrackets brackets = new CheckBrackets();
        List<String> list = new ArrayList<>();
        brackets.getFileWithBrackets(reader);
        brackets.deleteExceptBrackets(brackets.getFilledList(), list);
        boolean isCorrect = brackets.CheckPairOfBrackets(list);
        System.out.println("Brackets are " + (isCorrect ? "" : "not ") + "correct.");

        reader.close();
    }
}
