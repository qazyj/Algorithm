import java.util.*;

class Solution {

	static HashMap<String, List<Integer>> map;
	public int[] solution(String[] info, String[] query) {
		int[] answer = new int[query.length];

		map = new HashMap<String, List<Integer>>();

		for(int i = 0 ; i < info.length ; i++){
			String[] split = info[i].split("\\s");

			dfs(split, "", 0, Integer.parseInt(split[4]));
		}

		for(String key : map.keySet()){
			Collections.sort(map.get(key));
		}

		for(int i = 0 ; i < query.length ; i++){
			query[i] = query[i].replaceAll(" and ", "");
			String[] split = query[i].split("\\s");


			if(map.containsKey(split[0])){
				List<Integer> list = map.get(split[0]);

				int left = 0;
				int right = list.size() - 1;
				while(left <= right){
					int mid = (left + right) / 2;

					if(list.get(mid) >= Integer.parseInt(split[1])){
						right = mid - 1;
					}
					else{
						left = mid + 1;
					}
				}
				answer[i] = list.size() - left;
			}
		}

		return answer;
	}

	static void dfs(String[] query, String result, int index, int score){
		if(index == 4){
			if(map.containsKey(result)){
				map.get(result).add(score);
			}
			else{
				List<Integer> list = new ArrayList<Integer>();
				list.add(score);
				map.put(result, list);
			}

			return;
		}

		dfs(query, result + query[index], index + 1 , score);
		dfs(query, result + "-", index + 1, score);
	}
}