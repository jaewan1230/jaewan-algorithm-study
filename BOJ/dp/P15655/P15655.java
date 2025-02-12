import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P15655 {
    static int N, M;
    static int[] arr = new int[9], res = new int[9];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr, 0, N);
        for (int i = 0; i < N; i++) {
            res[0] = i;
            func(1);
        }
        bw.close();
    }

    static void func(int k) throws IOException {
        if (k == M) {
            for (int i = 0; i < M; i++) {
                bw.write(arr[res[i]] + " ");
            }
            bw.newLine();
            return;
        }
        for (int i = res[k - 1] + 1; i < N; i++) {
            res[k] = i;
            func(k + 1);
        }
    }
}