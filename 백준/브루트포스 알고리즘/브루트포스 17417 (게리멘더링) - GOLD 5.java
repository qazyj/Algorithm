import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Algorithm {
	static int N, answer;
	static int[] population, area;
	static boolean[][] connect;
	static boolean[] check;	

	public static void main(String[] args) throws Exception {
		SetData();
		dfs(1);
		if(answer == Integer.MAX_VALUE)
			answer = -1;
		System.out.println(answer);
	}

	private static void SetData() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		answer = Integer.MAX_VALUE;
		population = new int[N + 1];
		area = new int[N + 1];
		connect = new boolean[N+1][N+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			population[i] = Integer.parseInt(st.nextToken()); 
		}

		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int count = Integer.parseInt(st.nextToken());
			for(int j = 0; j < count; j++) {
				int temp = Integer.parseInt(st.nextToken());
				connect[i][temp] = true;
				connect[temp][i] = true;
			}
		}
	}
	
	static void dfs(int count) {
        if(count == N+1) {
        	// 나눈 지역의 인구 수를 구함
            int area1 = 0, area2 = 0;
            for(int i=1; i<=N; i++) {
                if(area[i] == 1)
                    area1 += population[i];
                else
                    area2 += population[i];
            }
            
            // 두 지역으로 나누어 져있는지 체크
            check = new boolean[N+1];
            int result = 0;
            for(int i=1; i<=N; i++) {
                if(!check[i]) {
                    checkArea(i, area[i]);
                    result++;
                }
            }
            
            // 두 지역으로 나누어져 있으면 두 지역 값의 차를 구해서 최소 값 도출
            if(result == 2) {
                if(answer > Math.abs(area1 - area2))
                    answer =  Math.abs(area1 - area2);
            }
            return;
        }
        
        // 지역 1 포함
        area[count] = 1;
        dfs(count+1);
        
        // 지역 2 포함
        area[count] = 2;
        dfs(count+1);
	}
	
    private static void checkArea(int index, int number) {
        check[index] = true;
        for(int i=1; i<=N; i++) {
            if(connect[index][i] && !check[i] && area[i]==number)
                checkArea(i, number);
        }
    }
}