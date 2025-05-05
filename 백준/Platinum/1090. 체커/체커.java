/*
 * 좌표가 (1, 1), (2, 1), (4, 1), (9, 1) 일 때, 4개의 체커를 같은 칸 (x, y) 에 놓는 이동 횟수는 
 * |x - 1| + |x - 2| + |x - 4| + |x - 9|
 * + |y - 1| + |y - 1| + |y - 1| + |y - 1| 이다.
 * 
 * 절댓값 구간을 나눠서 식을 세워보면,
 * > 1 <= x < 2 일떄 -2x + 14
 * > 2 <= x < 4 일때 10
 * > 4 <= x < 9 일때 2x + 2
 * 
 * y는 1일때 최소이므로 x만 해봤음.
 * 이 함수는 전 구간에서 연속함수이고, 각 구간의 양 끝에서 최솟값 또는 최댓값을 가짐.
 * 따라서, 지점들의 x, y 좌표를 기준으로 계산하면 된다. 즉,
 * 
 * (1, 1)에 1개, 2개, 3개, 4개를 모으는 이동 횟수
 * (2, 1)에 ..
 * ..
 * (9, 1)에 모으는 이동 횟수를 구하고 최솟값을 출력.
 * 
 * 시간복잡도 O(N^4) 
 */

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {
        int N = readInt();
        int[][] pos = new int[N][2];
        int[] min = new int[N];
        HashSet<Integer> xPos = new HashSet<>(), yPos = new HashSet<>();
        Arrays.fill(min, Integer.MAX_VALUE);

        for (int i = 0; i < N; i++) {
            pos[i][0] = readInt();
            pos[i][1] = readInt();
            xPos.add(pos[i][0]);
            yPos.add(pos[i][1]);
        }

        for (int x : xPos) {
            for (int y : yPos) {
                PriorityQueue<Integer> pq = new PriorityQueue<>();
                for (int j = 0; j < N; j++)
                    pq.offer(Math.abs(x - pos[j][0]) + Math.abs(y - pos[j][1]));

                int sum = 0;
                for (int j = 0; j < N; j++) {
                    sum += pq.poll();
                    min[j] = Math.min(sum, min[j]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(min[i]).append(' ');
        }
        System.out.print(sb);
    }

    static int readInt() throws IOException {
        int c = System.in.read();
        int n = c & 15;
        while ((c = System.in.read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}