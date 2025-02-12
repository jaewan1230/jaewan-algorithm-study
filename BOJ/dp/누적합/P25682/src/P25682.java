import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P25682 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N, M, K;
        String s;
        char t;
        int sumB[][] = new int[2001][2001];
        int sumW[][] = new int[2001][2001];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2001; i++) sumB[i][0] = sumB[0][i] = sumW[i][0] = sumW[0][i] = 0;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= N; i++) {
            s = br.readLine();
            for (int j = 1; j <= M; j++) {
                t = s.charAt(j - 1);
                if ((i + j) % 2 == 1) {
                    sumB[i][j] = sumB[i - 1][j] + sumB[i][j - 1] - sumB[i - 1][j - 1] + ((t == 'B') ? 1 : 0);
                    sumW[i][j] = sumW[i - 1][j] + sumW[i][j - 1] - sumW[i - 1][j - 1] + ((t == 'W') ? 1 : 0);
                } else {
                    sumB[i][j] = sumB[i - 1][j] + sumB[i][j - 1] - sumB[i - 1][j - 1] + ((t == 'W') ? 1 : 0);
                    sumW[i][j] = sumW[i - 1][j] + sumW[i][j - 1] - sumW[i - 1][j - 1] + ((t == 'B') ? 1 : 0);
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = K; i <= N; i++) {
            for (int j = K; j <= M; j++) {
                if (sumB[i][j] - sumB[i - K][j] - sumB[i][j - K] + sumB[i - K][j - K] < min)
                    min = sumB[i][j] - sumB[i - K][j] - sumB[i][j - K] + sumB[i - K][j - K];
                if (sumW[i][j] - sumW[i - K][j] - sumW[i][j - K] + sumW[i - K][j - K] < min)
                    min = sumW[i][j] - sumW[i - K][j] - sumW[i][j - K] + sumW[i - K][j - K];
            }
        }
        System.out.println(min);
    }
}