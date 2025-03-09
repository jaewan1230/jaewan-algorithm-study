/*
 * 루트 있는 트리에서 정점 U를 루트로 하는 서브트리에 속한 정점의 수를 출력
 * 
 * 입력 정점 수 N, 루트 번호 R, 쿼리의 수 Q (2 <= N <= 10^5, 1 <= R <= N, 1 <= Q <= 10^5)
 * 
 * 루트가 R 이므로, R 부터 출발해 DFS 로 트리를 탐색한다.
 * 각 서브트리의 정보를 dp 배열에 저장해, 쿼리가 들어오면 출력한다.
 * dp[i] 에 저장되는 값은, i를 루트로 하는 서브트리에 속한 노드의 갯수이다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;

public class P15681_트리와쿼리 {
	static int N;
	static int[] dp;
	static ArrayList<Integer>[] graph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer st = new StreamTokenizer(br);
		int R, Q;
		N = nextInt(st);
		R = nextInt(st);
		Q = nextInt(st);

		dp = new int[N + 1];
		graph = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++)
			graph[i] = new ArrayList<>();

		int u, v;
		for (int i = 1; i < N; i++) {
			u = nextInt(st);
			v = nextInt(st);
			graph[u].add(v);
			graph[v].add(u);
		}
		dfs(R, 0); // R를 루트 노드로 하는 트리를 순회하며, dp 값을 구함.

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < Q; i++) {
			u = nextInt(st);
			sb.append(dp[u]).append('\n');
		}
		System.out.println(sb.toString());
	}

	static int dfs(int cur, int parent) {
		dp[cur] = 1;
		for (int next : graph[cur])
			if (next != parent)
				dp[cur] += dfs(next, cur); // next를 루트 노드로 하는 서브 트리의 노드 수를 더함.
		return dp[cur]; // cur을 루트 노드로 하는 서브 트리 노드 수를 리턴
	}

	private static int nextInt(StreamTokenizer st) throws IOException {
		st.nextToken();
		return (int) st.nval;
	}

}
