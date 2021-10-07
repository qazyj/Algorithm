class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] temp = new int[100];

        int day = -1;
        for(int i = 0; i < progresses.length; i++){
            while(progresses[i] + (speeds[i] * day) < 100){
                day++;
            }
            temp[day]++;
        }

        int count = 0;
        for(int n : temp){
            if(n != 0) count++;
        }


        int[] answer = new int[count];
        count = 0;
        for(int n : temp){
            if(n != 0) answer[count++] = n;
        }
        return answer;
    }
}