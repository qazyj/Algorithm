import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Algorithm {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[] array = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; ++i) {
			array[i] = Integer.parseInt(st.nextToken());
		}
 
		int min = Integer.MAX_VALUE;
		int value1 = 0, value2 = 0;
		for (int i = 0; i < n; ++i) {
			int a = i+1, b = n-1;
			// i+1부터 n-1까지 이진 탐색
			while (a <= b) {
				int mid = (a+b)/2;
				int value = Math.abs(array[i] + array[mid]);
				
				// v가 최소일 때 특성값 갱신
				if (min > value) {
					min = value;
					value1 = array[i];
					value2 = array[mid];
				}
				
				if (array[mid] == -array[i]) {
					break;
				} else if (array[mid] < -array[i]) {
					a = mid+1;
				} else {
					b = mid-1;
				}
			}
		}
		System.out.println(value1 + " " + value2);
	}
}