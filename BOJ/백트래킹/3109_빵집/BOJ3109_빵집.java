/*
 * 가스를 훔쳐서 빵집의 경영난을 해소하려고 한다.
 * 
 * 첫째 열과 마지막 열을 연결
 * 파이프는 오른쪽, 오른쪽 위 대각선, 오른쪽 아래 대각선으로 연결 가능.
 * 겹치거나 건물이 있으면 파이프 놓을 수 없다.
 * 
 * 첫째 열에서 시작해 파이프 연결 시작, 오른쪽 위, 오른쪽, 오른쪽 아래 순으로 가지 뻗어나가며 파이프 놓기.
 * 한번 파이프 놓았으면, flag로 return 하기.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ3109_빵집 {
	static int R, C, cnt;
	static boolean flag;
	static boolean[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		R = Integer.parseInt(input[0]);
		C = Integer.parseInt(input[1]);

		map = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			String inStr = br.readLine();
			for (int j = 0; j < C; j++)
				if (inStr.charAt(j) == 'x')
					map[i][j] = true;
		}

		for (int i = 0; i < R; i++) {
			flag = false;
			putPipe(i, 0);
		}
		System.out.println(cnt);
	}

	static void putPipe(int y, int x) {
		if (x == C - 1) { // 파이프 연결
			flag = true;
			cnt++;
			return;
		}

		for (int i = -1; i <= 1; i++) {
			int ny = y + i;
			int nx = x + 1;
			if (ny < 0 || ny >= R || nx >= C || map[ny][nx])
				continue;
			map[ny][nx] = true;
			putPipe(ny, nx);
			if (flag)
				return;
		}
	}
}
