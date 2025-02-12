import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P15686 {
    static int homeCnt, chickenCnt, N, M, Min = 10000;
    static boolean[] selected = new boolean[13];
    static int[][] inArr = new int[50][50];
    static Node[] homeList = new Node[100], chickenList = new Node[13];

    public static void main(String[] args) throws IOException {
        input();
        func();
        System.out.println(Min);
    }

    static void func() {
        selectChicken(0, 0); // 폐업시키지 않을 치킨집 선택, chickenCnt 중 M개, 재귀
    }

    static void selectChicken(int k, int cnt) {
        if (cnt == M) {
            int res = calcChickenDist();
            if (res < Min) Min = res;
            return;
        }
        if (k == chickenCnt) return;
        selected[k] = true;
        selectChicken(k + 1, cnt + 1);
        selected[k] = false;
        selectChicken(k + 1, cnt);
    }

    static int calcChickenDist() {
        int res = 0;
        for (int i = 0; i < homeCnt; i++) {
            int min = 10000;
            for (int j = 0; j < chickenCnt; j++) {
                if (!selected[j]) continue;
                int t = Math.abs(homeList[i].y - chickenList[j].y) + Math.abs(homeList[i].x - chickenList[j].x);
                if (t < min) min = t;
            }
            res += min;
        }
        return res;
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                inArr[i][j] = Integer.parseInt(st.nextToken());
                if (inArr[i][j] == 1) homeList[homeCnt++] = new Node(i, j);
                if (inArr[i][j] == 2) chickenList[chickenCnt++] = new Node(i, j);
            }
        }
    }

    static class Node {
        int y, x;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}