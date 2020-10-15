import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
	private static int N, M, count = 0, min = Integer.MAX_VALUE;
	private static int[][] array;
	private static boolean[] check;
	private static ArrayList<int[]> virus;
	private static int[] x = { 0, 0, -1, 1 };
	private static int[] y = { -1, 1, 0, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		array = new int[N][N];
		virus = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				array[i][j] = Integer.parseInt(st.nextToken());
				if (array[i][j] == 2)
					virus.add(new int[] { i, j });
				if (array[i][j] == 0)
					count++;
			}
		}
		count += virus.size() - M;
		check = new boolean[virus.size()];
		if (count != 0)
			dfs(0, 0);
		else
			min = 0;
		
		if(min != Integer.MAX_VALUE)
			System.out.println(min);
		else
			System.out.println("-1");
	}

	private static void dfs(int start, int depth) {
		// Basecase
		if (depth == M) {
			int[][] tempArray = copyArray();
			bfs(tempArray, count);
			return;
		}
		for (int i = start; i < virus.size(); i++) {
			check[i] = true;
			dfs(i+1, depth + 1);
			check[i] = false;
		}
	}

	private static void bfs(int[][] tempArray, int count) {
		Queue<int[]> queue = new LinkedList<>();
		for (int i = 0; i < virus.size(); i++) {
			if (check[i])
				queue.add(virus.get(i));
		}
		
		int time = 0;
		while (!queue.isEmpty()) {
			if (time >= min)
				break;
			int len = queue.size();
			for (int j = 0; j < len; j++) {
				int[] location = queue.poll();
				for (int direction = 0; direction < 4; direction++) {
					int r = location[0] + x[direction];
					int c = location[1] + y[direction];
					if (r >= 0 && c >= 0 && r < N && c < N) {
						if (tempArray[r][c] == 0) {
							tempArray[r][c] = 2;
							queue.add(new int[] { r, c });
							count--;
						}
					}
				}
			}
			
			time++;
			if (count == 0) {
				min = time;
				return;
			}
		}
	}

	private static int[][] copyArray() {
		int[][] result = new int[N][N];
		for (int i = 0; i < N; i++) {
			System.arraycopy(array[i], 0, result[i], 0, N);
		}
		for (int i = 0; i < virus.size(); i++) {
			if (!check[i]) {
				int[] location = virus.get(i);
				result[location[0]][location[1]] = 0;
			}
		}
		return result;
	}
}
