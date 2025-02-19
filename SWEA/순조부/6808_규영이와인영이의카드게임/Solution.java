/*
 * 1 ~ 18 숫자가 적힌 18장의 카드를 9장씩 나눔.
 * 
 * 한 장씩 카드를 내서 상대방과 비교해 높은 수를 낸 사람이 합만큼 점수를 얻음.
 * 총점이 높은 사람이 승리, 총점이 같으면 무승부.
 * 규영이가 내는 순서가 주어질 때, 인영이가 카드를 내는 9!가지 순서에 따라 규영이가 이기는 경우와 지는 경우를 구해라.
 * 
 * 1 3 5 7 9 11 13 15 19
 * 
 * #1 112097 250783
 * 
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Solution {
	static int winCnt, loseCnt;
	static int[] deck, inDeck;
	static boolean[] isSelected;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer st = new StreamTokenizer(br);
		int TC = nextInt(st);

		for (int tc = 1; tc <= TC; tc++) {
			inDeck = new int[9];
			isSelected = new boolean[19];
			for (int i = 0; i < 9; i++) {
				inDeck[i] = nextInt(st);
				isSelected[inDeck[i]] = true;
			}
			deck = new int[9];
			winCnt = loseCnt = 0;
			makePerm(0);
			System.out.printf("#%d %d %d\n", tc, winCnt, loseCnt);
		}
	}

	static void playGame() {
		int sum = 0;
		for (int i = 0; i < 9; i++)
			sum += inDeck[i] > deck[i] ? inDeck[i] + deck[i] : 0;

		if (sum > 171 - sum)
			winCnt++;
		else if (sum < 171 - sum)
			loseCnt++;
	}

	static void makePerm(int depth) {
		if (depth == 9) {
			playGame();
			return;
		}
		for (int i = 1; i <= 18; i++) {
			if (!isSelected[i]) {
				isSelected[i] = true;
				deck[depth] = i;
				makePerm(depth + 1);
				isSelected[i] = false;
			}
		}
	}

	private static int nextInt(StreamTokenizer st) throws IOException {
		st.nextToken();
		return (int) st.nval;
	}
}