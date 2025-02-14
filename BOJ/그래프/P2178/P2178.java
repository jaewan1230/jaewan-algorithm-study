import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class P2178 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(), M = sc.nextInt();
        int[][] miro = new int[N + 1][M + 1];
        boolean[][] visited = new boolean[N + 1][M + 1];
        for (int i = 0; i < N; i++) {
            String arrStr = sc.next();
            for (int j = 0; j < M; j++) {
                miro[i][j] = arrStr.charAt(j) - '0';
            }
        }

        int[] dy = {0, 1, 0, -1};
        int[] dx = {1, 0, -1, 0};
        Queue<Node> queue = new LinkedList<Node>();
        queue.offer(new Node(0, 0));
        visited[0][0] = true;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (int i = 0; i < 4; i++) {
                int ty = node.y + dy[i];
                int tx = node.x + dx[i];
                if (ty < 0 || tx < 0 || ty >= N || tx >= M) continue;
                if (visited[ty][tx] || miro[ty][tx] == 0) continue;
                queue.offer(new Node(ty, tx));
                visited[ty][tx] = true;
                miro[ty][tx] = miro[node.y][node.x] + 1;
            }
        }
        System.out.println(miro[N - 1][M - 1]);
    }

    static class Node {
        int y, x;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}