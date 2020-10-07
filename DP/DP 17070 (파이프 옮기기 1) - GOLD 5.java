import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Algorithm {	
	static int[][] array;
	static int N;
	static int count;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		array = new int[N+1][N+1];
		count = 0;
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++) {
				 array[i][j] = Integer.parseInt(st.nextToken());
			}
		}		
		
		RecurPipe(1, 2, 0);

		System.out.println(count);
    }
	
	private static void RecurPipe(int x, int y, int type) {
		if(x == N && y == N && array[x][y] != 1) {
			count++;
			return;
		}
		
		// 가로
		if(type == 0) {
			// 오른쪽
			if(check(x, y+1) && array[x][y+1] == 0) 
				RecurPipe(x, y+1, 0);			
			// 오른쪽 아래 대각선
			if(check(x+1, y+1) && array[x+1][y+1] == 0 && array[x+1][y] == 0 && array[x][y+1] == 0) 
				RecurPipe(x+1, y+1, 2); 
			
		} else if(type == 1) { // 세로
			// 밑
			if(check(x+1, y) && array[x+1][y] == 0) 
				RecurPipe(x+1, y, 1);			
			// 오른쪽 아래 대각선
			if(check(x+1, y+1) && array[x+1][y+1] == 0 && array[x+1][y] == 0 && array[x][y+1] == 0) 
				RecurPipe(x+1, y+1, 2);
			
		} else if(type == 2) {	// 대각
			// 가로
			if(check(x, y+1) && array[x][y+1] == 0) 
				RecurPipe(x, y+1, 0);			
			// 세로
			if(check(x+1, y) && array[x+1][y] == 0) 
				RecurPipe(x+1,y, 1);			
			// 그대로 대각선
			if(check(x+1, y+1) && array[x+1][y+1] == 0) 
				RecurPipe(x+1,y+1,2);
		}
	}
	
	public static boolean check(int x, int y) {
		return x>=1 && x<=N && y>=1 && y<=N;
	}
}