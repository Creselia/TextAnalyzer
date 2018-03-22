import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Stack;

public class CheckBrackets {

    private List<String> filledList;

    public List<String> getFilledList() {
        return filledList;
    }

    //метод получает файл по пути, все строки содержимого файла помещает в List
    void getFileWithBrackets() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter path to the file. File should be .txt & UTF-8");
        try {
            Path path = Paths.get(reader.readLine());
            filledList = Files.readAllLines(path);
        } catch (IOException e) {
            System.out.println("File not found or can't read file");
        }
        reader.close();
    }

/*
    private void deleteExceptBrackets(List<String> list, List<String> newList) {
        for (String s : list) {
            //newList.add(s.replaceAll("[^(){}[]]", "")); //проблема с регулярным выражением
        }
    }
*/

    //стэк для хранения открытых скобок
    private Stack<Character> stack = new Stack<>();


    private boolean isBracketOpen(char bracket) {
        return "({[".indexOf(bracket) != -1;
    }

    private boolean isBracketClose(char bracket) {
        return ")}]".indexOf(bracket) != -1;
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
    boolean CheckPairOfBrackets(List<String> list) {
        for (String s : list) {
            for (char c : s.toCharArray()) {
                if (isBracketOpen(c)){
                    stack.push(c);
                }
                if (isBracketClose(c) && stack.isEmpty()) {
                    return false;
                }
                if (isBracketClose(c)) {
                    if (areBracketsPaired(stack.peek(), c)) {
                        stack.pop();
                    } else
                        return false;
                }
            }
        }
        return stack.isEmpty();
    }
}