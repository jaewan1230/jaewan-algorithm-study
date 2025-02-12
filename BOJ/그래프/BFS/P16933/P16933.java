import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class P16933 {
    static int N, M, K;
    static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
    static int[][][] map;
    static boolean[][][] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        K = Integer.parseInt(input[2]);
        map = new int[N][M][K + 1];
        visit = new boolean[N][M][K + 1];
        for (int i = 0; i < N; i++) {
            input[0] = br.readLine().toString();
            for (int j = 0; j < M; j++) {
                if (input[0].charAt(j) == '1') map[i][j][0] = -1;
            }
        }
        if (BFS()) System.out.println(RES());
        else System.out.println(-1);
    }

    static int RES() {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i <= K; i++) {
            if (visit[N - 1][M - 1][i]) {
                if (map[N - 1][M - 1][i] < min) min = map[N - 1][M - 1][i];
            }
        }
        return min;
    }

    static boolean BFS() {
        Queue<int[]> q = new LinkedList<>();
        visit[0][0][0] = true;
        map[0][0][0] = 1;
        q.offer(new int[]{0, 0, 0});
        while (!q.isEmpty()) {
            int[] now = q.poll();
            boolean stay = false;
            for (int i = 0; i < 4; i++) {
                int ty = now[0] + dy[i];
                int tx = now[1] + dx[i];
                if (ty < 0 || tx < 0 || ty >= N || tx >= M) continue;
                if (map[ty][tx][0] != -1) {
                    if (visit[ty][tx][now[2]]) continue;
                    visit[ty][tx][now[2]] = true;
                    map[ty][tx][now[2]] = map[now[0]][now[1]][now[2]] + 1;
                    q.offer(new int[]{ty, tx, now[2]});
                } else { // 벽일때
                    if (now[2] >= K) continue; // 횟수 초과
                    if (visit[ty][tx][now[2] + 1]) continue;
                    if (map[now[0]][now[1]][now[2]] % 2 == 1) { // 지금 낮이면
                        visit[ty][tx][now[2] + 1] = true;
                        map[ty][tx][now[2] + 1] = map[now[0]][now[1]][now[2]] + 1;
                        q.offer(new int[]{ty, tx, now[2] + 1});
                    } else { // 밤이면
                        stay = true;
                        q.offer(new int[]{now[0], now[1], now[2]});
                    }
                }
            }
            if (stay) map[now[0]][now[1]][now[2]]++;
        }
        boolean res = false;
        for (int i = 0; i <= K; i++) {
            if (visit[N - 1][M - 1][i]) res = true;
        }
        return res;
    }
}