import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class P14442 {
    static int N, M, K;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static boolean visit[][][];
    static int map[][][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputs[] = br.readLine().split(" ");
        N = Integer.parseInt(inputs[0]);
        M = Integer.parseInt(inputs[1]);
        K = Integer.parseInt(inputs[2]);
        map = new int[N][M][K + 1];
        visit = new boolean[N][M][K + 1];
        for (int i = 0; i < N; i++) {
            String strArr = br.readLine().toString();
            for (int j = 0; j < M; j++) {
                if (strArr.charAt(j) == '1') map[i][j][0] = -1;
            }
        }
        BFS();
        int res = Integer.MAX_VALUE;
        for (int i = 0; i <= K; i++) {
            if (visit[N - 1][M - 1][i]) {
                if (map[N - 1][M - 1][i] < res) res = map[N - 1][M - 1][i];
            }
        }
        System.out.println(res != Integer.MAX_VALUE ? res : -1);
    }

    static void BFS() {
        Queue<int[]> q = new LinkedList<>();
        map[0][0][0] = 1;
        visit[0][0][0] = true;
        q.offer(new int[]{0, 0, 0});
        while (!q.isEmpty()) {
            int[] now = q.poll();
            for (int i = 0; i < 4; i++) {
                int ty = now[0] + dy[i];
                int tx = now[1] + dx[i];
                if (ty < 0 || tx < 0 || ty >= N || tx >= M) continue;
                if (!visit[ty][tx][now[2]] && map[ty][tx][0] != -1) {
                    visit[ty][tx][now[2]] = true;
                    map[ty][tx][now[2]] = map[now[0]][now[1]][now[2]] + 1;
                    q.offer(new int[]{ty, tx, now[2]});
                }
                if (map[ty][tx][0] == -1 && now[2] < K) { // 벽 부수고 가기
                    if (visit[ty][tx][now[2] + 1]) continue;
                    visit[ty][tx][now[2] + 1] = true;
                    map[ty][tx][now[2] + 1] = map[now[0]][now[1]][now[2]] + 1;
                    q.offer(new int[]{ty, tx, now[2] + 1});
                }
            }
        }
    }
}