import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2573 {
    static int N, M;
    static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};

    static int[][] map;
    static boolean[][] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int res = 0;
        boolean visited = true;
        while (visited) {
            visited = false;
            visit = new boolean[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] > 0 && !visit[i][j]) {
                        if (!visited) {
                            visited = true;
                            dfs(i, j);
                        } else if (visited) {
                            System.out.println(res);
                            return;
                        }
                    }
                }
            }
            res++;
        }
        System.out.println(0);
    }

    static void dfs(int y, int x) {
        visit[y][x] = true;
        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            int ty = y + dy[i];
            int tx = x + dx[i];
            if (ty < 0 || tx < 0 || ty >= N || tx >= M) continue;
            if (map[ty][tx] == 0 && !visit[ty][tx]) {
                cnt++;
                continue;
            }
            if (visit[ty][tx]) continue;
            dfs(ty, tx);
        }
        map[y][x] -= cnt;
        if (map[y][x] < 0) map[y][x] = 0;
    }
}