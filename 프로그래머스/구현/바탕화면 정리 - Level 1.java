class Solution {
    public int[] solution(String[] wallpaper) {
        int[] answer = new int[4];
        answer[0] = 50;
        answer[1] = 50;
        int n = wallpaper.length;
        int m = wallpaper[0].length();
        char[][] arr = new char[n][m];
        for(int i = 0; i < n; i++) {
            arr[i] = wallpaper[i].toCharArray();
        }
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(arr[i][j] != '#') continue;

                answer[0] = Math.min(answer[0], i);
                answer[1] = Math.min(answer[1], j);
                answer[2] = Math.max(answer[2], i+1);
                answer[3] = Math.max(answer[3], j+1);
            }
        }
        return answer;
    }
}