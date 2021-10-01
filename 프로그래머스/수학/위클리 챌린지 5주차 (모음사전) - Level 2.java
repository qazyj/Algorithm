class Solution {
    public int solution(String word) {
        int answer = 0;
        int temp = 781;
        char c[] = {'A', 'E', 'I', 'O', 'U'};

        for(int i=0; i<word.length(); i++){
            for(int j=0; j<5; j++){
                if(c[j] == word.charAt(i)){
                    answer += 1 + j * temp;
                }
            }
            temp = (temp-1)/5;
        }

        return answer;
    }
}