import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class P3197 {
    static boolean waterChk;
    static int R, C;
    static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
    static int[][] map;
    static boolean[][] visit, visitWater;
    static Queue<int[]>[] queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input[] = br.readLine().split(" ");
        R = Integer.parseInt(input[0]);
        C = Integer.parseInt(input[1]);
        map = new int[R][C];
        visit = new boolean[R][C];
        visitWater = new boolean[R][C];
        queue = new Queue[3];
        for (int i = 0; i < 3; i++) queue[i] = new LinkedList<>();
        int t = 1;
        for (int i = 0; i < R; i++) {
            String inputStr = br.readLine().toString();
            for (int j = 0; j < C; j++) {
                if (inputStr.charAt(j) == 'X') map[i][j] = -1;
                if (inputStr.charAt(j) == 'L') {
                    map[i][j] = t;
                    visit[i][j] = true;
                    queue[t++].offer(new int[]{i, j});
                }
                if (inputStr.charAt(j) == '.') {
                    visitWater[i][j] = true;
                    queue[0].offer(new int[]{i, j});
                }
            }
        }
        bfs();
    }

    static void bfs() {
        int res = 0;
        while (!bfsFill()) res++;
        System.out.println(res);
    }

    static boolean bfsFill() {
        Queue<int[]> q;
        for (int queueNum = 1; queueNum < 3; queueNum++) { // 백조 먼저
            q = new LinkedList<>();
            while (!queue[queueNum].isEmpty()) q.offer(queue[queueNum].poll());
            while (!q.isEmpty()) {
                int[] now = q.poll();
                for (int i = 0; i < 4; i++) {
                    int ty = now[0] + dy[i];
                    int tx = now[1] + dx[i];
                    if (ty < 0 || tx < 0 || ty >= R || tx >= C) continue;
                    if ((map[now[0]][now[1]] == 1 && map[ty][tx] == 2) || (map[now[0]][now[1]] == 1 && map[ty][tx] == 2)) // 다른백조 만남
                        return true;
                    if (visit[ty][tx]) continue;
                    visit[ty][tx] = true;
                    if (map[ty][tx] == -1) queue[queueNum].offer(new int[]{ty, tx}); // 얼음이면
                    if (map[ty][tx] == 0) q.offer(new int[]{ty, tx}); // 물이면
                    map[ty][tx] = map[now[0]][now[1]];
                }
            }
        }
        q = new LinkedList<>(); // 물은 따로 얼음 녹이기
        while (!queue[0].isEmpty()) q.offer(queue[0].poll());
        while (!q.isEmpty()) {
            int[] now = q.poll();
            for (int i = 0; i < 4; i++) {
                int ty = now[0] + dy[i];
                int tx = now[1] + dx[i];
                if (ty < 0 || tx < 0 || ty >= R || tx >= C) continue;
                if (visit[ty][tx] || visitWater[ty][tx]) continue;
                visitWater[ty][tx] = true;
                if (map[ty][tx] == -1) queue[0].offer(new int[]{ty, tx}); // 얼음이면
                if (map[ty][tx] == 0) q.offer(new int[]{ty, tx}); // 물이면
                map[ty][tx] = map[now[0]][now[1]];
            }
        }
        return false;
    }
}