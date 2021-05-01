class Solution {
	public String solution(String new_id) {
		String answer = new_id;
        
		// 1단계
		answer = answer.toLowerCase();
        
		// 2단계
		answer = answer.replaceAll("[^0-9a-z-_.]", "");
        
		// 3단계
		answer = answer.replaceAll("[.]{2,}",".");
        
		// 4단계
		answer = answer.replaceAll("^[.]|[.]$", "");
        
		// 5단계
		if(answer.length() == 0) {
			answer = "aaa";
		}
		// 6단계
		else if(answer.length() >= 16) {
		answer = answer.substring(0, 15).replaceAll("[.]$", "");
		}
		else if(answer.length() <= 2) {
			// 7단계
			while(answer.length() < 3) {
				answer += answer.charAt(answer.length() -1);
		    }
	    }   
		return answer;
	}
}