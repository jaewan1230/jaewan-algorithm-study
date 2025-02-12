import java.util.Scanner;
public class P13300 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), k = sc.nextInt(), res = 0;
        int[][] student = new int[2][7];
        for (int i = 0; i < n; i++) {
            student[sc.nextInt()][sc.nextInt()]++;
        }
        for (int i = 0; i < 2; i++) {
            for (int j = 1; j <= 6; j++) {
                res += student[i][j] / k;
                if (student[i][j] % k > 0) res++;
            }
        }
        System.out.println(res);
    }
}