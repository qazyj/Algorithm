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
	static int n;
	static List<Node>[] nodes;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		nodes = new List[n+1];
		for(int i = 1; i <= n; i++) {
			nodes[i] = new ArrayList<>();
		}

		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			nodes[a].add(new Node(b,c));
		}

		int answer = 0;
		for(int i = 1; i <= n; i++) {
			if(i == x) continue;

			int go = bfs(i, x);
			int back = bfs(x, i);

			answer = Math.max(answer, go+back);
		}
		System.out.println(answer);
	}

	public static int bfs(int start, int end) {
		PriorityQueue<Dir> pq = new PriorityQueue<>();
		int[] distance = new int[n+1];
		for(int i = 1; i <= n; i++) {
			distance[i] = Integer.MAX_VALUE;
		}
		distance[start] = 0;
		pq.add(new Dir(start, 0));

		while(!pq.isEmpty()) {
			Dir dir = pq.poll();
			if(dir.cur == end) return dir.distance;

			for(Node next : nodes[dir.cur]) {
				if(distance[next.cur] <= dir.distance + next.distance) continue;

				distance[next.cur] = dir.distance + next.distance;
				pq.add(new Dir(next.cur, dir.distance + next.distance));
			}
		}

		// error
		// 문제의 조건은 모든 학생이 x의 집에 갈 수 있다고 가정
		return -1;
	}
}

class Dir implements Comparable<Dir> {
	int cur;
	int distance;

	public Dir(int cur, int distance) {
		this.cur = cur;
		this.distance = distance;
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