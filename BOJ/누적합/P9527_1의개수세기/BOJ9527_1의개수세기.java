import java.io.IOException;

/*
 * A <= x <= B 인 모든 x에 대해 이진수로 표현했을 때 1의 개수의 합.
 * 
 * 1 <= A <= B <= 10^16
 * 
 * 2 : 10
0000
0001

0010
0011
0100
0101
0110
0111
1000
1001
1010
1011
1100
12 : 1100

2^0 자리 1의 개수 : 5 = 6 - 1
2^1 자리 1의 개수 : 6 = 
2^2 자리 1의 개수 : 5
2^3 자리 1의 개수 : 5

2^0 자리는, 0 1 한 묶음으로 2^1개, 1의 개수는 2^0개.
2^1 자리는, 0011 한 묶음, 2^2개, 1의 개수는 2^1개.
2^2 자리는, 00001111 한 묶음, 2^3개, 1의 개수는 2^2개.

숫자 : N
따라서, 각 자리 별로 2^(i+1) 묶음이 몇 개 들어가는지 계산 * 2^i +
max(0, N % 2^(i+1) - 2^i) 가 해당 자리의 1의 개수이다.

Math.pow가 값이 정확하지 않아진다고 한다...
pow 함수를 직접 구현하니까 맞았다.....

 */
public class BOJ9527_1의개수세기 {

	public static void main(String[] args) throws IOException {
		long A = readInt(), B = readInt();

		System.out.println(func(B) - func(A - 1));
	}

	private static long func(long N) {
		long cnt = 0;

		for (int i = 0;; i++) {
			long powerOfI = pow(i);
			long powerOfIPlusOne = powerOfI << 1;
			if (powerOfI >= N + 1)
				break;
			cnt += ((N + 1) / powerOfIPlusOne) * powerOfI; // 2^i
			cnt += Math.max(0, (N + 1) % powerOfIPlusOne - powerOfI);
		}
		return cnt;
	}

	private static long pow(int i) { // 2^i 계산
		long res = 1L << i;
		return res;
	}

	private static long readInt() throws IOException {
		int c;
		do {
			c = System.in.read();
		} while (c <= 32);
		long n = c & 15;
		c = System.in.read();
		while (c > 47) {
			n = (n << 3) + (n << 1) + (c & 15);
			c = System.in.read();
		}
		return n;
	}
}
