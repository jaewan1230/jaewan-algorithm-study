import java.util.Scanner;
public class P1475 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), max = 0;
        int[] arr = new int[10];
        String str = Integer.toString(n);
        for (char ch : str.toCharArray()) arr[ch - '0']++;

        arr[6] += arr[9]; // 6과 9는 뒤집어서 이용할 수 있음.
        arr[6] = (arr[6] + 1) / 2;
        arr[9] = 0;

        for (int val : arr)
            if (max < val) max = val;
        System.out.println(max);
    }
}