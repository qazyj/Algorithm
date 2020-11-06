import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M, count, minValue;
	static int[][] array;
	static int[] x, y;

	public static void main(String[] args) throws Exception {
		SetData();
		DFS(0);
		System.out.println(minValue);
	}

	// 데이터
	private static void SetData() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		count = 0; // 벽이 아닌 방향이 있는 1~5 숫자가 몇개있는지 count (basecase로 두기 위함)
		array = new int[N][M];
		x = new int[8];
		y = new int[8];
		minValue = Integer.MAX_VALUE; // 사각지대 최소 개수

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				array[i][j] = Integer.parseInt(st.nextToken());

				// 미리 cctv 좌표를 저장한다.
				if (array[i][j] != 0 && array[i][j] != 6) {
					x[count] = i;
					y[count] = j;
					count++;
				}
			}
		}
	}

	private static void DFS(int startIndex) {

		// basecase 모든 cctv를 확인해본 결과
		if (startIndex == count) {
			minValue = Math.min(minValue, RemainCCTV());
			return;
		}

		for (int i = startIndex; i < count; i++) {
			if (array[x[i]][y[i]] == 1) {
				CheckCCTV(x[i], y[i], startIndex, 1);
			} else if (array[x[i]][y[i]] == 2) {
				CheckCCTV(x[i], y[i], startIndex, 2);
			} else if (array[x[i]][y[i]] == 3) {
				CheckCCTV(x[i], y[i], startIndex, 3);
			} else if (array[x[i]][y[i]] == 4) {
				CheckCCTV(x[i], y[i], startIndex, 4);
			} else if (array[x[i]][y[i]] == 5) {
				CheckCCTV(x[i], y[i], startIndex, 5);
			}
		}
	}

	private static void CheckCCTV(int i, int j, int startIndex, int numberOfCCTV) {
		switch (numberOfCCTV) {
		case 1:
			GoUp(i,j);
			DFS(startIndex + 1);
			GoBackUp(i,j);
			GoDown(i,j);
			DFS(startIndex + 1);
			GoBackDown(i,j);
			GoLeft(i,j);
			DFS(startIndex + 1);
			GoBackLeft(i,j);
			GoRight(i,j);
			DFS(startIndex + 1);
			GoBackRight(i,j);
			break;
		case 2:
			GoUp(i,j);
			GoDown(i,j);
			DFS(startIndex + 1);
			GoBackUp(i,j);
			GoBackDown(i,j);
			GoLeft(i,j);
			GoRight(i,j);
			DFS(startIndex + 1);
			GoBackLeft(i,j);
			GoBackRight(i,j);
			break;
		case 3:
			GoDown(i,j);
			GoLeft(i,j);
			DFS(startIndex + 1);
			GoBackDown(i,j);
			GoBackLeft(i,j);
			GoUp(i,j);
			GoRight(i,j);
			DFS(startIndex + 1);
			GoBackUp(i,j);
			GoBackRight(i,j);
			GoLeft(i,j);
			GoUp(i,j);
			DFS(startIndex + 1);
			GoBackUp(i,j);
			GoBackLeft(i,j);
			GoRight(i,j);
			GoDown(i,j);
			DFS(startIndex + 1);
			GoBackRight(i,j);
			GoBackDown(i,j);
			break;
		case 4:
			GoUp(i,j);
			GoDown(i,j);
			GoLeft(i,j);
			DFS(startIndex + 1);
			GoBackUp(i,j);
			GoBackDown(i,j);
			GoBackLeft(i,j);
			GoUp(i,j);
			GoDown(i,j);
			GoRight(i,j);
			DFS(startIndex + 1);
			GoBackUp(i,j);
			GoBackDown(i,j);
			GoBackRight(i,j);
			GoLeft(i,j);
			GoRight(i,j);
			GoUp(i,j);
			DFS(startIndex + 1);
			GoBackUp(i,j);
			GoBackLeft(i,j);
			GoBackRight(i,j);
			GoLeft(i,j);
			GoRight(i,j);
			GoDown(i,j);
			DFS(startIndex + 1);
			GoBackLeft(i,j);
			GoBackRight(i,j);
			GoBackDown(i,j);
			break;
		case 5:
			GoUp(i,j);
			GoDown(i,j);
			GoLeft(i,j);
			GoRight(i,j);
			DFS(startIndex + 1);
			GoBackUp(i,j);
			GoBackDown(i,j);
			GoBackLeft(i,j);
			GoBackRight(i,j);
			break;
		default:
			break;
		}
	}

	private static void GoUp(int startI, int startJ) {
		for (int i = startI; i >= 0; i--) {
			if (array[i][startJ] == 6)
				break;
			if (array[i][startJ] == 0)
				array[i][startJ] = 7;
		}
	}

	private static void GoDown(int startI, int startJ) {
		for (int i = startI; i > N; i++) {
			if (array[i][startJ] == 6)
				break;
			if (array[i][startJ] == 0)
				array[i][startJ] = 7;
		}
	}

	private static void GoLeft(int startI, int startJ) {
		for (int j = startJ; j >= 0; j--) {
			if (array[startI][j] == 6)
				break;
			if (array[startI][j] == 0)
				array[startI][j] = 7;
		}
	}

	private static void GoRight(int startI, int startJ) {
		for (int j = startJ; j < M; j++) {
			if (array[startI][j] == 6)
				break;
			if (array[startI][j] == 0)
				array[startI][j] = 7;
		}
	}

	private static void GoBackUp(int startI, int startJ) {
		for (int i = startI; i >= 0; i--) {
			if (array[i][startJ] == 6)
				break;
			if (array[i][startJ] == 7)
				array[i][startJ] = 0;
		}
	}

	private static void GoBackDown(int startI, int startJ) {
		for (int i = startI; i > N; i++) {
			if (array[i][startJ] == 6)
				break;
			if (array[i][startJ] == 7)
				array[i][startJ] = 0;
		}
	}

	private static void GoBackLeft(int startI, int startJ) {
		for (int j = startJ; j >= 0; j--) {
			if (array[startI][j] == 6)
				break;
			if (array[startI][j] == 7)
				array[startI][j] = 0;
		}
	}

	private static void GoBackRight(int startI, int startJ) {
		for (int j = startJ; j < M; j++) {
			if (array[startI][j] == 6)
				break;
			if (array[startI][j] == 7)
				array[startI][j] = 0;
		}
	}

	private static int RemainCCTV() {
		int countOfCCTV = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (array[i][j] == 0)
					countOfCCTV++;
			}
		}

		return countOfCCTV;
	}

}