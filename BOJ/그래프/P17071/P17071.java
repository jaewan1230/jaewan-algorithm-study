import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class P17071 {
    static int N, K;
    static int[][] map = new int[500001][2];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputs[] = br.readLine().split(" ");
        N = Integer.parseInt(inputs[0]);
        K = Integer.parseInt(inputs[1]);
        BFS();
        System.out.println(findBrother());
    }

    static int findBrother() {
        int time = 1, now = K;
        while (now <= 500000) {
            if (map[now][(time - 1) % 2] <= time) return time - 1;
            now += time++;
        }
        return -1;
    }

    static void BFS() {
        Pair now;
        int next;
        map[N][0] = 1;
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(N, 1));
        while (!queue.isEmpty()) {
            now = queue.poll();
            next = now.index + 1;
            if ((0 <= next) && (next <= 500000) && (map[next][now.time % 2] == 0)) {
                map[next][now.time % 2] = now.time + 1;
                queue.offer(new Pair(next, now.time + 1));
            }
            next = now.index - 1;
            if ((0 <= next) && (next <= 500000) && (map[next][now.time % 2] == 0)) {
                map[next][now.time % 2] = now.time + 1;
                queue.offer(new Pair(next, now.time + 1));
            }
            next = now.index * 2;
            if ((0 <= next) && (next <= 500000) && (map[next][now.time % 2] == 0)) {
                map[next][now.time % 2] = now.time + 1;
                queue.offer(new Pair(next, now.time + 1));
            }
        }
    }

    static class Pair {
        int index, time;

        public Pair(int index, int time) {
            this.index = index;
            this.time = time;
        }
    }
}