import java.util.Scanner;
import java.util.Stack;

public class P9012 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 0; i < T; i++)
            System.out.println(PS_Validation(sc.next()) ? "YES" : "NO");
    }

    public static boolean PS_Validation(String ps) {
        Stack<Character> stack = new Stack<>();
        for (char c : ps.toCharArray()) {
            if (c == '(') stack.push(c);
            else {
                if (stack.isEmpty()) return false;
                stack.pop();
            }
        }
        if (stack.isEmpty()) return true;
        return false;
    }
}