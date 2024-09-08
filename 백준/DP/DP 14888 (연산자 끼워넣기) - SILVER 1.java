import java.io.*;
import java.util.StringTokenizer;
import java.util.*;

public class Solution {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int[][] arr = new int[n+1][2];
		for(int i = 0; i < n; i++) {
			String[] temp = br.readLine().split(" ");
			for(int j = 0 ; j < 2; j++) {
				arr[i][j] = Integer.parseInt(temp[j]);
			}
		}

		for(int i = 1; i <= n; i++) {
			int max = 0;
			for(int j = 0; j < i; j++) {
				if(j + arr[j][0] > i) continue;

				max = Math.max(max, arr[j][1]);
			}
			arr[i][1] += max;
		}
		System.out.println(arr[n][1]);
	}
}
