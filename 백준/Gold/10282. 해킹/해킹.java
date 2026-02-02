/*
해킹
컴퓨터 a 가 b에 의존하면, b가 감염되면 일정 시간 뒤 a도 감염됨.
b가 a를 의존하지 않는다면, a가 감염되어도 b는 안전.

해킹한 컴퓨터 번호와 의존성이 주어질 때, 총 몇 대의 컴퓨터가 감염되며 걸리는 시간은 얼마인지 구하기

---

dijkstra  문제.

도달 가능한 정점들의 개수와, 최대 시간.
구현 해보자. 다익스트라 오랜만이라 까먹은 거 같은데..

 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main {
    static int N, D, C;
    static int[] dist;
    static Node[] graph;

    public static void main(String[] args) throws IOException {
        int TC = readInt();
        StringBuilder sb = new StringBuilder();
        while (TC-- > 0) {
            N = readInt();
            D = readInt();
            C = readInt();

            dist = new int[N + 1];
            graph = new Node[N + 1];
            for (int i = 1; i <= N; i++) {
                dist[i] = Integer.MAX_VALUE;
                graph[i] = new Node();
            }

            for (int i = 0; i < D; i++) {
                int a = readInt(), b = readInt(), s = readInt();
                graph[b].link.add(new Edge(a, s));
            }

            PriorityQueue<Edge> pq = new PriorityQueue<>();
            dist[C] = 0;
            pq.add(new Edge(C, 0));
            while (!pq.isEmpty()) {
                Edge cur = pq.poll();
                if (dist[cur.to] < cur.weight)
                    continue;

                for (Edge next : graph[cur.to].link) {
                    if (dist[next.to] > dist[cur.to] + next.weight) {
                        dist[next.to] = dist[cur.to] + next.weight;
                        pq.add(new Edge(next.to, dist[next.to]));
                    }
                }
            }
            int count = 0;
            int maxTime = 0;
            for (int i = 1; i <= N; i++) {
                if (dist[i] != Integer.MAX_VALUE) {
                    count++;
                    maxTime = Math.max(maxTime, dist[i]);
                }
            }
            sb.append(count).append(" ").append(maxTime).append("\n");
        }
        System.out.print(sb);
    }

    static class Node {
        ArrayList<Edge> link = new ArrayList<>();
    }

    static class Edge implements Comparable<Edge> {
        int to, weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
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
