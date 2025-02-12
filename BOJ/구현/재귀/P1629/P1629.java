import java.util.Scanner;

public class P1629 {
    static int A, B, C;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        A = sc.nextInt();
        B = sc.nextInt();
        C = sc.nextInt();
        System.out.println(fun(A, B, C));
    }

    static long fun(int A, int B, int C) {
        if (B == 1) return A % C;
        long val = fun(A, B / 2, C);
        val = val * val % C;
        if (B % 2 == 0) return val;
        return val = val * A % C;
    }
}