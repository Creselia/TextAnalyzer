import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static List<String> fileStringList;
    private static List<String> mainList = new ArrayList<>();
    private static HashMap<String, Integer> uniqueWords = new HashMap<>();

    public static void main(String[] args) throws IOException {

        getFile();

        deleteSpecialCharacters(fileStringList, mainList);

        uniqueWordsCount(mainList, uniqueWords);

        deleteExcludedWords(uniqueWords);
//СОРТИРОВКУ СДЕЛАТЬ ОТДЕЛЬНЫМ МЕТОДОМ!!!
        Map<String, Integer> sortedMap = uniqueWords.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).collect(Collectors.toMap(
                        Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        Set set = sortedMap.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry)iterator.next();
            System.out.println("key is: "+ entry.getKey() + " & Value is: " + entry.getValue());
        }
    }

    private static void getFile() {
        try {
            Path path = Paths.get("C:\\Users\\u1\\Desktop\\abcd.txt");
            fileStringList = Files.readAllLines(path);
        } catch (IOException e) {
            System.out.println("File not found or Can't read file");
        }
    }

    private static void deleteSpecialCharacters(List<String> list, List<String> newList) {
        for (String s : list) {
            newList.add(s.replaceAll("[^a-zA-Zа-яА-Я]", " ").toLowerCase());
        }
    }

    private static void uniqueWordsCount(List<String> list, HashMap<String, Integer> map) {
        String[] splittedList;
        for (String s : list) {
            splittedList = s.split(" ");
            for (String str : splittedList) {
                if (!map.containsKey(str)) {
                    map.put(str, 1);
                } else {
                    map.put(str, map.get(str) + 1);
                }
            }
        }
        if (map.containsKey("")) map.remove("");
    }

    private static void deleteExcludedWords(HashMap<String, Integer> map) throws IOException {
        Path path = Paths.get("C:\\Valeria\\Workspace\\Idea\\TextAnalyzer.git\\src\\excluded_words.txt");
        List<String> list = Files.readAllLines(path);
        for (String s : list) {
            if (map.containsKey(s)) {
                map.remove(s);
            }
        }
    }
}