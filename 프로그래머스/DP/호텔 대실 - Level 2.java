class Solution {
    public int solution(String[][] book_time) {
        int answer = 0;
        int[] dp = new int[1451];
        for(int i = 0; i < book_time.length; i++) {
            int start_time = getStringToTime(book_time[i][0]);
            int end_time = getStringToTime(book_time[i][1])+10;
            dp[start_time]++;
            dp[end_time]--;
        }

        for(int i = 1; i <= 1440; i++) {
            dp[i] += dp[i-1];
            answer = Math.max(answer, dp[i]);
        }
        return answer;
    }

    public int getStringToTime(String s) {
        String[] t = s.split(":");
        return Integer.parseInt(t[0])*60+Integer.parseInt(t[1]);
    }
}