import java.io.*;
import java.util.LinkedList;
import java.util.ListIterator;
public class P1406 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            LinkedList<Character> list = new LinkedList<>();
            ListIterator<Character> iter = list.listIterator();
            for (char c : br.readLine().toCharArray()) {
                switch (c) {
                    case '<':
                        if (iter.hasPrevious()) iter.previous();
                        break;
                    case '>':
                        if (iter.hasNext()) iter.next();
                        break;
                    case '-':
                        if (iter.hasPrevious()) {
                            iter.previous();
                            iter.remove();
                        }
                        break;
                    default:
                        iter.add(c);
                }
            }
            for (char c : list) bw.append(c);
            bw.append('\n');
            bw.flush();
        }
        bw.close();
    }
}