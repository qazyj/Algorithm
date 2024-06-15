class Solution {
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int answer = 0;
        int start = h1*3600+m1*60+s1;
        int end = h2*3600+m2*60+s2;
        answer = getCountOfAlram(end) - getCountOfAlram(start) + isAlram(start);
        return answer;
    }

    private int getCountOfAlram(int time){
        int sm = time*59/3600;
        int sh = time*719/43200;
        int a = 43200 <= time ? 2 : 1;
        return sm+sh - a;
    }

    private int isAlram(int time){
        if(time*59%3600==0 || time*719%43200==0) return 1;
        return 0;
    }
}