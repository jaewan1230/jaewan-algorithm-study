/*
치즈

한시간 지나면 접촉한 곳 녹음

모두 녹아 없어지는데 걸리는 시간, 모두 녹기 한 시간 전 남아있는 치즈 조각 개수 구하기

---

BFS로 시뮬레이션 돌려서 외곽부터 녹여나가기

각 시간별 개수 저장해서 모두 녹기 한 시간 전 개수 출력

 */

import java.io.IOException;
import java.util.ArrayDeque;

public class Main {
    static int N, M;
    static int[] dx = { 1, -1, 0, 0 }, dy = { 0, 0, 1, -1 };
    static boolean[][] cheese;

    public static void main(String[] args) throws IOException {
        N = readInt();
        M = readInt();
        cheese = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                cheese[i][j] = readInt() == 1;
            }
        }
        BFS();
    }

    public static void BFS() {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        boolean[][] visit = new boolean[N][M];
        int time = 1, cheeseCnt = 0, prevCheeseCnt = 0;
        // time 에 녹음
        q.add(new int[] { 0, 0, time });
        visit[0][0] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            if(cur[2] > time) {
                // 시간 변경
                prevCheeseCnt = cheeseCnt;
                cheeseCnt = 0;
                time = cur[2];
            }

            int y = cur[0], x = cur[1];

            for (int dir = 0; dir < 4; dir++) {
                int ny = y + dy[dir], nx = x + dx[dir];

                if (nx < 0 || ny < 0 || ny >= N || nx >= M || visit[ny][nx])
                    continue;

                visit[ny][nx] = true;

                if (cheese[ny][nx]) {
                    cheese[ny][nx] = false; // 녹음
                    cheeseCnt++;
                    q.addLast(new int[] { ny, nx, time + 1 });
                } else {
                    q.addFirst(new int[] { ny, nx, time });
                }
            }
        }

        System.out.println(time - 1);
        System.out.println(prevCheeseCnt);
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
