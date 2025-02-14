import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P5427 {
    static int[][] map;
    static boolean[][] visit;
    static boolean[][] fire;
    static int TC, w, h, startX, startY;
    static Queue<int[]> q;
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        TC = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < TC; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            map = new int[h][w];
            fire = new boolean[h][w];
            q = new LinkedList<int[]>();
            for (int i = 0; i < h; i++) {
                String str = br.readLine().toString();
                for (int j = 0; j < w; j++) {
                    if (str.charAt(j) == '#') map[i][j] = -1;
                    if (str.charAt(j) == '.') map[i][j] = 0;
                    if (str.charAt(j) == '@') {
                        map[i][j] = 0;
                        startY = i;
                        startX = j;
                    }
                    if (str.charAt(j) == '*') {
                        map[i][j] = 0;
                        fire[i][j] = true;
                        q.offer(new int[]{i, j});
                    }
                }
            }
            BFS_fire();
            int res = BFS();
            System.out.println(res == -1 ? "IMPOSSIBLE" : res);
        }
    }

    public static void BFS_fire() {
        while (!q.isEmpty()) {
            int[] now = q.poll();
            for (int i = 0; i < 4; i++) {
                int ty = now[0] + dy[i];
                int tx = now[1] + dx[i];
                if (ty < 0 || tx < 0 || ty >= h || tx >= w || fire[ty][tx] || map[ty][tx] == -1) continue;
                fire[ty][tx] = true;
                map[ty][tx] = map[now[0]][now[1]] + 1;
                q.offer(new int[]{ty, tx});
            }
        }
    }

    static int BFS() {
        if (startY == 0 || startX == 0 || startY == h - 1 || startX == w - 1) return 1;

        visit = new boolean[h][w];
        q = new LinkedList<int[]>();
        q.offer(new int[]{startY, startX});
        visit[startY][startX] = true;
        map[startY][startX] = 0;

        while (!q.isEmpty()) {
            int[] now = q.poll();
            for (int i = 0; i < 4; i++) {
                int ty = now[0] + dy[i];
                int tx = now[1] + dx[i];
                if (ty < 0 || tx < 0 || ty >= h || tx >= w || visit[ty][tx] || map[ty][tx] == -1) continue;
                if (fire[ty][tx] && (map[ty][tx] <= map[now[0]][now[1]] + 1)) continue;
                visit[ty][tx] = true;
                map[ty][tx] = map[now[0]][now[1]] + 1;
                q.offer(new int[]{ty, tx});
                if (ty == 0 || tx == 0 || ty == h - 1 || tx == w - 1) return map[ty][tx] + 1;
            }
        }
        return -1;
    }
}