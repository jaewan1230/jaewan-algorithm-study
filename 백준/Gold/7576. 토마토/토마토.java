/*
토마토

인접한 토마토는 익게 된다.
며칠이 지나야 모두 익는지 일수

모두 익지 못하는 상황이면 -1 출력

 */

import java.io.IOException;
import java.util.ArrayDeque;

public class Main {
    static int M, N;
    static int[] dx = { 1, -1, 0, 0 }, dy = { 0, 0, 1, -1 };
    static int[][] box;
    static ArrayDeque<int[]> queue = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        M = readInt();
        N = readInt();
        box = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                box[i][j] = readInt();
                if (box[i][j] == 1)
                    queue.offer(new int[] { i, j });
            }
        }

        System.out.println(BFS());
    }

    static int BFS() {
        int time = -1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] cur = queue.poll();
                for (int dir = 0; dir < 4; dir++) {
                    int ny = cur[0] + dy[dir], nx = cur[1] + dx[dir];
                    if (ny < 0 || nx < 0 || ny >= N || nx >= M ||
                            box[ny][nx] == -1 || box[ny][nx] == 1)
                        continue;
                    queue.offer(new int[] { ny, nx });
                    box[ny][nx] = 1;
                }
            }
            time++;
        }

        if (!check())
            return -1;
        else
            return time;
    }

    // 다 익었는지 확인
    static boolean check() {
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                if (box[i][j] == 0)
                    return false;
        return true;
    }

    public static int pos, len;
    public static byte[] buf = new byte[8192];

    public static byte read() throws IOException {
        if (pos == len) {
            len = System.in.read(buf);
            pos = 0;
        }
        return buf[pos++];
    }

    public static int readInt() throws IOException {
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
