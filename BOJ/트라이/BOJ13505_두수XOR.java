/*
 * N 개의 수가 주어졌을 때, XOR한 값이 가장 큰 두 수를 찾는 프로그램
 * i != j 이면서, A[i] ^ A[j] 가 가장 큰 것.
 * 
 * 2 <= N <= 100,000
 * 입력 수 <= 1,000,000,000 인 음이 아닌 정수.
 * 
 * N이 100,000개 이므로 전부 비교하는 N^2은 시간복잡도 초과.
 * 비트 연산. 비트는 32자리
 * Trie 자료구조는 문자열의 길이로 탐색 가능하게 하는 자료구조.
 * 비트 자리로 Trie 자료구조를 Build 함.
 * 
 * 수가 주어지면, 해당 수에 대해 XOR 연산 값이 최대로 되게 하는 문자열을 찾을 수 있다.
 * 최대값을 찾아서 리턴.
 * 
 */

import java.io.IOException;

public class BOJ13505_두수XOR {
    public static void main(String[] args) throws IOException {
        int N = readInt();
        int[] arr = new int[N];
        Trie trie = new Trie();
        for (int i = 0; i < N; i++) {
            arr[i] = readInt();
            trie.insert(arr[i]);
        }

        int max = 0;
        for (int i = 0; i < N; i++)
            max = Math.max(max, trie.search(arr[i]));
        System.out.println(max);
    }

    static class Node {
        Node[] child = new Node[2];
    }

    static class Trie {
        Node root = new Node();

        void insert(int N) {
            Node node = root;
            for (int i = 31; i >= 0; i--) {
                int t = N & (1 << i), idx = t == 0 ? 0 : 1;
                if (node.child[idx] == null)
                    node.child[idx] = new Node();
                node = node.child[idx];
            }
        }

        int search(int N) {
            Node node = root;
            int sum = 0;
            for (int i = 31; i >= 0; i--) {
                // N 의 높은 자릿수 부터
                int t = N & (1 << i);
                if (t == 0) {
                    if (node.child[1] != null) {
                        sum += (1 << i);
                        node = node.child[1];
                    } else {
                        node = node.child[0];
                    }
                } else {
                    if (node.child[0] != null) {
                        sum += (1 << i);
                        node = node.child[0];
                    } else {
                        node = node.child[1];
                    }
                }
            }
            return sum;
        }
    }

    private static int readInt() throws IOException {
        int c;
        do {
            c = System.in.read();
        } while (c <= 32);
        boolean negative = false;
        if (c == 45) {
            negative = true;
            c = System.in.read();
        }

        int n = c & 15;
        c = System.in.read();
        while (c > 47) {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();
        }
        return negative ? -n : n;
    }
}
