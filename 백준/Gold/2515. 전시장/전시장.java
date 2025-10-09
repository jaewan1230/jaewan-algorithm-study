/*
 * 높이 순서대로 오름차순 정렬
 * dp[i] : i 번째 그림까지 고려했을 때 얻을 수 있는 최대 가치
 * > i 번째 선택하는 경우, dp[j] : h[i] - h[j] >= S 만족하는 j 이분탐색, dp[j] + cost[i]
 * > i 번째 선택하지 않는 경우, dp[i - 1]
 * 중 최댓값을 저장
 */

import java.io.IOException;
import java.util.Arrays;

public class Main {
    static int N, S;
    static Painting[] paintings;

    public static void main(String[] args) throws IOException {
        N = readInt();
        S = readInt();
        paintings = new Painting[N + 1];
        paintings[0] = new Painting(0, 0);
        for (int i = 1; i <= N; i++)
            paintings[i] = new Painting(readInt(), readInt());
        Arrays.sort(paintings, 1, N + 1);

        int[] dp = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            int j = binarySearch(i);
            dp[i] = Math.max(dp[j] + paintings[i].c, dp[i - 1]);
        }

        System.out.println(dp[N]);
    }

    // H - h[i] >= S 인 최대의 idx 값 리턴
    static int binarySearch(int idx) {
        int left = 0, mid, right = idx - 1;
        while (left <= right) {
            mid = (left + right) / 2;
            if (paintings[idx].h - paintings[mid].h >= S)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return right > 0 ? right : 0;
    }

    static class Painting implements Comparable<Painting> {
        int h, c;

        public Painting(int h, int c) {
            this.h = h;
            this.c = c;
        }

        @Override
        public int compareTo(Main.Painting o) {
            return this.h - o.h;
        }
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

    static int readInt() throws IOException {
        int c;
        while ((c = read()) <= 32)
            ;
        int n = c & 15;
        while ((c = read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}