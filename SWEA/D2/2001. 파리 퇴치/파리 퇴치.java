/*
 * N x N 격자에 M x M 파리채를 내리쳐 최대한 많은 파리 죽이기
 * 누적합을 쓰는 게 정해일듯?
 * acc[i][j] = acc[i-1][j] + acc[i][j-1] - acc[i-1][j-1] + arr[i][j] 
 * 
 * 누적합을 구하고, M 에 따라 acc[i][j] - acc[i-M][j] - acc[i][j-M] + acc[i-M][j-M] 의 최댓값 구하기.
 */

import java.io.IOException;

public class Solution {

    public static void main(String[] args) throws IOException {
        int TC = readInt(), N, M;
        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= TC; tc++) {
            N = readInt();
            M = readInt();
            int[][] acc = new int[N + 1][N + 1];

            // 누적합 구하기
            for (int i = 1; i <= N; i++)
                for (int j = 1; j <= N; j++)
                    acc[i][j] = acc[i - 1][j] + acc[i][j - 1] - acc[i - 1][j - 1] + readInt();

            int max = 0;
            for (int i = M; i <= N; i++)
                for (int j = M; j <= N; j++)
                    max = Math.max(max, acc[i][j] - acc[i - M][j] - acc[i][j - M] + acc[i - M][j - M]);
            sb.append('#').append(tc).append(' ').append(max).append('\n');
        }
        System.out.print(sb);
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
