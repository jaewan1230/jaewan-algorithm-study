import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;

public class P1874 {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Integer> stack = new Stack<>();

        int num = 1, n = Integer.parseInt(br.readLine());
        boolean flag = true;
        for (int i = 0; i < n; i++) {
            int t = Integer.parseInt(br.readLine());
            if (num <= t) {
                while (num <= t) {
                    stack.push(num++);
                    sb.append("+\n");
                }
                stack.pop();
                sb.append("-\n");
            } else if (num > t) {
                if (stack.pop() != t) {
                    System.out.println("NO");
                    flag = false;
                    break;
                } else sb.append("-\n");
            }
        }
        if (flag) System.out.print(sb.toString());
    }
}
