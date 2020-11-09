import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, K;
	static int[][] jewelry;
	static int[] bag;
	static long maxValue;

	public static void main(String[] args) throws Exception {
		SetData();
		FindMaxValue();
		System.out.println(maxValue);
	}

	private static void SetData() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		maxValue = 0;
		jewelry = new int[N][2];
		bag = new int[K];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			jewelry[i][0] = Integer.parseInt(st.nextToken());
			jewelry[i][1] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < K; i++)
			bag[i] = Integer.parseInt(br.readLine());

		// 2차원 배열 sort
		Arrays.sort(jewelry, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				return o1[0] - o2[0];
			};

		});
		Arrays.sort(bag);
	}

	private static void FindMaxValue() {
		Queue<Integer> queue = new PriorityQueue<>();
		int j = 0;
		for (int i = 0; i < K; i++) {
			while (j < N && jewelry[j][0] <= bag[i]) { // 앞에서 담은것은 제외해야 하므로 while문과 j를 사용
				queue.add(-jewelry[j][1]);
				j++;
			}
			if (!queue.isEmpty()) { // for문 한번에 한번만 더한다.
				maxValue += Math.abs(queue.poll());
			}
		}

	}
}
