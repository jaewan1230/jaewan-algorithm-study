import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        int T, n, m, t;
        long res = 0;
        int[] sum = new int[1001];
        HashMap<Integer, Integer> sumA = new HashMap<>();
        HashMap<Integer, Integer> sumB = new HashMap<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        n = Integer.parseInt(br.readLine());
        sum[0] = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            t = Integer.parseInt(st.nextToken());
            sum[i] = sum[i - 1] + t;
        }
        t = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j++) {
                if (sumA.containsKey(sum[j] - sum[i - 1])) {
                    t = sumA.remove(sum[j] - sum[i - 1]);
                    sumA.put(sum[j] - sum[i - 1], t + 1);
                } else sumA.put(sum[j] - sum[i - 1], 1);

            }
        }
        Object[] mapkey = sumA.keySet().toArray(new Integer[0]);
        Arrays.sort(mapkey);

        sum[0] = 0;
        m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= m; i++) {
            t = Integer.parseInt(st.nextToken());
            sum[i] = sum[i - 1] + t;
        }
        t = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = i; j <= m; j++) {
                if (sumB.containsKey(sum[j] - sum[i - 1])) {
                    t = sumB.remove(sum[j] - sum[i - 1]);
                    sumB.put(sum[j] - sum[i - 1], t + 1);
                } else sumB.put(sum[j] - sum[i - 1], 1);
            }
        }
        for (Integer nKey : sumA.keySet()) {
            if (sumB.containsKey(T - nKey)) res += sumA.get(nKey) * sumB.get(T - nKey);
        }
        System.out.println(res);
    }
}