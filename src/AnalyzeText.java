import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class AnalyzeText {
    private List<String> fileStringList;
    public List<String> getFileStringList() {
        return fileStringList;
    }

    void getFileWithText() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter path to the file. File should be .txt & UTF-8");
        try {
            Path path = Paths.get(reader.readLine());
            fileStringList = Files.readAllLines(path);
        } catch (IOException e) {
            System.out.println("File not found or can't read file");
        }
        reader.close();
    }

    void deleteSpecialCharacters(List<String> list, List<String> newList) {
        for (String s : list) {
            newList.add(s.replaceAll("[^a-zA-Zа-яА-Я]", " ").toLowerCase());
        }
    }

    void uniqueWordsCount(List<String> list, HashMap<String, Integer> map) {
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

    void deleteExcludedWords(HashMap<String, Integer> map) throws IOException {
        Path path = Paths.get("C:\\Users\\Administrator\\Documents\\Workspace\\src\\excluded_words.txt");
        List<String> list = Files.readAllLines(path);
        for (String s : list) {
            if (map.containsKey(s)) {
                map.remove(s);
            }
        }
    }

    Map<String, Integer> sortMap(HashMap<String, Integer> map) {
        return map.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).collect(Collectors.toMap(
                        Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    void getTopTenWords(Map<String, Integer> sortedMap, List<String> list) {
        Set<String> set = sortedMap.keySet();
        int count = 0;
        for (String s : set) {
            list.add(s);
            if (count == 9) break;
            count++;
        }
    }
}