/*
 * n개의 대학에서 강연 요청(n <= 10,000)
 * d일(d <= 10,000) 안에 와서 강연을 해 주면 p(p <= 10,000) 만큼 강연료 지불
 * 
 * 하루에 최대 한 곳에서만 강연.
 * 가장 많은 돈을 벌 수 있도록 순회강연.
 * 
 * ? 가는 덴 시간이 안드나?
 * 
 * 걍 그리디하게 하면 되겠는데
 * 
 * d 일 안 이니까 꼭 그 날짜에 안 해도 된다.
 * 
 * 날짜 기준 내림차순, 같으면 보상 기준 내림차순.
 * 현재 날짜 기준 할 수 있는 것들 중에 가장 pay가 높은 걸 수행.
 */

import java.io.IOException;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {
        int N = readInt(), maxDay = 0;

        Lecture[] lectures = new Lecture[N];
        for (int i = 0; i < N; i++) {
            Lecture l = new Lecture(readInt(), readInt());
            maxDay = Math.max(maxDay, l.day);
            lectures[i] = l;
        }

        Arrays.sort(lectures, (o1, o2) -> o2.day - o1.day);

        int sum = 0, idx = 0;
        // maxDay부터 거꾸로 생각
        PriorityQueue<Lecture> pq = new PriorityQueue<>();
        for (int day = maxDay; day > 0; day--) {
            // 현재 day에서 가능한 강연을 pq에 넣음
            while (idx < N && lectures[idx].day >= day)
                pq.offer(lectures[idx++]);

            if (pq.isEmpty()) {
                if (idx == N)
                    break;
                day = lectures[idx].day + 1;
                continue;
            }
            sum += pq.poll().score;
        }

        System.out.println(sum);
    }

    static class Lecture implements Comparable<Lecture> {
        int day, score;

        public Lecture(int day, int score) {
            this.day = day;
            this.score = score;
        }

        @Override
        public int compareTo(Main.Lecture o) {
            return o.score - score;
        }

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