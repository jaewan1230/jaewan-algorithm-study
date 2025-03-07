/*
 * N 개의 문자열로 이루어진 집합 S 가 주어지고,
 * M개의 검사해야 하는 문자열이 주어진다.
 * 집합 S에 포함되어 있는 문자열 중 적어도 하나의 접두사인 것의 개수를 구해라.
 * 
 * Trie 구성해서, 해당 문자열이 트라이에 있으면 접두사인 것.
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ14426_접두사찾기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
        Trie trie = new Trie();
        for (int i = 0; i < N; i++)
            trie.insert(br.readLine());

        int cnt = 0;
        for (int i = 0; i < M; i++)
            if (trie.search(br.readLine()))
                cnt++;
        System.out.println(cnt);
    }

    static class Node {
        Node[] child = new Node[26];
        boolean endOfWord;
    }

    static class Trie {
        Node root = new Node();

        void insert(String str) {
            Node node = root;

            int len = str.length(), idx;
            for (int i = 0; i < len; i++) {
                idx = str.charAt(i) - 'a';
                if (node.child[idx] == null)
                    node.child[idx] = new Node();
                node = node.child[idx];
            }
            node.endOfWord = true;
        }

        boolean search(String str) {
            Node node = root;

            int len = str.length(), idx;
            for (int i = 0; i < len; i++) {
                idx = str.charAt(i) - 'a';
                if (node.child[idx] == null)
                    return false;
                node = node.child[idx];
            }

            return true;
        }
    }
}
