import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
    condition:
    student: N
    one way direction: M, time: Ti

    input:
    1 <= N <= 1000
    1 <= M <= 10000
 */

class Main {
	static int n, m;
	static char[][] arr;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static List<Integer>[] list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		arr = new char[n][m];
		int sX = 0;
		int sY = 0;
		for(int i = 0; i < n; i++) {
			String s = br.readLine();
			for(int j = 0; j < m; j++) {
				arr[i][j] = s.charAt(j);
				if(arr[i][j] == 'C') {
					sX = i;
					sY = j;
				}
			}
		}

		list = new ArrayList[4];
		for(int i = 0; i < 4; i++) {
			list[i] = new ArrayList<>();
			if(i == 0 || i == 1) {
				list[i].add(2);
				list[i].add(3);
			} else {
				list[i].add(0);
				list[i].add(1);
			}
			list[i].add(i);
		}

		System.out.println(bfs(sX, sY));
	}

	public static int bfs(int sX, int sY) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int[][][] visited = new int[n][m][4];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				for(int d = 0; d < 4; d++) {
					visited[i][j][d] = Integer.MAX_VALUE;
				}
			}
		}
		for(int i = 0; i < 4; i++) {
			int r = sX + dx[i];
			int c = sY + dy[i];

			if(r < 0 || r >= n || c < 0 || c >= m) continue;
			if(arr[r][c] == '*') continue;

			visited[r][c][i] = 0;
			pq.add(new Node(r, c, i, 0));
		}

		while(!pq.isEmpty()) {
			Node node = pq.poll();
			if(arr[node.x][node.y] == 'C' && !(node.x == sX && node.y == sY)) {
				return node.count;
			}

			for(int i : list[node.dir]) {
				int r = node.x + dx[i];
				int c = node.y + dy[i];

				if(r < 0 || r >= n || c < 0 || c >= m) continue;
				if(arr[r][c] == '*') continue;

				int newCount = (i == node.dir) ? node.count : node.count + 1;
				if(visited[r][c][i] > newCount) {
					visited[r][c][i] = newCount;
					pq.add(new Node(r, c, i, newCount));
				}
			}
		}

		return -1;
	}
}

class Node implements Comparable<Node> {
	int x;
	int y;
	int dir;
	int count;

	public Node(int x, int y, int dir, int count) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.count = count;
	}

	@Override
	public int compareTo(Node o) {
		return this.count - o.count;
	}
}