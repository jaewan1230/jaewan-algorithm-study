/*
 * 두 배열의 부배열의 합이 T가 되는 쌍의 개수를 구해라.
 * 
 * A 배열의 누적 합을 map에 저장 (숫자, 갯수)
 * B 동일
 * 
 * 더해서 T 되는 쌍 개수 구하기 
 */

import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

public class Main {

    public static void main(String[] args) throws IOException {
        int T = readInt();
        long sum;

        int N = readInt();
        int[] arrA = new int[N];
        HashMap<Long, Long> accA = new HashMap<>();
        for (int i = 0; i < N; i++) {
            arrA[i] = readInt();

            sum = 0;
            for (int j = i; j >= 0; j--) {
                sum += arrA[j];
                accA.put(sum, accA.getOrDefault(sum, 0L) + 1);
            }
        }

        int M = readInt();
        int[] arrB = new int[M];
        HashMap<Long, Long> accB = new HashMap<>();
        for (int i = 0; i < M; i++) {

            arrB[i] = readInt();

            sum = 0;
            for (int j = i; j >= 0; j--) {
                sum += arrB[j];
                accB.put(sum, accB.getOrDefault(sum, 0L) + 1);
            }
        }

        long res = 0;
        for (Entry<Long, Long> e : accA.entrySet()) {
            res += e.getValue() * accB.getOrDefault(T - e.getKey(), 0L);
        }
        System.out.println(res);
    }

    static int readInt() throws IOException {
        int c = System.in.read();
        boolean negative = c == 45;
        if (negative)
            c = System.in.read();
        int n = c & 15;
        while ((c = System.in.read()) > 32)
            n = (n << 3) + (n << 1) + (c & 15);
        return negative ? -n : n;
    }
}