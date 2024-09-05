import java.io.*;
import java.util.StringTokenizer;
import java.util.*;

public class Solution {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] s = br.readLine().split(" ");
		int n = Integer.parseInt(s[0]);
		int m = Integer.parseInt(s[1]);
		int b = Integer.parseInt(s[2]);
		int[][] arr = new int[n][m];
		int min = 300;
		int max = 0;
		for(int i = 0 ; i < n; i++) {
			s = br.readLine().split(" ");
			for(int j = 0 ; j < m; j++) {
				arr[i][j] = Integer.parseInt(s[j]);
				max = Math.max(max, arr[i][j]);
				min = Math.min(min, arr[i][j]);
			}
		}

		int answerTime = Integer.MAX_VALUE;
		int answerFloor = 0;
		for(int i = min; i <= max; i++) {
			int time = 0;
			int getBlock = 0;
			int putBlock = 0;

			for(int a = 0; a < n; a++) {
				for(int c = 0; c < m; c++) {
					if(arr[a][c] < i) {
						putBlock += (i-arr[a][c]);
					}
					if(arr[a][c] > i) {
						getBlock += (arr[a][c]-i);
					}
				}
			}

			if(putBlock > getBlock + b) continue;
			time += ((getBlock)*2 + putBlock);
			if(time > answerTime) continue;
			answerTime = time;
			answerFloor = i;
		}
		System.out.println(answerTime+ " " + answerFloor);
	}
}
