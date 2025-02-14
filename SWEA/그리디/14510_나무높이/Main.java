import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[] arr = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			int max = 0;
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				if (max < arr[i])
					max = arr[i];
			}
			int oddCnt = 0, remainHeight = 0;
			for (int i = 0; i < N; i++) {
				remainHeight += (max - arr[i]);
				if ((max - arr[i]) % 2 == 1) oddCnt++;
			}
			int res = (remainHeight / 3) * 2;
			oddCnt -= remainHeight / 3;
			remainHeight %= 3;
			if (oddCnt <= 0) res += remainHeight;
			else res += 2 * oddCnt - 1;
			System.out.println("#" + tc + " " + res);
		}
	}
}