import java.util.Scanner;

public class P1018 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
        char[][] board = new char[n][m + 1];
        for (int i = 0; i < n; i++) {
            String s = sc.next();
            board[i] = s.toCharArray();
        }
        int min=Integer.MAX_VALUE;
        for(int i=0;i<n-7;i++){
            for(int j=0;j<m-7;j++){
                int w_cnt=0, b_cnt=0;
                for(int i1=0;i1<8;i1++){
                    for(int j1=0;j1<8;j1++){
                        if((i1+j1)%2==0){
                            if(board[i+i1][j+j1]=='W'){
                                b_cnt++;
                            }
                            else w_cnt++;
                        }
                        else{
                            if(board[i+i1][j+j1]=='W'){
                                w_cnt++;
                            }
                            else b_cnt++;
                        }
                    }
                }
                if(w_cnt<min) min=w_cnt;
                if(b_cnt<min) min=b_cnt;
            }
        }
        System.out.println(min);
    }
}