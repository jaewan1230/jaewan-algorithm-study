import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P16987 {
    static int N, max;

    static class Egg {
        int S, W;

        Egg(int S, int W) {
            this.S = S;
            this.W = W;
        }
    }

    static Egg[] arrEgg = new Egg[8];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arrEgg[i] = new Egg(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        func(0);
        System.out.println(max);
    }

    static void func(int k) { // k번째 계란치기
        if (k == N) { // 종료
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                if (arrEgg[i].S <= 0) cnt++;
            }
            if (max < cnt) max = cnt;
            return;
        }
        if (arrEgg[k].S <= 0) {
            func(k + 1);
            return;
        }
        boolean isRun = false;
        for (int i = 0; i < N; i++) {
            if (i == k) continue;
            if (arrEgg[i].S <= 0) continue;
            arrEgg[k].S -= arrEgg[i].W;
            arrEgg[i].S -= arrEgg[k].W; // i번째와 k번째 계란 서로의 무게만큼 내구도 감소
            func(k + 1);
            isRun = true;
            arrEgg[k].S += arrEgg[i].W;
            arrEgg[i].S += arrEgg[k].W;
        }
        if (!isRun) func(k + 1); // 실행되지 않음 == 나머지 계란이 다 깨져있음
    }
}