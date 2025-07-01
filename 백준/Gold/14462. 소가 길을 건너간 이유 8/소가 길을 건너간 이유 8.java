import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        int N = readInt();
        int[] arr1 = new int[N + 1], arr2 = new int[N + 1];
        for (int i = 1; i <= N; i++)
            arr1[i] = readInt();
        for (int i = 1; i <= N; i++)
            arr2[i] = readInt();

        int[][] dp = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (Math.abs(arr1[i] - arr2[j]) <= 4)
                    dp[i][j] = dp[i - 1][j - 1] + 1;
            }
        }
        System.out.println(dp[N][N]);
    }

    static int readInt() throws IOException {
        int c;
        while ((c = System.in.read()) <= 32)
            ;
        int n = c & 15;
        while ((c = System.in.read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}