class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        int total = brown+yellow;
        for(int i = 3; i < total/2; i++)  {
            if(total%i != 0) continue;

            int b = total/i;
            if((b-2)*(i-2) != yellow) continue;

            answer[0] = b;
            answer[1] = i;
            break;
        }
        return answer;
    }
}