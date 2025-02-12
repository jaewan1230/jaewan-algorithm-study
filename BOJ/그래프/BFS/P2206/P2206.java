import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P2206 {
    static int N, M;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};
    static int[][] map;
    static boolean[][] wall;
    static boolean[][][] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        wall = new boolean[N][M];
        visit = new boolean[N][M][2];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                if (str.charAt(j) == '1') wall[i][j] = true;
            }
        }

        if (!BFS(0, 0)) System.out.println(-1);
    }

    public static boolean BFS(int y, int x) {
        map[y][x] = 1;
        visit[y][x][0] = true;
        Queue<NODE> q = new LinkedList<>();
        q.offer(new NODE(y, x, false));
        while (!q.isEmpty()) {
            NODE now = q.poll();
            if (now.y == N - 1 && now.x == M - 1) {
                System.out.println(map[now.y][now.x]);
                return true;
            }
            for (int i = 0; i < 4; i++) {
                int ty = now.y + dy[i];
                int tx = now.x + dx[i];
                if (ty < 0 || tx < 0 || ty >= N || tx >= M) continue;
                if (!now.broken) {
                    if (wall[ty][tx]) { // 벽 뿌시기
                        visit[ty][tx][1] = true;
                        map[ty][tx] = map[now.y][now.x] + 1;
                        q.offer(new NODE(ty, tx, true));
                    } else {
                        if (visit[ty][tx][0]) continue;
                        visit[ty][tx][0] = true;
                        map[ty][tx] = map[now.y][now.x] + 1;
                        q.offer(new NODE(ty, tx, false));
                    }
                }
                if (now.broken) {
                    if (visit[ty][tx][1] || wall[ty][tx]) continue;
                    visit[ty][tx][1] = true;
                    map[ty][tx] = map[now.y][now.x] + 1;
                    q.offer(new NODE(ty, tx, true));
                }
            }
        }
        return false;
    }

    static class NODE {
        public int x;
        public int y;
        public boolean broken;

        public NODE(int y, int x, boolean broken) {
            this.x = x;
            this.y = y;
            this.broken = broken;
        }
    }
}