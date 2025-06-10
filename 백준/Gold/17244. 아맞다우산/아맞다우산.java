/*
 * 필요한 물건들을 챙겨 외출하기까지 최소 몇 걸음이 필요한지.
 * 
 * S 출발, X 챙겨서, E 까지 도착하는 최소 시간.
 * 
 * 필요한 물건은 최대 5개.
 * 3차원 bfs로, visit 비트필드
 * 1 << (cnt+1) 만큼이니까, 63
 * N, M <= 50
 * 
 * 이 빠를까, 그냥 순서를 정해서 탐색하고, 최솟값을 찾는 게 빠를까.
 * 5!이니까, 120번 도는거고, 비트필드로 visit 하면, bfs 한번이니까 그래도 이게 더 빠르지 않을까.
 * 
 * 71퍼에서 메모리 초과 ????
 * visit 배열이 적절하지 못했음.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] in = br.readLine().split(" ");
        int N = Integer.parseInt(in[1]), M = Integer.parseInt(in[0]), cnt = 1;

        int[][] map = new int[N][M];

        int startY = 0, startX = 0, endY = 0, endX = 0;
        for (int i = 0; i < N; i++) {
            String inStr = br.readLine();
            for (int j = 0; j < M; j++) {
                switch (inStr.charAt(j)) {
                    case '#':
                        map[i][j] = -1;
                        break;
                    case 'S':
                        startY = i;
                        startX = j;
                        break;
                    case 'E':
                        endY = i;
                        endX = j;
                        break;
                    case 'X':
                        map[i][j] = cnt++;
                        break;
                    default:
                        map[i][j] = 0;
                }
            }
        }

        boolean[][][] visit = new boolean[N][M][1 << cnt];

        int endBit = (1 << cnt) - 1;

        int[] dy = { -1, 1, 0, 0 }, dx = { 0, 0, -1, 1 };
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] { startY, startX, 0b1, 0 });
        visit[startY][startX][0] = true;
        while (!q.isEmpty()) {
            int[] cur = q.poll();

            if (cur[0] == endY && cur[1] == endX && cur[2] == endBit) {
                System.out.println(cur[3]);
                break;
            }

            int ny, nx;
            for (int i = 0; i < 4; i++) {
                ny = cur[0] + dy[i];
                nx = cur[1] + dx[i];

                if (ny < 0 || nx < 0 || ny >= N || nx >= M || map[ny][nx] == -1)
                    continue;
                int nt = cur[3] + 1, nBit = cur[2];
                if (map[ny][nx] != 0)
                    nBit |= (1 << map[ny][nx]);
                if (visit[ny][nx][nBit])
                    continue;

                visit[ny][nx][nBit] = true;
                q.offer(new int[] { ny, nx, nBit, nt });
            }
        }
    }
}