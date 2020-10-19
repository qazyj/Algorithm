import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[] array;
	static int N, M, L;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		array = new int[N + 2];
		array[0] = 0;
		array[N + 1] = L;

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			array[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(array);

		System.out.println(BinarySearch(0, L-1));
	}

	private static int BinarySearch(int left, int right) {
		while (left <= right) {
			int mid = (left + right) / 2;
			int sum = 0;

			for (int i = 1; i < N + 2; i++) {
				if (array[i] > array[i - 1])
					// 두 편의점 사이에 세울 수 있는 편의점 수
					sum += (array[i] - array[i - 1] - 1) / mid;
			}

			// 세워야할 편의점 보다 더 많이 생기면 간격을 늘린다.
			if (sum > M)
				left = mid + 1;
			else	// 더 적게 생기면 간격을 줄인다.
				right = mid - 1;
		}

		return left;
	}
}
