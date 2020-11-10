import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Algorithm {
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
		PriorityQueue<Integer> queue = new PriorityQueue<>();	// 우선순위 큐를 사용해야지 자동 오름차순 정렬된다.
;
		for(int i = 0; i < N; i++) {
			if(!queue.isEmpty() && queue.peek() <= room[i][0]) 	// 맨 앞의 큐의 수가 현재 시작시간보다 작거나 같은경우
				queue.poll();
			queue.add(room[i][1]);
		}

		return queue.size();		//현재 queue 사이즈가 배정된 방의 개수이다.
	}
}
