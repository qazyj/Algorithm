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
	static int testcase, N, M;
	static int[][] book;
	static boolean[] check;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(sb);
	}

	private static void SetData() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		testcase = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		
		for(int i = 0; i < testcase; i++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			check = new boolean[N];
			book = new int[M][2];
			for(int j = 0; j < M; j++) {
				st = new StringTokenizer(br.readLine());
				book[j][0] = Integer.parseInt(st.nextToken());
				book[j][1] = Integer.parseInt(st.nextToken());
			}
			
	        Arrays.sort(book, new Comparator<int[]>() {
	            @Override
	            public int compare(int[] o1, int[] o2) {
	                if(o1[1] != o2[1])	// b 가 같지 않은 경우 b로 오름차순 정렬
	                	return Integer.compare(o1[1], o2[1]);
	                else		// b가 같은 경우 a로 오름차순 정렬
	                	return Integer.compare(o1[0], o2[0]);
	            }
	        });
		
			
			FindMaxValue(); // 각 testcase마다 책을 줄 수 있는 학생의 최대 수를 찾는다.
		}
	}

	private static void FindMaxValue() {
		int count = 0; // 나누어주는 학생의 수를 count
		
		for(int i = 0; i < M; i++) {
			int a = book[i][0];
			int b = book[i][1];

			// 해당하는 범위 내에서
			// 가능한 가장 작은 번호의 책부터 뽑는다.
			for (int j = a-1; j < b; j++) {
				if (!check[j]) {
					check[j] = true;
					count++;
					break;
				}
			}
		}		
		
		sb.append(count + "\n");
	}
}
