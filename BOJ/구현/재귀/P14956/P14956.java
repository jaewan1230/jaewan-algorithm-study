import java.util.Scanner;

public class P14956 {
    static int[] dx = {0, 0, 1, 1}, dy = {0, 1, 1, 0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] res = philoWalk(sc.nextInt(), sc.nextInt());
        System.out.println(res[0] + " " + res[1]);
    }

    static int[] philoWalk(int size, int m) {
        if (size == 2) {
            switch (m) {
                case 1:
                    return new int[]{1, 1};
                case 2:
                    return new int[]{1, 2};
                case 3:
                    return new int[]{2, 2};
                case 4:
                    return new int[]{2, 1};
            }
        }
        int half = size / 2;
        int quarter = half * half;
        int temp[] = philoWalk(half, (m - 1) % quarter + 1);
        if (m <= quarter) {
            return new int[]{temp[1], temp[0]};
        } else if (m <= 2 * quarter) {
            return new int[]{temp[0], temp[1] + half};
        } else if (m <= 3 * quarter) {
            return new int[]{temp[0] + half, temp[1] + half};
        }
        return new int[]{size + 1 - temp[1], half + 1 - temp[0]};
    }
}