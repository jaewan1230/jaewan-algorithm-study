import java.util.StringTokenizer;
import java.util.Stack;
import java.io.*;

public class P17298 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] res = new int[n];
        Stack<Node> stack = new Stack<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int t = Integer.parseInt(st.nextToken());
            while (!stack.isEmpty() && stack.peek().value < t) res[stack.pop().index] = t;
            stack.push(new Node(t, i));
        }
        while (!stack.isEmpty()) res[stack.pop().index] = -1;
        for (int val : res) bw.write(val + " ");
        bw.flush();
        bw.close();
    }

    static class Node {
        public int value;
        public int index;

        Node(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }
}