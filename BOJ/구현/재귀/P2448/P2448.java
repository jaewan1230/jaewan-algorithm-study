import java.io.*;
import java.util.Arrays;

public class P2448 {
    static char map[][];
    static char star[][] = {"  *  ".toCharArray(), " * * ".toCharArray(), "*****".toCharArray()};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        map = new char[n][2 * n - 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(map[i], ' ');
        }
        printStar(n, 0, 0);
        for (char[] str : map) {
            bw.write(str);
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }

    static void printStar(int size, int y, int x) {
        if (size == 3) {
            map[y][x + 2] = '*';

            map[y + 1][x + 1] = '*';
            map[y + 1][x + 3] = '*';

            for (int j = 0; j < 5; j++) {
                map[y + 2][x + j] = '*';
            }
            return;
        }
        int nextSize = size / 2;
        printStar(nextSize, y, x + size / 2);
        printStar(nextSize, y + size / 2, x);
        printStar(nextSize, y + size / 2, x + size);
    }
}