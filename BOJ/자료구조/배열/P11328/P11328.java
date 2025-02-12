import java.util.Arrays;
import java.util.Scanner;
public class P11328 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] cnt = new int[27], cnt2 = new int[27];
        for (int i = 0; i < n; i++) {
            Arrays.fill(cnt, 0);
            for (char c : sc.next().toCharArray()) {
                cnt[c - 'a']++;
            }
            Arrays.fill(cnt2, 0);
            for (char c : sc.next().toCharArray()) {
                cnt2[c - 'a']++;
            }
            boolean flag = true;
            for (int j = 0; j < 27; j++) {
                if (cnt[j] != cnt2[j]) flag = false;
            }
            if (flag) System.out.println("Possible");
            else System.out.println("Impossible");
        }
    }
}