import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int[] parent, value;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			sb.append(String.format("#%d ", tc));
			String[] input = br.readLine().split(" ");
			int N = Integer.parseInt(input[0]);
			int M = Integer.parseInt(input[1]);
			parent = new int[N + 1];
			value = new int[N + 1];
			for (int i = 1; i <= N; i++)
				parent[i] = i;

			for (int i = 0; i < M; i++) {
				input = br.readLine().split(" ");
				int a = Integer.parseInt(input[1]);
				int b = Integer.parseInt(input[2]);
				if (input[0].equals("!")) {
					int w = Integer.parseInt(input[3]);
					union(a, b, w);
				}
				if (input[0].equals("?")) {
					int parentA = find(a), parentB = find(b);
					if (parentA != parentB)
						sb.append("UNKNOWN ");
					else
						sb.append(String.format("%d ", value[b] - value[a]));
				}
			}
			sb.append('\n');
		}
		System.out.println(sb.toString());
	}

	static int find(int n) {
		int root = n;
		Stack<Integer> stack = new Stack<>();
		while (root != parent[root]) {
			stack.push(root);
			root = parent[root];
		}
		int node;
		while (!stack.isEmpty()) {
			node = stack.pop();
			value[node] += value[parent[node]];
			parent[node] = root;
		}

		return root;
	}

	static void union(int a, int b, int w) {
		int rootA = find(a);
		int rootB = find(b);
		if (rootA != rootB) {
			value[rootB] = value[a] + w - value[b];
			parent[rootB] = rootA;
		}
	}
}