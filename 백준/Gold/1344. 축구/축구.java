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
    static final double[] Comb = {
            1.0,
            18,
            3060,
            18564,
            43758,
            48620,
            43758,
            18564,
            3060,
            816,
            153,
            1.0
    };

    public static void main(String[] args) throws IOException {
        int A = readInt(), B = readInt();

        System.out.println(1 - func(A, B));
    }

    static double func(int a, int b) {
        double pA = a / 100.0, qA = 1 - pA, pB = b / 100.0, qB = 1 - pB, resA = 0, resB = 0;

        for (int i = 0; i < scores.length; i++) {
            int k = scores[i];
            resA += Math.pow(pA, k) * Math.pow(qA, 18 - k) * Comb[i];
            resB += Math.pow(pB, k) * Math.pow(qB, 18 - k) * Comb[i];
        }

        return resA * resB;
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