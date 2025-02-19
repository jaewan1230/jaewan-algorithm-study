/*
 * N명 학생 줄 세우기. 두 명씩 비교해서.
 * 앞에서부터 줄을 세운 결과를 출력. 답이 여러 가지면 아무거나 출력.
 * 
 * N <= 32,000, M <= 100,000
 * Topological Sort로 순서 결정.
 * 
 * 1) inDegree가 0인 노드를 큐에 넣음.
 * 2) 큐에서 팝 하면서, 연결된 노드들의 inDegree 감소.
 * 1-2) 반복.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Iterator;
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
		for (int i = 1; i <= N; i++)
			if (inDegree[i] == 0)
				q.offer(i);

		StringBuilder sb = new StringBuilder();
		while (!q.isEmpty()) {
			Node cur = adjList[q.poll()];
			Iterator<Integer> iter = cur.link.iterator();
			while (iter.hasNext()) {
				int t = iter.next();

				if (--inDegree[t] == 0)
					q.offer(t);
			}
			sb.append(cur.n).append(" ");
		}
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