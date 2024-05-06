class Solution {
    static char[][] arr;
    static int answer;

    public int solution(String[] board) {
        answer = 0;

        int o = 0;
        int x = 0;
        arr = new char[3][3];
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                arr[i][j] = board[i].charAt(j);

                if(arr[i][j] == 'O') o++;
                if(arr[i][j] == 'X') x++;
            }
        }

        // x가 o보다 많을리가 없음.
        if(x > o) return 0;

        // o가 x보다 2개 이상 많을리가 없음.
        if(o > x +1) return 0;

        if(isBingo('O')) {
            if(x == o) return 0;
        }
        if(isBingo('X')) {
            if(x < o) return 0;
        }
        return 1;
    }

    public boolean isBingo(char c) {
        //가로 검사
        for (int i = 0; i < 3; i++) {
            if (arr[i][0] == c
                    && arr[i][1] == c
                    && arr[i][2] == c) {
                return true;
            }
        }
        //세로 검사
        for (int i = 0; i < 3; i++) {
            if (arr[0][i] == c
                    && arr[1][i] == c
                    && arr[2][i] == c) {
                return true;
            }
        }
        //대각선 검사
        if (arr[0][0] == c
                && arr[1][1] == c
                && arr[2][2] == c) {
            return true;
        }
        if (arr[0][2] == c
                && arr[1][1] == c
                && arr[2][0] == c) {
            return true;
        }
        return false;
    }
}