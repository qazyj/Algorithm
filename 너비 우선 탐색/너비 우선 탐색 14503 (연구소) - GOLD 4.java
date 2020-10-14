import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
 
public class Algorithm {	
	static int[][] array;
	static int N, M, max = 0;
    static int[] x = {-1, 1, 0, 0};
    static int[] y = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		array = new int[N][M];

		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) 
				array[i][j] = Integer.parseInt(st.nextToken());			
		}
		
		dfs(0);
		System.out.println(max);

	}
	
    // 벽 기둥 3개를 세우기 위한 함수
	// DFS 재귀 형식
    private static void dfs(int count) {
    	// base case
        if(count == 3) {
            max = Math.max(max, bfs());
            return;
        }
        
        for(int i = 0; i < N; i++) {
            for(int j = 0 ; j < M; j++) {
                if(array[i][j] == 0) {
                    array[i][j] = 1;
					dfs(count+1);
                    array[i][j] = 0;
                }
            }
        }
    }
	
	private static int bfs() {		
		// 임시 배열 (기존 배열을 유지하기 위함)
        int[][] spreadVirusArray = new int[N][M];
        for(int i = 0; i < N; i++)
            for(int j = 0; j < M; j++)
            	spreadVirusArray[i][j] = array[i][j];
        
        Queue<int[]> queue = new LinkedList<>();
        
        // 바이러스 인 곳을 queue에 위치를 추가해줌.
        for(int i = 0; i < N; i++)
        	for(int j = 0; j < M; j++)
        		if(spreadVirusArray[i][j] == 2) queue.add(new int[] {i,j});
        
        // 벽으로 보호되지 않은 곳은 virus를 퍼뜨림
        // BFS
        while(!queue.isEmpty()){
            int location[] = queue.poll();

            for(int direction = 0; direction < 4; direction++){
                int r = location[0] + x[direction];
                int c = location[1] + y[direction];

                if(r >= 0 && c >= 0 && r < N && c < M) {
                    if(spreadVirusArray[r][c] == 0) {
                    	spreadVirusArray[r][c] = 2;
                        queue.add(new int[] {r,c});
                    }
                }
            }
        }                
        
        // 바이러스가 퍼지지 않은 지역 수
		int count = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++)
				if(spreadVirusArray[i][j] == 0) count++;
		}
		
        return count;
    }
}