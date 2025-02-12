import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P1926 {
    public static void main(String[] args) throws IOException {
        int map[][] = new int[501][501];
        boolean visit[][] = new boolean[501][501];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n, m;
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        Queue<Node> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++) map[i][j] = Integer.parseInt(st.nextToken());
        }
        int cnt = 0, max = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (map[i][j] == 1 && visit[i][j] == false) {
                    cnt++;
                    queue.offer(new Node(i, j));
                    int val = BFS(n, m, queue, map, visit);
                    if (max < val) max = val;
                }
            }
        }
        System.out.println(cnt);
        System.out.println(max);
    }

    public static int BFS(int n, int m, Queue<Node> queue, int[][] map, boolean[][] visit) {
        int dy[] = {0, 1, 0, -1};
        int dx[] = {1, 0, -1, 0};
        int size = 0;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (visit[node.y][node.x]) continue;
            visit[node.y][node.x] = true;
            size++;
            for (int i = 0; i < 4; i++) {
                int ty = node.y + dy[i];
                int tx = node.x + dx[i];
                if (0 < ty && 0 < tx && ty <= n && tx <= m) {
                    if (map[ty][tx] == 1 && visit[ty][tx] == false) {
                        queue.offer(new Node(ty, tx));
                    }
                }
            }
        }
        return size;
    }

    static class Node {
        public int y;
        public int x;

        Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}