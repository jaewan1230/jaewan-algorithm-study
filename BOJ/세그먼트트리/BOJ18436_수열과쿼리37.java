/*
 * 1 i x : A[i]를 x로 바꾼다.
 * 2 l r : 1 <= i <= r 에 속하는 모든 A[i]중에서 짝수의 개수를 출력
 * 3 l r : 1 <= i <= r 에 속하는 모든 A[i]중에서 홀수의 개수를 출력
 * 세그먼트 트리에 l, r 구간의 홀수 개수를 저장한다.
 * 짝수 개수는 전체 구간 수 - 홀수 개수로 구할 수 있다.
 */

import java.io.IOException;

public class BOJ18436_수열과쿼리37 {
    static int[] arr, tree;

    public static void main(String[] args) throws IOException {
        int N = readInt();
        arr = new int[N + 1];
        for (int i = 1; i <= N; i++)
            arr[i] = readInt() % 2;

        tree = new int[N * 4];
        init(1, N, 1);

        StringBuilder sb = new StringBuilder();
        int M = readInt();
        for (int i = 0; i < M; i++) {
            int a = readInt(), b = readInt(), c = readInt();
            if (a == 1) {
                int diff = c % 2 - arr[b];
                if (diff == 0)
                    continue;
                arr[b] = c % 2;
                update(1, N, 1, b, diff);
            } else if (a == 2)
                sb.append(c - b + 1 - oddCnt(1, N, 1, b, c)).append('\n');
            else if (a == 3)
                sb.append(oddCnt(1, N, 1, b, c)).append('\n');
        }
        System.out.println(sb.toString());
    }

    // start: 시작 인덱스, end: 끝 인덱스
    // 세그먼트 트리에 구간의 홀수 개수를 저장
    static int init(int start, int end, int node) {
        if (start == end)
            return tree[node] = arr[start] % 2;
        int mid = (start + end) / 2;
        return tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
    }

    // left, right: 구간의 홀수 개수 구하고자 하는 범위
    static int oddCnt(int start, int end, int node, int left, int right) {
        // 범위 밖에 있는 경우
        if (right < start || end < left)
            return 0;
        // 범위 안에 있는 경우
        if (left <= start && end <= right)
            return tree[node];

        // 그렇지 않다면, 두 부분으로 나누어 합을 구하기
        int mid = (start + end) / 2;
        return oddCnt(start, mid, node * 2, left, right) + oddCnt(mid + 1, end, node * 2 + 1, left, right);
    }

    // idx: 구간 합을 수정하고자 하는 노드, diff: 수정할 값
    static void update(int start, int end, int node, int idx, int diff) {
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
}