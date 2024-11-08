import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
	static int n, answer;
	static int[][] arr;
	static int R,G,B;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		arr = new int[n][3];
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
			arr[i][2] = Integer.parseInt(st.nextToken());
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		answer = Integer.MAX_VALUE;
		backtracking(0,0, 0, 0, 0);
		System.out.println(answer);
	}

	public static void backtracking(int index, int step, int r, int g, int b) {
		if(step >= 8) return;
		if(step > 1) {
			int gap = 0;
			gap += Math.abs(R-r/step);
			gap += Math.abs(G-g/step);
			gap += Math.abs(B-b/step);
			answer = Math.min(answer, gap);
		}

		for(int i = index; i < n; i++) {
			backtracking(i+1, step+1, r+arr[i][0], g+arr[i][1], b+arr[i][2]);
		}
	}
}