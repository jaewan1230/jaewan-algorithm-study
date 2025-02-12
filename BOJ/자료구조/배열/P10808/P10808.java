import java.util.Scanner;

public class P10808 {
    public static void main(String[] args) {
        int[] cnt = new int[26];
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        for (int i = 0; i < 26; i++) cnt[i] = 0;
        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }
        for (int i = 0; i < 26; i++) System.out.print(cnt[i] + " ");
    }
}