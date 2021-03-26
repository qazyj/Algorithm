import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
 
public class Main {
 
    private static int N;
    private static int K; 
    private static int[] Check;
 
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        Check = new int[100001];
        bfs();
        System.out.println(Check[K]);
    }
    private static void bfs(){
        Queue<Integer> queue = new LinkedList<>();
 
        queue.offer(N);
 
        while (!queue.isEmpty()){
            int n = queue.poll();
            if(n==K) break;
            if(n > 0 && Check[n-1] ==0){
                queue.offer(n-1);
                Check[n-1] = Check[n]+1;
            }
            if(n<100000 && Check[n+1]==0){
                queue.offer(n+1);
                Check[n+1] = Check[n] +1;
            }
            if(n*2<=100000 && Check[n*2]==0){
                queue.offer(n*2);
                Check[n*2] = Check[n] +1;
            }
        }
    }
}
 
