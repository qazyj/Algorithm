import java.util.*;

class Solution {
	public int solution(String[] friends, String[] gifts) {
		Map<String, Integer> map = new HashMap<>();
		for(int i = 0; i < friends.length; i++) {
			map.put(friends[i], i);
		}

		int n = friends.length;
		int[][] arr = new int[n][n];
		for(String gift : gifts) {
			String[] temp = gift.split(" ");
			String a = temp[0];
			String b = temp[1];
			int aIndex = map.get(a);
			int bIndex = map.get(b);

			arr[aIndex][bIndex]++;
		}

		for(int i = 0 ;i < n; i++) {
			for(int j = 0; j < n; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}

		for(int i = 0; i < n; i++) {

			int give = 0;
			for(int j = 0; j < n; j++) {
				give += arr[i][j];
			}

			int take = 0;
			for(int j = 0; j < n; j++) {
				take += arr[j][i];
			}

			arr[i][i] = give - take;
		}

		for(int i = 0; i < n; i++) {
			System.out.print(arr[i][i] + " ");
		}
		System.out.println();

		int answer = 0;
		int[] gift = new int[n];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(i == j) continue;

				if(arr[i][j] > arr[j][i])  gift[i]++;
				else if(arr[i][j] == arr[j][i] || (arr[i][j] == 0 && arr[j][i] == 0)) {
					if(arr[i][i] > arr[j][j]) gift[i]++;
				}
			}
		}

		for(int i = 0; i < n; i++) {
			answer = Math.max(answer, gift[i]);
		}

		return answer;
	}
}