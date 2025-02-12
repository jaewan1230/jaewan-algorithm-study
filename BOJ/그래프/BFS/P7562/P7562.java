import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P7562 {
    static int T, I, targetX, targetY;
    static int[][] map;
    static boolean[][] visit;
    static int[] dy = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] dx = {1, 2, 2, 1, -1, -2, -2, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            I = Integer.parseInt(br.readLine());
            map = new int[I][I];
            visit = new boolean[I][I];
            StringTokenizer st = new StringTokenizer(br.readLine());
            int startY = Integer.parseInt(st.nextToken());
            int startX = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            targetY = Integer.parseInt(st.nextToken());
            targetX = Integer.parseInt(st.nextToken());
            System.out.println(BFS(startY, startX));
        }
    }

    public static int BFS(int y, int x) {
        visit[y][x] = true;
        Queue<int[]> q = new LinkedList<int[]>();
        q.offer(new int[]{y, x});
        while (!q.isEmpty()) {
            int[] now = q.poll();
            for (int i = 0; i < 8; i++) {
                int ty = now[0] + dy[i];
                int tx = now[1] + dx[i];
                if (tx < 0 || ty < 0 || tx >= I || ty >= I) continue;
                if (visit[ty][tx]) continue;

                map[ty][tx] = map[now[0]][now[1]] + 1;
                if (ty == targetY && tx == targetX) return map[ty][tx];
                q.offer(new int[]{ty, tx});
                visit[ty][tx] = true;
            }
        }
        return 0;
    }
}