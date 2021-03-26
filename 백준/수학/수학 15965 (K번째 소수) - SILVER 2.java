import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Algorithm {
	static int K;
	static int[] prime;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(prime[K - 1]);
	}

	private static void SetData() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		prime = new int[10000001];
		boolean[] check = new boolean[10000001];
		int index = 0;

		// 에라스토테네스의 체
		for (int i = 2; i <= 10000000; ++i) {
			if (!check[i]) {
				prime[index++] = i;
				for (int j = i + i; j <= 10000000; j += i) {
					check[j] = true;
				}
			}
		}
	}
}
