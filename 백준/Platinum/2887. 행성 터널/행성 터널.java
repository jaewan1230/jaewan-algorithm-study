/*
 * N개의 행성(N <= 100,000)
 * 3차원 MST.
 * N개 행성 입력받고, 모든 간선을 만들면..? N^2인데 ??
 * 그런데 터널 건설 비용이 min(|xA-xB|, |yA-yB|, |zA-zB|) 니까
 * 좌표 하나씩만 보면 되네.
 * x, y, z 좌표랑 index 로 정렬하고, 크루스칼 3번 하고,
 * 그걸로 한번 총 4번.
 */

import java.io.IOException;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        int N = readInt();
        Data[] posX = new Data[N], posY = new Data[N], posZ = new Data[N];

        parent = new int[N];
        for (int i = 0; i < N; i++) {
            posX[i] = new Data(readInt(), i);
            posY[i] = new Data(readInt(), i);
            posZ[i] = new Data(readInt(), i);
            parent[i] = i;
        }
        Arrays.sort(posX);
        Arrays.sort(posY);
        Arrays.sort(posZ);

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int i = 1; i < N; i++) {
            pq.offer(new Edge(posX[i].num - posX[i - 1].num, posX[i].idx, posX[i - 1].idx));
            pq.offer(new Edge(posY[i].num - posY[i - 1].num, posY[i].idx, posY[i - 1].idx));
            pq.offer(new Edge(posZ[i].num - posZ[i - 1].num, posZ[i].idx, posZ[i - 1].idx));
        }

        int cnt = 0, res = 0;
        while (!pq.isEmpty()) {
            if (cnt == N - 1)
                break;
            Edge e = pq.poll();
            if (union(e.u, e.v)) {
                res += e.w;
                cnt++;
            }
        }

        System.out.println(res);
    }

    static boolean union(int u, int v) {
        int rootU = find(u), rootV = find(v);
        if (rootU != rootV) {
            parent[rootU] = rootV;
            return true;
        }
        return false;
    }

    static int find(int x) {
        if (x != parent[x]) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    static class Edge implements Comparable<Edge> {
        int w, u, v;

        public Edge(int w, int u, int v) {
            this.w = w;
            this.u = u;
            this.v = v;
        }

        @Override
        public int compareTo(Main.Edge o) {
            return w - o.w;
        }

    }

    static class Data implements Comparable<Data> {
        int num, idx;

        public Data(int num, int idx) {
            this.num = num;
            this.idx = idx;
        }

        @Override
        public int compareTo(Main.Data o) {
            return num - o.num;
        }

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

    static int readInt() throws IOException {
        int c = read();
        boolean negative = c == 45;
        if (negative)
            c = read();
        int n = c & 15;
        while ((c = read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return negative ? -n : n;
    }
}