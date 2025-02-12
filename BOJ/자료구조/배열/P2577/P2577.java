import java.util.Scanner;
public class P2577 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr = new int[10];
        int res = sc.nextInt() * sc.nextInt() * sc.nextInt();
        String s = Integer.toString(res);
        for (char ch : s.toCharArray()) arr[ch - '0']++;
        for (int value : arr) System.out.println(value);
    }
}