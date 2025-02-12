import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P2146 {
    static int N;
    static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
    static int[][] map, bridge;
    static boolean[][] visit;
    static Queue<NODE> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 1;
        bridge = new int[N][N];
        visit = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1 && !visit[i][j]) {
                    DFS(i, j, cnt++);
                }
            }
        }

        int min = Integer.MAX_VALUE, res;
        visit = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] > 0 && !visit[i][j]) {
                    visit = new boolean[N][N];
                    res = BFS(i, j);
                    if (res!=-1&&res < min) min = res;
                }
            }
        }
        System.out.println(min);
    }
    static void DFS(int y, int x, int num) {
        visit[y][x] = true;
        map[y][x] = num;
        for (int i = 0; i < 4; i++) {
            int ty = y + dy[i];
            int tx = x + dx[i];
            if (ty < 0 || tx < 0 || ty >= N || tx >= N) continue;
            if (visit[ty][tx] || map[ty][tx] == 0) continue;
            visit[ty][tx] = true;
            map[ty][tx] = num;
            DFS(ty, tx, num);
        }
    }

    static int BFS(int y, int x) {
        queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == map[y][x]) {
                    visit[i][j] = true;
                    queue.offer(new NODE(i, j, map[i][j]));
                }
            }
        }
        while (!queue.isEmpty()) {
            NODE now = queue.poll();
            for (int i = 0; i < 4; i++) {
                int ty = now.y + dy[i];
                int tx = now.x + dx[i];
                if (ty < 0 || tx < 0 || ty >= N || tx >= N) continue;
                if (visit[ty][tx]) continue;
                if (map[ty][tx] > 0) return bridge[now.y][now.x];
                visit[ty][tx] = true;
                bridge[ty][tx] = bridge[now.y][now.x] + 1;
                queue.offer(new NODE(ty, tx, now.num));
            }
        }
        return -1;
    }

    static class NODE {
        int y, x, num;

        public NODE(int y, int x, int num) {
            this.y = y;
            this.x = x;
            this.num = num;
        }
    }
}