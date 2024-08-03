class Solution {
    public int solution(String[][] board, int h, int w) {
        int answer = 0;
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};

        int n = board.length;
        for(int d = 0; d < 4; d++) {
            int r = h + dx[d];
            int c = w + dy[d];

            if(r < 0 || r >= n || c < 0 || c >= n) continue;
            if(!board[h][w].equals(board[r][c])) continue;

            answer++;
        }
        return answer;
    }
}