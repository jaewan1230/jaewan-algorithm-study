import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class P15650 {
    static int N, M;
    static int[] arr = new int[8];
    static boolean[] isused = new boolean[8];
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        for (int i = 1; i <= N; i++) {
            arr[0] = i;
            func(1);
        }
        bw.flush();
        bw.close();
    }

    static void func(int k) throws IOException {
        if (k == M) {
            for (int i = 0; i < M; i++) bw.write(arr[i] + " ");
            bw.newLine();
            return;
        }
        for (int i = arr[k - 1] + 1; i <= N; i++) {
            arr[k] = i;
            func(k + 1);
        }
    }
}