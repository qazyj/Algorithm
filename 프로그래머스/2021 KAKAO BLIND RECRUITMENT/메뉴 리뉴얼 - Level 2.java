import java.util.*;

class Solution {
    static HashMap<String,Integer> map;
    static int max;
    
    public String[] solution(String[] orders, int[] course) {
		PriorityQueue<String> priorityQueue = new PriorityQueue<>();
		for (int i = 0; i < course.length; i++) {
			map = new HashMap<>();
			max = 0;
			for (int j = 0; j < orders.length; j++) {
				dfs("", course[i], 0, orders[j]);
			}
			for (String s : map.keySet()) {
				if (map.get(s) == max && max > 1) {
					priorityQueue.offer(s);
				}
			}
		}
		String[] answer = new String[priorityQueue.size()];
		int index = 0;
		while (!priorityQueue.isEmpty()) {
			answer[index++] = priorityQueue.poll();
		}
		return answer;
    }
    
	private static void dfs(String string, int targetNum, int index, String word) {
		if (string.length() == targetNum) {
			char[] c = string.toCharArray();
			Arrays.sort(c);
			String temps = "";
			for (int i = 0; i < c.length; i++)
				temps += c[i];
			map.put(temps, map.getOrDefault(temps, 0) + 1);
			max = Math.max(max, map.get(temps));
			return;
		}

		for (int i = index; i < word.length(); i++) {
			dfs(string + word.charAt(i), targetNum, i + 1, word);
		}
	}
}