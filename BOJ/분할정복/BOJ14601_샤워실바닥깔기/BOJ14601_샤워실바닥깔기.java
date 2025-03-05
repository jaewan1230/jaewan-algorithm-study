import java.io.IOException;

/*
 * 바닥을 까는게, 샤워실이 한 변의 길이가 2의 제곱수.
 * 배수구를 고려해야 함.
 * 4칸을 차지하는 정사각형 타일 대신, 3칸을 차지하는 ㄱ자 모양의 타일을 사용
 * 
 * 한 변의 길이 1 <= K <= 7, 바닥의 크기는 2^K.
 * 배수구의 위치 x, y가 주어짐.
 * 타일마다 번호를 매긴 배치도를 출력.
 * 
 * 분할정복. 4개 사분면으로 나눠서 구멍 위치와 사이즈를 주며 호출함.
 * 시작 지점 좌표, 구멍 좌표, 사이즈
 * 
 * 
 * 
 */
public class BOJ14601_샤워실바닥깔기 {
	static int cnt, size;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		size = 1 << readInt();
		int x = readInt(), y = readInt();
		map = new int[size][size];
		map[--y][--x] = -1;
		fillFloor(size, 0, 0, y, x);

		StringBuilder sb = new StringBuilder();
		for (int i = size - 1; i >= 0; i--) {
			for (int j = 0; j < size; j++)
				sb.append(map[i][j]).append(" ");
			sb.append('\n');
		}
		System.out.println(sb.toString());
	}

	private static void fillFloor(int size, int sy, int sx, int hy, int hx) {
		int nextSize = size >> 1, type;
		// 구멍 사분면 판단
		if (hy < sy + nextSize) {
			if (hx < sx + nextSize)
				type = 0;
			else
				type = 1;
		} else {
			if (hx < sx + nextSize)
				type = 2;
			else
				type = 3;
		}

		// size==2 일때, 구멍 위치 제외하고 타일 놓음.
		if (size == 2) {
			putTile(type, sy, sx);
		}

		else {
			// 구멍 있는 사분면 제외, 붙어있는 세 사분면에 걸치게 ㄱ자 타일 하나 놓음.
			putTile(type, sy + nextSize - 1, sx + nextSize - 1);

			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 2; j++) {
					if (type == i * 2 + j) {
						// 구멍이 있는 사분면
						fillFloor(nextSize, sy + i * nextSize, sx + j * nextSize, hy, hx);
					} else {
						// 구멍이 없는 사분면, 처음 놓은 타일을 구멍 위치로 넘겨줌.
						fillFloor(nextSize, sy + i * nextSize, sx + j * nextSize, sy + nextSize - 1 + i,
								sx + nextSize - 1 + j);
					}
				}
			}
		}

	}

	private static void putTile(int type, int sy, int sx) {
		cnt++;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				if (type == i * 2 + j)
					continue;
				map[sy + i][sx + j] = cnt;
			}
		}
	}

	private static int readInt() throws IOException {
		int c;
		do {
			c = System.in.read();
		} while (c <= 32);
		int n = (c & 15);
		while ((c = System.in.read()) > 47) {
			n = (n << 3) + (n << 1) + (c & 15);
		}
		return n;
	}
}
