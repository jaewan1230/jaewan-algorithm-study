import java.util.Scanner;
public class P10807 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] cnt = new int[201]; // -100 ~ 100 -> 0 ~ 200
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) cnt[sc.nextInt() + 100]++;
        System.out.println(cnt[sc.nextInt() + 100]);
    }
}