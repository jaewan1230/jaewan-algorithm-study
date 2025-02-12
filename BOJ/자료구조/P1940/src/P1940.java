import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1940 {
    public static void main(String[] args) throws IOException {
        int N, M, start, end, cnt = 0;
        int[] arr = new int[15000];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(arr, 0, N);
        start = 0;
        end = N - 1;
        while (start < end) {
            if (arr[start] + arr[end] == M) {
                start++;
                end--;
                cnt++;
            } else if (arr[start] + arr[end] < M) start++;
            else if (arr[start] + arr[end] > M) end--;
        }
        System.out.println(cnt);
    }
}