import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Algorithm {
	static int S, K;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(FindMaxValue());
	}

	private static void SetData() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		S = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
	}

	private static long FindMaxValue() {
		int q = S / K; // 몫
		int r = S % K; // 나머지
		long max = 1;

		for (int i = 1; i <= K; i++) {
			if (i <= r) {// 나머지 갯수만큼 +1
				max *= (q + 1);
			} else {
				max *= q;
			}
		}

		return max;
	}
}
