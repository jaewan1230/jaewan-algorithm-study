/*
 * 여우는 일정한 속도 달려감.
 * 늑대는 두개 빠르게, 두 배 느리게 번갈아 감.
 * 여우가 늑대보다 먼저 도착할 수 있는 그루터기가 몇 개 있는지.
 * 
 * 여우가 각 그루터기까지 걸리는 시간,
 * 늑대의 홀수번째 이동하는 시간, 짝수번째 이동하는 시간을 구함.
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
    static int N, M;
    static Node[] graph;

    public static void main(String[] args) throws IOException {
        N = readInt();
        M = readInt();
        graph = new Node[N + 1];
        for (int i = 1; i < N + 1; i++)
            graph[i] = new Node();

        // 인접 리스트 입력
        for (int i = 0; i < M; i++) {
            int a = readInt(), b = readInt(), d = readInt();
            graph[a].link.add(new Edge(b, d << 1));
            graph[b].link.add(new Edge(a, d << 1));
        }

        // 거리 배열 선언 및 초기화
        int[] distFox = new int[N + 1];
        int[][] distWolf = new int[2][N + 1];
        Arrays.fill(distFox, Integer.MAX_VALUE);
        Arrays.fill(distWolf[0], Integer.MAX_VALUE);
        Arrays.fill(distWolf[1], Integer.MAX_VALUE);

        // 여우 다익스트라 먼저 진행해 각 그루터기까지 걸리는 최소 시간을 구함.
        PriorityQueue<Data> pq = new PriorityQueue<>();
        distFox[1] = 0;
        pq.offer(new Data(0, 1, distFox[1]));
        while (!pq.isEmpty()) {
            Data cur = pq.poll();

            if (cur.dist > distFox[cur.n])
                continue;

            for (Edge next : graph[cur.n].link) {
                if (distFox[next.n] <= distFox[cur.n] + next.weight)
                    continue;
                distFox[next.n] = distFox[cur.n] + next.weight;
                pq.offer(new Data(0, next.n, distFox[next.n]));
            }
        }

        // 늑대 다익스트라 진행
        pq = new PriorityQueue<>();
        distWolf[0][1] = 0; // 타입, 번호
        pq.offer(new Data(0, 1, distWolf[0][1]));
        while (!pq.isEmpty()) {
            Data cur = pq.poll();

            int nextType = cur.type ^ 1;
            if (cur.dist > distWolf[cur.type][cur.n])
                continue;

            for (Edge next : graph[cur.n].link) {
                int nextDist = cur.dist +
                        ((nextType & 1) == 1 ? next.weight >> 1 : next.weight << 1);
                if (distWolf[nextType][next.n] <= nextDist)
                    continue;
                distWolf[nextType][next.n] = nextDist;
                pq.offer(new Data(nextType, next.n, nextDist));
            }
        }

        int cnt = 0;
        for (int i = 1; i < N + 1; i++)
            if (distFox[i] < Math.min(distWolf[0][i], distWolf[1][i]))
                cnt++;
        System.out.println(cnt);
    }

    static class Data implements Comparable<Data> {
        int type, n, dist;

        public Data(int type, int n, int dist) {
            this.type = type;
            this.n = n;
            this.dist = dist;
        }

        @Override
        public int compareTo(Data d) {
            return dist - d.dist;
        }

    }

    static class Edge {
        int n, weight;

        public Edge(int n, int weight) {
            this.n = n;
            this.weight = weight;
        }

    }

    static class Node {
        ArrayList<Edge> link = new ArrayList<>();
    }

    static int readInt() throws IOException {
        int c;
        while ((c = System.in.read()) <= 32)
            ;
        int n = c & 15;
        while ((c = System.in.read()) > 47) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }
}