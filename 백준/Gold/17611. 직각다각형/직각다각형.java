
/* 
 * 수직선, 수평선은 각 N / 2 개씩 생긴다. (점 2개를 이어야 선분 1개)
 * 꼭짓점을 입력받으며 수직, 수평선을 배열에 저장한다. <처음, 끝>
 * 입력이 끝나면 처음 좌표 기준으로 정렬
 * 
 * PQ에 끝 좌표를 넣으며 최대 overlapping 되는 개수를 구한다.
 * 다음 선분의 시작 위치가, 최소 heap 에 들어 있는 위치 보다 작으면 겹치는 경우.
 * 크거나 같으면 겹치지 않는 경우다.
 * 
 * N <= 100,000 이므로, 정렬 + 각 선분마다 (N) * pq 삽입 삭제 = O(N log N) 충분함.
 * 
 */
import java.io.IOException;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {
        int N = readInt();
        Line[] vertical = new Line[N], horizon = new Line[N];
        int prevX = readInt(), prevY = readInt(), curX, curY, idxV = 0, idxH = 0, startX = prevX, startY = prevY;
        for (int i = 1; i < N; i++) {
            curX = readInt();
            curY = readInt();
            if (prevX == curX && prevY != curY) // 길이 0이 아닌 수직선만
                vertical[idxV++] = new Line(prevY, curY);
            else if (prevY == curY && prevX != curX) // 길이 0이 아닌 수평선만
                horizon[idxH++] = new Line(prevX, curX);
            prevX = curX;
            prevY = curY;
        }
        if (prevX == startX && prevY != startY)
            vertical[idxV++] = new Line(prevY, startY);
        else if (prevY == startY && prevX != startX)
            horizon[idxH++] = new Line(prevX, startX);

        int max = 0;
        if (idxH > 0) {
            Arrays.sort(horizon, 0, idxH);
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            for (int i = 0; i < idxH; i++) {
                while (!pq.isEmpty() && pq.peek() <= horizon[i].start)
                    pq.poll();
                pq.offer(horizon[i].end);
                max = Math.max(max, pq.size());
            }
        }

        if (idxV > 0) {
            Arrays.sort(vertical, 0, idxV);
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            for (int i = 0; i < idxV; i++) {
                while (!pq.isEmpty() && pq.peek() <= vertical[i].start)
                    pq.poll();
                pq.offer(vertical[i].end);
                max = Math.max(max, pq.size());
            }
        }

        System.out.println(max);
    }

    static class Line implements Comparable<Line> {
        int start, end;

        public Line(int a, int b) {
            this.start = Math.min(a, b);
            this.end = Math.max(a, b);
        }

        @Override
        public int compareTo(Main.Line o) {
            return this.start - o.start;
        }
    }

    static int pos, len;
    static byte[] buf = new byte[8192];

    static byte read() throws IOException {
        if (pos == len) {
            len = System.in.read(buf);
            pos = 0;
        }
        return buf[pos++];
    }

    static int readInt() throws IOException {
        int c;
        while ((c = read()) <= 32)
            ;
        boolean negative = c == 45;
        if (negative)
            c = read();
        int n = c & 15;
        while ((c = read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return negative ? -n : n;
    }
}