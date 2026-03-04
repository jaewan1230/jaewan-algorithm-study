/*
BOJ 17179 케이크 자르기

횟수대로 롤 케이크를 잘랐을 때 가장 작은 조각의 길이의 최댓값 출력
-> 매개변수 탐색

*/

import java.io.IOException;

public class Main {
    static int N, M, L, K;

    public static void main(String[] args) throws IOException {
        N = readInt();
        M = readInt();
        L = readInt();
        int[] arr = new int[M];
        for (int i = 0; i < M; i++)
            arr[i] = readInt();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            K = readInt();
            sb.append(parametricSearch(arr)).append("\n");
        }
        System.out.print(sb);
    }

    // 판정함수, 가장 작은 조각의 길이가 x 이상이 되도록 자를 수 있는가?
    static boolean check(int x, int[] arr) {
        int cnt = 0, length = 0;
        for (int i = 0; i < M; i++) {
            length += (i == 0 ? arr[i] : arr[i] - arr[i - 1]);
            if (length >= x) {
                cnt++;
                length = 0;
            }
        }
        // 마지막 조각이 x 이상인 경우
        if (length + (L - arr[M - 1]) >= x)
            cnt++;
        return cnt > K;
    }

    // 매개변수 탐색, 가장 작은 조각의 길이의 최댓값을 구하는 함수
    static int parametricSearch(int[] arr) {
        int left = 1, right = L;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (check(mid, arr))
                left = mid + 1;
            else
                right = mid - 1;
        }
        return right;
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
