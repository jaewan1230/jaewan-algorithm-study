
/*
 * 시작 칸에 말 10개
 * 10개의 턴
 * 주사위에서 나올 수 10개를 알 때 얻을 수 있는 점수의 최댓값
 * 
 * 모든 경우는 각 턴에 이동할 말을 선택 4 ^ 10 = 약 1,000,000회
 * 
 * 윷놀이 판은 그림과 같음.
 * 0번 라인, idx = 5, 10, 15, 20 분기점 마다 처리
 * > idx = 5에 도착하면 1번 라인으로 변경
 * > idx = 10은 2번 라인으로 변경
 * > idx = 15는 3번 라인으로 변경
 * 1번 라인, idx = 4 이후부터 4번 라인으로 변경
 * 2번 라인, idx = 3 이후부터 4번 라인으로 변경
 * 3번 라인, idx = 4 이후부터 4번 라인으로 변경
 * 4번 라인, idx = 3 이후부터 1번 라인으로 변경
 * 
 * 도착하면 라인을 -1 로 변경.
 * 
 * 각 말은 이동하기 전 도착하는 지점에 말이 있는지 확인
 * 말 선택은 판 위에 남아있는 말에 대해 모든 순열대로 진행.
 */
import java.io.IOException;
import java.util.Arrays;

public class BOJ17825_주사위윷놀이 {
    static int max;
    static int[] moves = new int[10];

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 10; i++)
            moves[i] = readInt();
        func(0, 0, new int[4], new int[4], new int[10]);
        System.out.println(max);
    }

    private static void func(int score, int cnt, int[] line, int[] idx, int[] debug) {
        if (cnt == 10) {
            max = Math.max(max, score);
            if (score == 252) {
                for (int i = 0; i < cnt; i++)
                    System.out.printf("%d ", debug[i]);
                System.out.println();
            }
            return;
        }

        boolean moved = false;
        loop: for (int i = 0; i < 4; i++) {
            if (line[i] == -1)
                continue;
            // cnt 번째 i번째 말을 이동
            int[] next = move(line[i], idx[i], moves[cnt]);

            for (int j = 0; j < 4; j++) {
                if (i == j || line[j] == -1)
                    continue;
                // 그 자리에 말이 있으면 이동 불가
                if (next[0] == line[j] && next[1] == idx[j])
                    continue loop;
            }

            // 이동 가능, 돌아오면 백트래킹
            moved = true;
            int[] newLine = Arrays.copyOf(line, 4);
            int[] newIdx = Arrays.copyOf(idx, 4);
            newLine[i] = next[0];
            newIdx[i] = next[1];
            debug[cnt] = i;
            func(score + getScore(next[0], next[1]), cnt + 1, newLine, newIdx, debug);
        }

        // 모든 말이 다 끝남. 움직인 말이 없음
        if (!moved)
            max = Math.max(max, score);
    }

    private static int getScore(int line, int idx) {
        switch (line) {
            case 0:
                return idx * 2;
            case 1:
                return 10 + idx * 3;
            case 2:
                return 20 + idx * 2;
            case 3:
                if (idx == 0)
                    return 30;
                else
                    return 29 - idx;
            case 4:
                return 25 + idx * 5;
        }
        return 0;
    }

    // 이동한 결과를 리턴
    private static int[] move(int line, int index, int dist) {
        int nextLine = line, nextIdx = index + dist;
        switch (line) {
            case 0:
                if (nextIdx > 20)
                    nextLine = -1;
                else if (nextIdx == 5) {
                    nextLine = 1;
                    nextIdx = 0;
                } else if (nextIdx == 10) {
                    nextLine = 2;
                    nextIdx = 0;
                } else if (nextIdx == 15) {
                    nextLine = 3;
                    nextIdx = 0;
                }
                break;
            case 1:
                if (nextIdx > 7)
                    nextLine = -1;
                else if (nextIdx > 6) {
                    nextLine = 0;
                    nextIdx = 20;
                } else if (nextIdx > 3) {
                    nextLine = 4;
                    nextIdx -= 4;
                }
                break;
            case 2:
                if (nextIdx > 6)
                    nextLine = -1;
                else if (nextIdx > 5) {
                    nextLine = 0;
                    nextIdx = 20;
                } else if (nextIdx > 2) {
                    nextLine = 4;
                    nextIdx -= 3;
                }
                break;
            case 3:
                if (nextIdx > 7)
                    nextLine = -1;
                else if (nextIdx > 6) {
                    nextLine = 0;
                    nextIdx = 20;
                } else if (nextIdx > 3) {
                    nextLine = 4;
                    nextIdx -= 4;
                }
                break;
            case 4:
                if (nextIdx > 3)
                    nextLine = -1;
                else if (nextIdx > 2) {
                    nextLine = 0;
                    nextIdx = 20;
                }
                break;
        }
        return new int[] { nextLine, nextIdx };
    }

    private static int readInt() throws IOException {
        int c;
        do {
            c = System.in.read();
        } while (c <= 32);
        int n = c & 15;
        c = System.in.read();
        while (c > 47) {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();
        }
        return n;
    }
}