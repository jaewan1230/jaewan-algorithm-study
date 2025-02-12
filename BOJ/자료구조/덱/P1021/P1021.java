import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class P1021 {
    public static void main(String[] args) {
        Deque<Integer> deq = new ArrayDeque<>();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 1; i <= n; i++) deq.addLast(i);
        int res = 0, m = sc.nextInt();
        for (int i = 0; i < m; i++) {
            int cnt = 0, t = sc.nextInt();
            while (deq.peekFirst() != t) {
                deq.addLast(deq.removeFirst());
                cnt++;
            }
            res += (cnt < deq.size() - cnt ? cnt : deq.size() - cnt);
            deq.removeFirst();
        }
        System.out.println(res);
    }
}