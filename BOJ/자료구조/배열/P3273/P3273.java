import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P3273 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()), target;
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(br.readLine());
        Arrays.sort(arr);
        int l = 0, r = n - 1, cnt = 0;
        while (l < r) {
            if (arr[l] + arr[r] == target) {
                cnt++;
                l++;
                r--;
            }
            if (arr[l] + arr[r] < target) {
                l++;
            }
            if (arr[l] + arr[r] > target) {
                r--;
            }
        }
        System.out.println(cnt);
    }
}