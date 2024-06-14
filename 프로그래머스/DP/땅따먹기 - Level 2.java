class Solution {
    int solution(int[][] land) {
        int answer = 0;
        int n = land.length;
        int[][] temp = new int[n][4];
        temp[0] = land[0];

        for(int i = 1; i < n; i++) {
            for(int j = 0; j < 4; j++) {
                int max = 0;
                for(int k = 0; k < 4; k++) {
                    if(j==k) continue;
                    max = Math.max(max,temp[i-1][k]);
                }
                temp[i][j] = max+land[i][j];
            }
        }

        for(int i = 0; i < 4; i++) {
            answer = Math.max(answer,temp[n-1][i]);
        }
        return answer;
    }
}