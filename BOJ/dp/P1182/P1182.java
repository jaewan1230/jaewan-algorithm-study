import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1182 {
    static int N, S, sum, cnt;
    static int[] arr = new int[20];
    static boolean[] is_used = new boolean[20];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            sum += arr[i];
            func(i);
            sum -= arr[i];
        }
        System.out.println(cnt);
    }

    static void func(int k) { // arr[k]를 부분집합의 원소로 선택한 경우
        if (sum == S) cnt++;
        for (int i = k + 1; i < N; i++) {
            sum += arr[i];
            func(i);
            sum -= arr[i];
        }
    }
}