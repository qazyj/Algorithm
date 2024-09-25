import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
    condition: no direction, 1->n, must visit temp node
 */

class Main {
	static int n, answer;
	static List<Node>[] nodes;
	static int[][] distance;
	static Map<Integer, Integer> map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		distance = new int[n+1][1<<2];
		nodes = new List[n+1];
		for(int i = 1; i <= n; i++) {
			nodes[i] = new ArrayList<>();
			for(int j = 0; j < (1<<2); j++) distance[i][j] = Integer.MAX_VALUE;
		}

		for(int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			nodes[a].add(new Node(b,c));
			nodes[b].add(new Node(a,c));
		}

		map = new HashMap<>();
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 2 ;i++) {
			map.put(Integer.parseInt(st.nextToken()), i);
		}

		System.out.println(bfs());
	}

	private static int bfs() {
		PriorityQueue<Dir> pq = new PriorityQueue<>();
		int bit = 0;
		if(map.containsKey(1)) bit |= (1<<map.get(1));
		distance[1][bit] = 0;
		pq.add(new Dir(1, 0, bit));
		while(!pq.isEmpty()) {
			Dir dir = pq.poll();

			if(dir.cur == n && dir.bit == 3) {
				return dir.distance;
			}

			for(Node next : nodes[dir.cur]) {
				bit = dir.bit;
				if(map.containsKey(next.cur)) bit |= (1<<map.get(next.cur));

				if(distance[next.cur][bit] <= distance[dir.cur][dir.bit]+next.distance) continue;

				distance[next.cur][bit] = distance[dir.cur][dir.bit]+next.distance;
				pq.add(new Dir(next.cur, distance[dir.cur][dir.bit]+next.distance, bit));
			}
		}
		return -1;
	}
}

class Dir implements Comparable<Dir> {
	int cur;
	int distance;
	int bit;

	public Dir(int cur, int distance, int bit) {
		this.cur = cur;
		this.distance = distance;
		this.bit = bit;
	}

	@Override
	public int compareTo(Dir o) {
		return this.distance - o.distance;
	}
}

class Node {
	int cur;
	int distance;

	public Node(int cur, int distance) {
		this.cur = cur;
		this.distance = distance;
	}
}