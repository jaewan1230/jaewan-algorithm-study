/* 
 * tree-dp
 * 
 * dp[i][0] = i 노드를 루트 노드로 하는 서브 트리의 최소 얼리 어답터 수(0은 i 가 얼리 어답터가 아닐 때, 1이면 i 가 얼리 어답터일 때)
 * dp[i][0] 은 자식 노드들의 dp [j][1] 값들의 합
 * 
 * dp[i][1] 은, 루트 노드가 얼리 어답터 이므로, 자식 노드들 dp[j][0], dp[j][1] 값 중 작은 값 들의 합.
 * 
 */

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    static boolean[] check;
    static Node[] tree;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        int N = readInt();
        tree = new Node[N + 1];
        for (int i = 1; i <= N; i++)
            tree[i] = new Node();

        int u, v;
        for (int i = 1; i < N; i++) {
            u = readInt();
            v = readInt();
            tree[u].link.add(v);
            tree[v].link.add(u);
        }

        dp = new int[N + 1][2];
        check = new boolean[N + 1];
        check[1] = true;
        dfs(1);
        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    static void dfs(int cur) {
        for (int next : tree[cur].link) {
            if (check[next])
                continue;
            check[next] = true;
            dfs(next);
            dp[cur][0] += dp[next][1];
            dp[cur][1] += Math.min(dp[next][0], dp[next][1]);
        }
        dp[cur][1] += 1;
    }

    static class Node {
        ArrayList<Integer> link = new ArrayList<>();
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