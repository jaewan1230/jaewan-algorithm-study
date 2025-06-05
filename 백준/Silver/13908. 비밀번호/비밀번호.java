
/*
 * 비밀번호 모든 조합을 눌러봄
 * 비밀번호에 들어가는수, m 개의 서로 다른 숫자를 알려줌.
 * 비밀번호는 총 n 자리
 * 
 * 주어지는 수는 중복 사용 가능함.
 * 가능한 모든 비밀번호의 개수를 출력
 * n = 2
 * m = 1 일때, 10 + 10 - 1, 십의 자리, 일의 자리 들어가는경우에서 중복된 경우 빼기
 * m = 2 일때, 1 + 1
 * 
 * n = 3 이면 , ...
 * 
 * 아 조합 식세워서 될거같은데 일단 다 해도 1e7 이니까 일단 브루트포스 ..
 * 아니 메모리 제한이 걸려버리네. 재귀 스택 때문인가?
 * 
 * 걍 식 ㅅ세워야겠다.
 * 어려운데?
 * 
 * 
 */

import java.io.IOException;

public class Main {
    static int N, M, res;
    static int[] nums;
    static boolean[] visit = new boolean[10];

    public static void main(String[] args) throws IOException {
        N = readInt();
        M = readInt();

        for (int i = 0; i < M; i++)
            visit[readInt()] = true;
        func(0, 0);
        System.out.println(res);
    }

    static void func(int idx, int cnt) {
        if (idx == N) {
            if (cnt == M)
                res++;
            return;
        }

        for (int i = 0; i < 10; i++) {
            if (visit[i]) {
                visit[i] = false;
                func(idx + 1, cnt + 1);
                visit[i] = true;
            } else {
                func(idx + 1, cnt);
            }
        }
    }

    static int readInt() throws IOException {
        int c;
        while ((c = System.in.read()) <= 32)
            ;
        int n = c & 15;
        while ((c = System.in.read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}