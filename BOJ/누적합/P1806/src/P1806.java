import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1806 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N, S, min = 999999;
        int[] sum = new int[100001];
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        sum[0] = 0;
        for (int i = 1; i <= N; i++) sum[i] = sum[i - 1] + Integer.parseInt(st.nextToken());
        int start = 0, end;
        for (end = 1; end <= N; end++) {
            while (start < end && sum[end] - sum[start] >= S) {
                if (sum[end] - sum[start] >= S) if (end - start < min) min = end - start;
                start++;
            }
        }
        System.out.println(min == 999999 ? 0 : min);
    }
}