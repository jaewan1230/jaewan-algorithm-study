/*
 * 선자 씨 빵상 외치도록 MST 를 만들라는 것 같음.
 * 아 근데 왜 소수점 나오게 하냐. 귀찮네
 * 
 * 크루스칼 알고리즘 구현
 */

import java.io.IOException;
import java.util.PriorityQueue;

public class Main {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        int N = readInt(), M = readInt();
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++)
            parent[i] = i;

        long[][] vertex = new long[N + 1][2];
        for (int i = 1; i <= N; i++) {
            vertex[i][0] = readInt();
            vertex[i][1] = readInt();
        }

        for (int i = 0; i < M; i++)
            union(readInt(), readInt());

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j < i; j++) {
                if (find(i) == find(j))
                    continue;
                pq.add(new Edge(i, j,
                        Math.sqrt((vertex[i][0] - vertex[j][0]) * (vertex[i][0] - vertex[j][0]) +
                                (vertex[i][1] - vertex[j][1]) * (vertex[i][1] - vertex[j][1]))));
            }
        }

        double res = 0;
        while (!pq.isEmpty()) {
            Edge e = pq.poll();
            if (find(e.u) == find(e.v))
                continue;

            res += e.w;
            union(e.u, e.v);
        }

        System.out.printf("%.2f", res);
    }

    static class Edge implements Comparable<Edge> {
        int u, v;
        double w;

        public Edge(int u, int v, double w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Main.Edge o) {
            return w - o.w > 0 ? 1 : -1;
        }

    }

    static int find(int x) {
        if (x != parent[x])
            parent[x] = find(parent[x]);
        return parent[x];
    }

    static void union(int u, int v) {
        int rootU = find(u), rootV = find(v);
        if (rootU != rootV) {
            parent[rootU] = rootV;
        }
    }

    static int readInt() throws IOException {
        int c;
        while ((c = System.in.read()) <= 32)
            ;
        int n = c & 15;
        while ((c = System.in.read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}