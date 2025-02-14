import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P1600 {
    static int K, W, H;
    static int[] dx = {1, -1, 0, 0, 2, 2, -2, -2, 1, 1, -1, -1};
    static int[] dy = {0, 0, 1, -1, 1, -1, 1, -1, 2, -2, 2, -2};
    static int[][][] map;
    static boolean[][][] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new int[H][W][K + 1];
        visit = new boolean[H][W][K + 1];

        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                map[i][j][0] = Integer.parseInt(st.nextToken());
                if (map[i][j][0] == 1) map[i][j][0] = -1;
            }
        }

        BFS();
        boolean res = false;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i <= K; i++) {
            if (visit[H - 1][W - 1][i]) {
                res = true;
                if (map[H - 1][W - 1][i] < min) min = map[H - 1][W - 1][i];
            }
        }
        if (res) System.out.println(min);
        else System.out.println(-1);
    }

    static void BFS() {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0, 0});
        visit[0][0][0] = true;
        while (!q.isEmpty()) {
            int[] now = q.poll();
            for (int i = 0; i < 4; i++) {
                int ty = now[0] + dy[i];
                int tx = now[1] + dx[i];
                if (ty < 0 || tx < 0 || ty >= H || tx >= W) continue;
                if (map[ty][tx][0] == -1 || visit[ty][tx][now[2]]) continue;
                visit[ty][tx][now[2]] = true;
                map[ty][tx][now[2]] = map[now[0]][now[1]][now[2]] + 1;
                q.offer(new int[]{ty, tx, now[2]});
            }
            for (int i = 4; i < 12; i++) {
                int ty = now[0] + dy[i];
                int tx = now[1] + dx[i];
                if (ty < 0 || tx < 0 || ty >= H || tx >= W) continue;
                if (now[2] + 1 > K) continue;
                if (map[ty][tx][0] == -1 || visit[ty][tx][now[2] + 1]) continue;
                visit[ty][tx][now[2] + 1] = true;
                map[ty][tx][now[2] + 1] = map[now[0]][now[1]][now[2]] + 1;
                q.offer(new int[]{ty, tx, now[2] + 1});
            }
        }
    }
}