/*
BOJ 1253 좋다

HashSet에 넣고, N^2 으로 확인하는 게 가장 간단한 풀이인 것 같다.
아, 한 숫자를 다른 두 수의 합으로 표현할 수 있는 개수를 찾아야 해서 중복도 되고 이미 확인한 숫자는 제외해야 해서 HashSet으로는 안 될 것 같다. 그냥 배열로 넣고, N^2으로 확인하면서 이미 확인한 숫자는 제외하는 게 가장 간단한 풀이인 것 같다.
그래서 정렬+투 포인터로 해도 시간복잡도는 O(N^2)으로 동일하다.

*/

import java.io.IOException;
import java.util.Arrays;

public class Main {
    static int N;

    public static void main(String[] args) throws IOException {
        N = readInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) 
            arr[i] = readInt();

        Arrays.sort(arr);

        int cnt = 0;
        for (int k = 0; k < N; k++) {
            int i = 0, j = N - 1;
            while (i < j) {
                if (i == k) {
                    i++;
                    continue;
                }
                if (j == k) {
                    j--;
                    continue;
                }
                int sum = arr[i] + arr[j];
                if (sum == arr[k]) {
                    cnt++;
                    break;
                } else if (sum < arr[k]) {
                    i++;
                } else {
                    j--;
                }
            }
        }
        System.out.println(cnt);
    }

    public static int pos, len;
    public static byte[] buf = new byte[8192];

    public static byte read() throws IOException {
        if (pos == len) {
            len = System.in.read(buf);
            pos = 0;
        }
        return buf[pos++];
    }

    public static int readInt() throws IOException {
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
