import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1253 {
    public static void main(String[] args) throws IOException {
        int N, i, j, cnt = 0;
        int[] arr = new int[2000];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(arr, 0, N);
        for (int k = 0; k < N; k++) {
            i = 0;
            j = N - 1;
            while (i < j) {
                if (i == k) {
                    i++;
                    continue;
                }
                if (j == k) {
                    j--;
                    continue;
                }
                if (arr[i] + arr[j] < arr[k]) {
                    i++;
                } else if (arr[i] + arr[j] > arr[k]) j--;
                else if (arr[i] + arr[j] == arr[k]) {
                    cnt++;
                    break;
                }
            }
        }
        System.out.println(cnt);
        br.close();
    }
}