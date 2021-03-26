import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Algorithm {
	static int N, count;
	static boolean[] check;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(count);
	}

	private static void SetData() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		check = new boolean[1001];
		count = 0;
		
		check[0] = check[1] = true;
		for (int i = 2; i <= 1000; i++) {
			if (check[i] == true) {
				continue;
			}
			// 해당 수로 나누어 떨어지는 수는 소수이므로 true로 check
			for (int j = i + i; j <= 1000; j+=i) {
				check[j] = true;
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			if(!check[Integer.parseInt(st.nextToken())]) count++;
		}
	}

}
