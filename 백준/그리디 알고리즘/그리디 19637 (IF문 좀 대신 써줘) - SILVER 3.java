import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		Power[] powers = new Power[n];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			powers[i] = new Power(st.nextToken(), Integer.parseInt(st.nextToken()));
		}
		PriorityQueue<Node> pq = new PriorityQueue<>();
		for(int i = 0; i < m; i++) {
			int v = Integer.parseInt(br.readLine());
			pq.add(new Node(i, v));
		}

		int index = 0;
		String[] answer = new String[m];
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			while(powers[index].value < cur.value) {
				index++;
			}
			answer[cur.index] = powers[index].name;
		}

		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < m; i++) {
			sb.append(answer[i]).append("\n");
		}
		System.out.println(sb);
	}
}

class Power {
	String name;
	int value;

	public Power(String name, int value) {
		this.name = name;
		this.value = value;
	}
}

class Node implements Comparable<Node> {
	int index;
	int value;

	public Node(int index, int value) {
		this.index = index;
		this.value = value;
	}

	@Override
	public int compareTo(Node o) {
		return this.value - o.value;
	}
}