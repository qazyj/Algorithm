import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
    static int[][] array;
    static boolean[][] check;
    static int N,M;
    static int[] x = {-1,1,0,0};
    static int[] y = {0,0,-1,1};

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        array = new int[N][M];
        check = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            for (int j = 0; j < M; j++) {
                array[i][j] = s.charAt(j) - '0';
            }
        }

        check[0][0] = true;
        bfs();

        System.out.println(array[N-1][M-1]);
    }

    public static void bfs(){
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {0,0});
        
        while(!queue.isEmpty()){
            int location[] = queue.poll();            	

            for(int direction = 0; direction<4; direction++){
                int r = location[0] + dr[direction];
                int c = location[1] + dc[direction];
                
                if(r >= 0 && c >= 0 && r < N && c < M){
                    if(array[r][c] != 0 && !check[r][c]){
                        queue.offer(new int[] {r,c});
                        check[r][c] = true;
                        array[r][c] = array[location[0]][location[1]] + 1;
                    }
                }
            }
        }
    }
}