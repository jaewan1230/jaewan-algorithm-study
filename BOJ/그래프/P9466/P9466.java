import java.io.*;
import java.util.StringTokenizer;

public class P9466 {
    static int T, n, cnt;
    static int[] choose;
    static boolean[] visit, check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            cnt = 0;
            n = Integer.parseInt(br.readLine());
            choose = new int[n + 1];
            check = new boolean[n + 1];
            visit = new boolean[n + 1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                choose[i] = Integer.parseInt(st.nextToken());
                if (choose[i] == i) {
                    check[i] = true;
                    cnt++;
                }
            }

            for (int i = 1; i <= n; i++) {
                dfs(i);
            }
            bw.write(n - cnt + "\n");
        }
        bw.flush();
    }

    static void dfs(int s) {
        if (check[s]) return;
        visit[s] = true;
        if (!visit[choose[s]] && !check[choose[s]]) dfs(choose[s]);

        if (!check[choose[s]]) { // 첫 발견 체인
            int next = choose[s];
            cnt++;
            while (next != s) {
                next = choose[next];
                cnt++;
            }
        }
        check[s] = true;
    }
}