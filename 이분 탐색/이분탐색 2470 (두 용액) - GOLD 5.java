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
		Arrays.sort(array);

		int min = Integer.MAX_VALUE;
		int value1 = 0, value2 = 0;
		int left = 0, right = n - 1;
		
		// 이진 탐색
		while (left <= right) {
			int sum = array[left] + array[right];

			// v가 최소일 때 특성값 갱신
			if (min > Math.abs(sum)) {
				min = Math.abs(sum);
				value1 = array[left];
				value2 = array[right];
			}

			if (sum > 0)
				right--;
			else
				left++;
		}

		System.out.println(value1 + " " + value2);
	}
}