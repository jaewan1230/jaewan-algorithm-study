/* 
 * dp[i] : i까지의 조가 잘 짜여진 정도의 최댓값
 * O(N^2)
 */

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws Exception {
        int N = readInt();
        int[] arr = new int[N + 1], dp = new int[N + 1];

        for (int i = 1; i <= N; i++)
            arr[i] = readInt();

        int min, max;
        for (int i = 1; i <= N; i++) {
            min = max = arr[i];
            for (int j = i; j > 0; j--) {
                min = Math.min(min, arr[j]);
                max = Math.max(max, arr[j]);
                dp[i] = Math.max(dp[i], dp[j - 1] + max - min);
            }
        }
        System.out.println(dp[N]);
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