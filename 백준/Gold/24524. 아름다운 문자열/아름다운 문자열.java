
/*
 * 문자열 S 길이 <= 1,000,000
 * 각 문자별 최대 하나씩 있는 문자열 T
 * 
 * 문자를 하나씩 골라 문자열 T 만들기. 단, 순서 유지
 * 
 * 문자열 S 에서 문자별 순서를 저장해 놓는다.
 * 그 후, 문자열 T의 순서대로 문자를 하나씩 고르기.
 * 증가하는 index가 있어야함.
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class Main {
    static int[] point = new int[26];
    static ArrayList<ArrayDeque<Integer>> list = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine(), T = br.readLine();
        for (int i = 0; i < 26; i++)
            list.add(new ArrayDeque<>());

        int len = S.length();
        for (int i = 0; i < len; i++)
            list.get(S.charAt(i) - 'a').add(i);

        int cnt = 0;
        while (makeWord(T))
            cnt++;

        System.out.println(cnt);
    }

    public static boolean makeWord(String T) {
        int len = T.length(), idx = 0;
        ArrayDeque<Integer> cur = list.get(T.charAt(0) - 'a');
        if (cur.isEmpty())
            return false;
        idx = cur.poll();

        for (int i = 1; i < len; i++) {
            cur = list.get(T.charAt(i) - 'a');
            while (!cur.isEmpty() && cur.peek() < idx) {
                cur.poll();
            }
            if (cur.isEmpty())
                return false;
            idx = cur.poll();
        }
        return true;
    }
}