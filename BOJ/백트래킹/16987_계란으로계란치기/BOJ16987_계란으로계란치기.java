/*
 * 계란으로 계란을 쳐서 계란을 깬다.
 * 
 * 계란에는 내구도와 무게가 있다.
 * 계란으로 계란을 치면, 상대 계란의 무게만큼 내구도가 깎인다.
 * 내구도가 0 이하가 되면 계란은 깨진다.
 * 
 * 계란을 왼쪽부터 차례로 들어 다른 계란을 쳐서 최대한 많은 계란을 깨는 문제.
 * 최대 몇 개의 계란을 깰 수 있는지 출력.
 * 
 * N <= 8
 * 
 * crashEgg(i, j) i를 들어 j 번째 계란 치기
 */

import java.io.IOException;

public class BOJ16987_계란으로계란치기 {
	static int N, max;
	static int[] s, w;

	public static void main(String[] args) throws IOException {
		N = readInt();
		s = new int[N];
		w = new int[N];
		for (int i = 0; i < N; i++) {
			s[i] = readInt();
			w[i] = readInt();
		}

		crashEgg(0);
		System.out.println(max);
	}

	static void crashEgg(int a) { // a 를 들어 내려침
		if (a >= N) { // 마지막 계란까지 내려쳤으면
			int cnt = 0;
			for (int i = 0; i < N; i++)
				if (s[i] <= 0)
					cnt++;
			max = Math.max(max, cnt);
			return;
		}

		if (s[a] <= 0) {
			crashEgg(a + 1); // 깨져있으면 넘어감
			return;
		}

		boolean canHit = false;
		for (int i = 0; i < N; i++) {
			if (a == i)
				continue;
			if (s[i] <= 0)
				continue;
			
			s[i] -= w[a];
			s[a] -= w[i];
			crashEgg(a + 1);
			s[i] += w[a];
			s[a] += w[i];
			canHit = true;
		}
		
		if(!canHit)  // 깰 수 있는 계란이 없으면
			crashEgg(a+1);
	}

	static int readInt() throws IOException {
		int c;
		do {
			c = System.in.read();
		} while (c <= 32);
		int n = c & 15;
		c = System.in.read();
		while (c > 47) {
			n = (n << 3) + (n << 1) + (c & 15);
			c = System.in.read();
		}
		return n;
	}

}
