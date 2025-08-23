
// 12720 KB, 84 ms
/*
 * BFS, 1번 2번이 같은 시간에 도착하면 3번 바이러스.
 */
import java.io.IOException;
import java.util.ArrayDeque;

public class Main {

    public static void main(String[] args) throws IOException {
        int N = readInt(), M = readInt();
        int[] res = new int[4];
        int[][] map = new int[N][M], time = new int[N][M];

        ArrayDeque<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++) {
                map[i][j] = readInt();
                if (map[i][j] == 1 || map[i][j] == 2) {
                    // 종류, 시간, y, x
                    queue.offer(new int[] { map[i][j], 1, i, j });
                    res[map[i][j]]++;
                }
            }

        int[] dx = { -1, 1, 0, 0 }, dy = { 0, 0, -1, 1 };
        for (int t = 1;; t++) {
            int cnt = queue.size();
            if (cnt == 0)
                break;
            while (cnt-- > 0) {
                int[] cur = queue.poll();
                for (int i = 0; i < 4; i++) {
                    int ny = cur[2] + dy[i];
                    int nx = cur[3] + dx[i];
                    if (ny < 0 || nx < 0 || ny >= N || nx >= M ||
                            map[ny][nx] == -1 || map[ny][nx] == cur[0] || map[cur[2]][cur[3]] == 3)
                        continue;
                    if (map[ny][nx] == 0) {
                        map[ny][nx] = cur[0];
                        time[ny][nx] = cur[1] + 1;
                        queue.offer(new int[] { cur[0], time[ny][nx], ny, nx });
                        res[cur[0]]++;
                    } else if (map[ny][nx] != 3 && time[ny][nx] == cur[1] + 1) {
                        res[map[ny][nx]]--;
                        res[3]++;
                        map[ny][nx] = 3;
                    }
                }
            }
        }
        System.out.printf("%d %d %d", res[1], res[2], res[3]);
    }

    static int pos, len;
    static byte[] buf = new byte[8192];

    static byte read() throws IOException {
        if (pos == len) {
            len = System.in.read(buf);
            pos = 0;
        }
        return buf[pos++];
    }

    static int readInt() throws IOException {
        int c;
        while ((c = read()) <= 32)
            ;
        boolean negative = c == 45;
        if (negative)
            c = read();
        int n = c & 15;
        while ((c = read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return negative ? -n : n;
    }
}