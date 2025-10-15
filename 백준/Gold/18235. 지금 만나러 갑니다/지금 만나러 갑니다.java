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
 */

import java.io.IOException;
import java.util.ArrayDeque;

public class Main {
    static int N;

    public static void main(String[] args) throws IOException {
        N = readInt();

        System.out.println(BFS(readInt(), readInt()));
    }

    static int BFS(int a, int b) {
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        int[] pos = new int[N + 1];

        // 오/육리, 일수, 위치
        queue.offer(new int[] { 5, 0, a });
        queue.offer(new int[] { 1, 0, b });

        while (!queue.isEmpty()) {
            int next;
            int[] cur = queue.poll();

            if (cur[0] == 5) {
                next = cur[2] - (1 << cur[1]);
                if (0 < next && next <= N) {
                    pos[next] = cur[1] + 1;
                    queue.offer(new int[] { 5, cur[1] + 1, next });
                }

                next = cur[2] + (1 << cur[1]);
                if (0 < next && next <= N) {
                    pos[next] = cur[1] + 1;
                    queue.offer(new int[] { 5, cur[1] + 1, next });
                }
            } else {
                next = cur[2] - (1 << cur[1]);
                if (0 < next && next <= N) {
                    if (pos[next] == cur[1] + 1)
                        return pos[next];
                    queue.offer(new int[] { 6, cur[1] + 1, next });
                }

                next = cur[2] + (1 << cur[1]);
                if (0 < next && next <= N) {
                    if (pos[next] == cur[1] + 1)
                        return pos[next];
                    queue.offer(new int[] { 6, cur[1] + 1, next });
                }
            }
        }

        return -1;
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