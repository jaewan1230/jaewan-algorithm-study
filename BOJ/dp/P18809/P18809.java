import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P18809 {
    static int N, M, G, R, cntGround, cntFlower, maxFlower;
    static int[] comb = new int[10], dy = {-1, 1, 0, 0}, dx = {0, 0, -1, 1};
    static int[][] list = new int[10][2], inMap = new int[50][50], mapType = new int[50][50], mapTime;
    static Queue<Node> q = new LinkedList<Node>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                inMap[i][j] = Integer.parseInt(st.nextToken());
                if (inMap[i][j] == 0) inMap[i][j] = -1; // -1 은 호수, 1은 땅
                if (inMap[i][j] == 1) inMap[i][j] = 0;
                if (inMap[i][j] == 2) {
                    list[cntGround][0] = i;
                    list[cntGround++][1] = j;
                    inMap[i][j] = 0;
                }
            }
        }
        makeComb(0, 0, 0); // R, G 조합 만들기


        System.out.println(maxFlower);
    }

    static void makeComb(int k, int g, int r) { // 0, g : 1, r : 2
        if (k == cntGround) {
            if (g < G || r < R) return;
            bfs(); // 배양액 초기 위치 조합으로 bfs
            return;
        }
        if (g < G) {
            comb[k] = 1;
            makeComb(k + 1, g + 1, r);
            comb[k] = 0;
        }
        if (r < R) {
            comb[k] = 2;
            makeComb(k + 1, g, r + 1);
            comb[k] = 0;
        }
        makeComb(k + 1, g, r);
    }

    static void bfs() {
        cntFlower = 0;
        for (int i = 0; i < N; i++) mapType[i] = inMap[i].clone();

        mapTime = new int[50][50];
        for (int i = 0; i < cntGround; i++) {
            if (comb[i] == 1) {
                q.offer(new Node(list[i][0], list[i][1], 1, true));
                mapType[list[i][0]][list[i][1]] = 1;
                mapTime[list[i][0]][list[i][1]] = 1;
            }
            if (comb[i] == 2) {
                q.offer(new Node(list[i][0], list[i][1], 1, false));
                mapType[list[i][0]][list[i][1]] = 2;
                mapTime[list[i][0]][list[i][1]] = 1;
            }
        }
        while (!q.isEmpty()) {
            spreadOnce();
        }
        if (maxFlower < cntFlower) maxFlower = cntFlower;

    }

    static void spreadOnce() {
        int time = q.peek().time;
        while (!q.isEmpty() && q.peek().time == time) {
            Node node = q.poll();
            if (mapType[node.y][node.x] == 3) continue; // 꽃이 된 경우 넘어감.
            for (int i = 0; i < 4; i++) {
                int ty = node.y + dy[i];
                int tx = node.x + dx[i];
                if (ty < 0 || tx < 0 || ty >= N || tx >= M) continue;
                if (mapType[ty][tx] == 0) { // 빈 땅이면
                    mapType[ty][tx] = node.isColorGreen ? 1 : 2;
                    mapTime[ty][tx] = node.time + 1;
                    q.offer(new Node(ty, tx, time + 1, node.isColorGreen));
                } else if (mapType[ty][tx] != -1 && mapType[ty][tx] != 3 && mapType[ty][tx] != mapType[node.y][node.x]) { // 바다가 아니고, 다른 색깔
                    if (mapTime[ty][tx] == mapTime[node.y][node.x] + 1) { // 도달 시간이 같으면
                        cntFlower++;
                        mapType[ty][tx] = 3; // 꽃이 됨.
                    }
                }
            }
        }
    }

    static class Node {
        int x, y, time;
        boolean isColorGreen;

        public Node(int y, int x, int time, boolean isColorGreen) {
            this.y = y;
            this.x = x;
            this.time = time;
            this.isColorGreen = isColorGreen;
        }
    }
}