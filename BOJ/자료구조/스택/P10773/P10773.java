import java.util.Scanner;
import java.util.Stack;

public class P10773 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int res = 0, n = sc.nextInt();
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            int t = sc.nextInt();
            if (t > 0) {
                stack.push(t);
            }
            if (t == 0) {
                stack.pop();
            }
        }
        for (int t : stack) {
            res += t;
        }
        System.out.println(res);
    }
}