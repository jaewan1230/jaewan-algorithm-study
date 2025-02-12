import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class P1697 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(), K = sc.nextInt();
        Queue<Node> q = new LinkedList<Node>();
        boolean[] visited = new boolean[100001];
        q.offer(new Node(N, 0));
        visited[N] = true;

        while (!q.isEmpty()) {
            Node node = q.poll();
            if (node.index == K) {
                System.out.println(node.second);
                break;
            }
            if (node.index + 1 <= 100000 && !visited[node.index + 1]) {
                q.offer(new Node(node.index + 1, node.second + 1));
                visited[node.index + 1] = true;
            }
            if (node.index - 1 >= 0 && !visited[node.index - 1]) {
                q.offer(new Node(node.index - 1, node.second + 1));
                visited[node.index + -1] = true;
            }
            if (node.index != 0 && node.index * 2 <= 100000 && !visited[node.index * 2]) {
                q.offer(new Node(node.index * 2, node.second + 1));
                visited[node.index * 2] = true;
            }
        }
    }

    static class Node {
        int index, second;

        public Node(int index, int second) {
            this.index = index;
            this.second = second;
        }
    }
}