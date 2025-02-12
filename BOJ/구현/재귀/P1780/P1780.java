import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1780 {
    static int[][] paper = new int[2500][2500];
    static int[] res = new int[3];

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
        System.out.println(res[2]);
    }

    static void cutPaper(int size, int y, int x) {
        if (size == 1) {
            res[paper[y][x] + 1]++;
            return;
        }
        boolean same = true;
        for (int i = 1; i < size; i++) {
            if (paper[y + i - 1][x] != paper[y + i][x]) same = false;
        }
        for (int i = 0; i < size; i++) {
            for (int j = 1; j < size; j++) {
                if (paper[y + i][x + j - 1] != paper[y + i][x + j]) same = false;
            }
        }
        if (same) {
            res[paper[y][x] + 1]++;
            return;
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cutPaper(size / 3, y + i * size / 3, x + j * size / 3);
            }
        }
    }
}