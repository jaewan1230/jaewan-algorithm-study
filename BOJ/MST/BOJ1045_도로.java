/*
 * 0부터 N-1까지 번호 매겨져 있는 N개의 도시와 두 도시를 연결하는 도로
 * 도로에 우선순위가 있다. A < B 가 도로 x로 연결, C와 D가 도로 y 로 연결되어 있을 때
 * (A, B) < (C, D) 면 x > y 즉, x의 우선순위가 더 높다.
 * 사전식 순서로 우선순위.
 * 그리디 하게 우선순위가 가장 높은 도로를 선택해도 되는 이유.
 * 어차피 연결 가능하다면 우선순위가 가장 높은 도로를 선택하는 게 맞다.
 * 안되면 애초에 불가능한 경우임.
 * 
 * 이미 연결된 도시라면 선택 안해도 되지만 M이 커서 선택해야 된다면 연결이 완료되어도 선택해야함.
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BOJ1045_도로 {
    static int N, M;
    static int[] parent, rank;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        PriorityQueue<Integer> edges = new PriorityQueue<>();
        PriorityQueue<Integer> unused = new PriorityQueue<>();
        parent = new int[N * N];
        rank = new int[N * N];
        for (int i = 0; i < N; i++) {
            String inStr = br.readLine();
            for (int j = i + 1; j < N; j++) {
                int idx = i * N + j;
                parent[idx] = idx;
                if (inStr.charAt(j) == 'Y')
                    edges.add(idx);
            }
        }
        int[] res = new int[N];
        while (!edges.isEmpty()) {
            int idx = edges.poll(), u = idx / N, v = idx % N;
            if (find(u) == find(v)) {
                unused.offer(idx);
                continue;
            }
            union(u, v);
            res[u]++;
            res[v]++;
            if (--M == 0)
                break;
        }

        boolean flag = true;
        int root = find(0);
        for (int i = 1; i < N; i++) {
            if (find(i) != root) {
                flag = false;
                break;
            }
        }
        if (flag) {
            while (!unused.isEmpty() && M-- > 0) {
                int idx = unused.poll();
                int u = idx / N, v = idx % N;
                res[u]++;
                res[v]++;
            }

            if (M > 0)
                System.out.println(-1);
            else
                for (int t : res)
                    System.out.printf("%d ", t);
        } else {
            System.out.println(-1);
        }
    }

    static int find(int x) {
        if (x != parent[x])
            parent[x] = find(parent[x]);
        return parent[x];
    }

    static void union(int u, int v) {
        int rootU = find(u), rootV = find(v);
        if (rootU != rootV) {
            if (rank[rootU] > rank[rootV]) {
                parent[rootV] = rootU;
                rank[rootU]++;
            } else {
                parent[rootU] = rootV;
                rank[rootV]++;
            }
        }
    }
}