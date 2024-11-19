import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		long[] arr = new long[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) arr[i] = Long.parseLong(st.nextToken());
		Arrays.sort(arr);
		long answer = 0;
		if(n%2==0) {
			int index = n-1;
			for(int i = 0; i < n/2 ; i++) {
				answer = Math.max(arr[i]+arr[index--], answer);
			}
		} else {
			int index = n-2;
			for(int i = 0; i < n/2-1; i++) {
				answer = Math.max(arr[i]+arr[index--], answer);
			}
			answer = Math.max(answer, arr[n-1]);
		}
		System.out.println(answer);
	}
}