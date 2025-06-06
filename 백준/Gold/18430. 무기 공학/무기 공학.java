/*
 * 'ㄱ' 자 모양 부메랑들의 강도 합이 최대가 되게 만들어라.
 * 
 * 중간에 있는 칸은 강도의 영향을 두배로.
 * N, M <= 5 범위 보니까 부르트포스가 될라나
 * 
 * 만들 수 있는데 일부러 안 만드는 건 아니고
 * 2개 만들 수 있는데 1개 만드는 게 더 큰 경우는 있겠지
 * 
 * 중심 칸을 기준으로 경우의 수를 나누면?
 * 5 x 5 의 경우에 201,267 가지 경우.
 * 브루트 포스로 최댓값 구하면 되겠다.
 */

import java.io.IOException;

public class Main {

    static int N, M, max;
    static int[][] map,
            dy = { { 0, 1 }, { -1, 0 }, { -1, 0 }, { 0, 1 } },
            dx = { { -1, 0 }, { 0, -1 }, { 0, 1 }, { 1, 0 } };
    static boolean[][] isUsed;

    public static void main(String[] args) throws IOException {
        N = readInt();
        M = readInt();
        map = new int[N][M];
        isUsed = new boolean[N][M];

        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                map[i][j] = readInt();

        dfs(0, 0, 0);
        System.out.println(max);
    }

    static void dfs(int y, int x, int sum) {
        if (x == M) {
            x = 0;
            y++;
        }

        if (y == N) {
            max = Math.max(max, sum);
            return;
        }

        if (!isUsed[y][x]) {
            for (int k = 0; k < 4; k++) {
                int ny1 = y + dy[k][0];
                int ny2 = y + dy[k][1];
                int nx1 = x + dx[k][0];
                int nx2 = x + dx[k][1];

                if (ny1 < 0 || ny2 < 0 || nx1 < 0 || nx2 < 0 || ny1 >= N || ny2 >= N || nx1 >= M || nx2 >= M ||
                        isUsed[y][x] || isUsed[ny1][nx1] || isUsed[ny2][nx2])
                    continue;
                isUsed[y][x] = isUsed[ny1][nx1] = isUsed[ny2][nx2] = true;
                int val = map[y][x] * 2 + map[ny1][nx1] + map[ny2][nx2];
                dfs(y, x + 1, sum + val);
                isUsed[y][x] = isUsed[ny1][nx1] = isUsed[ny2][nx2] = false;
            }
        }

        dfs(y, x + 1, sum);
    }

    static int readInt() throws IOException {
        int c;
        while ((c = System.in.read()) <= 32)
            ;
        int n = c & 15;
        while ((c = System.in.read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}