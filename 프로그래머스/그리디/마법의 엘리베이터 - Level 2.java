class Solution {
    public int solution(int storey) {
        int answer = 0;
        while(storey != 0) {
            int rest = storey%10;
            storey /= 10;
            if(rest <= 5) {
                if(rest == 5 && storey%10 >= 5) {
                    storey++;
                    answer += (10-rest);
                } else {
                    answer += rest;
                }
            } else {
                storey++;
                answer += (10-rest);
            }
        }
        return answer;
    }
}