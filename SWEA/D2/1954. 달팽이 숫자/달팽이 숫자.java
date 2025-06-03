/*
 * 달팽이 숫자
 * 0,0 에서 시작해서 가장자리거나, 이미 숫자가 있으면 방향 바꿔가며 숫자 넣기
 */

import java.io.IOException;

public class Solution {
    static int N;

    public static void main(String[] args) throws IOException {
        int[] dy = { 0, 1, 0, -1 }, dx = { 1, 0, -1, 0 };
        int TC = readInt();
        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= TC; tc++) {
            N = readInt();

            int[][] map = new int[N][N];
            int num = 1, dir = 0, y = 0, x = 0;
            map[y][x] = num++;

            while (true) {
                int ny = y + dy[dir], nx = x + dx[dir];
                if (ny < 0 || nx < 0 || ny >= N || nx >= N || map[ny][nx] != 0) {
                    dir = (dir + 1) % 4;
                    ny = y + dy[dir];
                    nx = x + dx[dir];
                    if (ny < 0 || nx < 0 || ny >= N || nx >= N || map[ny][nx] != 0)
                        break;
                }
                map[ny][nx] = num++;
                y = ny;
                x = nx;
            }

            sb.append('#').append(tc).append('\n');
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    sb.append(map[i][j]).append(' ');
                }
                sb.append('\n');
            }
        }
        System.out.println(sb.toString());
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
