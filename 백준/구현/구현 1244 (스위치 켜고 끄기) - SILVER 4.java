import java.util.Arrays;
import java.io.*;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++){
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int t = Integer.parseInt(br.readLine());
		while(t-->0) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int index = Integer.parseInt(st.nextToken());
			if(s == 1) {
				for(int i = index; i <= n; i+=index) {
					arr[i] = (arr[i] == 1)? 0:1;
				}
			} else {
				int left = index;
				int right = index;
				while(left > 1 && right < n && arr[left] == arr[right]) {
					left--;
					right++;
				}
				if(left != right && arr[left] != arr[right]) {
					left++;
					right--;
				}
				for(int i = left; i <= right; i++) {
					arr[i] = (arr[i] == 1)? 0:1;
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= n; i++) {
			sb.append(arr[i] + " ");
			if(i%20 == 0) sb.append("\n");
		}
		System.out.println(sb);
	}
}