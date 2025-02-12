import java.util.Scanner;
public class P1919 {
    public static void main(String[] args) {
        int[] cnt = new int[26], cnt2 = new int[26];
        Scanner sc = new Scanner(System.in);
        for (char c : sc.nextLine().toCharArray()) cnt[c - 'a']++;
        for (char c : sc.nextLine().toCharArray()) cnt2[c - 'a']++;

        int res = 0;
        for (int i = 0; i < 26; i++) res += Math.abs(cnt[i] - cnt2[i]);
        System.out.println(res);
    }
}