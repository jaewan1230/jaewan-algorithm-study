/*
 * 선수과목 조건을 지켜서 이수해야 함.
 * 선수 과목의 수 N <= 1,000 선수 조건의 수 M <= 500,000
 * 최소 몇 학기에 이수할 수 있는지 출력
 * 
 * 선수조건이 없는 과목들을 큐에 넣음. 1학기에 이수 가능.
 * poll 하면서 연결된 간선 제거, inDegree가 0이 되면 큐에 넣음.
 * 반복
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer st = new StreamTokenizer(br);
		int N = nextInt(st);
		int M = nextInt(st);
		Node[] adjList = new Node[N + 1];
		int[] inDegree = new int[N + 1];
		int[] res = new int[N + 1];

		for (int i = 1; i <= N; i++)
			adjList[i] = new Node(i);

		int A, B;
		for (int i = 0; i < M; i++) {
			A = nextInt(st);
			B = nextInt(st);
			adjList[A].link.add(B);
			inDegree[B]++;
		}

		Queue<Integer> q = new LinkedList<>();
		for (int i = 1; i <= N; i++) {
			if (inDegree[i] == 0)
				q.offer(i);
		}

		int time = 1;
		while (!q.isEmpty()) {
			int size = q.size();
			while (size-- > 0) {
				int cur = q.poll();
				res[cur] = time;
				for (int t : adjList[cur].link)
					if (--inDegree[t] == 0)
						q.offer(t);
			}
			time++;
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++)
			sb.append(res[i]).append(" ");
		System.out.println(sb.toString());
	}

	static class Node {
		int n;
		LinkedList<Integer> link;

		public Node(int n) {
			this.n = n;
			link = new LinkedList<>();
		}

	}

	private static int nextInt(StreamTokenizer st) throws IOException {
		st.nextToken();
		return (int) st.nval;
	}
}