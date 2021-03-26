import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Algorithm {
	static int N, M;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(sb);
	}

	private static void SetData() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		int[] array = new int[N];	
		
		for(int i = 0; i < N ; i++) 
			array[i] = Integer.parseInt(br.readLine());
		 
		Arrays.sort(array);		 
		int gcdValue = array[1] - array[0];	// 음수가 되지 않도록 큰 수에서 작은 수로 빼준다.
	 
		for(int i = 2; i < N; i++) {
			// 직전의 최대 공약수와 다음 수(arr[i] - arr[i - 1])의 최대공약수를 갱신 
			gcdValue = getGCD(gcdValue, array[i] - array[i - 1]);
		}
	
		for(int i = 2; i <= gcdValue; i++) {
			// i가 최대공약수의 약수라면 출력
			if(gcdValue% i == 0) {
				sb.append(i + " ");
			}
		}
	}

	private static int getGCD(int p, int q) {
		if (q == 0) {
			return p;
		}
		return getGCD(q, p % q);
	}

}