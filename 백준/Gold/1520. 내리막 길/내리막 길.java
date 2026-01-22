/*
내리막 길
왼쪽 위에서 시작해서 오른쪽 아래로 이동
높이가 낮은 지점으로만, 상하좌우 이동. 항상 내리막길로만 이동하는 경로의 개수.

dfs + memoization
 */

import java.io.IOException;

public class Main {
    static int N, M;
    static int[] dx = { -1, 1, 0, 0 }, dy = { 0, 0, -1, 1 };
    static int[][] map, dp;
    static boolean[][] visit;

    public static void main(String[] args) throws IOException {
        N = readInt();
        M = readInt();
        map = new int[N][M];
        dp = new int[N][M];
        visit = new boolean[N][M];

        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                map[i][j] = readInt();

        System.out.println(dfs(0, 0));
    }

    public static int dfs(int y, int x) {
        if(y == N - 1 && x == M - 1)
            return 1; // 도착지점
        if (visit[y][x])
            return dp[y][x]; // 이미 방문한 곳

        for (int dir = 0; dir < 4; dir++) {
            int ny = y + dx[dir], nx = x + dy[dir];

            if (ny < 0 || nx < 0 || ny >= N || nx >= M || map[ny][nx] >= map[y][x])
                continue; // 내리막길 아님

            dp[y][x] += dfs(ny, nx);
        }

        visit[y][x] = true;
        return dp[y][x];
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
        int n = c & 15;
        while ((c = read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}
