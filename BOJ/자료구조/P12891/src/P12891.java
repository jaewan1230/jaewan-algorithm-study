import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P12891 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
        int Result = 0;
        boolean flag;
        char arr[] = new char[S];
        int[] cur = new int[4];
        int[] chk = new int[4];

        arr = br.readLine().toCharArray();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            chk[i] = Integer.parseInt(st.nextToken());
            cur[i] = 0;
        }
        for (int i = 0; i < P; i++) {
            switch (arr[i]) {
                case 'A':
                    cur[0]++;
                    break;
                case 'C':
                    cur[1]++;
                    break;
                case 'G':
                    cur[2]++;
                    break;
                case 'T':
                    cur[3]++;
                    break;
            }
        }
        flag = true;
        for (int i = 0; i < 4; i++) if (cur[i] < chk[i]) flag = false;
        if (flag) Result++;
        for (int i = P; i < S; i++) {
            switch (arr[i - P]) {
                case 'A':
                    cur[0]--;
                    break;
                case 'C':
                    cur[1]--;
                    break;
                case 'G':
                    cur[2]--;
                    break;
                case 'T':
                    cur[3]--;
                    break;
            }
            switch (arr[i]) {
                case 'A':
                    cur[0]++;
                    break;
                case 'C':
                    cur[1]++;
                    break;
                case 'G':
                    cur[2]++;
                    break;
                case 'T':
                    cur[3]++;
                    break;
            }
            flag = true;
            for (int j = 0; j < 4; j++) if (cur[j] < chk[j]) flag = false;
            if (flag) Result++;
        }
        System.out.println(Result);
    }
}