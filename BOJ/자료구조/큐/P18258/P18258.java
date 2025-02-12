import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P18258 {
    public static void main(String[] args) throws IOException {
        Queue<Integer> queue = new LinkedList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int last = 0;
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            switch (st.nextToken()) {
                case "push":
                    last = Integer.parseInt(st.nextToken());
                    queue.add(last);
                    break;
                case "pop":
                    if (queue.isEmpty()) bw.write("-1\n");
                    else bw.write(queue.poll().toString() + '\n');
                    break;
                case "size":
                    bw.write(Integer.toString(queue.size()) + '\n');
                    break;
                case "empty":
                    bw.write(Integer.toString(queue.isEmpty() ? 1 : 0) + '\n');
                    break;
                case "front":
                    if (queue.isEmpty()) bw.write("-1\n");
                    else bw.write(Integer.toString(queue.peek()) + '\n');
                    break;
                case "back":
                    if (queue.isEmpty()) bw.write("-1\n");
                    else bw.write(Integer.toString(last) + '\n');
            }
        }
        bw.flush();
        bw.close();
    }
}