import java.io.*;
import java.util.*;

public class Main {
	static int n, k;
	static Node[] nodes;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		nodes = new Node[n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			nodes[i] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}

		int left = 0;
		int right = 14150;
		while(left <= right) {
			int mid = (left+right)/2;

			if(canGo(mid)) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		System.out.println(left);
	}

	public static boolean canGo(int target) {
		Queue<Nod> q = new LinkedList<>();
		q.add(new Nod(0, 0, 0));
		boolean[] visited = new boolean[n];
		while(!q.isEmpty()) {
			Nod nod = q.poll();
			if(target >= getDistance(nod.x, nod.y, 10000, 10000) && nod.distance <= k) {
				return true;
			}
			if(nod.distance >= k) continue;

			for(int i = 0; i < n; i++) {
				if(visited[i]) continue;
				if(getDistance(nod.x, nod.y, nodes[i].x, nodes[i].y) > target) continue;

				visited[i] = true;
				q.add(new Nod(nodes[i].x, nodes[i].y, nod.distance+1));
			}
		}

		return false;
	}

	public static int getDistance(int x1, int y1, int x2, int y2) {
		double dis = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
		return (int) roundUpToTens(dis);
	}

	public static double roundUpToTens(double number) {
		// 소수점이 있는 경우에만 올림 처리
		if (number % 1 > 0) {
			return Math.ceil(number / 10);
		}
		// 정수인 경우 그대로 반환
		return number;
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

class Nod {
	int x;
	int y;
	int distance;

	public Nod(int x, int y, int distance) {
		this.x = x;
		this.y = y;
		this.distance = distance;
	}
}