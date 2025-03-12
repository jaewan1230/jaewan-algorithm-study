/*
 * 정석 세그트리 문제
 * 
 */

import java.io.IOException;

public class Main {
    static long[] arr, tree;

    public static void main(String[] args) throws IOException {
        int N = readInt(), M = readInt(), K = readInt();
        arr = new long[N + 1];
        for (int i = 1; i <= N; i++)
            arr[i] = readLong();

        tree = new long[N * 4];
        init(1, N, 1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M + K; i++) {
            int a = readInt(), b = readInt();
            long c = readLong();
            if (a == 1) {
                long diff = c - arr[b];
                arr[b] = c;
                update(1, N, 1, b, diff);
            } else {
                sb.append(sum(1, N, 1, b, (int) c)).append('\n');
            }
        }
        System.out.println(sb.toString());
    }

    // start: 시작 인덱스, end: 끝 인덱스
    static long init(int start, int end, int node) {
        if (start == end)
            return tree[node] = arr[start];
        int mid = (start + end) / 2;
        return tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
    }

    // left, right: 구간 합 구하고자 하는 범위
    static long sum(int start, int end, int node, int left, int right) {
        // 범위 밖에 있는 경우
        if (right < start || end < left)
            return 0;
        // 범위 안에 있는 경우
        if (left <= start && end <= right) {
            return tree[node];
        }

        // 그렇지 않다면, 두 부분으로 나누어 합을 구하기
        int mid = (start + end) / 2;
        return sum(start, mid, node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right);
    }

    // idx: 구간 합을 수정하고자 하는 노드, diff: 수정할 값
    static void update(int start, int end, int node, int idx, long diff) {
        // 범위 밖에 있는 경우
        if (idx < start || end < idx)
            return;
        // 범위 안에 있으면 내려가며 다른 원소도 갱신
        tree[node] += diff;
        if (start == end)
            return;

        int mid = (start + end) / 2;
        update(start, mid, node * 2, idx, diff);
        update(mid + 1, end, node * 2 + 1, idx, diff);
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