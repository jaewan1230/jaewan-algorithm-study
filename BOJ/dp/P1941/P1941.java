import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P1941 {
    static int res;
    static int[] resSet = new int[7];
    static boolean[] visit;
    static char[][] map = new char[6][6];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 5; i++) {
            char[] inStr = br.readLine().toCharArray();
            for (int j = 0; j < 5; j++) {
                map[i][j] = inStr[j];
            }
        }
        func(0);
        System.out.println(res);
    }

    static void func(int k) {
        if (k == 7) {
            if (!isValid()) return;
            res++;
            return;
        }
        for (int i = k > 0 ? resSet[k - 1] + 1 : 0; i < 25; i++) {
            resSet[k] = i;
            func(k + 1);
        }
    }

    static boolean isValid() {
        visit = new boolean[7];
        for (int i = 0; i < 7; i++) visit[i] = false;
        visit[0] = true;
        dfs(resSet[0]);
        int cnt = 0;
        for (int i = 0; i < 7; i++) {
            if (!visit[i]) return false;
            int y = resSet[i] / 5;
            int x = resSet[i] % 5;
            if (map[y][x] == 'S') cnt++;
        }
        if (cnt < 4) return false;
        return true;
    }

    static void dfs(int k) {
        for (int i = 0; i < 7; i++) {
            if (!visit[i]) { // 방문 안했다 k 에서 갈 수 있는지 확인.
                if (resSet[i] == k - 5 || resSet[i] == k + 5) { // 위아래
                    visit[i] = true;
                    dfs(resSet[i]);
                }
                if ((resSet[i] / 5 == k / 5) && (resSet[i] == k - 1 || resSet[i] == k + 1)) {
                    visit[i] = true;
                    dfs(resSet[i]);
                }
            }
        }
    }
}