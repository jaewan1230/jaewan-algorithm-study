/* 
 * N개의 도시와 M개의 도로
 * K개의 도시는 좀비 점령
 * 
 * 1번에서 안전한 N번 도시로 피난
 * 점령당한 도시로부터 S 거리 이하면 위험한 도시
 * 
 * 점령당한 도시는 숙박 불가
 * 안전한 도시 숙박비 p
 * 위험한 도시 숙박비 q
 * 
 * 도시 1부터 N으로 이동하는 데 드는 최소 비용
 * 
 * 2 <= N <= 100,000
 * 1 <= M <= 200,000
 * 0 <= K <= N - 2
 * 0 <= S <= 100,000
 * 
 * 도로 양방향
 * 1번에서 N번으로 항상 이동 가능
 * 
 * ---
 * 
 * 1. 위험한 도시 정의 (BFS)
 * 2. 가중치 그래프에서 최소 비용 계산 (다익스트라)
 */

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
    static int N, M, K, S, p, q;
    static boolean[] isInfected, isUnsafe;
    static Node[] graph;

    public static void main(String[] args) throws IOException {
        N = readInt();
        M = readInt();
        K = readInt();
        S = readInt();
        p = readInt();
        q = readInt();

        graph = new Node[N + 1];
        isInfected = new boolean[N + 1];
        isUnsafe = new boolean[N + 1];
        for (int i = 1; i <= N; i++)
            graph[i] = new Node();

        ArrayDeque<int[]> queue = new ArrayDeque<>();
        boolean[] visit = new boolean[N + 1];
        for (int i = 0; i < K; i++) {
            int t = readInt();
            isInfected[t] = true;
            visit[t] = true;
            queue.offer(new int[] { t, 1 });
        }

        for (int i = 0; i < M; i++) {
            int u = readInt(), v = readInt();
            graph[u].next.add(v);
            graph[v].next.add(u);
        }

        // BFS 로 위험한 도시 판별
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (Integer next : graph[cur[0]].next) {
                if (visit[next] || cur[1] > S)
                    continue;
                isUnsafe[next] = true;
                visit[next] = true;
                queue.offer(new int[] { next, cur[1] + 1 });
            }
        }

        // 다익스트라로 1에서 시작해 N 까지 최소 비용 계산
        // dist[i] : i 번째 도시까지 가기 위한 최소 비용
        long[] dist = new long[N + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[1] = 0;

        PriorityQueue<Data> pq = new PriorityQueue<>();
        pq.offer(new Data(1, 0));
        while (!pq.isEmpty()) {
            Data cur = pq.poll();

            // visit 처리 ??
            for (Integer next : graph[cur.n].next) {
                long nextDist = dist[cur.n] + (isUnsafe[next] ? q : p);
                // 감염된 도시로는 못감 || 가격이 더 안싸지면 안감
                if (isInfected[next] || dist[next] <= nextDist)
                    continue;

                if (next == N) {
                    System.out.println(cur.dist);
                    System.exit(0);
                }
                dist[next] = nextDist;
                pq.offer(new Data(next, nextDist));
            }
        }
    }

    static class Data implements Comparable<Data> {
        int n;
        long dist;

        public Data(int n, long dist) {
            this.n = n;
            this.dist = dist;
        }

        @Override
        public int compareTo(Main.Data o) {
            return Long.compare(dist, o.dist);
        }

    }

    static class Node {
        ArrayList<Integer> next = new ArrayList<>();
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
        int c;
        while ((c = read()) <= 32);
        int n = c & 15;
        while ((c = read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}