import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Algorithm {
	static int N, M, count;
	static int[] array;

	public static void main(String[] args) throws Exception {
		SetData();
		FindMinCount();
		System.out.println(count);
	}

	private static void SetData() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		count = 0;
		array = new int[N];

		for (int i = 0; i < N; i++) 
			array[i] = Integer.parseInt(br.readLine());
		
	}

	public static void FindMinCount() {
		// 가장 큰 돈부터 채워나가면 최소의 동전 수로 원하는 값을 채울 수 있다.
        for(int i = N-1; i>=0; i--){
            if(M>=array[i]){
                count += M/array[i];
                M = M%array[i];
            }            
        }
	}

}