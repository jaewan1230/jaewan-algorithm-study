/*
 * 적어도 한 팀이 골을 소수로 득점할 확률
 * 5분 한 간격동안 득점할 확률
 * 총 18간격
 * 1 - 두 팀 모두 소수가 아닌 수로 득점할 확률
 * 
 * 2 3 5 7 11 13 17
 * 0 1 4 6 8 9 10 12 14 15 16 18
 * 
 * 이항분포 식이.. n번 시행, k점 득점 확률 nCk * p^k * (1-p)^(n-k)
 * 
 * 1 - (A팀 소수 아닌 득점 확률 * B팀 소수 아닌 득점 확률)
 */

import java.io.IOException;

public class Main {
    static final int[] scores = { 0, 1, 4, 6, 8, 9, 10, 12, 14, 15, 16, 18 };
    static final double[] logComb = {
            0.0,
            Math.log(18),
            Math.log(3060),
            Math.log(18564),
            Math.log(43758),
            Math.log(48620),
            Math.log(43758),
            Math.log(18564),
            Math.log(3060),
            Math.log(816),
            Math.log(153),
            0.0
    };

    public static void main(String[] args) throws IOException {
        int A = readInt(), B = readInt();

        System.out.println(1 - func(A) * func(B));
    }

    static double func(int n) {
        if (n == 0 || n == 100)
            return 1.0;

        double p = n / 100.0, res = 0;
        double logP = Math.log(p), logQ = Math.log(1 - p);

        for (int i = 0; i < scores.length; i++) {
            int k = scores[i];
            double t = logComb[i] + k * logP + (18 - k) * logQ;
            res += Math.exp(t);
        }

        return res;
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