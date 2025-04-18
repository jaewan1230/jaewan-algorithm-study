
/*
 * 공기 중에치즈가 모두 녹아 없어지는데 걸리는 시간
 * 모두 녹기 한 시간 전에 남아있는 치즈조각이 놓여 있는 칸의 개수
 * 1, 1부터 치즈 놓고, 0,0 부터 BFS로 바깥 부분만 녹이기.
 */
import java.io.IOException;
import java.util.ArrayDeque;

public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][] visit;
    static ArrayDeque<int[]> q, q2 = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        N = readInt();
        M = readInt();
        map = new int[N + 2][M + 2];
        for (int i = 1; i <= N; i++)
            for (int j = 1; j <= M; j++)
                map[i][j] = readInt();
        int prev = 0;
        for (int time = 0;; time++) {
            meltingCheese(time);
            if (q2.size() == 0) {
                System.out.printf("%d\n%d", time, prev);
                break;
            }
            prev = q2.size();
        }
    }

    // 0, 0 에서 출발해 공기와 맞닿아 있는 치즈를 녹인다.
    static int meltingCheese(int time) {
        int cnt = 0;
        int[] dy = { 0, 0, -1, 1 }, dx = { -1, 1, 0, 0 };
        ArrayDeque<int[]> q = new ArrayDeque<>();
        if (time == 0) {
            visit = new boolean[N + 2][M + 2];
            q.offer(new int[] { 0, 0 });
            visit[0][0] = true;
        } else
            while (!q2.isEmpty())
                q.offer(q2.poll());
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int dir = 0; dir < 4; dir++) {
                int ny = cur[0] + dy[dir];
                int nx = cur[1] + dx[dir];
                if (ny < 0 || nx < 0 || ny > N + 1 || nx > M + 1)
                    continue;
                if (visit[ny][nx])
                    continue;
                visit[ny][nx] = true;
                switch (map[ny][nx]) {
                    case 0:
                        q.offer(new int[] { ny, nx });
                        break;
                    case 1:
                        q2.offer(new int[] { ny, nx });
                        map[ny][nx] = 0;
                }
            }
        }
        return cnt;
    }

    static int readInt() throws IOException {
        int c;
        do {
            c = System.in.read();
        } while (c <= 32);
        int n = c & 15;

        c = System.in.read();
        while (c > 47) {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();
        }
        return n;
    }
}
