class Solution {
    public long solution(int price, int money, int count) {
        long answer = money;
        for(int i = 1; i <= count; i++){
            answer -= price*i;
        }

        if(answer < 0)
            return Math.abs(answer);
        else
            return 0;
    }
}