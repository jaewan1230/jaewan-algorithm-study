import java.util.Scanner;

public class P11659 {
    public static void main(String[] args) {
        int N, M;
        int arr[] = new int[100001];
        int sum[] = new int[100001];
        sum[0] = 0;
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        for (int i = 1; i <= N; i++) {
            arr[i] = sc.nextInt();
            sum[i] = sum[i - 1] + arr[i];
        }
        for (int i1 = 0; i1 < M; i1++) {
            int i, j;
            i = sc.nextInt();
            j = sc.nextInt();
            System.out.println(sum[j] - sum[i - 1]);
        }
    }
}