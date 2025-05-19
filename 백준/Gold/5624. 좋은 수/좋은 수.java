/*
 * 수 1개로 만들 수 있는 Set, 2개로 만든 Set, 3개로 만든 Set 만들기
 * 
 */

import java.io.IOException;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) throws IOException {
        int N = readInt(), cnt = 0, in;
        HashSet<Integer> one = new HashSet<>(), two = new HashSet<>();

        for (int i = 0; i < N; i++) {
            in = readInt();
            if (in % 3 == 0 && one.contains(in / 3))
                cnt++;
            else {
                for (int t : one) {
                    if (two.contains(in - t)) {
                        cnt++;
                        break;
                    }
                }
            }
            // 숫자 2개로 만들 수 있는 수 집합
            for (int t : one)
                two.add(in + t);
            one.add(in);
        }
        System.out.println(cnt);
    }

    static int readInt() throws IOException {
        int c;
        while ((c = System.in.read()) <= 32)
            ;
        boolean negative = c == 45;
        if (negative)
            c = System.in.read();
        int n = c & 15;
        while ((c = System.in.read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return negative ? -n : n;

    }
}