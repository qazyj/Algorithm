import java.io.*;
import java.util.*;
import java.util.function.Function;

public class Main {
	static int n,m, sheep, wolf;
	static char[][] arr;
	static boolean[][] visited;
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new char[n][m];
		visited = new boolean[n][m];
		for(int i = 0; i < n; i++) {
			String s = br.readLine();
			for(int j = 0 ; j < m; j++) {
				arr[i][j] = s.charAt(j);
			}
		}

		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(visited[i][j]) continue;

				bfs(i,j);
			}
		}

		System.out.println(sheep + " " + wolf);
	}

	private static void bfs(int i, int j) {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(i,j));
		visited[i][j] = true;
		int s = 0;
		int w = 0;
		while(!q.isEmpty()) {
			Node node = q.poll();

			for(int d = 0; d < 4 ; d++) {
				int r = node.x + dx[d];
				int c = node.y + dy[d];

				if(r < 0 || r >= n || c < 0 || c >= m) continue;
				if(visited[r][c] || arr[r][c] == '#') continue;

				if(arr[r][c] == 'v') w++;
				if(arr[r][c] == 'o') s++;

				visited[r][c] = true;
				q.add(new Node(r,c));
			}
		}

		if(s > w) sheep += s;
		else wolf += w;
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