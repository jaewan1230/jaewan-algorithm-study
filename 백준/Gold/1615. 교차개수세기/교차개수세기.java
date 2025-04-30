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

    public static void main(String[] args) throws IOException {
        N = readInt();
        M = readInt();
        Data[] inDatas = new Data[M];
        for (int i = 0; i < M; i++)
            inDatas[i] = new Data(readInt(), readInt());
        Arrays.sort(inDatas);

        segTree s = new segTree();
        s.init();
        long sum = 0;
        for (int i = 0; i < M; i++) {
            sum += s.sum(inDatas[i].r + 1, N);
            s.add(inDatas[i].r);
        }
        System.out.println(sum);
    }

    static class Data implements Comparable<Data> {
        int l, r;

        public Data(int l, int r) {
            this.l = l;
            this.r = r;
        }

        @Override
        public int compareTo(Main.Data o) {
            return l != o.l ? l - o.l : r - o.r;
        }

    }

    static class segTree {
        int[] tree;

        void init() {
            tree = new int[N << 1];
        }

        int sum(int left, int right) {
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

        void add(int x) {
            x += (N - 1);
            while (x > 0) {
                tree[x]++;
                x >>= 1;
            }
        }
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
