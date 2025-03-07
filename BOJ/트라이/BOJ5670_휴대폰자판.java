/*
 * 사전은 자동완성이 된다.
 * search 를 하면서, 각 글자에 대해 자동완성 여부를 파악하면 되겠다.
 * > child.size == 1 && endOfWord == false 면, 자동완성이 가능하므로 카운트 x
 * > 나머지 경우에 대해 입력 카운트
 * 
 * 
 * 1. 문자열을 입력받아 트라이를 구성
 * 2. 각 문자열별로 입력 횟수 계산
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class BOJ5670_휴대폰자판 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            Trie trie = new Trie();
            int N = Integer.parseInt(line);
            String[] inStr = new String[N];

            // 입력받고 trie 구성
            for (int i = 0; i < N; i++) {
                inStr[i] = br.readLine();
                trie.insert(inStr[i]);
            }

            int sum = 0;
            for (int i = 0; i < N; i++) {
                sum += trie.search(inStr[i]);
            }
            // System.out.println(String.format("%.2f", (double) sum / N));
            sb.append(String.format("%.2f", (double) sum / N)).append('\n');
        }
        System.out.println(sb.toString());
    }

    static class Node {
        HashMap<Character, Node> child = new HashMap<>();
        boolean endOfWord;
    }

    static class Trie {
        Node root = new Node();

        void insert(String str) {
            Node node = root;
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                node.child.putIfAbsent(c, new Node());
                node = node.child.get(c);
            }
            node.endOfWord = true;
        }

        int search(String str) {
            int cnt = 1; // 첫 글자는 눌러야함
            Node node = root;
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);

                // 자동완성이 가능한 경우
                if (node.child.size() == 1 && !node.endOfWord)
                    ;

                else if (i != 0)
                    cnt++;
                node = node.child.get(c);
            }

            return cnt;
        }
    }
}
