
/*
 * 3234. 준환이의 양팔저울
 * N개의 추
 * 왼쪽, 오른쪽 선택
 * 추를 올릴 때, 오른쪽 총합이 왼쪽보다 커지면 안됨.
 * 
 * 순서 정하고, 순서대로 왼쪽, 오른쪽 올리는 경우 재귀로 구현
 * 순열 만들때, 무게가 같은 거면 동일한 걸로 취급해야겠네.
 * N <= 9
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int N, cnt;
	static int[] perm, arr;
	static boolean[] isSelect;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++)
				arr[i] = Integer.parseInt(st.nextToken());

			Arrays.sort(arr);
			perm = new int[N];
			isSelect = new boolean[N];
			cnt = 0;
			makePerm(0);
			System.out.printf("#%d %d\n",tc, cnt);
		}
	}

	public static void func(int n, int left, int right) {
		if (n == N) {
			cnt++;
			return;
		}
		int t = arr[perm[n]]; // 올릴 추의 무게
		func(n + 1, left + t, right); // 왼쪽에 올리는 경우
		if (right + t <= left) // 오른쪽에 올릴려면, 합이 작거나 같아야 함.
			func(n + 1, left, right + t);
	}

	public static void makePerm(int n) {
		if (n == N) {
			func(0, 0, 0);
			return;
		}
		for (int i = 0; i < N; i++) {
			if (isSelect[i])
				continue;
			if (i > 0 && arr[i] == arr[i - 1] && !isSelect[i - 1]) // 중복 무게 추 처리 : 같은 무게면, 첫 번째 추 부터 선택하도록. 같은 무게 끼리는 순서가 유지됨.
				continue;
			perm[n] = i;
			isSelect[i] = true;
			makePerm(n + 1);
			isSelect[i] = false;
		}
	}
}
