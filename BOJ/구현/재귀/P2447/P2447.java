import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P2447 {
    static char[][] map = new char[2500][2500];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        printStar(n, 0, 0);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(String.valueOf(map[i], 0, n) + "\n");
        }
        System.out.println(sb);
    }

    static void printStar(int size, int y, int x) {
        if (size == 1) {
            map[y][x] = '*';
            return;
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 1 && j == 1) {
                    printSpace(size / 3, y + size / 3, x + size / 3);
                    continue;
                }
                printStar(size / 3, y + size / 3 * i, x + size / 3 * j);
            }
        }
    }

    static void printSpace(int size, int y, int x) {
        for (int i = 0; i < size; i++) {
            Arrays.fill(map[y + i], x, x + size, ' ');
        }
    }
}