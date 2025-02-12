import java.util.Scanner;

public class P2839 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        boolean flag = true;
        for (int i = 0; i <= n / 3; i++) {
            if ((n - 3 * i) % 5 == 0) {
                System.out.println(i + (n - 3 * i) / 5);
                flag = false;
                break;
            }
        }
        if (flag) System.out.println(-1);
    }
}