/*
 * 트라이 연습
 * 전화번호 목록이 일관성을 유지하는지 검사
 * 한 번호가 다른 번호의 접두어인 경우가 없어야 함
 * > 긴급전화 : 911
 * > 선영 : 91125426
 * 위 경우는 일관성이 없는 경우
 * 
 * 트라이에 문자열 저장, search 메소드로 일관성 여부 검사
 * 문자열 길이가 짧은 순서대로 들어오지 않는다.
 * 911 -> 91125426인 경우. 길이가 짧은 요소가 먼저 Trie 를 구성했으면
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class BOJ5052_전화번호목록 {
    static boolean res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
        }
        System.out.println(sb.toString());
    }

    static class Node {
        // 각 노드의 자식노드 저장
        HashMap<Character, Node> child = new HashMap<>();
        // 이 노드가 단어의 끝인지 여부
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

        boolean search(String str) {
            Node node = root;

            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                // 자식 노드에 없으면 일관성이 있는 경우
                if (!node.child.containsKey(c))
                    return false;
                node = node.child.get(c);

                // 중간에 단어의 끝을 만나면 일관성이 없는 경우
                if (node.endOfWord)
                    return true;
            }

            // 모든 문자를 확인한 후에 남아있으면 일관성이 없는 경우
            return node.child.size() > 0;
        }
    }
}
