import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P3020 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N, H, t, res, min_cnt = 0;
        long min = Long.MAX_VALUE;
        long[] sum = new long[500002];
        long[] suk = new long[500002];
        long[] jong = new long[500002];
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        for (int i = 0; i <= 500001; i++) suk[i] = jong[i] = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            t = Integer.parseInt(st.nextToken());
            if (i % 2 == 0) suk[t]++; /* 석순 */
            if (i % 2 == 1) jong[t]++; /* 종유석 */
        }
        for (int i = H; i >= 1; i--) {
            suk[i] += suk[i + 1];
            jong[i] += jong[i + 1];
        }
        for (int i = 1; i <= H; i++) {
            sum[i] = suk[i] + jong[H - i + 1];
            if (sum[i] < min) min = sum[i];
        }
        for (int i = 1; i <= H; i++) {
            if (min == sum[i]) min_cnt++;
        }
        System.out.printf("%d %d\n", min, min_cnt);
    }
}