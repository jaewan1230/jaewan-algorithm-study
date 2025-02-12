import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.Queue;

public class P2468 {
    static int N;
    static int[][] map;
    static boolean[][] visit;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

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
        int max = -1;
        for (int h = 1; h < 100; h++) {
            visit = new boolean[N][N];
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] > h && !visit[i][j]) {
                        cnt++;
                        BFS(i, j, h);
                    }
                }
            }
            if (max < cnt) max = cnt;
        }
        System.out.println(max == 0 ? 1 : max);
    }

    static void BFS(int y, int x, int h) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{y, x});
        visit[y][x] = true;
        while (!q.isEmpty()) {
            int[] now = q.poll();
            for (int i = 0; i < 4; i++) {
                int ty = now[0] + dy[i];
                int tx = now[1] + dx[i];
                if (ty < 0 || tx < 0 || ty >= N || tx >= N) continue;
                if (map[ty][tx] <= h || visit[ty][tx]) continue;
                visit[ty][tx] = true;
                q.offer(new int[]{ty, tx});
            }
        }
    }
}