import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/*
 * R*C 맵에서 지뢰
 * 지뢰 폭발할때 4방 함께 파괴, 지뢰 있어도 연쇄 폭발은 안함.
 * 지뢰는 3초 후 폭발
 * 빈칸은 . 지뢰는 o
 * 
 * 처음에 설치하고, 1초 대기
 * 1초 동안 설치되어 있지 않은 모든 칸에 지뢰 설치.
 * N초 후의 격자판의 상태.
 * 0은 빈 칸, 숫자는 터지는 시간을 표시해야겠다.
 * 
 * 0초 초기->1초 대기->2초 설치->3초 폭발->4초 설치->5초 폭발->6초 설치->7초 폭발->8초 설치->9초 폭발
 * 1초는 초기 상태와 동일, 2초는 전부 설치.
 * 2초 부터, 짝수 초에 빈칸에 설치, 홀수 초에 폭발을 반복함.
 * 짝수 초에는 모든 칸에 지뢰가 설치됨.
 * 홀수 칸에는 일정 시간부터 동일 패턴이 반복됨. 4초 간격으로 동일 패턴.
 * 
 * 초기 상태가 5초, 9초, 13초 , ... 후 상태와 동일하고,
 * 3초 후 상태가 7초, 11초, 15초, ... 후 상태와 동일하다.
 * 
 */
public class Main {
	static int R, C, N;
	static int[][] map;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		R = Integer.parseInt(input[0]);
		C = Integer.parseInt(input[1]);
		N = Integer.parseInt(input[2]);
		map = new int[R][C];
		for (int i = 0; i < R; i++) {
			String inStr = br.readLine();
			for (int j = 0; j < C; j++) {
				if (inStr.charAt(j) == '.')
					map[i][j] = 0;
				else
					map[i][j] = 3;
			}
		} // 입력 끝

		func();
	}

	static void func() {
		int time = 2;
		while (N >= 2) {
			if (time % 2 == 0) { // 짝수 초 설치
				for (int i = 0; i < R; i++) {
					for (int j = 0; j < C; j++) {
						if (map[i][j] == 0) // 빈칸이면 설치, 터지는 시간은 현재 시간 + 3
							map[i][j] = time + 3;
					}
				}
			} else if (time % 2 == 1) { // 홀수이면 폭발, 사방 연쇄 폭발.
				LinkedList<Pos> list = new LinkedList<>();
				for (int i = 0; i < R; i++) {
					for (int j = 0; j < C; j++) {
						if (map[i][j] == time) { // 지금 폭발하는 애면
							list.add(new Pos(i, j));
						}
					}
				}

				int[] dy = { 0, 0, 0, 1, -1 }, dx = { 0, 1, -1, 0, 0 };
				for (Pos p : list) {
					for (int dir = 0; dir < 5; dir++) {
						int ty = p.y + dy[dir];
						int tx = p.x + dx[dir];
						if (ty < 0 || tx < 0 || ty >= R || tx >= C)
							continue;
						map[ty][tx] = 0;
					}
				}
			}
//			System.out.printf("%d초 후------\n", time);
//			printMap(true);
			if ((N - time) % 4 == 0) // 4초마다 동일 패턴 반복됨.
				break;
			time++;
		}
		printMap(false);
	}

	static void printMap(boolean isDebug) {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (isDebug)
					System.out.printf("%d ", map[i][j]);
				else {
					if (map[i][j] == 0)
						System.out.printf(".");
					else if (map[i][j] > 0)
						System.out.printf("O");
				}
			}
			System.out.println();
		}
	}

	static class Pos {
		int y, x;

		public Pos(int y, int x) {
			this.y = y;
			this.x = x;
		}

	}
}
