import java.util.*;

class Solution {
	public int[] solution(int[][] edges) {
		int n = 0;
		for(int i = 0; i < edges.length; i++) {
			n = Math.max(n, edges[i][0]);
			n = Math.max(n, edges[i][1]);
		}

		int[] count = new int[n+1];

		List<Integer>[] list = new ArrayList[n+1];
		for(int i = 0; i <= n; i++) {
			list[i] = new ArrayList<>();
		}

		for(int i = 0; i < edges.length; i++) {
			list[edges[i][0]].add(edges[i][1]);
			count[edges[i][0]]++;
			count[edges[i][1]]--;
		}

		int rootNode = 0;
		for(int i = 1; i <= n; i++) {
			if(count[i] <= 1) continue;

			rootNode = i;
			break;
		}

		int[] answer = new int[]{rootNode,0,0,0};
		for(int start : list[rootNode]) {
			int[] temp = search(list, start);
			if(temp[0] == temp[1]) {
				// 도넛 : 정점 N, 간선 n
				//System.out.println("도넛 : " + temp[0] +" " + temp[1]);
				answer[1]++;
			} else if(temp[0] == temp[1]+1) {
				// 막대 : 정점 n, 간선 n-1
				//System.out.println("막대 : " + temp[0] +" " + temp[1]);
				answer[2]++;
			} else if(temp[0] + 1 == temp[1]) {
				// 8자 : 정점 2n+1, 간선 2n+2
				//System.out.println("8자 : " + temp[0] +" " + temp[1]);
				answer[3]++;
			}
		}


		return answer;
	}

	public int[] search(List<Integer>[] list, int start) {
		Queue<Integer> q = new LinkedList<>();
		q.add(start);
		int[] temp = new int[2];
		temp[0]++;
		Set<Integer> set = new HashSet<>();
		set.add(start);
		int count = 0;
		while(!q.isEmpty()) {
			int cur = q.poll();
			if(cur == start && count > 0) return temp;
			if(list[cur].size() >= 2) return new int[]{0, 1};

			for(int next : list[cur]) {
				if(!set.contains(next)) temp[0]++;

				count++;
				set.add(next);
				temp[1]++;
				q.add(next);
			}
		}
		return temp;
	}

}