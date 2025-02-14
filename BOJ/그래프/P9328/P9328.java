import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class P9328 {
    static int T, h, w, res;
    static boolean[] key;
    static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
    static Queue<int[]> queue;
    static char[][] map;
    static boolean[][] visit;
    static ArrayList<int[]>[] doorNotOpen;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            String input[] = br.readLine().split(" ");
            h = Integer.parseInt(input[0]);
            w = Integer.parseInt(input[1]);
            map = new char[h][w];
            visit = new boolean[h][w];
            key = new boolean[26];
            doorNotOpen = new ArrayList[26];
            for (int i = 0; i < 26; i++) doorNotOpen[i] = new ArrayList<int[]>();
            for (int i = 0; i < h; i++) {
                String inputStr = br.readLine().toString();
                for (int j = 0; j < w; j++) {
                    map[i][j] = inputStr.charAt(j);
                }
            }
            String keyList = br.readLine().toString();
            if (keyList.charAt(0) != '0')
                for (int i = 0; i < keyList.length(); i++) key[keyList.charAt(i) - 'a'] = true;
            BFS();
            System.out.println(res);
        }
    }

    static void BFS() {
        res = 0;
        queue = new LinkedList<>();
        for (int i = 0; i < h; i++) {
            processVisit(i, 0);
            processVisit(i, w - 1);
        }
        for (int j = 0; j < w; j++) {
            processVisit(0, j);
            processVisit(h - 1, j);
        }
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            for (int i = 0; i < 4; i++) {
                int ty = now[0] + dy[i];
                int tx = now[1] + dx[i];
                if (ty < 0 || tx < 0 || ty >= h || tx >= w) continue;
                if (map[ty][tx] == '*' || visit[ty][tx]) continue;
                processVisit(ty, tx);
            }

        }
    }

    static void processVisit(int y, int x) {
        if (map[y][x] == '.' && !visit[y][x]) { // 방문
            queue.offer(new int[]{y, x});
        }
        if (map[y][x] == '$' && !visit[y][x]) { // 문서 획득
            res++;
            queue.offer(new int[]{y, x});
        }
        if ('a' <= map[y][x] && map[y][x] <= 'z' && !visit[y][x]) { // 열쇠 획득
            key[map[y][x] - 'a'] = true;
            queue.offer(new int[]{y, x});
            for (int[] now : doorNotOpen[map[y][x] - 'a']) {
                processVisit(now[0], now[1]);
                queue.offer(new int[]{now[0], now[1]});
            }
        }
        if ('A' <= map[y][x] && map[y][x] <= 'Z') { // 문 만남
            if (key[map[y][x] - 'A']) { // 열쇠 있으면
                queue.offer(new int[]{y, x});
            } else { // 열쇠 없으면
                doorNotOpen[map[y][x] - 'A'].add(new int[]{y, x});
            }
        }
        visit[y][x] = true;
    }
}