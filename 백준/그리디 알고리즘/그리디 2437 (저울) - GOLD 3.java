import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Algorithm {
	private static int N;
	private static int[] array;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		array = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			array[i] = Integer.parseInt(st.nextToken());

		// 무게 순으로 정렬
		Arrays.sort(array);

		int sum = 0;
		for (int i = 0; i < N; i++) {
			if (sum + 1 < array[i]) {
				break;
			}
			
			sum += array[i];
		}

		System.out.println(sum + 1);
	}
}
