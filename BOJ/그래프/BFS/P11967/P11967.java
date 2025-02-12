import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P11967 {
    static int N, M, res;
    static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
    static int[][] room;
    static ArrayList<Pair>[][] room_switch;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        room_switch = new ArrayList[N + 1][N + 1];
        room = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                room_switch[i][j] = new ArrayList<>();
            }
        }
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            room_switch[y][x].add(new Pair(b, a));
        }
        BFS();
        System.out.println(res);

    }

    public static void BFS() {
        Queue<int[]> q = new LinkedList<>();
        room[1][1] = 2;
        res++;
        q.offer(new int[]{1, 1});
        while (!q.isEmpty()) {
            int[] now = q.poll();
            for (Pair p : room_switch[now[0]][now[1]]) {
                if (room[p.y][p.x] == 0) {
                    res++;
                    room[p.y][p.x] = 1; // 스위치 다 켜기
                    boolean chk = false;
                    for (int i = 0; i < 4; i++) {
                        int ty = p.y + dy[i];
                        int tx = p.x + dx[i];
                        if (ty < 1 || tx < 1 || ty > N || tx > N) continue;
                        if (room[ty][tx] == 2) chk = true;
                    }
                    if (chk) {
                        q.offer(new int[]{p.y, p.x});
                        room[p.y][p.x] = 2;
                    }
                }
            }
            for (int i = 0; i < 4; i++) {
                int ty = now[0] + dy[i];
                int tx = now[1] + dx[i];
                if (ty < 1 || tx < 1 || ty > N || tx > N) continue;
                if (room[ty][tx] != 1) continue;
                room[ty][tx] = 2;
                q.offer(new int[]{ty, tx});
            }
        }
    }

    static class Pair {
        int x, y;

        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}