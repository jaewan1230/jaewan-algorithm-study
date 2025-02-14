import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class P5014 {
    static int F, S, G, U, D;
    static int[] floor;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        F = sc.nextInt();
        S = sc.nextInt();
        G = sc.nextInt();
        U = sc.nextInt();
        D = sc.nextInt();
        floor = new int[F + 1];
        if (!BFS(S)) System.out.println("use the stairs");
        else System.out.println(floor[G] - 1);
    }

    static boolean BFS(int s) {
        floor[s] = 1;
        Queue<Integer> q = new LinkedList<>();
        q.offer(s);
        while (!q.isEmpty()) {
            int t = q.poll();
            if (t + U <= F && floor[t + U] == 0) {
                floor[t + U] = floor[t] + 1;
                q.offer(t + U);
            }
            if (t - D > 0 && floor[t - D] == 0) {
                floor[t - D] = floor[t] + 1;
                q.offer(t - D);
            }
        }
        if (floor[G] == 0) return false;
        else return true;
    }
}