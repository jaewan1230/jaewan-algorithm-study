import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ14939_불끄기 {
	static int min = 999;
	static boolean isPossible = false;
	static boolean[][] bulbs = new boolean[10][10];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < 10; i++) {
			String inStr = br.readLine();
			for (int j = 0; j < 10; j++) {
				bulbs[i][j] = inStr.charAt(j) == 'O' ? true : false;
			}
		}

		perm(0, 0, new boolean[10]);
		System.out.println(isPossible ? min : -1);
	}

	static void perm(int idx, int cnt, boolean[] switchOn) {
		if (cnt >= min)
			return;
		if (idx == 10) {
			int t = func(switchOn);
			if (t != -1) {
				isPossible = true;
				min = Math.min(min, t);
			}
			return;
		}
		switchOn[idx] = true;
		perm(idx + 1, cnt + 1, switchOn);
		switchOn[idx] = false;
		perm(idx + 1, cnt, switchOn);
	}

	static void toggleSwitch(int y, int x, boolean[][] temp) {
		int[] dy = { -1, 1, 0, 0, 0 }, dx = { 0, 0, -1, 1, 0 };
		for (int dir = 0; dir < 5; dir++) {
			int ty = y + dy[dir];
			int tx = x + dx[dir];
			if (ty < 0 || tx < 0 || ty >= 10 || tx >= 10)
				continue;
			temp[ty][tx] = !temp[ty][tx];
		}
	}

	static int func(boolean[] switchOn) {
		int cnt = 0;
		boolean[][] temp = new boolean[10][10];
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				temp[i][j] = bulbs[i][j];

		for (int j = 0; j < 10; j++) {
			if (switchOn[j]) {
				cnt++;
				toggleSwitch(0, j, temp);
			}
		}

		for (int i = 1; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (temp[i - 1][j]) { // 윗줄의 전구가 켜져있으면
					cnt++;
					toggleSwitch(i, j, temp);
				}
			}
		}

		boolean flag = true;
		for (int j = 0; j < 10; j++)
			if (temp[9][j])
				flag = false;

		return flag ? cnt : -1;
	}
}
