class Solution {
	static boolean[][] check;
	static char[][] array;

	public static int solution(int m, int n, String[] board) {
		int answer = 0;
		array = new char[m][n];

		for(int i = 0 ; i < m ; i++) {
			array[i] = board[i].toCharArray();
		}

		while(true) {
			check = new boolean[m][n];

			for(int i = 0 ; i < m - 1 ; i++) {
				for(int j = 0 ; j < n - 1 ; j++) {
					if(array[i][j] == ' ') continue;
					CheckFourBlockSame(i, j, array[i][j]);
				}
			}

			int temp = 0;
			for(int i = 0 ; i < m ; ++i) {
				for(int j = 0 ; j < n ; ++j) {
					if(check[i][j]) {
						temp++;
						array[i][j] = ' ';
					}
				}
			}
			if(temp == 0) break;
			else answer += temp;

			DropEmptySpace(m, n);
		}

		return answer;
	}

	private static void CheckFourBlockSame(int i, int j, char block) {
		for(int a = i ; a < i + 2 ; a++) {
			for(int b = j ; b < j + 2 ; b++) {
				if(array[a][b] != block) return;
			}
		}

		for(int a = i ; a < i + 2 ; a++) {
			for(int b = j ; b < j + 2 ; b++) {
				check[a][b] = true;
			}
		}
	}

	private static void DropEmptySpace(int m, int n) {
		for(int j = 0 ; j < n ; j++) {
			for(int i = m - 1 ; i >= 0 ; i--) {
				if(array[i][j] != ' ')  continue;

				for(int k = i - 1 ; k >= 0 ; k--) {
					if(array[k][j] == ' ')  continue;

					array[i][j] = array[k][j];
					array[k][j] = ' ';
					break;
				}
			}
		}
	}
}