import java.io.*;
import java.util.Stack;

public class P6198 {
    public static void main(String[] args) throws IOException {
        Stack<Integer> stack = new Stack<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        long res = 0;
        for (int i = 0; i < n; i++) {
            int h = Integer.parseInt(br.readLine());
            while (!stack.isEmpty() && stack.peek() <= h) {
                stack.pop();
            }
            res += stack.size();
            stack.push(h);
        }
        System.out.println(res);
    }
}