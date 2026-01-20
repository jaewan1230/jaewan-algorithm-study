/*
욕심쟁이 판다
상하좌우로 이동, 옮긴 지역에 전 지역보다 대나무가 많아야 함

N <= 500
대나무 개수 1,000,000 이하

판다가 이동할 수 있는 최대 칸 수 출력

dp 재귀
dp[i][j] = Math.max(dp[i-1][j], dp[i+1][j], dp[i][j-1], dp[i][j+1]) + 1 (갈 수 있는 경우)

 */

import java.io.IOException;

public class Main {
    static int N;
    static int[] dx = { -1, 1, 0, 0 }, dy = { 0, 0, -1, 1 };
    static int[][] map, dp;

    public static void main(String[] args) throws IOException {
        N = readInt();
        map = new int[N + 2][N + 2];
        dp = new int[N + 2][N + 2];

        for (int i = 1; i <= N; i++)
            for (int j = 1; j <= N; j++)
                map[i][j] = readInt();

        int max = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                max = Math.max(max, dfs(i, j));
            }
        }
        System.out.println(max);
    }

    public static int dfs(int y, int x) {
        if (dp[y][x] != 0)
            return dp[y][x];

        int max = 0;

        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i], nx = x + dx[i];

            if (map[ny][nx] <= map[y][x])
                continue;
            max = Math.max(max, dfs(ny, nx));
        }

        return dp[y][x] = max + 1;
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
