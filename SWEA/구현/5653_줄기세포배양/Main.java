import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			int[][] map = new int[1000][1000];
			PriorityQueue<Cell> inactiveCell = new PriorityQueue<>((o1, o2) -> o2.hp - o1.hp);
			for (int i = 400; i < N + 400; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 400; j < M + 400; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] != 0) {
						inactiveCell.add(new Cell(i, j, map[i][j]));
					}
				}
			}
			PriorityQueue<Cell> activeCell = new PriorityQueue<>((o1, o2) -> o2.hp - o1.hp);
			List<Cell> spreadList = new ArrayList<>();

			for (int time = 0; time < K; time++) {

				// 활성 -> 사망
				PriorityQueue<Cell> temp = new PriorityQueue<>((o1, o2) -> o2.hp - o1.hp);
				while (!activeCell.isEmpty()) {
					Cell c = activeCell.poll();
					if (map[c.y][c.x] == c.hp) { // 처음 활성화 됐으면, 상하좌우 번식
						int[] dy = { 0, 0, 1, -1 }, dx = { 1, -1, 0, 0 };
						for (int dir = 0; dir < 4; dir++) {
							int ty = c.y + dy[dir];
							int tx = c.x + dx[dir];
							if (map[ty][tx] != 0) // 빈칸 아니면
								continue;
							spreadList.add(new Cell(ty, tx, c.hp)); // 번식 추가
							map[ty][tx] = 1;
						}
					}
					if (--map[c.y][c.x] == 0)
						map[c.y][c.x] = c.hp;
					else
						temp.add(c); // 안 죽었으면 다시 activeCell에 추가
				}
				activeCell = temp;
				// 비활성 -> 활성

				temp = new PriorityQueue<>((o1, o2) -> o2.hp - o1.hp);
				while (!inactiveCell.isEmpty()) {
					Cell c = inactiveCell.poll();
					if (--map[c.y][c.x] == 0) { // 활성화
						activeCell.add(c); // 활성화 목록에 추가
						map[c.y][c.x] = c.hp;
					} else {
						temp.add(c);
					}
				}
				inactiveCell = temp;

				// 번식 리스트 -> 1시간 뒤 비활성 추가
				Iterator<Cell> iter = spreadList.iterator();
				while (iter.hasNext()) {
					Cell c = iter.next();
					if (--map[c.y][c.x] == 0) {
						inactiveCell.add(c);
						map[c.y][c.x] = c.hp;
						iter.remove();
					}
				}
			}
			System.out.printf("#%d %d\n", tc, inactiveCell.size() + activeCell.size());
		}
	}

	static class Cell {
		int y, x, hp;

		public Cell(int y, int x, int hp) {
			this.y = y;
			this.x = x;
			this.hp = hp;
		}

		@Override
		public String toString() {
			return "Cell [y=" + y + ", x=" + x + ", hp=" + hp + "]";
		}

	}
}
