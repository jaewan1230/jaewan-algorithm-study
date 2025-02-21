/*
 * 우수 마을
 * N개의 마을, 트리 구조로 이루어짐.
 * 무방향
 * 
 * 1. '우수 마을'로 선정된 마을 주민 수의 총 합을 최대로
 * 2. '우수 마을'끼리는 인접할 수 없음
 * 3. 선정되지 못한 마을은 적어도 하나의 '우수 마을'과 인접해야 함
 * 
 *  1 <= N <= 10,000
 *  주민 수는 10,000 이하.
 * 
 * 트리에서의 dp, dp[i][0, 1] : i를 부모 노드로 하는 값 중 최대값, 0이면 i 선택 안한 경우, 1은 i를 선택한 경우
 * dp[i][0] = i와 연결된 노드 중, 방문하지 않은(자식) dp 값 중 최대값의 합.
 * dp[i][1] = i를 선택해야 하므로, i의 자식 트리 중 선택하지 않은 값들의 합 dp[서브][0]의 합. 
 * 1번 노드를 루트 노드라고 지정(무방향이어서 상관없음)
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.LinkedList;

public class Main {
	static int N;
	static Node[] nodes;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer st = new StreamTokenizer(br);

		N = nextInt(st);
		dp = new int[N + 1][2];
		nodes = new Node[N + 1];

		for (int i = 1; i <= N; i++) {
			nodes[i] = new Node(i);
			nodes[i].population = nextInt(st);
		}
		int u, v;
		for (int i = 1; i < N; i++) {
			u = nextInt(st);
			v = nextInt(st);
			nodes[u].link.add(v);
			nodes[v].link.add(u);
		}

		dfs(1);
		System.out.println(Math.max(dp[1][0], dp[1][1]));
	}

	static void dfs(int n) {
		nodes[n].visit = true;
		dp[n][1] = nodes[n].population;
		for (int t : nodes[n].link) {
			if (!nodes[t].visit) {
				nodes[t].visit = true;
				dfs(t);
				dp[n][0] += Math.max(dp[t][0], dp[t][1]);
				dp[n][1] += dp[t][0];
			}
		}
//		System.out.printf("%d : %d\n", n, Math.max(dp[n][0], dp[n][1]));
	}

	static class Node {
		int n, population;
		boolean visit;

		LinkedList<Integer> link;

		public Node(int n) {
			this.n = n;
			this.visit = false;
			link = new LinkedList<>();
		}

	}

	private static int nextInt(StreamTokenizer st) throws IOException {
		st.nextToken();
		return (int) st.nval;
	}

}
