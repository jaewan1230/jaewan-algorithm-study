/*
 * 6개국 각 국가들과 한번씩 총 5번의 경리.
 * 
 * 조별리그가 끝난 후, 기자가 보내온 승무패 결과가 valid한지 판별.
 * 
 * A부터, 상대 국가들과 매치되는지 확인하기.
 */

import java.io.IOException;

public class BOJ6987_월드컵 {
	static int[][] score = new int[6][3];

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 18; j++) {
				score[j / 3][j % 3] = System.in.read() & 15;
				System.in.read();
			}
			sb.append(isValid() ? 1 : 0).append(" ");
		}
		System.out.println(sb.toString());
	}

	private static boolean isValid() {
		return match(0, 1);
	}

	static boolean match(int a, int b) {
		if (b >= 6) {
			a++;
			b = a + 1;
		}
		if (a >= 5) {
			for (int i = 0; i < 6; i++)
				for (int j = 0; j < 3; j++)
					if (score[i][j] != 0)
						return false;
			return true;
		}

		// a가 이기는 경우
		if (score[a][0] > 0 && score[b][2] > 0) {
			score[a][0]--;
			score[b][2]--;
			if(match(a, b + 1)) return true;
			score[a][0]++;
			score[b][2]++;
		}
		// 무승부
		if (score[a][1] > 0 && score[b][1] > 0) {
			score[a][1]--;
			score[b][1]--;
			if(match(a, b + 1)) return true;
			score[a][1]++;
			score[b][1]++;
		}
		// a가 지는 경우
		if (score[a][2] > 0 && score[b][0] > 0) {
			score[a][2]--;
			score[b][0]--;
			if(match(a, b + 1)) return true;
			score[a][2]++;
			score[b][0]++;
		}

		return false;
	}
}
