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

public class Main {
	static int N, K;
	static int[] array;
	static HashSet<Integer> powerSocket;	// 멀티탭에 먼저 꽂았는지 꽂지 않았는지는 상관이 없다. HashSet 사용

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(FindMinValue());
	}

	private static void SetData() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		powerSocket = new HashSet<Integer>();
		array = new int[K];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++)
			array[i] = Integer.parseInt(st.nextToken());

	}

	private static int FindMinValue() {
		int count = 0;
		
		for(int i = 0; i < K; i++) {
			// 콘센트가 꽉차지 않거나 콘센트에 꽂혀있는 번호이면 continue
			if(powerSocket.contains(array[i])  || isPossible(array[i])) 
				continue;
			
			// 콘센트가 꽉차고 콘센트에 꽂혀있는 번호가 아닐 시
			int max = -1, pick = -1;
			for (int num : powerSocket) {
				int temp = 0;	// temp를 통해 뒤에 가장 적게 오는 수를 지워줌
				for (int j = i + 1; j < K; j++) {
					if (num == array[j]) {
						break;
					}
					temp++;
				}
				if (temp > max) {
					pick = num;
					max = temp;
				}

			}
			powerSocket.remove(pick);
			powerSocket.add(array[i]);

			count++;
		}
		
		return count;
	}
	
	private static boolean isPossible(int item) {
		if (powerSocket.size() < N) {
			powerSocket.add(item);
			return true;
		}
		return false;
	}
	
}
