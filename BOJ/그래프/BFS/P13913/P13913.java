import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class P13913 {
    static int N, K;
    static int[] history;
    static boolean[] visit;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);
        visit = new boolean[100001];
        history = new int[100001];
        BFS();
    }

    static void BFS() {
        int now, next;
        Queue<Integer> q = new LinkedList<>();
        history[N] = -1;
        visit[N] = true;
        q.offer(N);
        while (!q.isEmpty()) {
            now = q.poll();
            if (now == K) {
                Stack<Integer> res = new Stack<>();
                while (now != -1) {
                    res.push(now);
                    now = history[now];
                }
                System.out.println(res.size() - 1);
                while (!res.isEmpty()) {
                    System.out.printf("%d ", res.pop());
                }
                return;
            }
            next = now * 2;
            if (0 <= next && next <= 100000) {
                if (!visit[next]) {
                    visit[next] = true;
                    history[next] = now;
                    q.offer(next);
                }
            }
            next = now + 1;
            if (0 <= next && next <= 100000) {
                if (!visit[next]) {
                    visit[next] = true;
                    history[next] = now;
                    q.offer(next);
                }
            }
            next = now - 1;
            if (0 <= next && next <= 100000) {
                if (!visit[next]) {
                    visit[next] = true;
                    history[next] = now;
                    q.offer(next);
                }
            }

        }
    }
}