import java.io.*;
import java.util.StringTokenizer;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			if(i > 0) arr[i] += arr[i - 1];
		}
		int m = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-2;
			int b = Integer.parseInt(st.nextToken())-1;
			if(a < 0) sb.append(arr[b]).append("\n");
			else sb.append(arr[b]-arr[a]).append("\n");
		}
		System.out.println(sb);
	}

}