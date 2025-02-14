import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class P10026 {
    static char[][] map;
    static boolean[][] visited;
    static int N, cnt;
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            String arrStr = br.readLine().toString();
            for (int j = 0; j < N; j++) {
                map[i][j] = arrStr.charAt(j);
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    BFS(i, j);
                    cnt++;
                }
            }
        }
        System.out.printf("%d ", cnt);

        cnt = 0;
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    BFS_RG(i, j);
                    cnt++;
                }
            }
        }
        System.out.println(cnt);
    }

    static void BFS(int y, int x) {
        Queue<int[]> q = new LinkedList<int[]>();
        q.offer(new int[]{y, x});
        visited[y][x] = true;
        while (!q.isEmpty()) {
            int[] node = q.poll();
            for (int i = 0; i < 4; i++) {
                int ty = node[0] + dy[i];
                int tx = node[1] + dx[i];
                if (ty < 0 || tx < 0 || ty >= N || tx >= N) continue;
                if (visited[ty][tx] || map[ty][tx] != map[node[0]][node[1]]) continue;
                visited[ty][tx] = true;
                q.offer(new int[]{ty, tx});
            }
        }
    }

    static void BFS_RG(int y, int x) {
        Queue<int[]> q = new LinkedList<int[]>();
        q.offer(new int[]{y, x});
        visited[y][x] = true;
        while (!q.isEmpty()) {
            int[] node = q.poll();
            for (int i = 0; i < 4; i++) {
                int ty = node[0] + dy[i];
                int tx = node[1] + dx[i];
                if (ty < 0 || tx < 0 || ty >= N || tx >= N) continue;
                if (visited[ty][tx] || ((map[ty][tx] == 'R' || map[ty][tx] == 'G') && map[node[0]][node[1]] == 'B') || (map[ty][tx] == 'B' && ((map[node[0]][node[1]] == 'R' || map[node[0]][node[1]] == 'G'))))
                    continue;
                visited[ty][tx] = true;
                q.offer(new int[]{ty, tx});
            }
        }
    }
}