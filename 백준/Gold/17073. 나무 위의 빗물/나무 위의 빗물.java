/*
BOJ 17073 나무 위의 빗물

트리의 1번 정점에 W만큼의 물이 고여 있음. 1번 정점을 제외하고 물이 고여 있지 않은 상태.

물을 가지고 있으며, 자식 정점이 있으면 자식 정점 중 하나를 골라 물을 1 준다.
자식 정점이 여러 개면 동일한 확률로 그 중 하나를 고른다.

만약 부모 정점이 자신에게 물을 흘려보냈다면 받아서 쌓아 둔다.

영훈이는 나무를 바라보면서 더 이상 물이 움직이지 않는 상태가 되면 각 정점에 어느 정도의 물이 있게 될지 궁금.
i번 정점에 쌓인 물의 양의 기댓값을 Pi, Pi가0보다 큰 정점들에 대해 Pi들의 평균은 어느 정도가 될까???

---

물은 아래로 계속 흘러서 leaf 노드에만 모이게 된다.
총량은 변하지 않는다. 따라서 leaf 노드의 개수로 나누면 된다.
1번 정점에서 탐색해서 leaf 노드의 개수를 세면 된다.
dfs 탐색하면 시간복잡도는 O(N)이다.

dfs 안 돌고, 엣지 갯수로 leaf 노드인지 판별이 가능함.

*/

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    static int N, W;
    static int[] edgeCnt;

    public static void main(String[] args) throws IOException {
        N = readInt();
        W = readInt();

        int leafCnt = 0;
        edgeCnt = new int[N + 1];
        for (int i = 0; i < N - 1; i++) {
            int u = readInt();
            int v = readInt();
            edgeCnt[u]++;
            edgeCnt[v]++;
        }

        for (int i = 2; i <= N; i++) 
            if (edgeCnt[i] == 1) leafCnt++;

        System.out.println((double) W / leafCnt);
    }

    static class Node {
        ArrayList<Integer> link = new ArrayList<>();
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
