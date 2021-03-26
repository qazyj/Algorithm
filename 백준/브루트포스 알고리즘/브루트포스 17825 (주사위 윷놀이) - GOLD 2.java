import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Algorithm {
	static int maxValue;
	static int[] dice, X, Y;
	static int[][] map;
	static boolean[][] check;

	public static void main(String[] args) throws Exception {
		SetData();
		dfs(0, 0);
		System.out.println(maxValue);
	}

	private static void SetData() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		dice = new int[10];
		map = new int[4][30]; // 각 판의 점수를 저장
		check = new boolean[4][30]; // 해당 지역에 말이 있는지 없는지 check
		X = new int[4]; // 각각 주사위 위치 말 번호 저장
		Y = new int[4]; // 각각 주사위 맵 위치 저장
		maxValue = Integer.MIN_VALUE;

		for (int i = 0; i < 10; i++)
			dice[i] = Integer.parseInt(st.nextToken());

		map[0] = new int[] { 0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, -1, -1, -1,
				-1, -1, -1, -1 };
		map[1] = new int[] { 0, 0, 0, 0, 0, 10, 13, 16, 19, 25, 30, 35, 40, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
				-1, -1, -1, -1 };
		map[2] = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 20, 22, 24, 25, 30, 35, 40, -1, -1, -1, -1, -1, -1, -1, -1,
				-1, -1, -1 };
		map[3] = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 30, 28, 27, 26, 25, 30, 35, 40, -1, -1, -1,
				-1, -1 };
	}

	private static void dfs(int index, int sum) {
		if (index == 10) {
			maxValue = Math.max(maxValue, sum);
			return;
		}
		for (int i = 0; i < 4; i++) {
			int tempX = X[i];
			int tempY = Y[i];

			if (map[X[i]][Y[i]] == -1)	continue;
			check[X[i]][Y[i]] = false;

			// 
			if (X[i] == 0) {
				switch (Y[i]) {
				case 5:
					X[i] += 1;
					break;
				case 10:
					X[i] += 2;
					break;
				case 15:
					X[i] += 3;
					break;
				}
			}

			Y[i] += dice[index];

			// 바뀐 map의 주사위 위치에  따라 map을 바꿈
			switch (map[X[i]][Y[i]]) {
			case 40:
				X[i] = 0;
				Y[i] = 20;
				break;
			case 25:
				X[i] = 1;
				Y[i] = 9;
				break;
			case 30:
				X[i] = 1;
				Y[i] = 10;
				break;
			case 35:
				X[i] = 1;
				Y[i] = 11;
				break;
			}

			if (!check[X[i]][Y[i]]) {
				if (map[X[i]][Y[i]] != -1) {
					check[X[i]][Y[i]] = true;
					dfs(index + 1, sum + map[X[i]][Y[i]]);
					check[X[i]][Y[i]] = false;
				} else {
					dfs(index + 1, sum);
				}
			}
			X[i] = tempX;
			Y[i] = tempY;
			check[X[i]][Y[i]] = true;
		}
	}
}
