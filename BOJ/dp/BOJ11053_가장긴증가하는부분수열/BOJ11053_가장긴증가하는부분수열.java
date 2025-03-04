
/*
 * binarySearch
 * 
 * s부터 e 까지,
 */
import java.io.IOException;

public class BOJ11053_가장긴증가하는부분수열 {

	public static void main(String[] args) throws IOException {
		int N = readInt(), len = 0, t, input;
		int[] LIS = new int[N];
		for (int i = 0; i < N; i++) {
			input = readInt();
			t = binarySearch(LIS, len, input);
			if (t < 0) {
				LIS[-t - 1] = input;
				if (-t > len)
					len++;
			}
		}
		System.out.println(len);
	}

	static int binarySearch(int[] arr, int len, int target) {
		int s = 0, e = len, m = 0;
		while (s < e) {
			m = (s + e) / 2;
			if (arr[m] == target)
				return m;
			if (arr[m] < target)
				s = m + 1;
			else if (target < arr[m])
				e = m;
		}
		return -s - 1;
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
