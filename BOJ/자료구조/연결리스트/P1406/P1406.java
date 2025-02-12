import java.io.*;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.StringTokenizer;

public class P1406 {
    public static void main(String[] args) throws IOException {
        LinkedList<Character> editor = new LinkedList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ListIterator<Character> cursor = editor.listIterator();
        for (char c : br.readLine().toCharArray()) cursor.add(c);
        int m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            switch (command.charAt(0)) {
                case 'L':
                    if (cursor.hasPrevious()) cursor.previous();
                    break;
                case 'D':
                    if (cursor.hasNext()) cursor.next();
                    break;
                case 'B':
                    if (cursor.hasPrevious()) {
                        cursor.previous();
                        cursor.remove();
                    }
                    break;
                case 'P':
                    cursor.add(st.nextToken().charAt(0));
                    break;
            }
        }
        for (char c : editor) bw.write(c);
        bw.flush();
        bw.close();
    }
}