/*
 * 오리 점 A, 육리 점 B
 * X +- 2^y-1 로 점프.
 * 
 * 만날 수 있는 최소 일수를 출력.
 * 영원히 만날 수 없으면 -1 출력.
 * 
 * N <= 500,000
 * 
 * 무작정 BFS 를 하면, 왔다 갔다 중복 제거가 안되기 때문에. 경우가 얼마나 될지 생각해보자.
 * 
 * 중간에서 출발하는 경우, 상태의 총 가짓수는 2^i 만큼 늘어남.
 * 하지만 점프 가능한 위치의 수가 500,000 이하이기 때문에,
 * 이것보다 늘어나는 경우의 i 까지는 증가할 수 없음.
 * 
 * 즉, 모든 경우를 해도, 상태의 총 가짓수가 1,000,000을 넘지 않기 때문에 (범위 체크만 한다면), 시간복잡도 내 충분히 구현 가능.
 * 
 * ---
 * 
 * 같은 일자에, 같은 방향으로 점프하면 '차'는 줄어들지 않음.
 * 반대 방향으로 점프하면, '차'는 (2 << d) 만큼 줄어들거나, 늘어남.
 * 
 * 줄어드는 거리는, 2의 제곱수 이므로, '차' 를 2의 제곱수의 합의 형태로 나타내는 문제임.
 * 예를 들어, '차' 가 12면, 1일차는 같은 방향, 2일차는 가까워지는 방향으로 움직여 4, 3일차는 가까워져 8 총 12만큼 가까워져야 함.
 * 따라서, 비트 연산 활용해 해당 일자에 같은 방향으로 움직일지, 멀어질 지 판단할 수 있음.
 * 
 * 그 전에, 차가 홀수면 절대 만날 수 없음.
 */

import java.io.IOException;

public class Main {
    static int N, res = 30;

    public static void main(String[] args) throws IOException {
        N = readInt();
        int A = readInt(), B = readInt();

        if ((A - B) % 2 == 1) {
            System.out.println(-1);
            return;
        }

        dfs(Math.min(A, B), Math.max(A, B), 0);
        System.out.println(res == 30 ? -1 : res);
    }

    static void dfs(int a, int b, int d) {
        if (a <= 0 || a > N || b <= 0 || b > N)
            return;

        if (a == b) {
            res = Math.min(res, d);
            return;
        }

        if ((Math.abs(a - b) & (2 << d)) != 0) {
            dfs(a + (1 << d), b - (1 << d), d + 1);
        } else {
            dfs(a + (1 << d), b + (1 << d), d + 1);
            dfs(a - (1 << d), b - (1 << d), d + 1);
        }
    }

    static int pos, len;
    static byte[] buf = new byte[8192];

    static byte read() throws IOException {
        if (pos == len) {
            len = System.in.read(buf);
            pos = 0;
        }
        return buf[pos++];
    }

    static int readInt() throws IOException {
        int c;
        while ((c = read()) <= 32)
            ;
        int n = c & 15;
        while ((c = read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}