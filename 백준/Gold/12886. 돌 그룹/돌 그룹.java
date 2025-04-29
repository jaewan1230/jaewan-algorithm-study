/*
 * 돌 그룹 두 그룹. 개수 작은 쪽 X, 큰 쪽 Y.
 * X 개수를 X+X로, Y 개수를 Y-X로.
 * A, B, C가 주어졌을 때 같은 개수로 만들 수 있으면 1, 아니면 0 출력.
 * 
 * A, B, C <= 500
 * 
 * 그냥 다 해보면 되는듯?
 * 중복만 제거하고.
 */

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Objects;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println(BFS());
    }

    static int BFS() throws IOException {
        HashSet<Data> visit = new HashSet<>();
        ArrayDeque<Data> q = new ArrayDeque<>();
        Data d = new Data(readInt(), readInt(), readInt());
        visit.add(d);
        q.offer(d);
        while (!q.isEmpty()) {
            Data cur = q.poll();
            if (cur.A == cur.B && cur.B == cur.C) {
                return 1;
            }

            if (cur.A != cur.B) {
                Data next = new Data(cur.A + cur.A, cur.B - cur.A, cur.C);
                if (!visit.contains(next)) {
                    visit.add(next);
                    q.offer(next);
                }
            }

            if (cur.B != cur.C) {
                Data next = new Data(cur.A, cur.B + cur.B, cur.C - cur.B);
                if (!visit.contains(next)) {
                    visit.add(next);
                    q.offer(next);
                }
            }

            Data next = new Data(cur.A + cur.A, cur.B, cur.C - cur.A);
            if (!visit.contains(next)) {
                visit.add(next);
                q.offer(next);
            }
        }
        return 0;
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

    static class Data {
        int A, B, C;

        @Override
        public boolean equals(Object obj) {
            Data d = (Data) obj;
            return A == d.A && B == d.B && C == d.C;
        }

        @Override
        public int hashCode() {
            return Objects.hash(A, B, C);
        }

        public Data(int a, int b, int c) {
            int sum = a + b + c;
            int max = Math.max(a, Math.max(b, c));
            int min = Math.min(a, Math.min(b, c));
            A = min;
            C = max;
            B = sum - A - C;
        }

    }
}
