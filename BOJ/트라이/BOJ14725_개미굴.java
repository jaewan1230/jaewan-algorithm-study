/*
 * 개미가 개미굴을 타고 내려가면서 있는 먹이를 파악한다.
 * 입력 순서대로 Trie 생성 후, dfs 로 출력
 * 
 * =======================
 * Refactor : HashMap 대신 Node[26] 배열 사용
 * 314808 KB, 1048 ms -> 174492 KB, 492 ms 로 메모리, 시간 대략 절반 감소
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.TreeMap;

public class BOJ14725_개미굴 {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Trie trie = new Trie();
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            int t = Integer.parseInt(input[0]);
            for (int j = 0; j < t; j++)
                trie.insert(Arrays.copyOfRange(input, 1, t + 1));
        }

        // dfs 로 순회하며 출력
        printDfs(trie.root, 0);
        System.out.println(sb.toString());
    }

    private static void printDfs(Node cur, int depth) {
        for (String str : cur.child.keySet()) {
            for (int i = 0; i < depth; i++)
                sb.append("--");
            sb.append(str).append('\n');

            // 다음 노드 dfs 로 탐색
            printDfs(cur.child.get(str), depth + 1);
        }
    }

    static class Node {
        TreeMap<String, Node> child = new TreeMap<>();
    }

    static class Trie {
        Node root = new Node();

        void insert(String[] str) {
            Node node = root;

            for (int i = 0; i < str.length; i++) {
                node.child.putIfAbsent(str[i], new Node());
                node = node.child.get(str[i]);
            }
        }
    }
}
