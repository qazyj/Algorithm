import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Algorithm {
	private static int S, answer;
	private static boolean[][] check;

	public static void main(String[] args) throws Exception {
		SetData();
		bfs();
		System.out.println(answer);
	}
	
	private static void SetData() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		S = Integer.parseInt(br.readLine());
		dp = new boolean[1001][1001];	
		answer = 0;
	}
	
	private static void bfs() {
        Queue<int []> queue = new LinkedList<>();
        queue.offer(new int[] {1,1,1});
        dp[1][1] = true;
        
        while(!queue.isEmpty()){
        	int location[] = queue.peek();
            
            if(location[0] == S){
                answer = location[2];
                break;
            }
            
            if(location[0] - 1 != 0 && !dp[location[0] - 1][location[1]]){
                queue.offer(new int[] {location[0] - 1, location[1], location[2] + 1});
                dp[location[0] - 1][location[1]] = true;
            }
            
            if(!dp[location[0]][location[0]]){
                queue.offer(new int[] {location[0], location[0], location[2] + 1});
                dp[location[0]][location[0]] = true;
            }
            
            if(location[0] + location[1] <= S && !dp[location[0] + location[1]][location[1]]){
            	queue.offer(new int[] {location[0] + location[1], location[1], location[2] + 1});
                dp[location[0] + location[1]][location[1]] = true;
            }
            
            queue.poll();
        }
	}
}
