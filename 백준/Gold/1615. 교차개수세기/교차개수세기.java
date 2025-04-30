/* 
 * 왼쪽 오름차순 정렬, 오른쪽 오름차순 정렬했을 때
 * 
 * 오른쪽 숫자가 큰 선분이 교차하는 선분임.
 * 
 * 즉, 구간 합을 구해야 하는데, 갱신이 잦으므로 세그트리 사용
 */

import java.io.IOException;
import java.util.Arrays;

public class Main {
    static int N, M;
    static int[] tree;

    public static void main(String[] args) throws IOException {
        N = readInt();
        M = readInt();
        int[] inDatas = new int[M];
        for (int i = 0; i < M; i++)
            inDatas[i] = 10000 * readInt() + readInt();
        Arrays.sort(inDatas);
        for (int i = 0; i < M; i++)
            inDatas[i] %= 10000;

        tree = new int[N << 1];
        long sum = 0;
        for (int i = 0; i < M; i++) {
            sum += sum(inDatas[i] + 1, N);
            add(inDatas[i]);
        }
        System.out.println(sum);
    }

    static int sum(int left, int right) {
        left += (N - 1);
        right += (N - 1);
        int res = 0;
        while (left <= right) {
            if ((left & 1) != 0)
                res += tree[left++];
            if ((right & 1) == 0)
                res += tree[right--];
            left >>= 1;
            right >>= 1;
        }
        return res;
    }

    static void add(int x) {
        x += (N - 1);
        while (x > 0) {
            tree[x]++;
            x >>= 1;
        }
    }

    static int readInt() throws IOException {
        int c = System.in.read();
        int n = c & 15;
        while ((c = System.in.read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}
