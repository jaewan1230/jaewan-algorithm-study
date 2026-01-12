
/*
 * 도시 분할 계획
 * 마을은 N개의 집과 M개의 양방향 길
 * 
 * 임의의 두 집 사이 경로가 항상 존재
 * 마을을 분할할 때는 서로 연결되도록 분할해야 함.
 * 
 * 마을 안에 길이 너무 많아서 길을 모두 없애고 나머지 길의 유지비의 합을 최소로.
 * MST? 인데, 두 개로 분할하니까 N - 2 개만 선택하면,
 * 최소 비용으로 두 개로 분할됨.
 * 
 * N - 1 개와 1 개 or N - 2 개와 2개.
 * 
 * Kruskal Algorithm.
 */
import java.io.IOException;
import java.util.PriorityQueue;

public class Main {
    public static int[] parent;

    public static void main(String[] args) throws IOException {
        int N = readInt(), M = readInt();
        parent = new int[N + 1];
        PriorityQueue<Road> pq = new PriorityQueue<>();

        for (int i = 1; i <= N; i++)
            parent[i] = i;

        for (int i = 0; i < M; i++)
            pq.offer(new Road(readInt(), readInt(), readInt()));

        int cnt = 0, res = 0;
        while (cnt < N - 2) {
            Road cur = pq.poll();
            if (union(cur.u, cur.v)) {
                res += cur.weight;
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
        if (x != parent[x])
            parent[x] = find(parent[x]);

        return parent[x];
    }

    static class Road implements Comparable<Road> {
        int u, v, weight;

        public Road(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }

        @Override
        public int compareTo(Main.Road o) {
            return weight - o.weight;
        }
    }

    public static int pos, len;
    public static byte[] buf = new byte[8192];

    static byte read() throws IOException {
        if (pos == len) {
            len = System.in.read(buf);
            pos = 0;
        }
        return buf[pos++];
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