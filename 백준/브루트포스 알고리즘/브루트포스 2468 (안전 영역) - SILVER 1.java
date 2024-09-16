import java.io.*;
import java.util.StringTokenizer;
import java.util.*;

public class Solution {
	static int n, answer;
	static int[][] arr;
	static boolean[][] visited;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,1,-1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				min = Math.min(min, arr[i][j]);
				max = Math.max(max, arr[i][j]);
			}
		}

		answer = 0;
		min = (min==0)?0:min-1;
		for(int i = min; i <= max; i++) {
			dfs(i);
		}
		System.out.println(answer);
	}

	public static void dfs(int min) {
		visited = new boolean[n][n];
		int count = 0;
		for(int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (visited[i][j] || arr[i][j] <= min) continue;

				count++;
				bfs(i, j, min);
			}
		}
		answer = Math.max(answer, count);
	}

	public static void bfs(int i, int j, int min) {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(i,j));
		visited[i][j] = true;
		while(!q.isEmpty()) {
			Node cur = q.poll();

			for(int d = 0; d < 4; d++) {
				int r = cur.x + dx[d];
				int c = cur.y + dy[d];

				if(r < 0 || c < 0 || r >= n || c >= n) continue;
				if(visited[r][c] || arr[r][c] <= min) continue;

				visited[r][c] = true;
				q.add(new Node(r, c));
			}
		}
	}
}

class Node {
	int x;
	int y;

	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
