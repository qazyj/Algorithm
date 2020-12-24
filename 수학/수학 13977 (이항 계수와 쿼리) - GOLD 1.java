import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* [페르마의 소정리] _ 혜민 코드 참고
a^(p-1) % p = 1
(a/b) % M = ((a % M) * (b^-1 % M)) = ((a % M) * (b^(M-2) % M))
*/

public class Algorithm {
	static StringBuilder sb;
	static long[] fac,inv;
	static final int MOD = 1000000007;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(sb);
	}
	
	private static void SetData() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		sb = new StringBuilder();

		fac = new long[4040404];
		inv = new long[4040404];
		fac[0] = 1;
		for(int i=1; i<=4000000; i++) fac[i] = fac[i - 1] * i % MOD;
		inv[4000000] = pow(fac[4000000], MOD-2);
		for(int i=3999999; i>=0; i--){
			inv[i] = inv[i+1] * (i + 1) % MOD;
		}
		
		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());

			long result = fac[N];
			result = result * inv[K] % MOD;
			result = result * inv[N-K] % MOD;
			sb.append(String.valueOf(result) + "\n");
		}
	}
	private static long pow(long a, int b) {
		long result = 1;

		while (b > 0) {
			if (b % 2 == 1)
				result = result * a % MOD;
			b >>= 1;
			a = (a * a) % MOD;
		}

		return result;
	}
}
