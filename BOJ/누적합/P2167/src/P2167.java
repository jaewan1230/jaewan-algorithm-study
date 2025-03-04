import java.util.Scanner;

public class P2167 {
    public static void main(String[] args) {
        int N, M, K;
        int[][] arr = new int[301][301];
        int[][] sum = new int[301][301];
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        for (int i = 1; i <= N; i++)
            for (int j = 1; j <= M; j++) sum[i][j] = 0; /* 누적 합 배열 초기화 */
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                arr[i][j] = sc.nextInt();
                sum[i][j] = sum[i][j - 1] + sum[i - 1][j] - sum[i - 1][j - 1] + arr[i][j];
            }
        }
        K = sc.nextInt();
        int i, j, x, y;
        for (int i1 = 0; i1 < K; i1++) {
            i = sc.nextInt();
            j = sc.nextInt();
            x = sc.nextInt();
            y = sc.nextInt();
            System.out.println(sum[x][y]-sum[x][j-1]-sum[i-1][y]+sum[i-1][j-1]);
        }
    }
}