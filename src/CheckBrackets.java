import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Stack;

public class CheckBrackets {
    List<String> filledList;

    void getFileWithText() throws IOException {
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

    private void deleteExceptBrackets(List<String> list, List<String> newList) {
        for (String s : list) {
            //newList.add(s.replaceAll("[^(){}[]]", "")); //проблема с регулярным выражением
        }
    }


    private Stack<Character> stack = new Stack<>();


    private boolean isBracketOpen(char bracket) {
        return "({[".indexOf(bracket) != -1;
    }

    private boolean isBracketClose(char bracket) {
        return ")}]".indexOf(bracket) != -1;
    }

    private boolean areBracketsPaired(char open, char close) {
        return open == '(' && close == ')' ||
                open == '{' && close == '}' ||
                open == '[' && close == ']';
    }

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
