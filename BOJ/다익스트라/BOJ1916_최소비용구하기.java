import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class BOJ1916_최소비용구하기 {
    static int N, M;
    static int[] dist;
    static Node[] graph;

    public static void main(String[] args) throws IOException {
        N = readInt();
        M = readInt();
        graph = new Node[N + 1];
        for (int i = 1; i <= N; i++)
            graph[i] = new Node();
        dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        for (int i = 0; i < M; i++) {
            int u = readInt(), v = readInt(), weight = readInt();
            graph[u].link.add(new Pair(v, weight));
        }

        int start = readInt(), end = readInt();
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.offer(new Pair(start, 0));
        dist[start] = 0;
        while (!pq.isEmpty()) {
            Pair cur = pq.poll();
            if (dist[cur.n] < cur.weight)
                continue;

            // 조기 종료
            if (cur.n == end)
                break;
            for (Pair next : graph[cur.n].link) {
                int nextWeight = cur.weight + next.weight;
                if (dist[next.n] <= nextWeight)
                    continue;
                dist[next.n] = nextWeight;
                pq.offer(new Pair(next.n, nextWeight));
            }
        }

        System.out.println(dist[end]);
    }

    static class Node {
        ArrayList<Pair> link = new ArrayList<>();
    }

    static class Pair implements Comparable<Pair> {
        int n, weight;

        @Override
        public int compareTo(Pair p) {
            return weight - p.weight;
        }

        public Pair(int n, int weight) {
            this.n = n;
            this.weight = weight;
        }
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
