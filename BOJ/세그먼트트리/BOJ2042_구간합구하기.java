/*
 * 정석 세그트리 문제
 * 
 */

import java.io.IOException;

public class BOJ2042_구간합구하기 {
    static int N, M, K;
    static long[] tree;

    public static void main(String[] args) throws IOException {
        N = readInt();
        M = readInt();
        K = readInt();
        tree = new long[N << 1];

        for (int i = 0; i < N; i++)
            tree[N + i] = readLong();

        init();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M + K; i++) {
            int a = readInt(), b = readInt();
            long c = readLong();
            if (a == 1)
                update(b, c);
            else
                sb.append(sum(b, (int) c)).append('\n');

        }
        System.out.println(sb.toString());
    }

    // start: 시작 인덱스, end: 끝 인덱스
    static void init() {
        for (int i = N - 1; i > 0; i--)
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
    }

    // left, right: 구간 합 구하고자 하는 범위
    static long sum(int left, int right) {
        left += N - 1;
        right += N - 1;

        long sum = 0;

        while (left <= right) {
            if ((left & 1) == 1)
                sum += tree[left++];
            if ((right & 1) == 0)
                sum += tree[right--];
            left >>= 1;
            right >>= 1;
        }
        return sum;
    }

    // idx: 구간 합을 수정하고자 하는 노드, value: 수정할 값
    static void update(int idx, long value) {
        int startIdx = idx + N - 1;
        long diff = value - tree[startIdx];

        while (startIdx > 0) {
            tree[startIdx] += diff;
            startIdx >>= 1;
        }
    }

    static int readInt() throws IOException {
        int c;
        do {
            c = System.in.read();
        } while (c <= 32);
        int n = c & 15;
        c = System.in.read();
        while (c > 47) {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();
        }
        return n;
    }

    static long readLong() throws IOException {
        int c;
        do {
            c = System.in.read();
        } while (c <= 32);
        boolean negative = false;
        if (c == 45) {
            negative = true;
            c = System.in.read();
        }
        long n = c & 15;
        c = System.in.read();
        while (c > 47) {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();
        }
        return negative ? -n : n;
    }
}