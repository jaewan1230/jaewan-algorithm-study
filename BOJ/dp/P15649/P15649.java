import java.io.*;
import java.util.StringTokenizer;

public class P15649 {
    static int N, M;
    static int[] res = new int[9];
    static boolean[] used = new boolean[9];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        func(1);
        bw.flush();
        bw.close();
    }

    static void func(int k) throws IOException { // k번째를 채우는 함수
        if (k > M) {
            for (int i = 1; i <= M; i++) {
                bw.write(res[i] + " ");
            }
            bw.write("\n");
            return;
        }
        for (int i = 1; i <= N; i++) {
            if (!used[i]) {
                used[i] = true;
                res[k] = i;
                func(k + 1);
                used[i] = false;
            }
        }
    }
}