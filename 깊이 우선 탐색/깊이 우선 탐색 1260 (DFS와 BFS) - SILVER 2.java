import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static ArrayList<Integer>[] array;
	static boolean[] check;

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(st.nextToken());
		array = (ArrayList<Integer>[]) new ArrayList[a + 1];
		for (int i = 1; i <= a; i++) {
			array[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < b; i++) {
			st = new StringTokenizer(br.readLine());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			array[c].add(d);
			array[d].add(c);
		}
		for (int i = 1; i <= a; i++) {
			Collections.sort(array[i]);
		}
		check = new boolean[a + 1];
		dfs(start);
		System.out.println();
		check = new boolean[a + 1];
		bfs(start);
	}

	public static void dfs(int x) {
		if (check[x]) {
			return;
		}
		check[x] = true;
		System.out.print(x + " ");
		for (int y : array[x]) {
			if (check[y] == false) {
				dfs(y);
			}
		}
	}

	public static void bfs(int start) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(start);
		check[start] = true;
		StringBuilder sb = new StringBuilder();
		while (!queue.isEmpty()) {
			int x = queue.remove();
			sb.append(x + " ");
			for (int y : array[x]) {
				if (check[y] == false) {
					check[y] = true;
					queue.add(y);
				}
			}
		}
		System.out.print(sb);
	}
}
