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
	static int G, P;
	static int[] gate, airplane;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(FindMaxValue());
	}

	private static void SetData() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		G = Integer.parseInt(br.readLine());
		P = Integer.parseInt(br.readLine());
		gate = new int[G + 1];
		airplane = new int[P + 1];

		for (int i = 1; i <= G; i++)
			gate[i] = i;

		for (int i = 1; i <= P; i++)
			airplane[i] = Integer.parseInt(br.readLine());
	}

	private static int FindMaxValue() {
		int count = 0;

		for (int i = 1; i <= P; i++) {
			if (docking(airplane[i]) < 0)
				break;
			count++;
		}

		return count;
	}

	private static int docking(int index) {
		// basecase (게이트 번호의 값이 0이면 도킹 불가)
		if (gate[index] == 0)	return -1;
		
		if (gate[index] == index)	// 도킹을 아직 시도하지 않은 게이트라면
			gate[index]--;
		else						// 도킹을 한 게이트라면 현재 게이트보다 낮은 게이트를  찾기 위해 재귀를 돔.
			gate[index] = docking(gate[index]);
		
		return 1;
	}
}
