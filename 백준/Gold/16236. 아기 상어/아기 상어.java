/*
아기상어

N x N 공간에 물고기 M 마리와 아기 상어 1마리

처음 크기 2, 1초에 상하좌우 한 칸씩 이동

큰 물고기는 지나갈 수 없고, 같은 물고기는 먹을 수 없지만 지나갈 수 있고, 작은 물고기만 먹을 수 있음.

먹을 수 있는 물고기가 1마리면, 물고기 먹으러 이동
1마리보다 많으면, 가장 가까운 물고기로
가까운 물고기가 많으면, 가장 위, 중 가장 왼쪽에 있는 물고기.

크기와 같은 수의 물고기를 먹을 때마다 크기 1 증가.

몇 초 동안 물고기를 잡아먹을 수 있는지 구하기.

---

BFS 를 통해서, 먹을 수 있는 물고기 있는지 찾기 (거리별로)
있으면 이동, 없으면 종료

 */

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.PriorityQueue;

public class Main {
    static int N, M, sy, sx, shark_size = 2, shark_cnt = 0, res = 0;
    static int[] dx = { 1, -1, 0, 0 }, dy = { 0, 0, 1, -1 };
    static int[][] map;

    public static void main(String[] args) throws IOException {
        N = readInt();
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = readInt();

                if (map[i][j] == 9) {
                    sy = i;
                    sx = j;
                    map[i][j] = 0;
                }
            }
        }

        while (BFS())
            ;

        System.out.println(res);
    }

    // 먹을 수 있는 물고기 없으면 false 반환
    static boolean BFS() {
        boolean[][] visit = new boolean[N][N];
        PriorityQueue<Pos> feed = new PriorityQueue<>();
        ArrayDeque<int[]> queue = new ArrayDeque<>();

        queue.add(new int[] { sy, sx });
        visit[sy][sx] = true;

        int time = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {

                int[] cur = queue.poll();

                for (int dir = 0; dir < 4; dir++) {
                    int ny = cur[0] + dy[dir], nx = cur[1] + dx[dir];

                    if (ny < 0 || nx < 0 || ny >= N || nx >= N || visit[ny][nx] || map[ny][nx] > shark_size)
                        continue;

                    queue.add(new int[] { ny, nx });
                    visit[ny][nx] = true;

                    if (map[ny][nx] != 0 && map[ny][nx] < shark_size)
                        feed.add(new Pos(ny, nx));
                }
            }
            if (!feed.isEmpty())
                break;
            time++;
        }

        if (feed.isEmpty())
            return false;

        res += time;
        Pos next = feed.poll();
        sy = next.y;
        sx = next.x;
        map[sy][sx] = 0;

        if (++shark_cnt == shark_size) {
            shark_size++;
            shark_cnt = 0;
        }

        return true;
    }

    static class Pos implements Comparable<Pos> {
        int y, x;

        public Pos(int y, int x) {
            this.y = y;
            this.x = x;
        }

        @Override
        public int compareTo(Main.Pos o) {
            return y != o.y ? y - o.y : x - o.x;
        }

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
