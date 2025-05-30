
/*
* 그림을 소유했던 사람의 수의 최댓값을 출력
 * arr[i][j] = j번 예술가가 i번 예술가에게 그림을 그 그림을 살 때의 가격
 * 
 * 그림을 팔 때, 그림을 산 가격보다 크거나 같은 가격으로 팔아야 함. 증가해야 함.
 * 같은 그림을두 번 이상 사는 것은 불가능
 * 
 * 처음은 1번 아티스트가.
 * N <= 15
 * 
 * 2차원 dp[i][j] = i에서 시작해 j까지 오는 최대 길이.
 * dp[i][j] = dp[i][k] + dp[k][j] 이렇게 되니까 그 n^3 돌리는거 뭐더라 그거 하면 되겠는데
 * 
 */
import java.io.IOException;

public class Main {
    static int N, max;
    static int[][] price;
    static boolean[][][] visit;

    public static void main(String[] args) throws IOException {
        N = readInt();
        price = new int[N][N];
        visit = new boolean[N][10][1 << (N + 1)];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                price[i][j] = System.in.read() - '0';
            System.in.read();
        }
        visit[0][0][1] = true;
        dfs(0, 0, 1, 1);
        System.out.println(max);
    }

    static void dfs(int idx, int cost, int cnt, int path) {
        max = Math.max(max, cnt);

        for (int i = 0; i < N; i++) {
            int next = path | (1 << i);
            if ((1 << i & path) != 0 || cost > price[idx][i] || visit[i][price[idx][i]][next])
                continue;
            visit[i][price[idx][i]][next] = true;
            dfs(i, price[idx][i], cnt + 1, path | (1 << i));
        }
    }

    static int readInt() throws IOException {
        int c;
        while ((c = System.in.read()) <= 32)
            ;
        int n = c & 15;
        while ((c = System.in.read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;

    }
}