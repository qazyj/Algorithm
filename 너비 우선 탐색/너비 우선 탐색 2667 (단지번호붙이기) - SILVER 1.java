import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
    static int[][] array;
    static boolean[][] check;
    static int N;
    static int[] x = {-1,1,0,0};
    static int[] y = {0,0,-1,1};
    static PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        array = new int[N][N];
        check = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                array[i][j] = s.charAt(j) - '0';
            }
        }

        for(int i = 0; i < N; i++) {
        	for(int j = 0; j < N; j++) {
        		if(!check[i][j] && array[i][j] == 1) {
        			bfs(i,j);
        		}
        	}
        }
        
        System.out.println(priorityQueue.size());
        while (!priorityQueue.isEmpty())
            System.out.println(priorityQueue.poll());
    }

    public static void bfs(int i, int j){
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {i,j});
        
        int count = 0;
        while(!queue.isEmpty()){
            int location[] = queue.poll();            	

            for(int direction = 0; direction<4; direction++){
                int r = location[0] + x[direction];
                int c = location[1] + y[direction];
                
                if(r >= 0 && c >= 0 && r < N && c < N){
                    if(array[r][c] == 1 && !check[r][c]){
                        queue.offer(new int[] {r,c});
                        check[r][c] = true;
                        count++;
                    }
                }
            }
        }
        if(count == 0)
        	priorityQueue.offer(1);
        else
        	priorityQueue.offer(count);
    }
}