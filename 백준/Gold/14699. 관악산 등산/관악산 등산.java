/*
 * 쉼터 높이 입력받고,
 * 길 입력받으면서 높은 방향으로 단방향 그래프 구성
 * 
 * 높이 순으로 정렬해서, 높은 순서대로, 자식 중 최댓값 + 1 이 현재 쉼터의 최댓값
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    static int N, M;
    static 쉼터[] list;
    static Node[] graph;

    public static void main(String[] args) throws IOException {
        N = readInt();
        M = readInt();
        list = new 쉼터[N + 1];

        graph = new Node[N + 1];
        for (int i = 1; i <= N; i++) {
            list[i] = new 쉼터(i, readInt());
            graph[i] = new Node();
        }
        for (int i = 0; i < M; i++) {
            int u = readInt(), v = readInt();

            if (list[u].height < list[v].height)
                graph[u].next.add(v);
            else
                graph[v].next.add(u);
        }
        Arrays.sort(list, 1, N + 1);

        int[] dp = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            int max = 0;
            for (int child : graph[list[i].idx].next) {
                max = Math.max(max, dp[child]);
            }

            dp[list[i].idx] = 1 + max;
        }

        for (int i = 1; i <= N; i++) {
            System.out.println(dp[i]);
        }
    }

    static class Node {
        ArrayList<Integer> next = new ArrayList<>();
    }

    static class 쉼터 implements Comparable<쉼터> {
        int idx, height;

        public 쉼터(int idx, int height) {
            this.idx = idx;
            this.height = height;
        }

        @Override
        public int compareTo(Main.쉼터 o) {
            return o.height - this.height;
        }

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
        while ((c = read()) <= 32)
            ;
        int n = c & 15;
        while ((c = read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}