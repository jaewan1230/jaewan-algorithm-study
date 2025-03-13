import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class BOJ16118_달빛여우 {
    static int N, M;
    static int[] distFox;
    static int[][] distWolf;
    static Node[] graph;

    public static void main(String[] args) throws IOException {
        N = readInt();
        M = readInt();
        distFox = new int[N + 1];
        Arrays.fill(distFox, Integer.MAX_VALUE);
        distWolf = new int[2][N + 1];
        Arrays.fill(distWolf[0], Integer.MAX_VALUE);
        Arrays.fill(distWolf[1], Integer.MAX_VALUE);
        graph = new Node[N + 1];
        for (int i = 1; i <= N; i++)
            graph[i] = new Node();
        for (int i = 0; i < M; i++) {
            int u = readInt(), v = readInt(), weight = readInt();
            graph[u].link.add(new Data(v, 0, weight << 1));
            graph[v].link.add(new Data(u, 0, weight << 1));
        }

        // 여우 다익스트라
        PriorityQueue<Data> pq = new PriorityQueue<>();
        pq.offer(new Data(1, 0, 0));
        distFox[1] = 0;
        while (!pq.isEmpty()) {
            Data cur = pq.poll();
            if (distFox[cur.n] < cur.weight)
                continue;
            for (Data next : graph[cur.n].link) {
                int nextWeight = cur.weight + next.weight;
                // 갱신 안되면
                if (distFox[next.n] <= nextWeight)
                    continue;
                distFox[next.n] = nextWeight;
                pq.offer(new Data(next.n, 0, nextWeight));
            }
        }

        // 늑대 다익스트라
        // distWolf[0][i] 은 짝수 시간에 도달, distWolf[1][i] 은 홀수 시간에 도달
        // type = 0 이 짝수 시간.
        pq = new PriorityQueue<>();
        pq.offer(new Data(1, 0, 0));
        distWolf[0][1] = 0;
        while (!pq.isEmpty()) {
            Data cur = pq.poll();
            if (distWolf[cur.type][cur.n] < cur.weight)
                continue;
            for (Data next : graph[cur.n].link) {
                int nextWeight = cur.weight + ((cur.type & 1) == 0 ? next.weight >> 1 : next.weight << 1);
                int nextType = (cur.type & 1) ^ 1;
                if (distWolf[nextType][next.n] <= nextWeight)
                    continue;
                distWolf[nextType][next.n] = nextWeight;
                pq.offer(new Data(next.n, nextType, nextWeight));
            }
        }

        int cnt = 0;
        for (int i = 1; i <= N; i++) {
            // System.out.printf("여우:%d %d %d\n", distFox[i], distWolf[0][i],
            // distWolf[1][i]);
            if (distFox[i] < Math.min(distWolf[0][i], distWolf[1][i]))
                cnt++;
        }
        System.out.println(cnt);
    }

    static class Node {
        ArrayList<Data> link = new ArrayList<>();
    }

    static class Data implements Comparable<Data> {
        int n, type, weight;

        public Data(int n, int type, int weight) {
            this.n = n;
            this.type = type;
            this.weight = weight;
        }

        @Override
        public int compareTo(Data d) {
            return weight - d.weight;
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
