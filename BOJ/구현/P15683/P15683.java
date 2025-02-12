import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P15683 {
    static cctvInfo[] list = new cctvInfo[8];
    static boolean[][] isNotBlind, dir = new boolean[8][4];
    static int N, M, cnt, min = 100;
    static int[][] map = new int[8][8];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 6) map[i][j] = -1;
                if (map[i][j] > 0) {
                    list[cnt++] = new cctvInfo(i, j, map[i][j]);
                }
            }
        }
        setDir(0);
        System.out.println(min);
    }

    static void setDir(int k) {
        if (k == cnt) { // 마지막 거까지 방향 다 정하면
            spreadMap();
            int res = countBlind();
            if (res < min) min = res;
            return;
        }
        if (list[k].type == 1) { // 0 : 상, 1 : 우, 2 : 하, 3 : 좌
            for (int i = 0; i < 4; i++) {
                dir[k][i] = true;
                setDir(k + 1);
                dir[k][i] = false;
            }
        }
        if (list[k].type == 2) { // 0: 좌우, 1 : 상하
            for (int i = 0; i < 2; i++) {
                dir[k][i] = true;
                dir[k][i + 2] = true;
                setDir(k + 1);
                dir[k][i] = false;
                dir[k][i + 2] = false;
            }
        }
        if (list[k].type == 3) { // 0: 상우, 1 : 우하, 2: 좌하, 3: 좌상
            for (int i = 0; i < 4; i++) {
                dir[k][i] = true;
                dir[k][(i + 1) % 4] = true;
                setDir(k + 1);
                dir[k][i] = false;
                dir[k][(i + 1) % 4] = false;
            }
        }
        if (list[k].type == 4) { // 0: 상빼고, 1 : 우뺴고, 2: 하뺴고, 3: 좌빼고
            dir[k][0] = dir[k][1] = dir[k][2] = dir[k][3] = true;
            for (int i = 0; i < 4; i++) {
                dir[k][i] = false;
                setDir(k + 1);
                dir[k][i] = true;
            }
        }
        if (list[k].type == 5) { // 얘는 전부
            for (int i = 0; i < 4; i++) dir[k][i] = true;
            setDir(k + 1);
        }
    }

    static void spreadMap() { // CCTV 감시지대 칠하기
        isNotBlind = new boolean[8][8];
        for (int k = 0; k < cnt; k++) { // k번째 cctv
            int y = list[k].y;
            int x = list[k].x;
            isNotBlind[y][x] = true;
            if (dir[k][0]) {
                for (int i = y; i >= 0; i--) {
                    isNotBlind[i][x] = true;
                    if (map[i][x] == -1) break;
                }
            }
            if (dir[k][1]) {
                for (int j = x; j < M; j++) {
                    isNotBlind[y][j] = true;
                    if (map[y][j] == -1) break;
                }
            }
            if (dir[k][2]) {
                for (int i = y; i < N; i++) {
                    isNotBlind[i][x] = true;
                    if (map[i][x] == -1) break;
                }
            }
            if (dir[k][3]) {
                for (int j = x; j >= 0; j--) {
                    isNotBlind[y][j] = true;
                    if (map[y][j] == -1) break;
                }
            }
        }
    }

    static int countBlind() { // 사각지대 개수 count
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j]==-1) isNotBlind[i][j]=true;
                if (!isNotBlind[i][j]) cnt++;
            }
        }
        return cnt;
    }

    static class cctvInfo {
        int y, x, type;

        public cctvInfo(int y, int x, int type) {
            this.y = y;
            this.x = x;
            this.type = type;
        }
    }
}