import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Algorithm {
	static int N,M, minMemory;
	static int[] usingByteOfMemory, disabledByteOfMemory, dp;

	public static void main(String[] args) throws Exception {
		SetData();	
		minMemory = ReturnMinMemory();
		System.out.println(minMemory);
	}

	// 데이터
	private static void SetData() throws Exception  {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		usingByteOfMemory = new int[N];
		disabledByteOfMemory = new int[N];
		dp = new int[10001];
		Arrays.fill(dp, -1);
		minMemory = Integer.MAX_VALUE;
		
		st = new StringTokenizer(br.readLine());
		StringTokenizer st2 = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			usingByteOfMemory[i] = Integer.parseInt(st.nextToken());
			disabledByteOfMemory[i] = Integer.parseInt(st2.nextToken());
		}
	}
	
	private static int ReturnMinMemory() {		
		
		// dp[i]: i cost를 써서 확보할 수 있는 최대 메모리
		for(int i=0; i<N; i++){
			int cost = disabledByteOfMemory[i];
			// 뒤에서 부터 확인해야 겹치지 않고 값을 update 할 수 있다.
			for(int j=10000; j>=cost; j--){
				if(dp[j-cost] != -1){
					// 이전 j-cost 까지의 최대 값에 현재 memory를 더하는게 더 크다면 update
					if(dp[j-cost] + usingByteOfMemory[i] > dp[j])
						dp[j] = dp[j-cost] + usingByteOfMemory[i];
				}
			}
			// 메모리 업데이트가 안되어있는 경우 업데이트
			// 단 메모리가 더 큰 경우에만 업데이트 가능
			if(dp[cost] < usingByteOfMemory[i]) dp[cost] = usingByteOfMemory[i];
		}

		for(int i=0; i<10001; i++){
			// 최소 메모리 return
			if(dp[i] >= M){
				return i;
			}
		}
		return 0;
	}
}