/*
 * 위상 정렬로 사이클 판단.
 * 모든 Node 에 visit 안했으면 "IMPOSSIBLE"
 * 
 * 모든 노드 간에 상대적인 정보가 있으므로 ? 가 출력되는 경우는 없을듯
 * 
 */

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.HashSet;

public class Main {
    static Node[] tree;

    public static void main(String[] args) throws IOException {
        int T = readInt();
        for (int t = 0; t < T; t++) {
            StringBuilder sb = new StringBuilder();
            int n = readInt();
            tree = new Node[n + 1];
            for (int i = 1; i <= n; i++)
                tree[i] = new Node();

            int[] arr = new int[n], inDegree = new int[n + 1];
            for (int i = 0; i < n; i++) {
                arr[i] = readInt();
                for (int j = 0; j < i; j++) {
                    tree[arr[j]].link.add(arr[i]);
                    inDegree[arr[i]]++;
                }
            }

            int m = readInt();
            for (int i = 0; i < m; i++) {
                int a = readInt(), b = readInt();
                if (tree[a].link.contains(b)) {
                    tree[a].link.remove(b);
                    inDegree[b]--;
                    tree[b].link.add(a);
                    inDegree[a]++;
                } else {
                    tree[b].link.remove(a);
                    inDegree[a]--;
                    tree[a].link.add(b);
                    inDegree[b]++;
                }
            }

            ArrayDeque<Integer> q = new ArrayDeque<>();
            for (int i = 1; i <= n; i++)
                if (inDegree[i] == 0) {
                    q.offer(i);
                    sb.append(i).append(' ');
                }

            boolean[] visit = new boolean[n + 1];
            while (!q.isEmpty()) {
                int cur = q.poll();
                visit[cur] = true;
                for (int next : tree[cur].link) {
                    if (--inDegree[next] == 0) {
                        q.offer(next);
                        sb.append(next).append(' ');
                    }
                }
            }

            boolean flag = false;
            for (int i = 1; i <= n; i++) {
                if (!visit[i])
                    flag = true;
            }
            System.out.println(flag ? "IMPOSSIBLE" : sb);
        }
    }

    static class Node {
        HashSet<Integer> link = new HashSet<>();
    }

    static int readInt() throws IOException {
        int c;
        while ((c = System.in.read()) <= 32)
            ;
        int n = c & 15;
        while ((c = System.in.read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}