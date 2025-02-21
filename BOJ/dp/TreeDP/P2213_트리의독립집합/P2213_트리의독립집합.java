/*
 * 그래프 G(V, E) 에서 정점의 부분 집합 S 에 속한 모든 정점쌍이 서로 인접하지 않으면, S는 독립 집합이다.
 * 
 * 독립 집합의 크기는 정점에 가중치가 없으면, 정점의 수, 가중치가 있으면 가중치의 합.
 * 트리에서, 각 정점의 가중치가 양의 정수로 주어져 있을 때, 최대 독립 집합을 구하는 것.
 * 
 * 트리 dp. i를 루트 노드로 하는 서브 트리의 가중치 합의 최대
 * > dp[i][0] : i를 선택하지 않는 경우에, 자식 노드들의 최대 가중치 합
 * > dp[i][1] : i를 선택한 경우, 자식 노드들 선택 안한 경우들의 가중치 합
 * 
 *  n <= 10,000, w <= 10,000.
 *  
 *  최대 독립집합에 속하는 정점을 출력.
 *  dp 값을 구한 다음, trace로 경로를 추적.
 *  > 현재 노드를 선택한 경우엔, 자식 노드의 선택 안한 dp 값과 비교, 추적
 *  > 현재 노드를 선택 안한 경우엔, 자식 노드들의 dp 값과 비교해 추적
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.TreeSet;

public class Main {

	static int N;
	static int[][] dp;
	static ArrayList<Node> graph;
	static TreeSet<Integer> maxIndependentSet = new TreeSet<>();
	static boolean[] inSet;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer st = new StreamTokenizer(br);

		N = nextInt(st);
		dp = new int[N + 1][2];
		graph = new ArrayList<>();
		graph.add(new Node(0, 0));
		graph.get(0).link.add(1);
		for (int i = 1; i <= N; i++) {
			graph.add(new Node(i, nextInt(st)));
		}
		int u, v;
		for (int i = 1; i < N; i++) {
			u = nextInt(st);
			v = nextInt(st);
			graph.get(u).link.add(v);
			graph.get(v).link.add(u);
		}
		dfs(1, 0);

		inSet = new boolean[N + 1];
		trace(1, 0, dp[1][0] > dp[1][1] ? 0 : 1);

		StringBuilder sb = new StringBuilder();
		sb.append((dp[1][0] > dp[1][1] ? dp[1][0] : dp[1][1])).append('\n');
		for (int i = 1; i <= N; i++)
			if (inSet[i])
				sb.append(i).append(" ");
		
		System.out.println(sb.toString());
	}

	static void trace(int cur, int parent, int state) { // state = 0 은 현재 노드 선택 안한 경우, state = 1 은 현재 노드 선택한 경우
		if (state == 1)
			inSet[cur] = true;
		for (int next : graph.get(cur).link) {
			if (next == parent)
				continue;
			if (state == 0) {
				if (dp[next][0] > dp[next][1]) {
					trace(next, cur, 0);
				} else {
					trace(next, cur, 1);
				}
			} else if (state == 1) {
				trace(next, cur, 0);
			}
		}
	}

	static void dfs(int cur, int parent) {
		dp[cur][1] = graph.get(cur).w;
		for (int next : graph.get(cur).link) {
			if (next == parent)
				continue;
			dfs(next, cur);
			dp[cur][0] += Math.max(dp[next][0], dp[next][1]); // 현재 노드를 선택 안하는 경우엔, 두가지 경우 중 최대값
			dp[cur][1] += dp[next][0]; // 현재 노드를 선택한 경우, 자식 노드들의 선택 안한 값을 더함
		}
	}

	private static int nextInt(StreamTokenizer st) throws IOException {
		st.nextToken();
		return (int) st.nval;
	}

	static class Node {
		int n, w;
		ArrayList<Integer> link;

		public Node(int n, int w) {
			this.n = n;
			this.w = w;
			link = new ArrayList<>();
		}
	}
}
