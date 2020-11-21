import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Algorithm {
	static int N, M;
	static ArrayList<Integer> crain, box;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(FindMixValue());
	}

	private static void SetData() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		crain = new ArrayList<>();

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			crain.add(Integer.parseInt(st.nextToken()));

		M = Integer.parseInt(br.readLine());
		box = new ArrayList<>();

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++)
			box.add(Integer.parseInt(st.nextToken()));

		Collections.sort(crain, new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o2.compareTo(o1);
			}
		});
		Collections.sort(box, new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o2.compareTo(o1);
			}
		});
	}

	private static int FindMixValue() {
		int count = 0;		// 걸린 시간
		
		// 가장 무거운 박스의 무게 > 크레인 최대 중량일 경우
		if (box.get(0) > crain.get(0))
			return -1;
		
		while (box.size() != 0) {
			int crainIndex = 0, boxIndex = 0;
			
			while (crainIndex < N) {
				if (boxIndex == box.size())
					break;
				// 현재 크레인 index 크기로 박스 index를 옮길 수 있으면
				if (box.get(boxIndex) <= crain.get(crainIndex)) {
					box.remove(boxIndex);
					crainIndex++;
				} else if (box.get(boxIndex) > crain.get(crainIndex)) {
					boxIndex++;
				}
			}
			count++;
		}
		return count;

	}
}
