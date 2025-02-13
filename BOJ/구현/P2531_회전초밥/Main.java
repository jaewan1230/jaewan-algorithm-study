import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()), d = Integer.parseInt(st.nextToken()),
				k = Integer.parseInt(st.nextToken()), c = Integer.parseInt(st.nextToken());

		int[] sushi = new int[N];
		for (int i = 0; i < N; i++)
			sushi[i] = Integer.parseInt(br.readLine());
		int[] slidingWindow = new int[d + 1];
		slidingWindow[c]++;
		for (int i = 0; i < k; i++)
			slidingWindow[sushi[i]]++;

		int max = 0, cnt = 0;
		for (int j = 1; j <= d; j++) {
			if (slidingWindow[j] > 0)
				cnt++;
			max = Math.max(max, cnt);
		}

		for (int i = 0; i < N; i++) {
			int targetSushi = (i + k) % N;
			slidingWindow[sushi[targetSushi]]++;
			slidingWindow[sushi[i]]--;

			cnt = 0;
			for (int j = 1; j <= d; j++) {
				if (slidingWindow[j] > 0)
					cnt++;
				max = Math.max(max, cnt);
			}
		}
		System.out.println(max);
	}
}
