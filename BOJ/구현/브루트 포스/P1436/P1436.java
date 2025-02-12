import java.util.Scanner;

public class P1436 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), num = 1, cnt = 0;
        while (cnt < n) {
            String s = Integer.toString(num);
            boolean flag = false;
            for (int i = 2; i < s.length(); i++) {
                if (s.charAt(i) == '6' && s.charAt(i - 1) == '6' && s.charAt(i - 2) == '6') {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                cnt++;
            }
            num++;
        }
        System.out.println(num - 1);
    }
}