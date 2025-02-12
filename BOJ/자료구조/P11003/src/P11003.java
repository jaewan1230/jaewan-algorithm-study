import java.io.*;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class P11003 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N,SIZE_OF_WINDOW;
        StringTokenizer st = new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        SIZE_OF_WINDOW=Integer.parseInt(st.nextToken());
        st=new StringTokenizer(br.readLine());
        Deque<Node> mydeque = new LinkedList<>();
        for(int i=0;i<N;i++){
            int now=Integer.parseInt(st.nextToken());
            while(!mydeque.isEmpty()&&mydeque.getLast().value>now){
                mydeque.removeLast();
            }
            mydeque.addLast(new Node(now,i));
            if(mydeque.getFirst().index<=i-SIZE_OF_WINDOW){
                mydeque.removeFirst();
            }
            bw.write(mydeque.getFirst().value+" ");
        }
        bw.flush();
        bw.close();
    }
    static class Node {
        public int value;
        public int index;

        Node(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }
}
