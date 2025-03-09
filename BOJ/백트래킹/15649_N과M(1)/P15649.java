/* 1부터 N까지 자연수 중 중복 없이 M개를 고른 수열
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.LinkedList;

public class P15649 {

	static int N, M;
	static boolean[] visit;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		N = nextInt(st);
		M = nextInt(st);
		visit = new boolean[N + 1];
		dfs(1, new LinkedList<Integer>());
		System.out.println(sb.toString());
	}

	static void dfs(int depth, LinkedList<Integer> list) {
		if (depth == M + 1) {
			for (int t : list)
				sb.append(t).append(" ");
			sb.append('\n');
		}
		for (int i = 1; i <= N; i++) {
			if (visit[i])
				continue;
			visit[i] = true;
			list.add(i);
			dfs(depth + 1, list);
			list.remove(depth - 1);
			visit[i] = false;
		}
	}

	private static int nextInt(StreamTokenizer st) throws IOException {
		st.nextToken();
		return (int) st.nval;
	}

}