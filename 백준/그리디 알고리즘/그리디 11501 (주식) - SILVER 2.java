import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Algorithm {
	static int T;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		SetData();
        //Calculate();
		System.out.println(sb);
	}

	private static void SetData() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		
		for( int i = 0 ; i < T ; i++ ) {
			int N = Integer.parseInt(br.readLine());
			long stocks[] = new long[N];
			st = new StringTokenizer(br.readLine());
			for( int j = 0 ; j < N ; j++ ) {
				stocks[j] = Integer.parseInt(st.nextToken());
			}
			
			long max = 0;
			long answer = 0;
			for( int j = N-1 ; j >= 0 ; j-- ) {
				if(stocks[j] > max) {	// 현재 가격이 지금까지 젤 큰 가격이라면 바꿔줌.
					max = stocks[j];
				}else {
					answer += max - stocks[j];	// 팔았을 때 이득을 더 해줌
				}
			}
			sb.append(answer + "\n");
		}
	}

    private static void Calculate(){
    }
}