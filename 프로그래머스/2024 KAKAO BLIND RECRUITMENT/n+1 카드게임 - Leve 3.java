import java.util.*;

class Solution {
	public int solution(int coin, int[] cards) {
		int answer = 0;
		int len = cards.length;
		Set<Integer> original = new HashSet();
		Set<Integer> additional = new HashSet();

		int index = len / 3;
		for(int i = 0 ; i < index; ++i)
			original.add(cards[i]);

		int target = len + 1;
		while(true){
			answer++;
			if(index >= len){
				break;
			}
			additional.add(cards[index]);
			additional.add(cards[index+1]);
			index += 2;
			boolean flag = false;

			// 추가 안해도 되는 경우
			for(int value : original){
				if(!original.contains(target - value)) continue;

				original.remove(value);
				original.remove(target - value);
				flag = true;
				break;
			}
			if(flag) continue;

			// 1개 추가되면 해결되는 경우
			if(coin > 0){
				for(int value : original){
					if(!additional.contains(target - value)) continue;

					original.remove(value);
					additional.remove(target - value);
					coin--;
					flag = true;
					break;
				}
			}
			if(flag) continue;

			// 2개 추가될 때, 해결되는 경우
			if(coin > 1){
				for(int value : additional){
					if(!additional.contains(target - value)) continue;

					additional.remove(value);
					additional.remove(target - value);
					coin -= 2;
					flag = true;
					break;
				}
			}

			if(!flag) break;
		}
		return answer;
	}
}