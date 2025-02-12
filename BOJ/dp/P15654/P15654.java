import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P15654 {
    static int N, M;
    static int[] arr = new int[9], res = new int[9];
    static boolean[] isused = new boolean[9];
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
        func(0);
        bw.close();
    }

    static void func(int k) throws IOException {
        if (k == M) {
            for (int i = 0; i < M; i++) {
                bw.write(res[i] + " ");
            }
            bw.newLine();
            return;
        }
        for (int i = 0; i < N; i++) {
            if (isused[i]) continue;
            res[k] = arr[i];
            isused[i] = true;
            func(k + 1);
            isused[i] = false;
        }
    }
}