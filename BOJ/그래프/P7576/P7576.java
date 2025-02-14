import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P7576 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken()), N = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        boolean[][] visited = new boolean[N][M];
        Queue<Node> q = new LinkedList<Node>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    q.offer(new Node(i, j));
                    visited[i][j] = true;
                }
            }
        }
        int[] dy = {0, 1, 0, -1};
        int[] dx = {1, 0, -1, 0};
        while (!q.isEmpty()) {
            Node node = q.poll();
            for (int i = 0; i < 4; i++) {
                int ty = node.y + dy[i];
                int tx = node.x + dx[i];
                if (ty < 0 || tx < 0 || ty >= N || tx >= M) continue;
                if (visited[ty][tx] || map[ty][tx] < 0) continue;
                q.offer(new Node(ty, tx));
                visited[ty][tx] = true;
                map[ty][tx] = map[node.y][node.x] + 1;
            }
        }
        int max = -1;
        boolean res = false;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) res = true;
                if (max < map[i][j]) max = map[i][j];
            }
        }
        if (res) System.out.println(-1);
        else System.out.println(max - 1);
    }

    static class Node {
        int y, x;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}