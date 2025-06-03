
/*
 * R * C 위의 보드판에서 미친 아두이노를 피해다니기
 * 1. 종수가 아두이노를 8방향 이동시키거나, 제자리에 놔둠
 * 2. 종수의 아두이노가 미친 아두이노가 있는 칸으로 이동하면 짐
 * 3. 미친 아두이노는 8방향 중에서 종수의 아두이노와 가장 가까워 지는 방향으로 한 칸 이동.
 *     즉, |r1 - r2| + |s1 - s2| 가 가장 작아지는 방향으로 이동
 * 4. 미친 아두이노가 종수의 아두이노가 있는 칸으로 이동하면 게임이 끝나고, 종수는 게임을 지게 됨.
 * 5. 2개 또는 그 이상의 미친 아두이노가 같은 칸에 있는 경우에는 큰 폭발이 일어나고, 그 칸에 있는 아두이노는 모두 파괴됨.
 * 
 * 8방형 이동이 가능하기 때문에, 미친 아두이노가 이동할 위치는 하나로 특정됨.
 * 미친 아두이노는 y 좌표와 x좌표를 종수의 아두이노와 비교해서 해당 방향으로 한칸씩 이동.
 * 이동할 때마다 지도를 그리자.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    static int R, C;
    static int[][] map;
    static Pos jongsu;
    static ArrayList<Pos> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        R = Integer.parseInt(in[0]);
        C = Integer.parseInt(in[1]);
        map = new int[R][C];
        for (int i = 0; i < R; i++) {
            String inStr = br.readLine();
            for (int j = 0; j < C; j++) {
                switch (inStr.charAt(j)) {
                    case 'I':
                        jongsu = new Pos(i, j);
                        break;
                    case 'R':
                        list.add(new Pos(i, j));
                        break;
                }
            }
        }
        int result = func(br.readLine());
        if (result == -1) {
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    switch (map[i][j]) {
                        case 1:
                            System.out.printf("I");
                            break;
                        case 2:
                            System.out.printf("R");
                            break;
                        default:
                            System.out.printf(".");
                            break;
                    }
                }
                System.out.println();
            }
        } else {
            System.out.printf("kraj %d", result);
        }
    }

    static int func(String moves) {
        int[] dy = { 0, 1, 1, 1, 0, 0, 0, -1, -1, -1 }, dx = { 0, -1, 0, 1, -1, 0, 1, -1, 0, 1 };
        for (int move = 0; move < moves.length(); move++) {
            int[][] newMap = new int[R][C];
            int dir = moves.charAt(move) - '0';
            jongsu.y += dy[dir];
            jongsu.x += dx[dir];
            if (map[jongsu.y][jongsu.x] == 2)
                return move + 1;
            newMap[jongsu.y][jongsu.x] = 1;

            ArrayList<Pos> newList = new ArrayList<>();
            for (Pos pos : list) {
                int ny = pos.y, nx = pos.x;
                if (pos.y > jongsu.y)
                    ny--;
                else if (pos.y < jongsu.y)
                    ny++;
                if (pos.x > jongsu.x)
                    nx--;
                else if (pos.x < jongsu.x)
                    nx++;

                if (newMap[ny][nx] == 1)
                    return move + 1;
                // 이미 미친 아두이노가 있으면 터짐
                if (newMap[ny][nx] == 2 || newMap[ny][nx] == -1) {
                    newMap[ny][nx] = -1;
                } else
                    newMap[ny][nx] = 2;
            }

            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (newMap[i][j] == 2)
                        newList.add(new Pos(i, j));
                }
            }
            map = newMap;
            list = newList;
        }
        return -1;
    }

    static class Pos {
        int y, x;

        public Pos(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}