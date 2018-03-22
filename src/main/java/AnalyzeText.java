

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

public class AnalyzeText {
    private List<String> fileStringList;

    //метод получает файл по пути, все строки содержимого файла помещает в List
    private void getFileWithText(BufferedReader reader) {
        System.out.println("Enter the path to the file to get word rating. File should be .txt & UTF-8");
        try {
            Path path = Paths.get(reader.readLine());
            fileStringList = Files.readAllLines(path);
        } catch (IOException e) {
            System.out.println("File not found or can't read file");
        }
    }

    //метод удаляет все символы кроме русских и латинских букв, приводит их к нижнему регистру
    private void deleteSpecialCharacters(List<String> list, List<String> newList) {
        for (String s : list) {
            newList.add(s.replaceAll("[^a-zA-Zа-яА-Я]", " ").toLowerCase());
        }
    }

    //каждую строку из List помещает в массив и сплитит ее на слова по пробелам
    //провереят HashMap на наличие слова, если слова нет, добавляет его со значением 1
    //если слово есть, то +1 в текущему занчению
    //удаляет с пустым ключем, если такой есть
    private void uniqueWordsCount(List<String> list, HashMap<String, Integer> map) {
        String[] splitArray;
        for (String s : list) {
            splitArray = s.split(" ");
            for (String str : splitArray) {
                if (!map.containsKey(str)) {
                    map.put(str, 1);
                } else {
                    map.put(str, map.get(str) + 1);
                }
            }
        }
        if (map.containsKey("")) map.remove("");
    }

    //слова, которые необходимо исключить из статистики, внесены в отдельный файл.
    //удаляет из HashMap все слова, которые есть в файле
    private void deleteExcludedWords(HashMap<String, Integer> map) throws IOException, URISyntaxException {
        final URI uri = getClass().getResource("excluded_words.txt").toURI();
        Map<String, String> env = new HashMap<>();
        env.put("create", "true");
        FileSystem fileSystem = FileSystems.newFileSystem(uri, env);
        Path path = Paths.get(uri);
        List<String> list = Files.readAllLines(path);

        for (String s : list) {
            if (map.containsKey(s)) {
                map.remove(s);
            }
        }
    }

    //сортировка по значению в порядке убывавния
    private Map<String, Integer> sortMap(HashMap<String, Integer> map) {
        return map.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).collect(Collectors.toMap(
                        Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    //помещает в List первые 10 слов из отсортированного Map
    private void topTenWords(Map<String, Integer> sortedMap, List<String> list) {
        Set<String> set = sortedMap.keySet();
        int count = 0;
        for (String s : set) {
            list.add(s);
            if (count == 9) break;
            count++;
        }
    }

    void printTopTenWords(BufferedReader reader) throws IOException, URISyntaxException {
        List<String> mainList = new ArrayList<>();
        HashMap<String, Integer> uniqueWords = new HashMap<>();
        List<String> topTenWords = new ArrayList<>();

        getFileWithText(reader);
        deleteSpecialCharacters(fileStringList, mainList);
        uniqueWordsCount(mainList, uniqueWords);
        deleteExcludedWords(uniqueWords);
        topTenWords(sortMap(uniqueWords), topTenWords);
        for (String s : topTenWords) {
            System.out.println(s);
        }
    }
}