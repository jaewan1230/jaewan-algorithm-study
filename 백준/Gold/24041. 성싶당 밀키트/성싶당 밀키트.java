/*
BOJ 24041 성싶당 밀키트

밀키트 재료마다 유통기한이 다름.
밀키트에 N 개의 재료, i 번째 재료 유통기한은 L_i 일, 부패 속도는 S_i.
구매 후 x 일에 i 번째 재료ㅣ에 있는 세균수는 S_i * max(1, x - L_i) 이다.

모든 재료의 세균수의 합이 G마리 이하면 먹을 수 있음.
중요하지 않은 재료를 최대 K 개 빼서 G 마리 이하가 되면 그냥 먹기로 함.

며칠 후까지 먹을 수 있을까?

입력

재료 개수 N, 세균 기준수 G, 중요하지 않은 뺄 재료 개수 K
i 번째 재료 정보 부패 속도 S_i, 유통기한 L_i, 중요한 재료인지 O_i(1이면 중요하지 않아서 빼도 됨)

구매 후 며칠 후까지 먹을 수 있는지.
이진 탐색으로 풀 수 있음.

판정함수의 시간복잡도는, O(N) 이고, 이진 탐색의 시간복잡도는 O(log L) 이므로, 전체 시간복잡도는 O(N log L) 이다.

log 10^9 = 30, N <= 2 * 10^5 이므로, 6 * 10^6 정도로 충분히 가능하다.
*/

import java.io.IOException;
import java.util.Arrays;

public class Main {
    static int N, G, K;
    static int[] S, L, O;

    public static void main(String[] args) throws IOException {
        N = readInt();
        G = readInt();
        K = readInt();
        S = new int[N];
        L = new int[N];
        O = new int[N];
        for (int i = 0; i < N; i++) {
            S[i] = readInt();
            L[i] = readInt();
            O[i] = readInt();
        }
        System.out.println(parametricSearch(0, 2_000_000_000));
    }

    // 판정함수. x일 후에 먹을 수 있는지 여부를 반환하는 함수
    static boolean check(int x) {
        long[] bacteria = new long[N];
        for (int i = 0; i < N; i++) {
            bacteria[i] = (long) S[i] * Math.max(1, x - L[i]);
        }
        long sum = 0;
        for (int i = 0; i < N; i++) {
            // 중요한 재료는 뺄 수 없으므로, 일단 세균 수를 더해줌
            if (O[i] == 0)
                sum += bacteria[i];
        }
        if (sum > G)
            return false;

        // 중요하지 않은 재료는 최대 K 개 뺄 수 있음. 정렬해서 세균 수가 가장 많은 K개 빼고 sum에 더함.
        long[] unimportantBacteria = new long[N];
        int idx = 0;
        for (int i = 0; i < N; i++) {
            if (O[i] == 1)
                unimportantBacteria[idx++] = bacteria[i];
        }
        Arrays.sort(unimportantBacteria, 0, idx);
        for (int i = 0; i < idx - K; i++)
            sum += unimportantBacteria[i];
        return sum <= G;
    }

    // 매개변수 탐색
    static int parametricSearch(int left, int right) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (check(mid))
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
