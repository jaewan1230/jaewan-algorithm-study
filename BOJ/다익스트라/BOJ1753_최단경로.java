import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class BOJ1753_최단경로 {
    static int V, E;
    static int[] dist;
    static Node[] graph;
    static final int MAX = 999999;

    public static void main(String[] args) throws IOException {
        V = readInt();
        E = readInt();
        graph = new Node[V + 1];
        for (int i = 1; i <= V; i++)
            graph[i] = new Node();
        dist = new int[V + 1];
        Arrays.fill(dist, MAX);
        int start = readInt();
        for (int i = 0; i < E; i++) {
            int u = readInt(), v = readInt(), weight = readInt();
            graph[u].link.add(new Data(v, weight));
        }

        PriorityQueue<Data> pq = new PriorityQueue<>();
        dist[start] = 0;
        pq.offer(new Data(start, 0));
        while (!pq.isEmpty()) {
            Data cur = pq.poll();
            if (dist[cur.n] < cur.dist)
                continue;
            for (Data next : graph[cur.n].link) {
                int nextWeight = cur.dist + next.dist;
                // 값이 갱신되면 pq에 추가
                if (nextWeight < dist[next.n]) {
                    dist[next.n] = nextWeight;
                    pq.offer(new Data(next.n, nextWeight));
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= V; i++)
            sb.append(dist[i] == MAX ? "INF" : dist[i]).append('\n');
        System.out.println(sb.toString());
    }

    static class Data implements Comparable<Data> {
        int n, dist;

        public Data(int n, int dist) {
            this.n = n;
            this.dist = dist;
        }

        @Override
        public int compareTo(Data d) {
            return dist - d.dist;
        }

    }

    static class Node {
        ArrayList<Data> link = new ArrayList<>();
    }

    static int readInt() throws IOException {
        int c;
        do {
            c = System.in.read();
        } while (c <= 32);
        int n = c & 15;
        c = System.in.read();
        while (c > 47) {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();
        }
        return n;
    }
}