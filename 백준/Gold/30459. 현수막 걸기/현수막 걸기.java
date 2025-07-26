
/* 
 * N개의 말뚝에 대해 가능한 밑변의 길이를 전부 생성한다.
 * 정렬한다.
 * 
 * M개의 말뚝에 대해 R 을 upper bound로 하여
 * 가능한 현수막의 최대 넓이의 넓이를 구한다.
 * 
 */
import java.io.IOException;
import java.util.Arrays;

public class Main {
    static int max, R;
    static int[] width;

    public static void main(String[] args) throws IOException {
        int N = readInt(), M = readInt();
        R = readInt();
        int[] arr = new int[N];

        for (int i = 0; i < N; i++)
            arr[i] = readInt();
        Arrays.sort(arr);

        width = new int[(N * (N - 1)) >> 1];
        int idx = 0;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < i; j++)
                width[idx++] = arr[i] - arr[j];
        Arrays.sort(width);

        int max = 0;
        for (int i = 0; i < M; i++)
            max = Math.max(max, parametricSearch(0, idx - 1, readInt()));
        if (max == 0)
            System.out.println(-1);
        else
            System.out.printf("%.1f", max / 2.0);
    }

    static int parametricSearch(int left, int right, int height) {
        int mid;
        double upper = 2.0 * R / height;

        while (right >= 0 && left <= right) {
            mid = (left + right) >> 1;
            if (width[mid] <= upper)
                left = mid + 1;
            else
                right = mid - 1;
        }

        return right == -1 ? 0 : height * width[right];
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
        boolean negative = c == 45;
        if (negative)
            c = read();
        int n = c & 15;
        while ((c = read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return negative ? -n : n;
    }
}