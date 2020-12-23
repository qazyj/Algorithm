import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, answer;
	static int[] array;
	static boolean[] check, reCheck;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(sb);
	}

	private static void SetData() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int i = 1; i <= T; i++) {
			N = Integer.parseInt(br.readLine());
			answer = N;
			array = new int[N + 1];
			check = new boolean[N + 1];
			reCheck = new boolean[N + 1];
			
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++)
				array[j] = Integer.parseInt(st.nextToken());
			
			for (int j = 1; j <= N; j++) {
				if (check[j] == false) {
					check[j] = true;
					dfs(j);
				}

			}

			sb.append(answer + "\n");
		}

	}

	private static void dfs(int number) {
		int next = array[number];
		if (check[next] == false) {
			check[next] = true;
			dfs(next);
		}
		if (reCheck[next] == false) {		// 한번 갈때마다 N에서 빼줌
			answer--;
			for (int i = next; i != number; i = array[i])
				answer--;
		}

		reCheck[number] = true;
	}
}
