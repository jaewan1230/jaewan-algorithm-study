import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;

public class P1158 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), k = sc.nextInt();

        Queue<Integer> myQueue = new LinkedList<>();
        for (int i = 1; i <= n; i++) myQueue.offer(i);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        bw.write("<");
        while (myQueue.size() != 1) {
            for (int i = 0; i < k - 1; i++) myQueue.offer(myQueue.poll());
            bw.write(myQueue.poll() + ", ");
        }
        bw.write(myQueue.poll() + ">");
        bw.flush();
        bw.close();
    }
}