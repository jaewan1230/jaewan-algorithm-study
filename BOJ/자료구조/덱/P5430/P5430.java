import java.io.*;
import java.util.Deque;
import java.util.LinkedList;

public class P5430 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            String p = br.readLine();
            int n = Integer.parseInt(br.readLine());
            String arrStr = br.readLine();
            Deque<Integer> deq = new LinkedList<>();
            for (String s : arrStr.substring(1, arrStr.length() - 1).split(",")) {
                if (!s.equals("")) {
                    deq.add(Integer.valueOf(s));
                }
            }
            bw.write(AC(deq, p) + "\n");
        }
        bw.flush();
        bw.close();
    }

    static String AC(Deque<Integer> deq, String commands) {
        boolean reverse = false;
        for (char command : commands.toCharArray()) {
            if (command == 'R') reverse = !reverse;
            if (command == 'D') {
                if (deq.isEmpty()) return "error";
                if (reverse) deq.removeLast();
                else deq.removeFirst();
            }
        }
        StringBuilder sb = new StringBuilder("[");
        while (!deq.isEmpty()) {
            if (reverse) sb.append(deq.removeLast());
            else sb.append(deq.removeFirst());
            if (!deq.isEmpty()) sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }
}