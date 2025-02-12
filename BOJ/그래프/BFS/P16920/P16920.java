import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P16920 {
    static int N, M, P;
    static int[] S, dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1}, res;
    static int[][] map;
    static Queue[] queues;
    static boolean[][] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        S = new int[P];
        res = new int[P];
        queues = new Queue[P];
        map = new int[N][M];
        visit = new boolean[N][M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < P; i++) {
            S[i] = Integer.parseInt(st.nextToken());
            queues[i] = new LinkedList<int[]>();
        }
        for (int i = 0; i < N; i++) {
            String arrStr = br.readLine();
            for (int j = 0; j < M; j++) {
                if (arrStr.charAt(j) == '#') map[i][j] = -1;
                if ('1' <= arrStr.charAt(j) && arrStr.charAt(j) <= '9') {
                    map[i][j] = arrStr.charAt(j) - '0';
                    visit[i][j] = true;
                    queues[arrStr.charAt(j) - '0' - 1].offer(new int[]{i, j, 0});
                    res[map[i][j] - 1]++;
                }
            }
        }
        BFS();
        for (int i = 0; i < P; i++) System.out.printf("%d ", res[i]);
    }

    static void BFS() {
        int cnt = 1;
        boolean chk = true;
        while (chk) {
            chk = false;
            for (int p = 0; p < P; p++) {
                while (!queues[p].isEmpty()) {
                    int[] now = (int[]) queues[p].peek();
                    if (now[2] >= cnt * S[p]) break;
                    chk = true;
                    now = (int[]) queues[p].poll();
                    for (int i = 0; i < 4; i++) {
                        int ty = now[0] + dy[i];
                        int tx = now[1] + dx[i];
                        if (ty < 0 || tx < 0 || ty >= N || tx >= M) continue;
                        if (visit[ty][tx] || map[ty][tx] == -1) continue;
                        visit[ty][tx] = true;
                        map[ty][tx] = map[now[0]][now[1]];
                        res[map[ty][tx] - 1]++;
                        queues[p].offer(new int[]{ty, tx, now[2] + 1});
                    }
                }
            }
            cnt++;
        }
    }
}