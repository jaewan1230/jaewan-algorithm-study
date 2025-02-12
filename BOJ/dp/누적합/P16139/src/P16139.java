import java.io.*;
import java.util.StringTokenizer;

public class P16139 {
    public static void main(String args[]) throws IOException {
        String S;
        int sum[][] = new int[26][200001];
        int q, l, r;
        char a;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        S = br.readLine();
        for (int i = 0; i < 26; i++) sum[i][0] = 0;
        for (int i = 1; i <= S.length(); i++) {
            for (int j = 0; j < 26; j++) sum[j][i] = sum[j][i - 1];
            a = S.charAt(i - 1);
            sum[a - 97][i]++;
        }
        q = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            a = st.nextToken().charAt(0);
            l = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken());
            bw.append((sum[a - 97][r + 1] - sum[a - 97][l])+"\n");
        }
        br.close();
        bw.close();
    }
}