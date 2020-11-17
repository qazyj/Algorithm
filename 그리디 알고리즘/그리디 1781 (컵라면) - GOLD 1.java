import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Algorithm {
	static int N;
	static int[][] array;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(FindMaxValue());
	}
	
	private static void SetData() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		array = new int[N][2];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			array[i][0] = Integer.parseInt(st.nextToken());
			array[i][1] = Integer.parseInt(st.nextToken());
		}
		
		// 2차원 배열 정렬 (deadline 오름차순)
		Arrays.sort(array, new Comparator<int[]>() {
		    @Override
		    public int compare(int[] o1, int[] o2) {
		        return o1[0] - o2[0];
		    }
		});
	}
	
	private static int FindMaxValue() {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int sum = 0;
        for (int i = 0; i < N; i++) {
            queue.add(array[i][1]);
            while (!queue.isEmpty() && queue.size() > array[i][0]) queue.poll();
        }
        while (!queue.isEmpty())
            sum += queue.poll();
		return sum;
	}
}
