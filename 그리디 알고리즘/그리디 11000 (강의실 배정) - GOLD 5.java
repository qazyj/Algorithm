import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] room;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(FindMinValue());
	}

	private static void SetData() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		room = new int[N][2];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			room[i][0] = Integer.parseInt(st.nextToken());
			room[i][1] = Integer.parseInt(st.nextToken());
		}
		
		// 2차원 배열 정렬
        Arrays.sort(room, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int result = o1[0] - o2[0];
                if (result != 0) return result;
                return o1[1] - o2[1];
            }
        });
	}

	private static int FindMinValue() {
		PriorityQueue<Integer> queue = new PriorityQueue<>();

		queue.offer(room[0][1]);
		for(int i=1;i<N;i++) {
			if(room[i][0] < queue.peek()) {
				queue.offer(room[i][1]);
			}else {
				queue.poll();
				queue.offer(room[i][1]);
			}
		}

		return queue.size();
	}
}