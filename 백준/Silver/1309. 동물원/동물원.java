/*
동물원
2 x N 배열에 사자를 배치하는 경우의 수
사자 배치할 때, 인접한 칸에 배치할 수 없음.

우리의 크기 N <= 100,000
사자를 배치하는 경우의 수를 9,901로 나눈 나머지

---
dp로 접근

dp[i][0] = (i,0)번째 칸에 사자를 배치하는 경우의 수
dp[i][1] = (i,1)번째 칸에 사자를 배치하는 경우의 수
dp[i][2] = i번째 칸에 사자를 배치하지 않는 경우의 수

dp[i][0] = dp[i-1][1] + dp[i-1][2]
dp[i][1] = dp[i-1][0] + dp[i-1][2]
dp[i][2] = dp[i-1][0] + dp[i-1][1] + dp[i-1][2]

최종 답 = dp[N][0] + dp[N][1] + dp[N][2] % 9901
 */

import java.io.IOException;

public class Main {
    static int N;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        N = readInt();
        dp = new int[N + 1][3];
        dp[0][0] = dp[0][1] = dp[0][2] = 1;
        for (int i = 1; i < N; i++) {
            dp[i][0] = (dp[i - 1][1] + dp[i - 1][2]) % 9901;
            dp[i][1] = (dp[i - 1][0] + dp[i - 1][2]) % 9901;
            dp[i][2] = (dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2]) % 9901;
        }
        System.out.println((dp[N - 1][0] + dp[N - 1][1] + dp[N - 1][2]) % 9901);
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
