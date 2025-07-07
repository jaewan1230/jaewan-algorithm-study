/* 
 * (1, a - 1), max (a, b), (b - 1 ~ 1)
 * 이렇게 탑 쌓기.
 * 
 * a + b > N + 1 이면 불가능한 경우.
 * 
 * N - (a + b - 1) 개의 1 을 가장 왼쪽 다음에 추가
 */

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        int N = readInt(), a = readInt(), b = readInt(), idx = 0;
        int[] arr = new int[N];
        StringBuilder sb = new StringBuilder();
        if (a + b > N + 1)
            System.out.println(-1);
        else {
            for (int i = 1; i <= a - 1; i++)
                arr[idx++] = i;
            arr[idx++] = Math.max(a, b);
            for (int i = b - 1; i > 0; i--)
                arr[idx++] = i;
            sb.append(arr[0]).append(' ');
            for (int i = 0; i < N - (a + b - 1); i++)
                sb.append(1).append(' ');
            for (int i = 1; i < idx; i++)
                sb.append(arr[i]).append(' ');
            System.out.println(sb);
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