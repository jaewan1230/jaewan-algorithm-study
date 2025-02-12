import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P2018 {
    public static void main(String[] args) throws IOException {
        int N, start = 1, end = 1, cnt = 0;
        long sum = 1;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        while (end != N) {
            if (sum < N) sum += ++end;
            else if (sum > N) sum -= start++;
            else if (sum == N) {
                sum += ++end;
                sum -= start++;
                cnt++;
            }
        }
        System.out.println(cnt + 1);
    }
}