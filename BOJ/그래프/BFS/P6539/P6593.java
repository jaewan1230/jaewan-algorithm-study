import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P6593 {
    static int L, R, C, startX, startY, startZ, exitX, exitY, exitZ, res;
    static int[][][] map;
    static boolean[][][] visit;
    static int[] dz = {1, -1, 0, 0, 0, 0};
    static int[] dy = {0, 0, 1, -1, 0, 0};
    static int[] dx = {0, 0, 0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            if (L == 0 && R == 0 && C == 0) break;
            map = new int[L][R][C];
            visit = new boolean[L][R][C];
            for (int k = 0; k < L; k++) {
                for (int i = 0; i < R; i++) {
                    String str = br.readLine().toString();
                    for (int j = 0; j < C; j++) input(k, i, j, str.charAt(j));
                }
                br.readLine();
            }

            if (!BFS()) System.out.println("Trapped!");
            else System.out.println("Escaped in " + map[exitZ][exitY][exitX] + " minute(s).");
        }
    }

    static void input(int k, int i, int j, char c) {
        if (c == 'S') {
            startY = i;
            startX = j;
            startZ = k;
            map[k][i][j] = 0;
        }
        if (c == 'E') {
            exitY = i;
            exitX = j;
            exitZ = k;
            map[k][i][j] = 0;
        }
        if (c == '.') map[k][i][j] = 0;
        if (c == '#') map[k][i][j] = -1;
    }

    static boolean BFS() {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{startZ, startY, startX});
        visit[startZ][startY][startX] = true;
        while (!q.isEmpty()) {
            int[] now = q.poll();
            for (int i = 0; i < 6; i++) {
                int tz = now[0] + dz[i];
                int ty = now[1] + dy[i];
                int tx = now[2] + dx[i];
                if (tz < 0 || ty < 0 || tx < 0 || tz >= L || ty >= R || tx >= C) continue;
                if (map[tz][ty][tx] == -1 || visit[tz][ty][tx]) continue;
                visit[tz][ty][tx] = true;
                map[tz][ty][tx] = map[now[0]][now[1]][now[2]] + 1;
                q.offer(new int[]{tz, ty, tx});
                if (tz == exitZ && ty == exitY && tx == exitX) return true;
            }
        }
        return false;
    }
}