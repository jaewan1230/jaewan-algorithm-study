
// 49628 KB, 1404 ms
/*
 * Union-Find -> 같은 그룹일 때 주종 역전인 경우 처리 
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

public class Main {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] in = br.readLine().split(" ");
        int N = Integer.parseInt(in[0]), M = Integer.parseInt(in[1]);

        parent = new int[N];
        String[] names = new String[N];
        for (int i = 0; i < N; i++) {
            names[i] = br.readLine();
            parent[i] = i;
        }

        Arrays.sort(names);
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++)
            map.put(names[i], i);
        for (int i = 0; i < M; i++) {
            in = br.readLine().split(",");
            int u = map.get(in[0]), v = map.get(in[1]), w = Integer.parseInt(in[2]);
            switch (w) {
                case 1:
                    union(u, v);
                    break;
                case 2:
                    union(v, u);
            }
        }

        int cnt = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            if (parent[i] == i) {
                sb.append(names[i]).append('\n');
                cnt++;
            }
        }
        System.out.println(cnt);
        System.out.print(sb);
    }

    static int find(int x) {
        if (x != parent[x])
            parent[x] = find(parent[x]);
        return parent[x];
    }

    // u 가 승리
    static void union(int u, int v) {
        int rootU = find(u), rootV = find(v);
        // 같은 그룹이었으면
        if (rootU == rootV) {
            parent[u] = u;
            parent[v] = u;
        } else
            parent[rootV] = rootU;
    }
}