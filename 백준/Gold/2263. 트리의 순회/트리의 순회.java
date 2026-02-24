/*
BOJ 2263 트리의 순회
inorder와 postorder가 주어졌을 때 preorder를 구하는 문제

postorder에서 root는 제일 마지막에 위치하고, inorder에서 root를 기준으로 왼쪽은 left subtree, 오른쪽은 right subtree가 된다.
따라서 postorder에서 root를 찾고, inorder에서 root의 위치를 찾아 left subtree와 right subtree의 크기를 구할 수 있다.
이후 postorder에서 left subtree와 right subtree의 위치를 구할 수 있다.

재귀로 postorder에서 root를 순서대로 찾으면서 preorder를 구할 수 있다.
*/

import java.io.IOException;

public class Main {
    static int N;
    static int[] inorder, postorder, idx;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        N = readInt();
        inorder = new int[N];
        postorder = new int[N];
        for (int i = 0; i < N; i++)
            inorder[i] = readInt();
        for (int i = 0; i < N; i++)
            postorder[i] = readInt();
        idx = new int[N + 1];
        for (int i = 0; i < N; i++)
            idx[inorder[i]] = i;
        preorder(0, N - 1, 0, N - 1);
        System.out.println(sb);
    }

    public static void preorder(int inStart, int inEnd, int postStart, int postEnd) {
        if (inStart > inEnd || postStart > postEnd)
            return;
        int root = postorder[postEnd];
        sb.append(root).append(' ');
        int rootIdx = idx[root];
        int leftSize = rootIdx - inStart;
        preorder(inStart, rootIdx - 1, postStart, postStart + leftSize - 1);
        preorder(rootIdx + 1, inEnd, postStart + leftSize, postEnd - 1);
    }

    public static int pos, len;
    public static byte[] buf = new byte[8192];

    public static byte read() throws IOException {
        if (pos == len) {
            len = System.in.read(buf);
            pos = 0;
        }
        return buf[pos++];
    }

    public static int readInt() throws IOException {
        int c;
        while ((c = read()) <= 32)
            ;
        int n = c & 15;
        while ((c = read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}
