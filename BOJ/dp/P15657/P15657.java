import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P15657 {
    static int N, M;
    static int[] in_arr = new int[8], res_arr = new int[8];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            in_arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(in_arr, 0, N);
        for (int i = 0; i < N; i++) {
            res_arr[0] = i;
            func(1);
        }
        bw.close();
    }

    static void func(int k) throws IOException {
        if (k == M) {
            for (int i = 0; i < M; i++) {
                bw.write(in_arr[res_arr[i]] + " ");
            }
            bw.newLine();
            return;
        }
        for (int i = res_arr[k - 1]; i < N; i++) {
            res_arr[k] = i;
            func(k + 1);
        }
    }
}