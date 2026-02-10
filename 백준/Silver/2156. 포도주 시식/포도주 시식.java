/*
포도주 시식

1. 포도주 잔 선택하면 모두 마시고 원위치
2. 연속으로 3잔 모두 마실 수 없음

가능한 많은 양의 포도주 마시기.
---
dp 문제
연속 3잔이 안되니까, dp[i][0] = i번째 잔 안마심
dp[i][1] = i번째 잔 마심, i-1번째 잔 안마심
dp[i][2] = i번째 잔 마심, i-1번째 잔 마심
dp[i][0] = max(dp[i-1][0], dp[i-1][1], dp[i-1][2])
dp[i][1] = dp[i-1][0] + wine[i]
dp[i][2] = dp[i-1][1] + wine[i] 

결과는 dp[N][0], dp[N][1], dp[N][2] 중 최대값

*/

import java.io.IOException;

public class Main {
    static int N;
    static int[] wine;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        N = readInt();
        wine = new int[N];
        dp = new int[N][3];

        for (int i = 0; i < N; i++)
            wine[i] = readInt();

        dp[0][0] = 0;
        dp[0][1] = wine[0];
        dp[0][2] = 0;

        for (int i = 1; i < N; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], Math.max(dp[i - 1][1], dp[i - 1][2]));
            dp[i][1] = dp[i - 1][0] + wine[i];
            dp[i][2] = dp[i - 1][1] + wine[i];
        }

        System.out.println(Math.max(dp[N - 1][0], Math.max(dp[N - 1][1], dp[N - 1][2])));
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
