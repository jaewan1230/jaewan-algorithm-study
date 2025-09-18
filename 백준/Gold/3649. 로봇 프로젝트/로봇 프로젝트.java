/*
 * 레고 조각의 수 <= 1,000,000
 * 레고 조각의 길이 <= 100,000,000 (10 센티미터)
 * 
 * 구멍의 너비 <= 20 센티미터
 * Set 쓰고, 넣을 때마다 기존 값과 매칭해서 최댓값으로 갱신
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {
    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            int x = Integer.parseInt(line);
            x *= 10000000;
            int n = Integer.parseInt(br.readLine());

            HashSet<Integer> set = new HashSet<>();
            int l1 = -1, l2 = -2;
            for (int i = 0; i < n; i++) {
                int t = Integer.parseInt(br.readLine());
                if (set.contains(x - t)) {
                    if (l2 - l1 < Math.max(t, x - t) - Math.min(t, x - t)) {
                        l2 = Math.max(t, x - t);
                        l1 = Math.min(t, x - t);
                    }
                }
                set.add(t);
            }

            if (l1 == -1)
                sb.append("danger").append('\n');
            else
                sb.append("yes ").append(l1).append(' ').append(l2).append('\n');
        }
        System.out.println(sb);
    }
}