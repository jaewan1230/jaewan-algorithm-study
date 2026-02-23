
/*
BOJ 1999 최대최소
B 크기가 고정되어 있으니 deque 를 이용하여 슬라이딩 윈도우로 최대값과 최소값을 구할 수 있다.
각 행과 열에 대해서 최대값과 최소값을 구한 뒤, K 개의 질의에 대해서 최대값과 최소값의 차이를 구하면 된다.

예제 입력
5 3 1
5 1 2 6 3
1 3 5 2 7
7 2 4 6 1
9 9 8 6 5
0 6 9 3 9
1 2
예제 출력
5
*/
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    static int N, B, K;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        N = readInt();
        B = readInt();
        K = readInt();
        arr = new int[N][N];

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                arr[i][j] = readInt();

        // Sliding Window 로 행, 열 각각 최대, 최소값 구하기
        int W = N - B + 1;

        int[][] rowMin = new int[N][W];
        int[][] rowMax = new int[N][W];

        // 행 단위로 가로 길이 B의 min/max 구하기
        for (int i = 0; i < N; i++) {
            Deque<Integer> dqMin = new ArrayDeque<>();
            Deque<Integer> dqMax = new ArrayDeque<>();
            for (int j = 0; j < N; j++) {
                while (!dqMin.isEmpty() && arr[i][dqMin.peekLast()] >= arr[i][j])
                    dqMin.pollLast();
                dqMin.offerLast(j);
                if (dqMin.peekFirst() <= j - B)
                    dqMin.pollFirst();

                while (!dqMax.isEmpty() && arr[i][dqMax.peekLast()] <= arr[i][j])
                    dqMax.pollLast();
                dqMax.offerLast(j);
                if (dqMax.peekFirst() <= j - B)
                    dqMax.pollFirst();

                if (j >= B - 1) {
                    int idx = j - B + 1;
                    rowMin[i][idx] = arr[i][dqMin.peekFirst()];
                    rowMax[i][idx] = arr[i][dqMax.peekFirst()];
                }
            }
        }

        // 열 단위로 세로 길이 B의 min/max 구하기 (행별로 미리 구한 값 사용)
        int[][] squareMin = new int[W][W];
        int[][] squareMax = new int[W][W];

        for (int j = 0; j < W; j++) {
            Deque<Integer> dqMin = new ArrayDeque<>();
            Deque<Integer> dqMax = new ArrayDeque<>();
            for (int i = 0; i < N; i++) {
                while (!dqMin.isEmpty() && rowMin[dqMin.peekLast()][j] >= rowMin[i][j])
                    dqMin.pollLast();
                dqMin.offerLast(i);
                if (dqMin.peekFirst() <= i - B)
                    dqMin.pollFirst();

                while (!dqMax.isEmpty() && rowMax[dqMax.peekLast()][j] <= rowMax[i][j])
                    dqMax.pollLast();
                dqMax.offerLast(i);
                if (dqMax.peekFirst() <= i - B)
                    dqMax.pollFirst();

                if (i >= B - 1) {
                    int idx = i - B + 1;
                    squareMin[idx][j] = rowMin[dqMin.peekFirst()][j];
                    squareMax[idx][j] = rowMax[dqMax.peekFirst()][j];
                }
            }
        }

        // K 개의 질의에 대해서 최대값과 최소값의 차이 구하기
        int a, b;
        for (int i = 0; i < K; i++) {
            a = readInt() - 1;
            b = readInt() - 1;
            sb.append(squareMax[a][b] - squareMin[a][b]).append('\n');
        }
        System.out.print(sb);
    }

    public static int pos, len;
    public static byte[] buf = new byte[8192];

    public static byte read() throws IOException {
        if (pos == len) {
            pos = 0;
            len = System.in.read(buf);
        }
        return buf[pos++];
    }

    public static int readInt() throws IOException {
        int c;
        while ((c = read()) <= 32)
            ;

        int n = c & 15;
        while ((c = read()) > 32)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}