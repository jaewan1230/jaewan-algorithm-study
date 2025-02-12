import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class P4949 {
    public static void main(String[] args) throws IOException {
        // (),[], .
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String str = br.readLine().toString();
            if (str.length() == 1) break;
            System.out.println(IsMatched(str) ? "yes" : "no");
        }
    }

    public static boolean IsMatched(String str) {
        Stack<Character> stack = new Stack<>();
        for (char c : str.toCharArray()) {
            if (c == '(' || c == '[') stack.push(c);
            else if (c == ')' || c == ']') {
                if (stack.isEmpty()) return false;
                if (c == ')' && stack.pop() != '(') return false;
                if (c == ']' && stack.pop() != '[') return false;
            }
        }
        if(stack.isEmpty()) return true;
        return false;
    }
}