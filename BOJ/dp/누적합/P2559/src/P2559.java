import java.util.Scanner;

public class P2559 {
    public static void main(String[] args) {
        int N, K, max = -999;
        int arr[] = new int[100001];
        int sum[] = new int[100001];
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        sum[0] = 0;
        for (int i = 1; i <= N; i++) {
            arr[i] = sc.nextInt();
            sum[i] = sum[i - 1] + arr[i];
        }
        for (int i = K; i <= N; i++)
            if (max < sum[i] - sum[i - K]) max = sum[i] - sum[i - K];
        System.out.println(max);
    }
}