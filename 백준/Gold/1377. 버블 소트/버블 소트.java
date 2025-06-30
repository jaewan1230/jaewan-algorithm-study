/* 
 * professor don't wait
 * 
 * 분리집합 문제
 */

import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        int N = readInt();

        int[] arr = new int[N];

        for (int i = 0; i < N; i++)
            arr[i] = readInt();

        int[] sortedArr = Arrays.copyOf(arr, N);
        Arrays.sort(sortedArr);

        int[] idx = new int[1_000_001];
        for (int i = 0; i < N; i++)
            idx[sortedArr[i]] = i;

        int maxDiff = 0;
        for (int i = N - 1; i >= 0; i--) {
            maxDiff = Math.max(maxDiff, i - idx[arr[i]]);
        }

        System.out.println(maxDiff + 1);
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