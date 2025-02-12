import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P7569 {
    static int N, M, H;
    static int[][][] tomato;
    static boolean[][][] visited;
    static int[] dz = {1, -1, 0, 0, 0, 0};
    static int[] dy = {0, 0, 0, 1, 0, -1};
    static int[] dx = {0, 0, 1, 0, -1, 0};
    static Queue<int[]> q = new LinkedList<int[]>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        tomato = new int[H][N][M];
        visited = new boolean[H][N][M];
        for (int k = 0; k < H; k++) {
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    tomato[k][i][j] = Integer.parseInt(st.nextToken());
                    if (tomato[k][i][j] == 1) {
                        visited[k][i][j] = true;
                        q.offer(new int[]{k, i, j});
                    }
                }
            }
        }
        System.out.println(BFS());
    }

    public static int BFS() {
        while (!q.isEmpty()) {
            int[] now = q.poll();
            for (int i = 0; i < 6; i++) {
                int tz = now[0] + dz[i];
                int ty = now[1] + dy[i];
                int tx = now[2] + dx[i];
                if (tz < 0 || ty < 0 || tx < 0 || tz >= H || ty >= N || tx >= M) continue;
                if (visited[tz][ty][tx] || tomato[tz][ty][tx] == -1) continue;
                tomato[tz][ty][tx] = tomato[now[0]][now[1]][now[2]] + 1;
                visited[tz][ty][tx] = true;
                q.offer(new int[]{tz, ty, tx});
            }
        }
        int max = -1;
        for (int k = 0; k < H; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (tomato[k][i][j] == 0) return -1;
                    if (max < tomato[k][i][j]) max = tomato[k][i][j];
                }
            }
        }
        return max - 1;
    }
}