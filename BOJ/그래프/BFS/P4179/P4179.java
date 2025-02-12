import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P4179 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken()), C = Integer.parseInt(st.nextToken());
        int[][] map = new int[R][C];
        int[][] fire = new int[R][C];
        boolean[][] burned = new boolean[R][C];
        Queue<Node> q = new LinkedList<Node>();
        int startY = 0, startX = 0;
        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                if (str.charAt(j) == '#') {
                    map[i][j] = -1;
                    fire[i][j] = -1;
                }
                if (str.charAt(j) == '.') {
                    map[i][j] = 0;
                    fire[i][j] = 0;
                }
                if (str.charAt(j) == 'J') {
                    map[i][j] = 0;
                    fire[i][j] = 0;
                    startY = i;
                    startX = j;
                }
                if (str.charAt(j) == 'F') {
                    fire[i][j] = 0;
                    q.offer(new Node(i, j));
                    burned[i][j] = true;
                }
            }
        }
        int[] dy = {0, 1, 0, -1};
        int[] dx = {1, 0, -1, 0};
        while (!q.isEmpty()) {
            Node node = q.poll();
            for (int i = 0; i < 4; i++) {
                int ty = node.y + dy[i];
                int tx = node.x + dx[i];
                if (ty < 0 || tx < 0 || ty >= R || tx >= C) continue;
                if (burned[ty][tx] || fire[ty][tx] == -1) continue;
                q.offer(new Node(ty, tx));
                fire[ty][tx] = fire[node.y][node.x] + 1;
                burned[ty][tx] = true;
            }
        }

        boolean exit = false;
        boolean[][] visited = new boolean[R][C];
        visited[startY][startX] = true;
        q.offer(new Node(startY, startX));
        while (!exit && !q.isEmpty()) {
            Node node = q.poll();
            if (node.y == 0 || node.x == 0 || node.y == R - 1 || node.x == C - 1) {
                exit = true;
                System.out.println(map[node.y][node.x] + 1);
                break;
            }
            for (int i = 0; i < 4; i++) {
                int ty = node.y + dy[i];
                int tx = node.x + dx[i];
                if (ty < 0 || tx < 0 || ty >= R || tx >= C) continue;
                if (visited[ty][tx] || map[ty][tx] == -1) continue;
                if (burned[ty][tx] && fire[ty][tx] <= map[node.y][node.x] + 1) continue;
                q.offer(new Node(ty, tx));
                map[ty][tx] = map[node.y][node.x] + 1;
                visited[ty][tx] = true;
            }
        }
        if (!exit) System.out.println("IMPOSSIBLE");
    }

    static class Node {
        int y, x;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}