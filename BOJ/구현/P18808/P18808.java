import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P18808 {
    static int N, M, K;
    static boolean[][] notebook = new boolean[40][40];
    static int[][][] sticker = new int[100][10][10];
    static Sticker[] stickerInfo = new Sticker[100];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            int tR = Integer.parseInt(st.nextToken());
            int tC = Integer.parseInt(st.nextToken());
            stickerInfo[k] = new Sticker(tR, tC);
            for (int i = 0; i < stickerInfo[k].R; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < stickerInfo[k].C; j++) {
                    sticker[k][i][j] = Integer.parseInt(st.nextToken());
                }
            }
        }
        for (int i = 0; i < K; i++) func(i);
        System.out.println(count());
    }

    static int count() {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (notebook[i][j]) cnt++;
            }
        }
        return cnt;
    }

    static void func(int k) { // k번째 스티커 갖고놀기

        if (k == 3) {
            int t = 0;
        }
        for (int rotate = 0; rotate < 4; rotate++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (isAvailable(k, i, j)) {
                        putSticker(k, i, j);
                        return;
                    }
                }
            }
            rotateSticker(k);
        }
    }

    static void printMoniter() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.printf("%d ", notebook[i][j] ? 1 : 0);
            }
            System.out.println();
        }
        System.out.println();
    }

    static boolean isAvailable(int k, int y, int x) { // k번째 스티커를 왼쪽 위 기준 (y, x) 에 붙일 수 있는지
        if (N < y + stickerInfo[k].R || M < x + stickerInfo[k].C) return false;
        for (int i = 0; i < stickerInfo[k].R; i++) {
            for (int j = 0; j < stickerInfo[k].C; j++) {
                if (sticker[k][i][j] == 1 && notebook[y + i][x + j]) return false;
            }
        }
        return true;
    }

    static void putSticker(int k, int y, int x) {
        for (int i = 0; i < stickerInfo[k].R; i++) {
            for (int j = 0; j < stickerInfo[k].C; j++) {
                if (sticker[k][i][j] == 1) notebook[y + i][x + j] = true;
            }
        }
    }

    static void rotateSticker(int k) {
        int newR = stickerInfo[k].C;
        int newC = stickerInfo[k].R;
        int[][] newSticker = new int[10][10];
        for (int i = 0; i < newR; i++) {
            for (int j = 0; j < newC; j++) {
                newSticker[i][j] = sticker[k][newC - j - 1][i];
            }
        }
        for (int i = 0; i < 10; i++) sticker[k][i] = newSticker[i].clone();
        stickerInfo[k].R = newR;
        stickerInfo[k].C = newC;
    }

    static class Sticker {
        int R, C;

        public Sticker(int R, int C) {
            this.R = R;
            this.C = C;
        }
    }
}