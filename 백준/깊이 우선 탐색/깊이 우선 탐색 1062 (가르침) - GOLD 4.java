import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Algorithm {
	static int maxValue, N, K;
	static String[] s;
	static boolean[] check;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		maxValue = 0;
		s = new String[N];
		check = new boolean[26];

		// 무조건 배워야하는 단어 5개
		check['a' - 'a'] = true;
		check['n' - 'a'] = true;
		check['t' - 'a'] = true;
		check['i' - 'a'] = true;
		check['c' - 'a'] = true;

		// 앞 anta 뒤 tica를 제외한 문자열을 갖고온다.
		for (int i = 0; i < N; i++) {
			String temp = br.readLine();
			s[i] = temp.substring(4, temp.length() - 4);
		}

		dfs(0, 0);
		System.out.println(maxValue);
	}

	private static void dfs(int count, int start) {
		// basecase
		if (count == K - 5) {
			int temp = 0;
			for (int i = 0; i < N; i++) {
				boolean flag = true;

				// 배운 알파벳이 있는지 check
				for (int j = 0; j < s[i].length(); j++) {
					if (!check[s[i].charAt(j) - 'a']) {
						flag = false;
						break;
					}
				}

				if (flag) 
					temp++;				
			}
			maxValue = Math.max(temp, maxValue);
			return;
		}

		for (int i = start; i < 26; i++) {
			if (!check[i]) {
				check[i] = true;
				dfs(count + 1, i);
				check[i] = false;
			}
		}
	}
}