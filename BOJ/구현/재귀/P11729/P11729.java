import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class P11729 {
    static int N;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1 << 25);

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        System.out.println((towerOfHanoi(N, 1, 3)));
        bw.flush();
        bw.close();
    }

    static int towerOfHanoi(int n, int a, int b) throws IOException {
        if (n == 1) {
            bw.write(a + " " + b + "\n");
            return 1;
        }
        int val1 = towerOfHanoi(n - 1, a, 6 - a - b);
        bw.write(a + " " + b + "\n");
        int val2 = towerOfHanoi(n - 1, 6 - a - b, b);
        return val1 + val2 + 1;
    }
}