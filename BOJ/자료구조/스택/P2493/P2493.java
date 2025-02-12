import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;
import java.util.StringTokenizer;

public class P2493 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Node> stack = new Stack<>();
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            int height = Integer.parseInt(st.nextToken());
            while (stack.size() > 0 && stack.peek().height < height) stack.pop();
            if (stack.size() == 0) {
                System.out.print("0 ");
                stack.push(new Node(i, height));
            } else if (stack.peek().height > height) {
                System.out.print(stack.peek().index + " ");
                stack.push(new Node(i, height));
            }
        }
    }
    static class Node {
        public int index;
        public int height;

        Node(int index, int height) {
            this.index = index;
            this.height = height;
        }
    }
}
