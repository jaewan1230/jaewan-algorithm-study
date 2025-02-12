import java.util.Scanner;
import java.util.Stack;

public class P2504 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String arrStr = sc.next();
        Stack<Character> stack = new Stack<>();
        int d = 1, res = 0;
        boolean flag = false;
        char[] arr = arrStr.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '(') {
                stack.push(arr[i]);
                d *= 2;
            }
            if (arr[i] == '[') {
                stack.push(arr[i]);
                d *= 3;
            }
            if (arr[i] == ')') {
                if (stack.isEmpty() || stack.pop() != '(') {
                    flag = true;
                    break;
                }
                d /= 2;
                if (arr[i - 1] == '(') res += d * 2;
            }
            if (arr[i] == ']') {
                if (stack.isEmpty() || stack.pop() != '[') {
                    flag = true;
                    break;
                }
                d /= 3;
                if (arr[i - 1] == '[') res += d * 3;
            }
        }
        if (flag || stack.size() != 0) System.out.println(0);
        else System.out.println(res);
    }
}