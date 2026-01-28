/*
컨베이어 벨트 위의 로봇

내구도. 1번 칸은 올리는 위치, N번 칸은 내리는 위치
로봇을 올리는 위치에 올리거나 로봇이 어떤 칸으로 이동하는 그 칸의 내구도 1 감소

1. 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전한다.
2. 가장 먼저 벨트에 올라간 로봇부터, 한 칸 이동할 수 있다면 이동한다. (이동할 칸의 내구도가 0이 아니어야 함)
3. 올리는 위치에 있는 칸의 내구도가 0이 아니면 로봇을 올리는 위치에 올린다.
4. 내구도가 0인 칸의 개수가 K개 이상이라면 과정 종료

종료되었을 때 몇 번째 단계가 진행 중이었는지 구하기.

로봇은 시계방향으로만 이동하기 때문에 N 에 가까운 로봇이 가장 먼저 올라간 로봇임.
pointer 로 시작점을 관리하여 벨트 회전을 구현
올리는 위치 A[start], 내리는 위치 A[(start + N - 1) % (2 * N)]
시계방향 회전은 start = (start - 1 + N << 1) % (N << 1);
 */

import java.io.IOException;

public class Main {
    static int N, K, start;
    static boolean[] robot;
    static int[] A;

    public static void main(String[] args) throws IOException {
        N = readInt();
        K = readInt();
        A = new int[N << 1];
        robot = new boolean[N << 1];
        for (int i = 0; i < N << 1; i++)
            A[i] = readInt();

        int step = 1;
        while (true) {
            // 1. 벨트 회전
            start = (start - 1 + (N << 1)) % (N << 1);
            robot[(start + N - 1) % (N << 1)] = false; // 내리는 위치에 있는 로봇 내림

            // 2. 로봇 이동
            // 내리는 위치 바로 전 칸에 있는 로봇부터 이동 시도
            int cur = (start + N - 2) % (N << 1);
            int next = (cur + 1) % (N << 1);
            if (robot[cur] && !robot[next] && A[next] > 0) {
                robot[cur] = false;
                A[next]--;
            }

            for (int i = N - 2; i >= 0; i--) {
                cur = (start + i + (N << 1)) % (N << 1);
                next = (cur + 1) % (N << 1);
                if (robot[cur] && !robot[next] && A[next] > 0) {
                    robot[cur] = false;
                    robot[next] = true;
                    A[next]--;
                }
            }

            // 3. 로봇 올리기
            if (A[start] > 0) {
                robot[start] = true;
                A[start]--;
            }

            // 4. 종료 검사
            int cnt = 0;
            for (int durability : A) {
                if (durability == 0)
                    cnt++;
            }
            if (cnt >= K)
                break;

            step++;
        }
        System.out.println(step);
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
