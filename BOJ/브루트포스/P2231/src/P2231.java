import java.util.Scanner;

public class P2231 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        boolean flag = true;

        for (int i = 1; i <= N; i++) {
            int t = i, sum = i;
            while (t > 0) {
                sum += t % 10;
                t /= 10;
            }
            if (sum == N) {
                System.out.println(i);
                flag = false;
                break;
            }
        }
        if (flag) System.out.println(0);
    }
}