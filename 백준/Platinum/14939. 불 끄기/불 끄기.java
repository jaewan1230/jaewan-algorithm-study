/*
 * 전구 100개 10*10. 정사각형 모양
 * 전구 스위치 누르면 4방 전구 상태도 바뀜.
 * 
 * 전구 100개 상태가 주어지면 모든 전구를 끄기 위해 최소한으로 눌러야 하는 스위치의 개수를 출력.
 * 켜진 전구 기준으로, 켜진 전구, 상하좌우 꺼서 해당 전구를 끌 수 있다.
 * 
 * 눌렀던 스위치는 다시 누르면 안된다. 원상복구됨.
 * 스위치 100개에 대해 누를지 말지 결정.
 * 
 * 2^100 는 안된다.
 * 
 * 제일 윗줄부터 차례대로 스위치를 눌러야될지 안 누를지 판단한다.
 * 그리고 2번째 줄 부터는, 윗줄의 전구가 켜져있으면 스위치를 누른다.
 * 
 * 윗줄의 전구는, 아래에서 누르는 거 아니면 이후로는 끌 수 있는 방법이 없음.
 * 
 * 그래서 첫 줄만, 연속해서 있는경우, 스위치를 누르고
 * 두번째 줄부터는 윗줄의 전구가 켜져있으면 스위치를 누른다.
 * 
 * 마지막 줄까지 판단하고, 켜져있는 스위치가 있으면 불가능한 경우, -1 출력
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
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

		perm(0, new boolean[10]);
		System.out.println(isPossible ? min : -1);
	}

	static void perm(int idx, boolean[] switchOn) {
		if (idx == 10) {
			int t = func(switchOn);
			if (t != -1) {
				isPossible = true;
				min = Math.min(min, t);
			}
			return;
		}
		switchOn[idx] = true;
		perm(idx + 1, switchOn);
		switchOn[idx] = false;
		perm(idx + 1, switchOn);
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
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++)
				if (temp[i][j])
					flag = false;
		}

		return flag ? cnt : -1;
	}

	static void print(boolean[][] bulbs) {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++)
				System.out.printf("%c", bulbs[i][j] ? 'O' : '#');
			System.out.println();
		}
	}
}
