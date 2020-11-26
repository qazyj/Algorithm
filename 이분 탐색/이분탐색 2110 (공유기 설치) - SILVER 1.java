import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Algorithm {
	static int N, C;
	static int[] array;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(SearchBinary());
	}

	private static void SetData() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		array = new int[N];
		for (int i = 0; i < N; i++)
			array[i] = Integer.parseInt(br.readLine());
		Arrays.sort(array);

	}

	private static int SearchBinary() {
		int left = 1;
		int right = array[N - 1] - array[0];
		int d = 0;
		int value = 0;

		while (left <= right) {
			int mid = (left + right) / 2;
			int start = array[0];
			int count = 1;
			for (int i = 0; i < N; i++) { // 집집마다 검색함.
				d = array[i] - start;
				if (d >= mid) { // 만약 첫번째 집과의 거리가 더 크다면 찾았다고 count 올려주고, 내가 찾는집에 이번 집을 넣어준다.
					count++;
					start = array[i];
				}
			}

			if (count >= C) {
				value = mid;
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return value;
	}
}
