import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Queue;

public class P2583 {
    static int[][] map;
    static boolean[][] visit;
    static int M, N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visit = new boolean[N][M];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            fill(x1, y1, x2, y2);
        }

        PriorityQueue<Integer> res = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0 && !visit[i][j]) {
                    res.offer(BFS(i, j));
                }
            }
        }
        System.out.println(res.size());
        while (!res.isEmpty()) System.out.printf("%d ", res.poll());
    }

    static void fill(int x1, int y1, int x2, int y2) {
        for (int i = y1; i < y2; i++) {
            for (int j = x1; j < x2; j++) map[i][j] = 1;
        }
    }

    static int BFS(int y, int x) {
        int size = 1;
        map[y][x] = size;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{y, x});
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        while (!q.isEmpty()) {
            int[] now = q.poll();
            for (int i = 0; i < 4; i++) {
                int tx = now[1] + dx[i];
                int ty = now[0] + dy[i];
                if (tx < 0 || ty < 0 || ty >= N || tx >= M) continue;
                if (visit[ty][tx] || map[ty][tx] != 0) continue;
                visit[ty][tx] = true;
                map[ty][tx] = ++size;
                q.offer(new int[]{ty, tx});
            }
        }
        return size;
    }
}