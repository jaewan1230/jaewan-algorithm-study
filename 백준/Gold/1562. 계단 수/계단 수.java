/*
 * 계단 수, 인접한 자리의 차이가 1.
 * 45656
 * N이 주어질 때, 길이가 N 이면서 0부터 9까지 숫자가 모두 등장하는 계단 수의 개수, 0으로 시작하는 수는 아님
 * 
 * N <= 100
 * 
 * f(1) = 0,
 * 9876543210
 * f(10) = 1
 * 
 * dp[i][j][k] = dp[i - 1][j - 1] + dp[i - 1][j + 1] 에서 옴.
 * i번째 자리, j 숫자, k 방문 셋
 */

public class Main {
    public static void main(String[] args) throws Exception {
        final int SIZE = 1 << 10, MOD = 1_000_000_000;
        int N = readInt();
        int[][][] dp = new int[N][10][SIZE];

        for (int i = 1; i < 10; i++)
            dp[0][i][1 << i] = 1;

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < SIZE; k++) {
                    if (j > 0)
                        dp[i][j][k | (1 << j)] += dp[i - 1][j - 1][k];
                    if (j < 9)
                        dp[i][j][k | (1 << j)] += dp[i - 1][j + 1][k];
                    dp[i][j][k | (1 << j)] %= MOD;
                }
            }
        }

        int res = 0;
        for (int i = 0; i < 10; i++) {
            res += dp[N - 1][i][1023];
            res %= MOD;
        }
        System.out.println(res);
    }

    static int readInt() throws Exception {
        int c;
        while ((c = System.in.read()) <= 32)
            ;
        boolean negative = c == 45;
        if (negative)
            c = System.in.read();
        int n = c & 15;
        while ((c = System.in.read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return negative ? -n : n;
    }
}