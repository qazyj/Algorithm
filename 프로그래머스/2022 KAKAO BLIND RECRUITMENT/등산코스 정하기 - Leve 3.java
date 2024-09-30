import java.util.*;

class Solution {
	static int n, answer;
	static List<Node>[] nodes;
	static int[] distance;
	static Set<Integer> gateSet, summitSet;

	public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
		this.n = n;
		distance = new int[n+1];
		nodes = new ArrayList[n+1];
		for(int i = 1; i <= n; i++) {
			distance[i] = Integer.MAX_VALUE;
			nodes[i] = new ArrayList<>();
		}
		gateSet = new HashSet<>();
		summitSet = new HashSet<>();
		for(int gate : gates) gateSet.add(gate);
		for(int summit : summits) summitSet.add(summit);
		for(int i = 0; i < paths.length; i++) {
			nodes[paths[i][0]].add(new Node(paths[i][1], paths[i][2]));
			nodes[paths[i][1]].add(new Node(paths[i][0], paths[i][2]));
		}
		answer = Integer.MAX_VALUE;
		int[] answer = bfs();
		return answer;
	}

	public int[] bfs() {
		PriorityQueue<Node> pq = new PriorityQueue<>();

		int answer = Integer.MAX_VALUE;
		for(int gate : gateSet) {
			distance[gate] = 0;
			pq.add(new Node(gate, 0));
		}

		while(!pq.isEmpty()) {
			Node node = pq.poll();

			if(summitSet.contains(node.cur)) {
				answer = Math.min(node.distance, answer);
				continue;
			}
			if(answer < node.distance) continue;

			for(Node cur : nodes[node.cur]) {
				int dis = Math.max(node.distance, cur.distance);
				if(distance[cur.cur] <= dis) continue;

				distance[cur.cur] = dis;
				pq.add(new Node(cur.cur, dis));
			}
		}

		int index = Integer.MAX_VALUE;
		for(int i : summitSet) {
			if(distance[i] > answer) continue;

			index = Math.min(index, i);
		}

		return new int[]{index, answer};
	}
}

class Node implements Comparable<Node> {
	int cur;
	int distance;

	public Node(int cur, int distance) {
		this.cur = cur;
		this.distance = distance;
	}

	@Override
	public int compareTo(Node o) {
		if(this.distance != o.distance)
			return this.distance - o.distance;
		return this.cur - o.cur;
	}
}