import java.util.Scanner;

import static java.lang.Math.pow;

public class P1074 {
    static int N,r,c;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N=sc.nextInt();
        r=sc.nextInt();
        c=sc.nextInt();
        System.out.println(z(N,r,c));
    }
    static int z(int n,int r,int c) {
        if (n == 1) {
            return r * 2 + c;
        }
        int t = (int) pow(2, 2 * n - 2);
        if (r < pow(2, n - 1)) {
            if (c < pow(2, n - 1)) {
                return z(n - 1, r, c);
            } else {
                return t + z(n - 1, r, c - (int) pow(2, n - 1));
            }
        } else {
            if (c < pow(2, n - 1)) {
                return 2 * t + z(n - 1, (int) (r - pow(2, n - 1)), c);
            } else {
                return 3 * t + z(n - 1, r - (int) pow(2, n - 1), c - (int) pow(2, n - 1));
            }
        }
    }
}