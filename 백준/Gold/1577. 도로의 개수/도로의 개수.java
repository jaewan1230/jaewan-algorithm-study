/*
 */

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        int N = readInt(), M = readInt(), K = readInt();
        boolean[][][] 공사중 = new boolean[N + 1][M + 1][2];

        for (int i = 0; i < K; i++) {
            int a = readInt(), b = readInt(), c = readInt(), d = readInt();

            if (a == c) {
                if (b < d) {
                    공사중[a][d][0] = true;
                } else
                    공사중[a][b][0] = true;
            } else {
                if (a < c)
                    공사중[c][d][1] = true;
                else
                    공사중[a][d][1] = true;
            }
        }

        long[][] dp = new long[N + 1][M + 1];

        dp[0][0] = 1;
        for (int i = 1; i <= N; i++) {
            dp[i][0] += (공사중[i][0][1] ? 0 : dp[i - 1][0]);
        }
        for (int j = 1; j <= M; j++) {
            dp[0][j] += (공사중[0][j][0] ? 0 : dp[0][j - 1]);
        }
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                dp[i][j] += (공사중[i][j][0] ? 0 : dp[i][j - 1]) + (공사중[i][j][1] ? 0 : dp[i - 1][j]);
            }
        }
        System.out.println(dp[N][M]);
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
        int n = c & 15;
        while ((c = read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}