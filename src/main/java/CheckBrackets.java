

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CheckBrackets {
    private List<String> filledList;


    //метод получает файл по пути, все строки содержимого файла помещает в List
    private void getFileWithBrackets(BufferedReader reader) {
        System.out.println("Enter the path to the file to check brackets. File should be .txt & UTF-8");
        try {
            Path path = Paths.get(reader.readLine());
            filledList = Files.readAllLines(path);
        } catch (IOException e) {
            System.out.println("File not found or can't read file");
        }
    }

    //метод "чистит" list от всех символов и пробелов, кроме скобок
    private void deleteExceptBrackets(List<String> list, List<String> newList) {
        for (String s : list) {
            newList.add(s.replaceAll("[^(){}\\[\\]]", "").replaceAll(" ", ""));
        }
    }

    //стэк для хранения открытых скобок
    private Stack<Character> stack = new Stack<>();

    //проверяет символ открытой скобки
    private boolean isBracketOpen(char bracket) {
        return '(' == bracket || '{' == bracket || '[' == bracket;
    }

    //проверяет символ закрытой скобки
    private boolean isBracketClose(char bracket) {
        return ')' == bracket || '}' == bracket || ']' == bracket;
    }

    //проверяет на соответствие пар скобок
    private boolean areBracketsPaired(char open, char close) {
        return open == '(' && close == ')' ||
                open == '{' && close == '}' ||
                open == '[' && close == ']';
    }

    //метод получает каждую стороку List, из строки делает массив символов
    //каждый символ массива проверяет, если это открытая скобка, то помещает ее в стэк
    //если скобка закрыта и стэк пуст - не верно
    //если скобка закрыта, то берет последнюю скобку из стэка и проверяет на соответствие пары
    //если пара есть, то удаляет из стэка и продожнает, а если не соответствует - не верно
    private boolean CheckPairOfBrackets(List<String> list) {
        for (String s : list) {
            for (char c : s.toCharArray()) {
                if (isBracketOpen(c)) {
                    stack.push(c);
                } else if (isBracketClose(c) && stack.isEmpty()) {
                    return false;
                } else if (isBracketClose(c)) {
                    if (areBracketsPaired(stack.peek(), c)) {
                        stack.pop();
                    } else
                        return false;
                }
            }
        }
        return stack.isEmpty();
    }

    void printBracketResult(BufferedReader reader) {
        List<String> list = new ArrayList<>();
        getFileWithBrackets(reader);
        deleteExceptBrackets(filledList, list);
        boolean isCorrect = CheckPairOfBrackets(list);
        System.out.println("Brackets are " + (isCorrect ? "" : "not ") + "correct.");
    }
}