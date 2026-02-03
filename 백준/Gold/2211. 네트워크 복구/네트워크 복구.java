/*
네트워크 복구

보안 시스템이 한 대의 슈퍼컴퓨터에만 설치.
1. 최소 개수의 회선만 복구. 서로 다른 두 컴퓨터 간 통신이 가능해야 함.
2. 슈퍼컴퓨터가 다른 컴퓨터들과 통신하는데 걸리는 최소 시간이, 원래 네트워크 통신에서 걸리는 최소 시간보다 커져선 안 됨.

---

dijkstra 로, 1번 정점에서 다른 모든 정점으로의 최단 거리 구하기

최초 네트워크에서 구한 최단 거리와, 복구된 네트워크에서 구한 최단 거리가 같아야 함.

다익스트라로 정점 하나씩 추가할 때마다 해당 노드에서 연결된 노드들의 최단 거리를 갱신.
단, 기존 네트워크의 최단 거리보다 커지면 갱신하지 않음.

*/

import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main {
    static int N, M;
    static int[] prevDist, dist;
    static Node[] graph;

    public static void main(String[] args) throws IOException {
        N = readInt();
        M = readInt();

        graph = new Node[N + 1];
        for (int i = 1; i <= N; i++)
            graph[i] = new Node();

        for (int i = 0; i < M; i++) {
            int a = readInt();
            int b = readInt();
            int w = readInt();

            graph[a].link.add(new Edge(b, w));
            graph[b].link.add(new Edge(a, w));
        }

        prevDist = new int[N + 1];
        prevDist = dijkstra(1);

        dist = new int[N + 1];
        for (int i = 1; i <= N; i++)
            dist[i] = Integer.MAX_VALUE;
        dist[1] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(1, 0));

        ArrayList<int[]> result = new ArrayList<>();

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            if (dist[cur.to] < cur.weight)
                continue;

            for (Edge next : graph[cur.to].link) {
                int newDist = dist[cur.to] + next.weight;
                if (newDist < dist[next.to] && newDist <= prevDist[next.to]) {
                    dist[next.to] = newDist;
                    result.add(new int[] { cur.to, next.to });
                    pq.add(new Edge(next.to, newDist));
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(result.size()).append('\n');
        for (int[] r : result)
            sb.append(r[0]).append(' ').append(r[1]).append('\n');
        System.out.print(sb);
    }

    static int[] dijkstra(int start) {
        for (int i = 1; i <= N; i++)
            prevDist[i] = Integer.MAX_VALUE;
        prevDist[start] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            if (prevDist[cur.to] < cur.weight)
                continue;

            for (Edge next : graph[cur.to].link) {
                int newDist = prevDist[cur.to] + next.weight;
                if (newDist < prevDist[next.to]) {
                    prevDist[next.to] = newDist;
                    pq.add(new Edge(next.to, newDist));
                }
            }
        }

        return prevDist;
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
