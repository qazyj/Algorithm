import java.io.*;
import java.util.*;

public class Main {
	static StringBuilder sb;
	static List<Integer> list;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		list = new ArrayList<>();
		visited = new boolean[n+1];
		dfs(n, 0);
		System.out.println(sb);
	}

	public static void dfs(int n, int count) {
		if(count == n) {
			for(int v : list) {
				sb.append(v).append(" ");
			}
			sb.append("\n");
			return;
		}

		for(int i = 1; i <= n; i++) {
			if(visited[i]) continue;

			visited[i] = true;
			list.add(i);
			dfs(n, count + 1);
			visited[i] = false;
			list.remove(Integer.valueOf(i));
		}
	}
}