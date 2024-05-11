class Solution {
    public int solution(int n, long l, long r) {
        int answer = 0;
        for(long i = l-1; i < r; i++) {
            if(isOne(i)) answer++;
        }
        return answer;
    }

    public boolean isOne(long i) {
        while(i >= 5) {
            if(i%5==2) return false;
            i/=5;
        }
        return i!=2;
    }
}