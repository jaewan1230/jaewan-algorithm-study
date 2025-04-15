/*
 * N개의 정수 수열.
 * K라운드, 라운드별 명령
 * 1. 변경. 수열의 한 값을 바꿈
 * 2. 곱셈: i, j 말하면 X[i] x X[i+1] x ... x X[j-1] x X[j]가 양수인지, 음수인지, 0인지 대답
 * 
 * 세그트리 활용
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N, K;
        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(line);
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            tree = new int[N << 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                int t = Integer.parseInt(st.nextToken());
                if (t > 0)
                    tree[i + N] = 1;
                else if (t < 0)
                    tree[i + N] = -1;
            }

            for (int i = N - 1; i > 0; i--)
                tree[i] = tree[i << 1] * tree[(i << 1) + 1];

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                switch (st.nextToken().charAt(0)) {
                    case 'C':
                        int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
                        // a번째 수를 b로 변경
                        a += N - 1;
                        if (b > 0)
                            b = 1;
                        else if (b < 0)
                            b = -1;
                        else
                            b = 0;
                        tree[a] = b;
                        a /= 2;
                        while (a > 0) {
                            tree[a] = tree[a << 1] * tree[(a << 1) + 1];
                            a /= 2;
                        }
                        break;
                    case 'P':
                        int l = Integer.parseInt(st.nextToken()), r = Integer.parseInt(st.nextToken()), res = 1;
                        l += N - 1;
                        r += N - 1;
                        while (l <= r) {
                            if ((l & 1) != 0)
                                res *= tree[l++];
                            if ((r & 1) == 0)
                                res *= tree[r--];
                            l >>= 1;
                            r >>= 1;
                        }
                        if (res == 0)
                            sb.append(res);
                        else
                            sb.append(res > 0 ? '+' : '-');
                        break;
                }
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }
}