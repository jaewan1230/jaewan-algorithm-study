import java.io.*;
import java.util.Stack;

public class P3015 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long res = 0;
        Stack<Pair> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            int height = Integer.parseInt(br.readLine());
            if (stack.isEmpty()) {
                stack.push(new Pair(height, 1));
            } else {
                if (stack.peek().height > height) {
                    res++;
                    stack.push(new Pair(height, 1));
                } else {
                    Pair top = null;
                    while (!stack.isEmpty() && stack.peek().height <= height) {
                        top = stack.pop();
                        res += top.cnt;
                    }
                    if(!stack.isEmpty()) res++;
                    if (top.height == height) {
                        top.cnt++;
                        stack.push(top);
                    }
                    else stack.push(new Pair(height,1));
                }
            }
        }
        System.out.println(res);
    }

    static class Pair {
        public int height;
        public int cnt;

        Pair(int height, int cnt) {
            this.height = height;
            this.cnt = cnt;
        }
    }
}