/*
 * 그룹 단어 체커
 * 그룹 단어, 모든 문자가 연속해서 나타나는 경우
 * ccazzzzbb
 * 
 * 단어 N개를 입력받아 그룹 단어의 개수를 출력하는 프로그램 작성
 * 
 * 나온 글자가 다시 나오는지 여부를 판단(연속은 하나로)
 * 소문자로만 구성
 */

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int N = nextInt(), t, cnt = 0;
        for (int i = 0; i < N; i++) {
            boolean[] chk = new boolean[26];
            st.nextToken();
            char[] str = st.sval.toCharArray();
            chk[str[0] - 'a'] = true;
            boolean flag = true;
            for (int j = 1; j < str.length; j++) {
                if (str[j] == str[j - 1])
                    continue;
                if (chk[str[j] - 'a']) {
                    flag = false;
                    break;
                }
                chk[str[j] - 'a'] = true;
            }
            if (flag)
                cnt++;
        }
        System.out.println(cnt);
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}