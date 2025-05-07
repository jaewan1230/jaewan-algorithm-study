/*
 * N K D
 * K개의 줄에 상자 N개 도토리 D개
 * A B C, A 상자부터 B 상자까지 C개 간격으로 도토리 하나씩 넣음.
 * 
 * D개의 도토리를 규칙에 맞게 상자 앞에서부터 넣었을 때 마지막 도토리가 들어가는 상자의 번호.
 * 
 * 매개변수 탐색. 규칙을 적용해서 해당 상자 번호까지 몇 개 들어간지 판단 O(K) * log D
 * 
 */

import java.io.IOException;

public class Main {
    static int N, K, D;
    static Rule[] rules;

    public static void main(String[] args) throws IOException {
        // System.setIn(new FileInputStream("in.txt"));
        N = readInt();
        K = readInt();
        D = readInt();

        rules = new Rule[K];
        for (int i = 0; i < K; i++)
            rules[i] = new Rule(readInt(), readInt(), readInt());

        System.out.println(paramSearch());
    }

    static int paramSearch() {
        int left = 1, right = Integer.MAX_VALUE, m;
        while (left <= right) {
            m = left + (right - left) / 2;
            if (check(m))
                left = m + 1;
            else
                right = m - 1;
        }
        return left;
    }

    static boolean check(int no) {
        long sum = 0;
        for (int i = 0; i < K; i++) {
            if (no < rules[i].start) {
                continue;
            }
            sum += Math.max((Math.min(no, rules[i].end) - rules[i].start) / rules[i].gap + 1, 0);
        }
        return sum < D;
    }

    static class Rule {
        int start, end, gap;

        public Rule(int start, int end, int gap) {
            this.start = start;
            this.end = end;
            this.gap = gap;
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