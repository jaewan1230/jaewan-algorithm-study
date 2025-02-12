import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class P6549 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Node> stack = new Stack<>();
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            if (n == 0) break;
            int[] h = new int[n + 1];
            int[] left = new int[n + 1], right = new int[n + 1];
            for (int i = 1; i <= n; i++) h[i] = Integer.parseInt(st.nextToken());

            stack.push(new Node(-1, 0));
            for (int i = 1; i <= n; i++) {
                while (stack.peek().h >= h[i]) stack.pop();
                left[i] = stack.peek().i;
                stack.push(new Node(h[i], i));
            }

            while (!stack.isEmpty()) stack.pop();
            stack.push(new Node(-1, n + 1));
            for (int i = n; i >= 1; i--) {
                while (stack.peek().h >= h[i]) stack.pop();
                right[i] = stack.peek().i;
                stack.push(new Node(h[i], i));
            }
            while (!stack.isEmpty()) stack.pop();

            long max = Long.MIN_VALUE;
            for (int i = 1; i <= n; i++) {
                long t = (long) (right[i] - left[i] - 1) * h[i];
                if (max < t) max = t;
            }
            System.out.println(max);
        }
    }

    static class Node {
        public int h;
        public int i;

        Node(int h, int i) {
            this.h = h;
            this.i = i;
        }
    }
}