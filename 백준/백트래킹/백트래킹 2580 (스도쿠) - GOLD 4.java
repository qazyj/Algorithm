import java.io.*;
import java.util.*;

public class Algorithm {

	static int[][] array;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		array = new int[9][9];
		sb = new StringBuilder();

		for (int i = 0; i < 9; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++)
				array[i][j] = Integer.parseInt(st.nextToken());
		}

		Sudoku(0, 0);

	}

	static void Sudoku(int r, int c) {

		if (c == 9) {
			sb.append("\n");
			Sudoku(r + 1, 0);
			return;
		}

		if (r == 9) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++)
					System.out.print(array[i][j] + " ");
				System.out.println("");
			}
			return;
		}

		if (array[r][c] == 0) {
			for (int i = 1; i <= 9; i++) {
				if (Possible(r, c, i)) {
					array[r][c] = i;
					Sudoku(r, c + 1);
				}
				array[r][c] = 0;
			}
		} else {
			Sudoku(r, c + 1);
		}
	}

	static boolean Possible(int r, int c, int value) {

		// За
		for (int i = 0; i < 9; i++) {
			if (array[r][i] == value) {
				return false;
			}
		}

		// ї­
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
