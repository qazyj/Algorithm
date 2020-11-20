import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Algorithm {
	static int N, M;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(Combination());
	}

	private static void SetData() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
	}

	private static BigInteger Combination() {
		BigInteger num1 = BigInteger.ONE;
		BigInteger num2 = BigInteger.ONE;
		for (int i = 0; i < M; i++) {
			num1 = num1.multiply(new BigInteger(String.valueOf(N - i)));
			num2 = num2.multiply(new BigInteger(String.valueOf(i + 1)));
		}

		return num1.divide(num2);
	}
}
