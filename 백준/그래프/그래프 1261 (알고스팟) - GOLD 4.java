import java.io.*;
import java.util.*;

public class Main {
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int n,m;
	static int[][] arr, distance;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		arr = new int[n][m];
		distance = new int[n][m];
		for(int i = 0; i < n; i++) {
			String s = br.readLine();
			for(int j = 0; j < m; j++) {
				arr[i][j] = s.charAt(j) - '0';
			}
		}

		bfs();
		System.out.println(distance[n-1][m-1]);
	}

	public static void bfs() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		distance = new int[n][m];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				distance[i][j] = Integer.MAX_VALUE;
			}
		}
		pq.add(new Node(0,0,0));
		distance[0][0] = 0;
		while(!pq.isEmpty()) {
			Node node = pq.poll();

			if(node.x == n-1 && node.y == m-1) {
				break;
			}

			for(int d = 0; d < 4; d++) {
				int r = node.x + dx[d];
				int c = node.y + dy[d];

				if (r < 0 || r >= n || c < 0 || c >= m) continue;
				if (arr[r][c] == 1 && distance[r][c] <= distance[node.x][node.y] + 1) continue;
				if (arr[r][c] == 0 && distance[r][c] <= distance[node.x][node.y]) continue;

				if (arr[r][c] == 1) {
					distance[r][c] = distance[node.x][node.y] + 1;
					pq.add(new Node(r, c, node.distance + 1));
				}
				if(arr[r][c] == 0) {
					distance[r][c] = distance[node.x][node.y];
					pq.add(new Node(r, c, node.distance));
				}
			}
		}
	}
}

class Node implements Comparable<Node> {
	int x;
	int y;
	int distance;

	public Node(int x, int y, int distance) {
		this.x = x;
		this.y = y;
		this.distance = distance;
	}

	@Override
	public int compareTo(Node o) {
		return this.distance - o.distance;
	}
}