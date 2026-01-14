/*
나만 안되는 연애

사심 경로
1. 남초 대학교와 여초 대학교들을 연결하는 도로로만 구성
2. 어떤 대학교에서든 모든 대학교로 이동이 가능한 경로
3. 최단 거리

MST 인데 유형이 다른 노드간의 간선만 선택
- 입력 단계에서 제외하기
 */

import java.io.IOException;
import java.util.PriorityQueue;

public class Main {
    static int N, M;
    static boolean[] isMale;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        N = readInt();
        M = readInt();
        parent = new int[N + 1];
        isMale = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            int t = read();
            read();
            isMale[i] = t == 'M';
            parent[i] = i;
        }

        PriorityQueue<Road> pq = new PriorityQueue<>();
        for (int i = 0; i < M; i++) {
            int u = readInt(), v = readInt(), d = readInt();
            if (isMale[u] != isMale[v])
                pq.offer(new Road(u, v, d));
        }

        int res = 0, cnt = 0;
        while (!pq.isEmpty() && cnt < N - 1) {
            Road r = pq.poll();
            if (union(r.u, r.v)) {
                res += r.d;
                cnt++;
            }
        }
        System.out.println(cnt == N - 1 ? res : -1);
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
        int u, v, d;

        public Road(int u, int v, int d) {
            this.u = u;
            this.v = v;
            this.d = d;
        }

        @Override
        public int compareTo(Road o) {
            return this.d - o.d;
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
        int c = read(), n = c & 15;
        while ((c = read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}
