import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1799 {
    static int N, max, res;
    static int[][] chessBoard = new int[10][10];
    static boolean[] chkDiagonal1 = new boolean[20], chkDiagonal2 = new boolean[20];

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                chessBoard[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        putBishop(0, 0, 0);
        res += max;
        max = 0;
        putBishop(0, 0, 1);
        res += max;
        System.out.println(res);
    }

    static void putBishop(int cnt, int y, int x) {
        if (y >= N) {
            if (max < cnt) max = cnt;
            return;
        }
        if (x >= N) {
            int ty, tx;
            ty = y + 1;
            tx = 0;
            if ((x + y) % 2 == 0 && (ty + tx) % 2 == 1) tx++;
            if ((x + y) % 2 == 1 && (ty + tx) % 2 == 0) tx++;
            putBishop(cnt, ty, tx);
            return;
        }
        if (isValid(y, x)) {
            chkDiagonal1[y + x] = true;
            chkDiagonal2[N + y - x - 1] = true;
            putBishop(cnt + 1, y, x + 2); // 놓을 수 있으면 놓기
            chkDiagonal1[y + x] = false;
            chkDiagonal2[N + y - x - 1] = false;
        }
        putBishop(cnt, y, x + 2); // 안놓기
    }

    static boolean isValid(int y, int x) {
        if (chessBoard[y][x] == 0) return false;
        if (chkDiagonal1[y + x]) return false;
        if (chkDiagonal2[N + y - x - 1]) return false;
        return true;
    }
}