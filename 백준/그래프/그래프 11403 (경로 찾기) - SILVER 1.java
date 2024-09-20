import java.io.*;
import java.util.StringTokenizer;
import java.util.*;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int[][] arr = new int[n][n];
		List<Integer>[] nodes = new List[n];
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			nodes[i] = new ArrayList<>();
			for(int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j] == 0) continue;
				nodes[i].add(j);
			}
		}

		for(int i = 0; i < n; i++) {
			Queue<Integer> q = new LinkedList<>();
			for(int next : nodes[i]) {
				q.add(next);
			}
			while(!q.isEmpty()) {
				int cur = q.poll();

				for(int next : nodes[cur]) {
					if(arr[i][next] == 1) continue;

					arr[i][next] = 1;
					q.add(next);
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				sb.append(arr[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
