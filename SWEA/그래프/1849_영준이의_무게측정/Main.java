import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc < T; tc++) {
			StringBuilder sb = new StringBuilder();
			sb.append(String.format("#%d ", tc));
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());

			Graph g = new Graph();
			for (int i = 1; i <= N; i++)
				g.addNode(i);

			for (int i = 0; i < M; i++) {
				String[] inStr = br.readLine().split(" ");
				if (inStr[0].charAt(0) == '!') {
					g.addEdge(g.getNode(Integer.parseInt(inStr[1])), g.getNode(Integer.parseInt(inStr[2])), Integer.parseInt(inStr[3]));
				}
				else if (inStr[0].charAt(0) == '?') {
					dfs(Integer.parseInt(inStr[1]), Integer.parseInt(inStr[2]), 0);
				}
			}
		}
	}

	static void dfs(int cur, int dest, int res) {
		if(cur==dest) {
			
		}
	}
	static class Graph {
		Map<Integer, Node> nodes;

		public Graph() {
			this.nodes = new HashMap<>();
		}

		void addNode(int no) {
			nodes.put(no, new Node(no));
		}

		void addEdge(Node source, Node destination, int weight) {
			source.addEdge(new Edge(source, destination, weight));
			destination.addEdge(new Edge(destination, source, -weight));
		}

		Node getNode(int no) {
			return nodes.get(no);
		}
	}

	static class Node {
		List<Edge> edges;
		int no;

		public Node(int no) {
			this.no = no;
			edges = new ArrayList<>();
		}

		void addEdge(Edge e) {
			edges.add(e);
		}

	}

	static class Edge {
		Node source, dest;
		int weight;

		public Edge(Node source, Node dest, int weight) {
			this.source = source;
			this.dest = dest;
			this.weight = weight;
		}
	}
}
