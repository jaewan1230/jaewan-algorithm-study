import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P15665 {
    static int N, M;
    static int[] res = new int[9], in_arr = new int[9];
    static boolean[] is_used = new boolean[9];
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

    static void func(int k) throws IOException { // res[k] 를 결정
        int prev = -1;
        if (k == M) {
            for (int i = 0; i < M; i++) {
                bw.write(in_arr[res[i]] + " ");
            }
            bw.newLine();
            return;
        }
        for (int i = 0; i < N; i++) {
            if (in_arr[i] == prev) continue;
            res[k] = i;
            prev = in_arr[i];
            func(k + 1);
        }
    }
}