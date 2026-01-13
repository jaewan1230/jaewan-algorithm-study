/*
N 개의 요리 학원

M개의 양방향 길
i번째 길에는 T[i]일에만 문을 여는 디저트 노점
매일 디저트 먹어야 함.
N - 1 개의 길만 기억하게 됨.
키위새 쓰러지는 날이 d일차, d의 최댓값

---

MST.
모든 요리 학원에 다닐 수 있도록 N - 1 개의 길을 선택.
T[i] != T[j] 니까 d 최댓값 체크하며 Kruskal.
 */

import java.io.IOException;
import java.util.PriorityQueue;

public class Main {
    static int N, M;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        N = readInt();
        M = readInt();
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++)
            parent[i] = i;
        PriorityQueue<Road> pq = new PriorityQueue<>();
        for (int i = 0; i < M; i++)
            pq.offer(new Road(readInt(), readInt(), readInt()));

        int res = 1, cnt = 0;
        while (cnt < N - 1) {
            Road r = pq.poll();
            if (union(r.u, r.v)) {
                if (res == r.t)
                    res++;
                else
                    break;
                cnt++;
            }
        }
        System.out.println(res);
    }

    public static boolean union(int u, int v) {
        int rootU = find(u), rootV = find(v);
        if (rootU != rootV) {
            parent[rootU] = rootV;
            return true;
        }
        return false;
    }

    public static int find(int x) {
        if (parent[x] != x)
            parent[x] = find(parent[x]);
        return parent[x];
    }

    public static class Road implements Comparable<Road> {
        int u, v, t;

        public Road(int u, int v, int t) {
            this.u = u;
            this.v = v;
            this.t = t;
        }

        @Override
        public int compareTo(Road o) {
            return t - o.t;
        }
    }

    public static int pos, len;
    public static byte[] buf = new byte[8192];

    public static byte read() throws IOException {
        if (pos == len) {
            len = System.in.read(buf);
            pos = 0;
        }
        return buf[pos++];
    }

    public static int readInt() throws IOException {
        int c;
        while ((c = read()) <= 32)
            ;
        int n = c & 15;
        while ((c = read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}
