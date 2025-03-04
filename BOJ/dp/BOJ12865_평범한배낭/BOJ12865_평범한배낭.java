import java.io.IOException;

/*
 * N개의 물건, 무게 W와 가치 V
 * 배낭에 넣을 수 있는 물건 가치의 최댓값. 최대 무게 K.
 * 
 * dp로 풀면, O(N*K)
 * 1차원 dp 사용
 * dp[i] = max(dp[i], dp[i-w]+v)
 */
public class BOJ12865_평범한배낭 {

	public static void main(String[] args) throws IOException {
		int N = readInt(), K = readInt(), w, v;
		int[] dp = new int[K + 1];
		for (int i = 0; i < N; i++) {
			w = readInt();
			v = readInt();

			for (int j = K; j >= w; j--)
				dp[j] = Math.max(dp[j], dp[j - w] + v);
		}
		System.out.println(dp[K]);
	}

	private static int readInt() throws IOException {
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
