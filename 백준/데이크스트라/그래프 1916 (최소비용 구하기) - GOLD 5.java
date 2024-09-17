import java.io.*;
import java.util.StringTokenizer;
import java.util.*;

public class Solution {
	static int n;
	static List<Node>[] nodes;
	static int[] distance;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		nodes = new List[n+1];
		distance = new int[n+1];
		for(int i = 1; i <= n; i++) {
			nodes[i] = new ArrayList<Node>();
			distance[i] = Integer.MAX_VALUE;
		}
		int m = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			nodes[a].add(new Node(b,c));
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());

		int answer = 0;
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0));
		distance[start] = 0;
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			if(cur.next == end) {
				answer = cur.amount;
				break;
			}

			for(Node next : nodes[cur.next]) {
				if(distance[next.next] <= cur.amount + next.amount) continue;

				distance[next.next] = cur.amount + next.amount;
				pq.add(new Node(next.next, cur.amount+next.amount));
			}
		}
		System.out.println(answer);
	}
}

class Node implements Comparable<Node> {
	int next;
	int amount;

	public Node(int next, int amount) {
		this.next = next;
		this.amount = amount;
	}

	@Override
	public int compareTo(Node o) {
		return this.amount - o.amount;
	}
}
