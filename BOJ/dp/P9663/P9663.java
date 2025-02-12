import java.io.*;

public class P9663 {
    static int N, cnt;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static boolean[] is_used1 = new boolean[14];
    static boolean[] is_used2 = new boolean[27];
    static boolean[] is_used3 = new boolean[27];

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        func(0);
        bw.write(cnt + "\n");
        bw.flush();
        bw.close();
    }

    static void func(int k) { // 0번쨰 행에 퀸을 놓는 함수
        if (k == N) {
            cnt++;
            return;
        }
        for (int i = 0; i < N; i++) { // i열에 퀸을 놓는다.
            if (is_used1[i]) continue;
            if (is_used2[k + i]) continue;
            if (is_used3[k - i + N - 1]) continue;
            is_used1[i] = true;
            is_used2[k + i] = true;
            is_used3[k - i + N - 1] = true;
            func(k + 1);
            is_used1[i] = false;
            is_used2[k + i] = false;
            is_used3[k - i + N - 1] = false;
        }
    }
}