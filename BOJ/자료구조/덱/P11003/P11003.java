import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class P11003 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), L = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        Deque<Node> deq = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            Node node = new Node(Integer.parseInt(st.nextToken()), i);
            if (deq.isEmpty()) deq.add(node);
            else {
                if (deq.peekFirst().index < i - L + 1) deq.removeFirst();
                while (!deq.isEmpty() && deq.peekLast().value > node.value) deq.removeLast();
                deq.add(node);
            }
            sb.append(deq.peekFirst().value + " ");
        }
        System.out.println(sb.toString());
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