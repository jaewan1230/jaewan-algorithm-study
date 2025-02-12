import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1759 {
    static int L, C;
    static char[] in_arr = new char[16];
    static int[] res = new int[16];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            in_arr[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(in_arr, 0, C);
        generatePassword(0, 0);
        bw.close();
    }

    static void generatePassword(int k, int numVowel) throws IOException {
        if (k == L) {
            if (numVowel > 0 && k - numVowel > 1) {
                for (int i = 0; i < k; i++) {
                    bw.write(in_arr[res[i]]);
                }
                bw.newLine();
            }
            return;
        }
        for (int i = k > 0 ? res[k - 1] + 1 : 0; i < C; i++) {
            res[k] = i;
            if (in_arr[i] == 'a' || in_arr[i] == 'e' || in_arr[i] == 'i' || in_arr[i] == 'o' || in_arr[i] == 'u')
                generatePassword(k + 1, numVowel + 1);
            else generatePassword(k + 1, numVowel);
        }
    }
}