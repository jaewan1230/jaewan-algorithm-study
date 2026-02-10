/*
공항

공항에는 G개의 게이트가 있으며, 각각은 1에서 G까지 번호를 가지고 있음
공항에는 P개의 비행기가 순서대로 도착, i번째 비행기를 1번부터 g 번째 게이트중 하나에 영구적으로 도킹.
비행기가 어느 게이트에도 도킹할 수 없다면 공항이 폐쇄, 이후 어떤 비행기도 도착할 수 없다.

가장 많은 비행기를 도킹시키면 몇 대?
---
G <= 10^5
P <= 10^5

비행기를 가능한 높은 번호의 게이트에 도킹시켜야 함.
각 게이트에 현재 도킹 가능한 가장 높은 번호의 게이트를 저장.

gate[i] = i 이하인 도킹 가능한 가장 높은 번호의 게이트
비행기를 도킹시킬 때마다 union-find로 갱신.
union(a, a-1): a 게이트가 도킹 불가능해졌으므로 a-1 게이트와 합침.

*/

import java.io.IOException;

public class Main {
    static int G, P;
    static int[] gate;

    public static void main(String[] args) throws IOException {
        G = readInt();
        P = readInt();
        gate = new int[G + 1];

        for (int i = 1; i <= G; i++)
            gate[i] = i;

        int count = 0;
        for (int i = 0; i < P; i++) {
            int target = readInt();
            int root = find(target);
            if (root == 0)
                break;
            count++;
            union(root, root - 1);
        }
        System.out.println(count);
    }

    public static int find(int x) {
        if (gate[x] != x)
            gate[x] = find(gate[x]);
        return gate[x];
    }

    public static void union(int u, int v) {
        int RootU = find(u), RootV = find(v);
        if (RootU != RootV)
            gate[RootU] = RootV;
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
