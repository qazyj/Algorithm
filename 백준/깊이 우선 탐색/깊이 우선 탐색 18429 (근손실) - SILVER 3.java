import java.io.*;
import java.util.*;

public class Main {
	static int N,K,answer;
	static int[] arr;
	static boolean[] visited;
	static List<Integer> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		arr = new int[N];

		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0 ; i < N ; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		visited = new boolean[N];
		dfs(0, 0);

		System.out.println(answer);
	}

	private static void dfs(int idx, int depth) {
		if(depth == N) {
			int weight = 500;
			for(int i : list) {
				weight = weight - K + i;

				if(weight < 500)
					return;
			}

			answer++;
			return;
		}

		for(int i = 0 ; i < N ; i++) {
			if(visited[i]) continue;

			visited[i] = true;
			list.add(arr[i]);
			dfs(i + 1, depth + 1);
			visited[i] = false;
			list.remove(list.size() - 1);
		}
	}
}