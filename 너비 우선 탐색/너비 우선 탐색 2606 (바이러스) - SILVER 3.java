import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int a, b, result;
	static boolean[][] graph;	
	static boolean[] check;	

	public static void main(String[] agrs) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		a = Integer.parseInt(br.readLine());
		b = Integer.parseInt(br.readLine());

		graph = new boolean[a + 1][a + 1];
		check = new boolean[a + 1];

		for (int i = 1; b >= i; ++i) {
			String s = br.readLine();
			StringTokenizer st = new StringTokenizer(s);
			int first = Integer.parseInt(st.nextToken());
			int second = Integer.parseInt(st.nextToken());
			graph[first][second] = true;
			graph[second][first] = true;
		}		

		dfs(1);
		System.out.println(result);

	}

	static void dfs(int start) {
		check[start] = true;
		for (int i = 1; a >= i; ++i) {	        								
			if (graph[start][i] == true && check[i] == false) {
					dfs(i);		
					result++;	
				}
			}
		}

}
 