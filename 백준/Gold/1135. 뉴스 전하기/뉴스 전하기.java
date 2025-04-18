/*
 * 모든 노드는 자기 직속 부하들에게 전부 전화를 해야 함.
 * 결국 리프 노드가 연락을 가장 마지막에 받게 됨.
 * 
 * 자기가 연락 받은 시간 + 자식 수
 * 연락 받은 시간이 depth인가? 했는데 그건 또 아님.
 * 
 * 서브 트리로 나눠서 트리 dp 상향식으로 접근하면
 * 자식이 없는 리프 노드의 dp 값은 0

 * 자식이 있으면 dp 값은 
 * > 자식을 dp값 큰 순서대로(sub tree의 전파 완료 시간이 오래 걸리는 순서대로) 먼저 전파함.
 * > dp[자식] + 시간 max 값을 부모 노드의 dp 값으로 저장.
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
    static int N;
    static int[] dp;
    static Node[] tree;

    public static void main(String[] args) throws IOException {
        N = readInt();
        tree = new Node[N];
        tree[0] = new Node();
        readInt();
        for (int i = 1; i < N; i++) {
            tree[i] = new Node();
            tree[readInt()].child.add(i);
        }

        dp = new int[N];
        for (int i = N - 1; i >= 0; i--) {
            PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
            for (int child : tree[i].child)
                pq.offer(dp[child]);

            int time = 1;
            while (!pq.isEmpty())
                dp[i] = Math.max(dp[i], time++ + pq.poll());
        }

        System.out.println(dp[0]);
    }

    static class Node {
        ArrayList<Integer> child = new ArrayList<>();
    }

    static int readInt() throws IOException {
        int c;
        while ((c = System.in.read()) <= 32)
            ;
        boolean negative = c == 45;
        if (negative)
            c = System.in.read();
        int n = c & 15;
        while ((c = System.in.read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return negative ? -n : n;
    }
}