/*
 * 트리 구조. dfs 돌면서 지지대 중 depth 가 작은 것 2개의 depth를 구하면 끝.
 * 답은 N - 1 - depth1 - depth2
 * 
 * 2개의 지지대와 연결되어야 하기 때문.
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main {
    static int N, S, P, res;
    static boolean[] visit;
    static Node[] tree;
    static PriorityQueue<Integer> depthPQ = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        N = readInt();
        S = readInt();
        P = readInt();

        res = N - 1;

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

        visit = new boolean[N + 1];
        visit[P] = true;

        DFS(P, 0);
        System.out.println(res - depthPQ.poll() - depthPQ.poll());
    }

    static void DFS(int n, int depth) {
        // 지지대일때
        if (n <= S)
            depthPQ.add(depth);

        for (int next : tree[n].link) {
            if (visit[next])
                continue;
            visit[next] = true;
            DFS(next, depth + 1);
        }
    }

    static class Node {
        ArrayList<Integer> link = new ArrayList<>();
    }

    static int readInt() throws IOException {
        int c = read();
        int n = c & 15;
        while ((c = read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }

    static int pos, len;
    static byte[] buf = new byte[8192];

    static int read() throws IOException {
        if (pos == len) {
            len = System.in.read(buf);
            pos = 0;
        }
        return buf[pos++];
    }
}