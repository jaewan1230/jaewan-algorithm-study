import java.io.*;

public class P11720_숫자의합구하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n, res = 0;
        String s;
        n = Integer.parseInt(br.readLine());
        s = br.readLine();
        for (int i = 0; i < s.length(); i++) {
            res += s.charAt(i) - '0';
        }
        System.out.println(res);
    }
}