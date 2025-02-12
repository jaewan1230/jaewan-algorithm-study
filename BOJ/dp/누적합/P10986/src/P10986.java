import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P10986 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N, M, sum = 0, t;
        long res = 0;
        int cnt[] = new int[1000];
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < 1000; i++) cnt[i] = 0;
        cnt[0] = 1;
        for (int i = 1; i <= N; i++) {
            t = Integer.parseInt(st.nextToken());
            sum = (sum + t) % M;
            res += cnt[sum]++;
        }
        System.out.println(res);
    }
}