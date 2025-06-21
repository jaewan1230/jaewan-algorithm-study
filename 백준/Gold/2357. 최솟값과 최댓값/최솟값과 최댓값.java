/* 
 * 구간 정보에 대한 조회가 많은 문제 -> 세그트리 활용
 * 
 * 최솟값 저장하는 트리, 최댓값 저장하는 트리 2개 만들기
 * 최댓값 트리는 음수로 저장하고 로직은 따로 만들지 말자.
 */

import java.io.IOException;

public class Main {

    static int N, M;
    static int[] minTree, maxTree;

    public static void main(String[] args) throws Exception {
        N = readInt();
        M = readInt();
        minTree = new int[N << 1];
        maxTree = new int[N << 1];

        for (int i = 0; i < N; i++) {
            minTree[N + i] = readInt();
            maxTree[N + i] = -minTree[N + i];
        }

        init(minTree);
        init(maxTree);

        StringBuilder sb = new StringBuilder();
        int a, b;
        for (int i = 0; i < M; i++) {
            a = readInt();
            b = readInt();
            sb.append(findMin(minTree, a, b)).append(' ').append(-findMin(maxTree, a, b)).append('\n');
        }
        System.out.print(sb);
    }

    static void init(int[] tree) {
        for (int i = N - 1; i > 0; i--)
            tree[i] = Math.min(tree[i << 1], tree[(i << 1) + 1]);
    }

    static int findMin(int[] tree, int left, int right) {
        int res = Integer.MAX_VALUE;
        left += (N - 1);
        right += (N - 1);
        while (left <= right) {
            if ((left & 1) == 1)
                res = Math.min(tree[left++], res);
            if ((right & 1) == 0)
                res = Math.min(tree[right--], res);
            left >>= 1;
            right >>= 1;
        }
        return res;
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

    static int readInt() throws Exception {
        int c = read();
        int n = c & 15;
        while ((c = read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}