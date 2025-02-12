import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Math.max;

public class P2616 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N, M, t, first, second, third, max = 0, v;
        int[] sum = new int[50001];
        int[][] dp = new int[4][50001];
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        sum[0] = 0;
        for (int i = 1; i <= N; i++) {
            t = Integer.parseInt(st.nextToken());
            sum[i] = sum[i - 1] + t;
        }
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= 3; i++) {
            for (int j = i * M; j <= N; j++) {
                dp[i][j] = max(dp[i][j - 1], dp[i - 1][j - M] + sum[j] - sum[j - M]);
            }
        }
        System.out.println(dp[3][N]);
    }
}