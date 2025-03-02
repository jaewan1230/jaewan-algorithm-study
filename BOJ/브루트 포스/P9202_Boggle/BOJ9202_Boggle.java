/*
 * 글자가 쓰여 있는 주사위로 이루어진 4*4의 그리드에서 최대한 많은 단어를 찾는 게임
 * 인접한 글자(가로, 세로, 대각선)을 이용해 만들 수 있다.
 * 
 * 게임 사전에 등재되어 있는 단어만 올바른 단어
 * 1글자, 2글자는 0점. 3글자, 4글자는 1점. 5글자, 6글자는 3점. 7글자는 5점. 8글자는 11점.
 * 
 * 단어 사전 단어의 수 w (1 < w < 300,000)
 * 단어는 최대 8글자, 대문자로만 이루어짐
 * 
 * Boggle 보드의 수 b (1 < b < 30)
 * 각 보드별로 dfs 로 모든 단어를 생성한다.
 * 
 * 사전은 HashSet에 저장, 길이가 1, 2글자는 점수가 0이어도 사전에서 찾은 개수는 count
 * 
 * visit 적용하면 한 보드당 만들 수 있는 문자열 개수는 44,104개
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

public class BOJ9202_Boggle {
	static int w, b, cnt = 0;
	static int[] dy = { -1, -1, -1, 0, 1, 1, 1, 0 }, dx = { -1, 0, 1, 1, 1, 0, -1, -1 };
	static boolean[][] visit;
	static char[][][] boards;
	static HashSet<String> dict = new HashSet<>();
	static TreeSet<String> find;

	static void dfs(int b, StringBuilder str, int y, int x, int len) { // b번째 보드로 단어 만들기
		// String 객체 말고 StringBuilder 객체를 사용해 문자열을 만들었다.
		// java에서 String 의 + 연산은 느려서, StringBuilder로 변환해 처리한다. 
		
		String word = str.toString();
		if (dict.contains(word)) { // str 단어 있으면 find에 추가.
			find.add(word);
		}
		if (len == 8) // 길이는 최대 8이다.
			return;
		for (int dir = 0; dir < 8; dir++) {
			int ty = y + dy[dir];
			int tx = x + dx[dir];
			if (ty < 0 || tx < 0 || ty >= 4 || tx >= 4 || visit[ty][tx])
				continue;
			visit[ty][tx] = true;
			str.append(boards[b][ty][tx]);  // 연결된 글자를 이어 만든다.
			dfs(b, str, ty, tx, len + 1);
			str.setLength(len); // 마지막 글자 자른다.
			visit[ty][tx] = false;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		w = Integer.parseInt(br.readLine());
		for (int i = 0; i < w; i++) {
			dict.add(br.readLine()); // 사전에 추가
		}
		br.readLine();
		b = Integer.parseInt(br.readLine());
		boards = new char[b][4][4];
		for (int k = 0; k < b; k++) {
			for (int i = 0; i < 4; i++) {
				String inStr = br.readLine();
				for (int j = 0; j < 4; j++)
					boards[k][i][j] = inStr.charAt(j); // 보드 입력
			}
			if (k < b - 1)
				br.readLine();
		}

		for (int k = 0; k < b; k++) {
			find = new TreeSet<>((o1, o2) -> o1.length() == o2.length() ? o1.compareTo(o2)
					: Integer.compare(o2.length(), o1.length())); // 결과 TreeSet에 저장되는 순서 Comparator 재정의
			visit = new boolean[4][4];
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					visit[i][j] = true;
					dfs(k, new StringBuilder().append(boards[k][i][j]), i, j, 1);
					visit[i][j] = false;
				}
			}

			int score = 0, cnt = find.size();
			Iterator<String> iter = find.iterator();
			String res = find.first();
			while (iter.hasNext()) {
				switch (iter.next().length()) { // 점수 계산
				case 3:
				case 4:
					score += 1;
					break;
				case 5:
					score += 2;
					break;
				case 6:
					score += 3;
					break;
				case 7:
					score += 5;
					break;
				case 8:
					score += 11;
					break;
				default:
				}
			}
			System.out.printf("%d %s %d\n", score, res, cnt);
		}
	}
}
