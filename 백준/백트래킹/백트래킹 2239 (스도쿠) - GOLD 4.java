import java.io.*;
import java.util.*;

public class Algorithm {
	static int[][] array;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		SetData();
		dfs(0, 0);
	}

	private static void SetData() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		array = new int[9][9];
		sb = new StringBuilder();

		for (int i = 0; i < 9; i++) {
			String s = br.readLine();
			for (int j = 0; j < 9; j++)
				array[i][j] = s.charAt(j) - '0';
		}
	}

	static void dfs(int r, int c) {

		if (c == 9) {
			dfs(r + 1, 0);
			return;
		}

		if (r == 9) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					sb.append(array[i][j]);
				}
				sb.append("\n");
			}
			System.out.println(sb);
			System.exit(0); // 이렇게 바로 끝내줘야지 시간초과가 나지 않음
		}

		if (array[r][c] == 0) {
			for (int i = 1; i <= 9; i++) {
				if (Sudoku(r, c, i)) {
					array[r][c] = i;
					dfs(r, c + 1);
				}
				array[r][c] = 0;
			}
		} else {
			dfs(r, c + 1);
		}
	}

	static boolean Sudoku(int r, int c, int value) {
		// 행
		for (int i = 0; i < 9; i++) {
			if (array[r][i] == value) {
				return false;
			}
		}

		// 열
		for (int i = 0; i < 9; i++) {
			if (array[i][c] == value) {
				return false;
			}
		}

		// 3*3
		int tempR = (r / 3) * 3;
		int tempC = (c / 3) * 3;

		for (int i = tempR; i < tempR + 3; i++) {
			for (int j = tempC; j < tempC + 3; j++) {
				if (array[i][j] == value) {
					return false;
				}
			}
		}

		return true;
	}
}