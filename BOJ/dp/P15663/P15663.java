import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P15663 {
    static int N, M;
    static int[] in_arr = new int[10001], res_arr = new int[8];
    static boolean[] is_used = new boolean[8];
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
        func(0);
        bw.close();
    }

    static void func(int k) throws IOException {
        if (k == M) {
            for (int i = 0; i < M; i++) {
                bw.write(res_arr[i] + " ");
            }
            bw.newLine();
            return;
        }
        int before = 0;
        for (int i = 0; i < N; i++) {
            if (is_used[i]) continue;
            if (before == in_arr[i]) continue;
            is_used[i] = true;
            before = in_arr[i];
            res_arr[k] = in_arr[i];
            func(k + 1);
            is_used[i] = false;
        }
    }
}