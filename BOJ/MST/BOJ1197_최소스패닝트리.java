/*
 * N개의 문자열로 이루어진 집합 S
 * M개의 문자열 중 집합 S에 포함되어 있는 것이 총 몇 개인지
 * 길이는 500을 넘지 않음.
 */

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.PriorityQueue;

public class BOJ1197_최소스패닝트리 {
    static int[] parent, rank;
    static Node[] graph;
    static PriorityQueue<Edge> allEdges = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        int V = readInt(), E = readInt();
        graph = new Node[V + 1];
        parent = new int[V + 1];
        rank = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            graph[i] = new Node(i);
            parent[i] = i;
        }

        for (int i = 0; i < E; i++) {
            Edge e = new Edge(readInt(), readInt(), readInt());
            graph[e.u].link.add(e);
            graph[e.v].link.add(e);
            allEdges.add(e);
        }

        int sum = 0;
        while (!allEdges.isEmpty()) {
            Edge e = allEdges.poll();

            // 같은 그룹이면 넘어감
            if (find(e.u) == find(e.v))
                continue;

            sum += e.weight;
            union(e.u, e.v);
        }
        System.out.println(sum);
    }

    static int find(int v) {
        if (parent[v] != v)
            parent[v] = find(parent[v]); // 경로 압축
        return parent[v];
    }

    static void union(int u, int v) {
        int rootU = find(u);
        int rootV = find(v);

        if (rank[rootU] <= rank[rootV]) {
            parent[rootU] = rootV;
        } else {
            parent[rootV] = rootU;
        }
    }

    static class Edge implements Comparable<Edge> {
        int u, v, weight;

        public Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge e) {
            return Integer.compare(this.weight, e.weight);
        }
    }

    static class Node {
        int n;
        ArrayDeque<Edge> link = new ArrayDeque<>();

        Node(int n) {
            this.n = n;
        }
    }

    static int readInt() throws IOException {
        int c;
        do {
            c = System.in.read();
        } while (c <= 32);
        boolean negative = false;
        if (c == 45) {
            negative = true;
            c = System.in.read();
        }
        int n = c & 15;
        c = System.in.read();
        while (c > 47) {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();
        }
        return negative ? -n : n;
    }
}
