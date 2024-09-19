import java.io.*;
import java.util.StringTokenizer;
import java.util.*;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		boolean[][] arr = new boolean[n][n];
		for(int i = 0; i < n; i++) {
			String s = br.readLine();
			for(int j = 0; j < n; j++) {
				arr[i][j] = s.charAt(j) == '1';
			}
		}

		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[][] visited = new boolean[n][n];
		visited[0][0] = true;
		pq.add(new Node(0,0,0));
		int[] dx = {-1,1,0,0};
		int[] dy = {0,0,-1,1};
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			if(node.x == n - 1 && node.y == n - 1) {
				System.out.println(node.amount);
				break;
			}

			for(int i = 0; i < 4; i++) {
				int r = node.x + dx[i];
				int c = node.y + dy[i];

				if(r < 0 || r >= n || c < 0 || c >= n) continue;
				if(visited[r][c]) continue;

				visited[r][c] = true;
				if(!arr[r][c]) pq.add(new Node(r,c,node.amount+1));
				else pq.add(new Node(r,c,node.amount));
			}
		}
	}
}

class Node implements Comparable<Node> {
	int x;
	int y;
	int amount;

	public Node(int x, int y, int amount) {
		this.x = x;
		this.y = y;
		this.amount = amount;
	}

	@Override
	public int compareTo(Node o) {
		return this.amount - o.amount;
	}
}
