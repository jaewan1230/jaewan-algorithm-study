/* 
 * Floyd-Warshall 로 다른 정점들과 비교가 가능한지, 즉 연결되어 있는지 확인
 * i, j, k에 대해, i k 연결, k j 연결돼 있으면, i j 도 연결됨.
 * 
 * 이후 count 
 */

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws Exception {
        int N = readInt(), M = readInt();
        boolean[][] adj = new boolean[N + 1][N + 1];

        for (int i = 0; i < M; i++) {
            int u = readInt(), v = readInt();
            adj[u][v] = true;
        }

        for (int k = 1; k <= N; k++)
            for (int i = 1; i <= N; i++)
                for (int j = 1; j <= N; j++)
                    if (adj[i][k] & adj[k][j])
                        adj[i][j] = true;

        int res = 0, cnt;
        for (int i = 1; i <= N; i++) {
            cnt = 0;
            for (int j = 1; j <= N; j++) {
                if (adj[i][j] | adj[j][i])
                    cnt++;
            }
            if (cnt == N - 1)
                res++;
        }
        System.out.println(res);
    }

    static int pos, len;
    static byte[] buf = new byte[8192];

    static byte read() throws IOException {
        if (pos == len) {
            len = System.in.read(buf);
            pos = 0;
        }
        return buf[pos++];
    }

    static int readInt() throws Exception {
        int c;
        while ((c = read()) <= 32)
            ;
        boolean negative = c == 45;
        if (negative)
            c = read();
        int n = c & 15;
        while ((c = read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return negative ? -n : n;
    }
}