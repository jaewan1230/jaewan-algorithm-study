/*
마법사 상어와 비바라기
N x N 격자에 바구니 하나, 물을 저장. N <= 50, M <= 100

비바라기 시전하면 (N, 1), (N, 2), (N-1, 1), (N-1, 2) 칸에 구름이 생긴다.
구름에 이동을 M 번 명령
명령은 방향 d와 거리 s로 이루어짐.
8방향

이동 명령하면 다음 순서대로 진행
1. 모든 구름이 d 방향으로 s 칸 이동 : visit 체크
2. 각 구름이 있는 칸에 물의 양 1 증가
3. 구름이 모두 사라짐
4. 2에서 물이 증가한 칸에 물 복사 버그 마법 시전
   대각선 방향으로 거리가 1인 칸에 물이 있는 바구니의 수만큼 현재 칸의 물 양이 증가
5. 바구니에 저장된 물의 양이 2 이상인 모든 칸에 구름이 생기고, 물의 양이 2 줄어듦.
   단, 3에서 구름이 사라진 칸은 제외됨. // visit 하지 않은 곳만

M 번 이동 명령 후 격자에 저장된 물의 양의 합 구하기

---

시뮬레이션 + 구현

 */

import java.io.IOException;

public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][] cloud;

    public static void main(String[] args) throws IOException {
        N = readInt();
        M = readInt();
        map = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                map[i][j] = readInt();

        cloud = new boolean[N][N];
        cloud[N - 1][0] = true;
        cloud[N - 1][1] = true;
        cloud[N - 2][0] = true;
        cloud[N - 2][1] = true;

        int[] dy = { 0, -1, -1, -1, 0, 1, 1, 1 }, dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
        // 대각선은 1, 3, 5, 7
        while (M-- > 0) {
            int d = readInt() - 1, s = readInt();

            // 1. 구름 이동 및 물 증가
            boolean[][] newCloud = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!cloud[i][j])
                        continue;
                    int ny = (i + dy[d] * s) % N, nx = (j + dx[d] * s) % N;
                    if (ny < 0)
                        ny += N;
                    if (nx < 0)
                        nx += N;
                    newCloud[ny][nx] = true;
                    map[ny][nx]++;
                }
            }
            cloud = newCloud;

            // 4. 물 복사 버그 마법
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!cloud[i][j])
                        continue;
                    int cnt = 0;
                    for (int dir = 1; dir < 8; dir += 2) {
                        int ny = i + dy[dir], nx = j + dx[dir];
                        if (ny >= 0 && nx >= 0 && ny < N && nx < N && map[ny][nx] > 0)
                            cnt++;
                    }
                    map[i][j] += cnt;
                }
            }

            // 5. 구름 생성하고 물 2 감소. cloud에 있던 곳 제외
            boolean[][] newCloud2 = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] >= 2 && !cloud[i][j]) {
                        map[i][j] -= 2;
                        newCloud2[i][j] = true;
                    }
                }
            }
            cloud = newCloud2;
        }

        int res = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                res += map[i][j];
            }
        }
        System.out.println(res);
    }

    static void print() {
        System.out.println("===============");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                System.out.printf("%2d ", map[i][j]);
            System.out.println();
        }
    }

    public static int pos, len;
    public static byte[] buf = new byte[8192];

    public static byte read() throws IOException {
        if (pos == len) {
            len = System.in.read(buf);
            pos = 0;
        }
        return buf[pos++];
    }

    public static int readInt() throws IOException {
        int c;
        while ((c = read()) <= 32)
            ;
        int n = c & 15;
        while ((c = read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}