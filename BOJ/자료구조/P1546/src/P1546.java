import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1546 {
    public static void main(String[] args) throws IOException {
        int t, n, max = Integer.MIN_VALUE, sum = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            t = Integer.parseInt(st.nextToken());
            if (max < t) max = t;
            sum += t;
        }
        System.out.println(100.0 / max * sum / n);
    }
}