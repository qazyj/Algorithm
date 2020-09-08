import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Algorithm {
	static int [][] graph;
	static boolean[] check;

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		
		graph = new int[1001][1001];
		check = new boolean[1001];
		
		for (int i = 0; i < b; i++) {
			st = new StringTokenizer(br.readLine());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			graph[c][d] = graph[d][c] = 1;
		}
		
		int result = 0;
		
		for(int i = 1; i <= a; i++) {
			if(check[i] == false) {
				bfs(a, i);
				result++;
			}
		}
		
		System.out.print(result);
	}

	public static void bfs(int a, int index) {
		if(check[index] == true)
			return;
		else {
			check[index] = true;
			for(int i = 1; i <= a; i++) {
				if(graph[index][i] == 1)
					bfs(a, i);
			}
		}
	}
}
