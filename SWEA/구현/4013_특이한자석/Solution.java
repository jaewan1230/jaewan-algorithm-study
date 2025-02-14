import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int[][] magnet = new int[5][8];
	static int[] pointer;
	static boolean[] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int K = Integer.parseInt(br.readLine());
			for (int i = 1; i <= 4; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 8; j++)
					magnet[i][j] = Integer.parseInt(st.nextToken());
			}
			pointer = new int[5];
			for (int i = 0; i < K; i++) {
				String[] input = br.readLine().split(" ");
				int target = Integer.parseInt(input[0]);
				int dir = Integer.parseInt(input[1]);
				visit = new boolean[5];
				visit[target] = true;
				func(target, dir);
//				System.out.printf("%d번째 돌린 후\n", i + 1);
//				print();
			}
			int score = 0;
			for (int i = 1; i <= 4; i++) {
				if (magnet[i][pointer[i]] == 1)
					score += 1 << (i - 1);
			}
			System.out.printf("#%d %d\n", tc, score);
		}
	}

	static void print() {
		for (int i = 1; i <= 4; i++) {
			for (int j = 0; j < 8; j++) {
				System.out.printf("%d ", magnet[i][(pointer[i] + j) % 8]);
			}
			System.out.println();
		}
		System.out.println("---------");
	}

	static void func(int target, int dir) {
		if (target - 1 >= 1 && target - 1 <= 4 && !visit[target - 1]
				&& magnet[target][(pointer[target] + 6) % 8] != magnet[target - 1][(pointer[target - 1] + 2) % 8]) {
			visit[target - 1] = true;
			func(target - 1, -dir);
		}
		if (target + 1 >= 1 && target + 1 <= 4 && !visit[target + 1]
				&& magnet[target][(pointer[target] + 2) % 8] != magnet[target + 1][(pointer[target + 1] + 6) % 8]) {
			visit[target + 1] = true;
			func(target + 1, -dir);
		}

		switch (dir) { // 회전
		case 1:
			pointer[target] = (pointer[target] + 7) % 8;
			break;
		case -1:
			pointer[target] = (pointer[target] + 1) % 8;
			break;
		}
	}
}
