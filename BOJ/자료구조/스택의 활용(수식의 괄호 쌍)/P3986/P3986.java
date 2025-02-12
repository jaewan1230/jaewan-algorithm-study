import java.util.Scanner;
import java.util.Stack;

public class P3986 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(), cnt = 0;
        for (int i = 0; i < N; i++) {
            Stack<Character> stack = new Stack<>();
            String str = sc.next();
            for (char c : str.toCharArray()) {
                if (stack.isEmpty()) stack.push(c);
                else {
                    if (stack.peek() != c) stack.push(c);
                    else stack.pop();
                }
            }
            if (stack.isEmpty()) cnt++;
        }
        System.out.println(cnt);
    }
}