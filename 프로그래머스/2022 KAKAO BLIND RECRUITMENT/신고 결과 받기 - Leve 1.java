import java.util.*;

class Solution {
	public int[] solution(String[] id_list, String[] reports, int k) {
		int n = id_list.length;
		int[] answer = new int[n];
		Map<String, Integer> map = new HashMap<>();
		for(int i = 0; i < n; i++) {
			map.put(id_list[i], i);
		}

		// 신고한 유저
		List<Integer>[] lists = new ArrayList[n];
		for(int i = 0; i < n; i++) {
			lists[i] = new ArrayList<>();
		}

		for(String report : reports) {
			String[] temp = report.split(" ");
			int a = map.get(temp[0]);
			int b = map.get(temp[1]);

			if(lists[b].contains(a)) continue;
			lists[b].add(a);
		}

		for (int i = 0; i < n; i++) {
			if(lists[i].size() < k) continue;

			for(int val : lists[i]) {
				answer[val]++;
			}
		}


		return answer;
	}
}