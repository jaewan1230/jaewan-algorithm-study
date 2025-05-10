/*
 * 그리디로 접근, 
 */

import java.io.IOException;
import java.util.ArrayDeque;

public class Main {

    public static void main(String[] args) throws IOException {
        int N = readInt(), max = 0;
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        long res = 0;
        for (int i = 0; i < N; i++) {
            int t = readInt();
            max = Math.max(max, t);
            if (stack.isEmpty()) {
                stack.push(t);
                continue;
            }
            if (stack.peek() == t)
                continue;
            else if (stack.peek() < t) {
                res += (t - stack.pop());
                stack.push(t);
            } else {
                stack.pop();
                stack.push(t);
            }
        }

        res += (max - stack.pop());

        System.out.println(res);
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