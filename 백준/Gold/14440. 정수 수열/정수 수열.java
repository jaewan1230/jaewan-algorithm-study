/*
 * A[n] = x * A[n - 1] + y * A[n - 2]
 * x, y, a0, a1, n 이 주어질 때, A[n]의 마지막 두 자리를 구해라.
 * n < 1e8.
 * 
 * 행렬 곱 + 제곱 이용해 최적화 
 */

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        int x = readInt(), y = readInt(), a0 = readInt(), a1 = readInt(), n = readInt();
        int[][] res = powMatrix(new int[][] { { x, y }, { 1, 0 } }, n - 1);
        System.out.println(String.format("%02d", (res[0][0] * a1 + res[0][1] * a0) % 100));
    }

    // 2x2 행렬을 n 제곱
    static int[][] powMatrix(int[][] matrix, int n) {
        int[][] temp = matrix, res = { { 1, 0 }, { 0, 1 } };

        int p = 1;
        while (p <= n) {
            if ((n & p) != 0)
                res = mulMatrix(temp, res);
            temp = mulMatrix(temp, temp);
            p <<= 1;
        }
        return res;
    }

    // 2x2 행렬 A, B 곱
    static int[][] mulMatrix(int[][] matrixA, int[][] matrixB) {
        int[][] res = new int[2][2];

        res[0][0] = (matrixA[0][0] * matrixB[0][0] + matrixA[0][1] * matrixB[1][0]) % 100;
        res[0][1] = (matrixA[0][0] * matrixB[0][1] + matrixA[0][1] * matrixB[1][1]) % 100;
        res[1][0] = (matrixA[1][0] * matrixB[0][0] + matrixA[1][1] * matrixB[1][0]) % 100;
        res[1][1] = (matrixA[1][0] * matrixB[0][1] + matrixA[1][1] * matrixB[1][1]) % 100;

        return res;
    }

    static int readInt() throws IOException {
        int c = System.in.read();
        int n = c & 15;
        while ((c = System.in.read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}