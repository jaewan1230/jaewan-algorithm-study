import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2630 {
    static int[] res = new int[2];
    static int[][] paper = new int[128][128];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        cutPaper(n, 0, 0);
        System.out.println(res[0]);
        System.out.println(res[1]);
    }

    static void cutPaper(int size, int y, int x) {
        if (size == 1) {
            res[paper[y][x]]++;
            return;
        }
        boolean same = true;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (paper[y + i][x + j] != paper[y][x]) {
                    same = false;
                    break;
                }
            }
            if (!same) break;
        }
        if (same) {
            res[paper[y][x]]++;
            return;
        }
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                cutPaper(size / 2, y + size / 2 * i, x + size / 2 * j);
            }
        }
    }
}