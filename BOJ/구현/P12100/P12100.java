import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P12100 {
    static int N, max;
    static int[][] board = new int[20][20], inBoard = new int[20][20];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                inBoard[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < 1024; i++) {
            int t = i;
            for (int j = 0; j < N; j++) board[j] = inBoard[j].clone();
            for (int j = 0; j < 5; j++) {
                move(t % 4);
                t /= 4;
            }
        }
        System.out.println(max);
    }

    static void printBoard() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.printf("%d ", board[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    static void move(int dir) { // dir : 0 상, 1: 하, 2: 좌, 3:우
        if (dir == 0) {
            for (int j = 0; j < N; j++) {
                int prevNum = 0, curIdx = 0;
                int[] newArr = new int[20];
                for (int i = 0; i < N; i++) {
                    if (board[i][j] == 0) continue;
                    if (prevNum == 0) prevNum = newArr[curIdx++] = board[i][j];
                    else if (prevNum == board[i][j]) { // 같으면 합치기
                        newArr[curIdx - 1] *= 2;
                        prevNum = 0;
                    } else { // 다르면
                        prevNum = newArr[curIdx++] = board[i][j];
                    }
                }
                for (int i = 0; i < N; i++) {
                    board[i][j] = newArr[i];
                    if (max < board[i][j]) max = board[i][j];
                }
            }
        }
        if (dir == 1) {
            for (int j = 0; j < N; j++) {
                int prevNum = 0, curIdx = 0;
                int[] newArr = new int[20];
                for (int i = N - 1; i >= 0; i--) {
                    if (board[i][j] == 0) continue;
                    if (prevNum == 0) prevNum = newArr[curIdx++] = board[i][j];
                    else if (prevNum == board[i][j]) { // 같으면 합치기
                        newArr[curIdx - 1] *= 2;
                        prevNum = 0;
                    } else { // 다르면
                        prevNum = newArr[curIdx++] = board[i][j];
                    }
                }
                for (int i = 0; i < N; i++) {
                    board[N - i - 1][j] = newArr[i];
                    if (max < board[i][j]) max = board[i][j];
                }
            }
        }
        if (dir == 2) {
            for (int i = 0; i < N; i++) {
                int prevNum = 0, curIdx = 0;
                int[] newArr = new int[20];
                for (int j = 0; j < N; j++) {
                    if (board[i][j] == 0) continue;
                    if (prevNum == 0) prevNum = newArr[curIdx++] = board[i][j];
                    else if (prevNum == board[i][j]) { // 같으면 합치기
                        newArr[curIdx - 1] *= 2;
                        prevNum = 0;
                    } else { // 다르면
                        prevNum = newArr[curIdx++] = board[i][j];
                    }
                }
                for (int j = 0; j < N; j++) {
                    board[i][j] = newArr[j];
                    if (max < board[i][j]) max = board[i][j];
                }
            }
        }
        if (dir == 3) {
            for (int i = 0; i < N; i++) {
                int prevNum = 0, curIdx = 0;
                int[] newArr = new int[20];
                for (int j = N - 1; j >= 0; j--) {
                    if (board[i][j] == 0) continue;
                    if (prevNum == 0) prevNum = newArr[curIdx++] = board[i][j];
                    else if (prevNum == board[i][j]) { // 같으면 합치기
                        newArr[curIdx - 1] *= 2;
                        prevNum = 0;
                    } else { // 다르면
                        prevNum = newArr[curIdx++] = board[i][j];
                    }
                }
                for (int j = 0; j < N; j++) {
                    board[i][N - j - 1] = newArr[j];
                    if (max < board[i][j]) max = board[i][j];
                }
            }
        }
    }
}