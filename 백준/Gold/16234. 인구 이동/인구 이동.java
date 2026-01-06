/*
N x N 의 땅, 땅은 1 x 1개의 칸, 각 칸에 A[r][c]명이 살고 있음.

인구 이동이 진행되는 방법.
1. 국경선을 공유하는 두 나라 인구 차이가 L명 이상, R명 이하면, 국경선을 하루 동안 연다.
2. 국경선이 모두 열렸으면 인구 이동 시작
3. 국경선이 열려 이동할 수 있으면 하루 동안 연합이라고 한다.
4. 연합을 이루고 있는 각 칸의 인구수는 (연합의 인구수)/(연합 칸 개수) 가 된다. 소수점은 버림.
5. 연합 해체하고, 모든 국경선을 닫는다.

인구 이동이 며칠 동안 발생하는지 출력.

---

N <= 50, 1 <= L <= R <= 100, 0 <= A[r][c] <= 100

인구 이동 발생 일수는 2,000보다 작거나 같다.
인구 이동이 발생하면 해당 연합의 각 나라의 인구수는 모두 같아진다.

BFS 돌아서 연합 구하고 갱신하기.

 */

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class Main {
    static boolean flag;
    static boolean[][] visit;
    static int N, L, R;
    static int[] dy = { 0, 0, 1, -1 }, dx = { 1, -1, 0, 0 };
    static int[][] A;

    public static void main(String[] args) throws IOException {
        N = readInt();
        L = readInt();
        R = readInt();

        A = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                A[i][j] = readInt();

        System.out.println(func());
    }

    static int func() {
        for (int time = 0;; time++) {
            visit = new boolean[N][N];
            flag = false;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visit[i][j]) {
                        BFS(i, j);
                    }
                }
            }

            if (!flag)
                return time;
        }
    }

    static void BFS(int i, int j) {
        int sum = A[i][j];
        ArrayDeque<int[]> q = new ArrayDeque<>();
        ArrayList<int[]> list = new ArrayList<>();
        q.offer(new int[] { i, j });
        visit[i][j] = true;
        list.add(new int[] { i, j });

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int dir = 0; dir < 4; dir++) {
                int ny = cur[0] + dy[dir], nx = cur[1] + dx[dir];
                if (ny < 0 || nx < 0 || ny >= N || nx >= N || visit[ny][nx] ||
                        Math.abs(A[ny][nx] - A[cur[0]][cur[1]]) < L || R < Math.abs(A[ny][nx] - A[cur[0]][cur[1]]))
                    continue;
                flag = true;
                q.offer(new int[] { ny, nx });
                if (A[cur[0]][cur[1]] != A[ny][nx])
                    visit[ny][nx] = true;
                list.add(new int[] { ny, nx });
                sum += A[ny][nx];
            }
        }

        int avg = sum / list.size();
        for (int[] pos : list)
            A[pos[0]][pos[1]] = avg;
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
