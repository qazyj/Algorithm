import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Algorithm {
	static int N, M, minValue, destinationX, destinationY;
	static Queue<int[]> HedgehogQueue, waterQueue;
	static char[][] array;
	static boolean[][] check;
	static int[] x = { -1, 1, 0, 0 };
	static int[] y = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		SetData();
		bfs();
		if (minValue == Integer.MAX_VALUE)
			System.out.println("KAKTUS");
		else
			System.out.println(minValue);
	}

	private static void SetData() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		array = new char[N][M];
		check = new boolean[N][M];
		HedgehogQueue = new LinkedList<>();
		waterQueue = new LinkedList<>();
		minValue = Integer.MAX_VALUE;
		String s;

		for (int i = 0; i < N; i++) {
			s = br.readLine();
			for (int j = 0; j < M; j++) {
				array[i][j] = s.charAt(j);
				if (array[i][j] == 'D') {
					destinationX = i;
					destinationY = j;
				} else if (array[i][j] == 'S') {
					HedgehogQueue.offer(new int[] { i, j, 0 });
					check[i][j] = true;
				} else if (array[i][j] == '*') {
					waterQueue.offer(new int[] { i, j });
				}
			}
		}
	}

	private static void bfs() {
		while (!HedgehogQueue.isEmpty()) {
			int size = waterQueue.size();
			for (int i = 0; i < size; i++) {
				int location[] = waterQueue.poll();
				for (int direction = 0; direction < 4; direction++) {
					int r = location[0] + x[direction]; // 물 방향
					int c = location[1] + y[direction];

					if (r < 0 || r >= N || c < 0 || c >= M)
						continue;
					if (array[r][c] != '.')
						continue;
					array[r][c] = '*';
					waterQueue.offer(new int[] { r, c });
				}
			}

			size = HedgehogQueue.size();
			for (int i = 0; i < size; i++) {
				int location[] = HedgehogQueue.poll();
				int count = location[2];

				if (location[0] == destinationX && location[1] == destinationY) {
					minValue = Math.min(minValue, count); // 마지막 위치에 도달했을 때 지나온 칸 수를 return
				}

				for (int direction = 0; direction < 4; direction++) {
					int r = location[0] + x[direction]; // 고슴도치 방향
					int c = location[1] + y[direction];

					if (r < 0 || r >= N || c < 0 || c >= M)
						continue;
					if (check[r][c] || array[r][c] == '*' || array[r][c] == 'X')
						continue;
					check[r][c] = true;
					HedgehogQueue.offer(new int[] { r, c, count + 1 });
				}
			}
		}
	}
}
