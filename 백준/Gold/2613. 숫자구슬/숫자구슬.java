/*
* N개의 숫자 구슬
* 
* M개의 그룹으로 나누었을 때 각각의 그룹의 합 중 최댓값이 최소가 되도록.
* 
* 각 그룹의 합 중 최댓값이 최소가 되도록 M개의 그룹으로 나누었을 때,
* 최댓값과 각 그룹을 구성하는 구슬의 개수를 출력
* 
* 그룹에 포함된 숫자 구슬의 개수는 0보다 커야 함.
* 
* 매개변수 탐색. 만들어진 그룹의 개수가 M 이하면 가능한 경우임.
* 판정함수 시간복잡도 O(N)
* 
* 그룹의 합의 최댓값은? N = 300, 구슬에 100 이하 자연수. 최대 30,000
*/

import java.io.IOException;

public class Main {
    static int N, M;
    static int[] bead;

    public static void main(String[] args) throws IOException {
        N = readInt();
        M = readInt();
        bead = new int[N];
        for (int i = 0; i < N; i++)
            bead[i] = readInt();

        int min = parametricSearch(1, 30000);

        int[] res = new int[M];
        int groupIdx = 0, groupSum = 0, i = 0;
        for (i = 0; i < N; i++) {
            if (groupSum + bead[i] <= min) {
                groupSum += bead[i];
                res[groupIdx]++;
            }
            // 넘어가면 다음 그룹으로
            else {
                res[++groupIdx]++;
                groupSum = bead[i];
            }
            // 남은 구슬 개수가 부족해서 다음 그룹부터 하나씩 채워야 할 경우 처리
            if (N - i == M - groupIdx)
                break;
        }
        for (groupIdx++; groupIdx < M; groupIdx++, i++)
            res[groupIdx] = 1;

        StringBuilder sb = new StringBuilder();
        sb.append(min).append('\n');
        for (i = 0; i < M; i++)
            sb.append(res[i]).append(' ');
        System.out.print(sb);
    }

    static int parametricSearch(int l, int r) {
        int m;
        while (l <= r) {
            m = (l + r) / 2;
            if (check(m))
                r = m - 1;
            else
                l = m + 1;
        }
        return l;
    }

    // 그룹의 합이 n 일때 가능한지 판정
    static boolean check(int n) {
        int groupCnt = 1, groupSum = 0;
        for (int i = 0; i < N; i++) {
            if (bead[i] > n)
                return false;
            // 더해도 그룹 합이 n 이하면 더함
            if (groupSum + bead[i] <= n)
                groupSum += bead[i];
            // 넘어가면 다음 그룹으로
            else {
                groupCnt++;
                groupSum = bead[i];
            }
        }
        return groupCnt <= M;
    }

    static int readInt() throws IOException {
        int c;
        while ((c = System.in.read()) <= 32)
            ;
        int n = c & 15;
        while ((c = System.in.read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}