import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.function.Function;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[] arr = new int[n];
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			if(arr[i] <= k) arr[i] = 0;
			else if(arr[i] < k*2) arr[i] = arr[i] - k;
			else arr[i] = arr[i] - k*2;

		}
		int left = 1;
		int right = 1000000000;
		int P = -1;

		while(left <= right) {
			int mid = (left + right) / 2;
			int count = 0;
			for(int i=0; i<n; i++) count += (arr[i] / mid);

			if(count >= m) {
				P = mid;
				left = mid + 1;
			}
			else {
				right = mid - 1;
			}
		}
		System.out.println(P);
	}
}