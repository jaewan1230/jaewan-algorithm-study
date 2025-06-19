/* 
 * dp[i] : i까지의 조가 잘 짜여진 정도의 최댓값
 * O(N^2)
 */

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws Exception {
        int N = readInt();

        if (N == 1) {
            readInt();
            System.out.println(0);
        } else {

            int[] dp = new int[N];
            int prevNum, num, diff, prevDiff = -readInt() + (prevNum = readInt());
            dp[1] = Math.abs(prevDiff);
            for (int i = 2; i < N; i++) {
                num = readInt();
                diff = num - prevNum;
                if (diff == 0)
                    dp[i] = dp[i - 1];
                else if (prevDiff * diff > 0) {
                    dp[i] = dp[i - 1] + Math.abs(diff);
                } else {
                    dp[i] = Math.max(dp[i - 1], dp[i - 2] + Math.abs(diff));
                }
                prevDiff = diff;
                prevNum = num;
            }
            System.out.println(dp[N - 1]);
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

    static int readInt() throws Exception {
        int c;
        while ((c = read()) <= 32)
            ;
        boolean negative = c == 45;
        if (negative)
            c = read();
        int n = c & 15;
        while ((c = read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return negative ? -n : n;
    }
}