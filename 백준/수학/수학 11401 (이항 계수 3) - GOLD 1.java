import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* [페르마의 소정리] _ 혜민 코드 참고
a^(p-1) % p = 1
(a/b) % M = ((a % M) * (b^-1 % M)) = ((a % M) * (b^(M-2) % M))
*/

public class Algorithm {
    static final int MOD = 1000000007;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        long a = fac(N);
        long b = fac(N - K) * fac(K) % MOD;
        long result = a * pow(b, MOD - 2) % MOD;
        System.out.print(String.valueOf(result));
        
    }

    public static long fac(long n) {
        long result = 1;
        while (n > 1) {
            result = (result * n) % MOD;
            n--;
        }
        return result;
    }

    public static long pow(long a, int b) {
        long result = 1;
        long temp = a;

        while (b > 0) {
            if (b % 2 == 1) result = result * temp % MOD;
            b = b / 2;
            temp = (temp * temp) % MOD;
        }

        return result;
    }
}
