import java.util.Scanner;

public class P2003 {
    public static void main(String[] args) {
        int N, M, cnt = 0;
        int A[] = new int[10001];
        int sum[] = new int[10001];
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        sum[0] = 0;
        for (int i = 1; i <= N; i++) {
            A[i] = sc.nextInt();
            sum[i] = sum[i - 1] + A[i];
        }
        for (int i = 1; i <= N; i++) {
            if (sum[i] >= M) {
                for (int j = 0; j < i; j++) {
                    if (sum[i] - sum[j] == M) {
                        cnt++;
                        break;
                    }
                }
            }
        }
        System.out.println(cnt);
    }
}