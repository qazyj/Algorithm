class Solution {
    private static int[] board;
    private static int answer;

    public int solution(int n) {
        answer = 0;
        board = new int[n];

        backTracking(0, n);

        return answer;
    }

    private static void backTracking(int depth, int n) {
        if (depth == n) {
            answer++;
            return;
        }

        for (int i = 0; i < n; i++) {
            board[depth] = i;
            if (!isTouch(depth)) continue;

            backTracking(depth + 1, n);
        }
    }

    private static boolean isTouch(int i) {
        for (int j = 0; j < i; j++) {
            if (board[i] == board[j]) return false;
            if (Math.abs(i - j) == Math.abs(board[i] - board[j])) return false;
        }
        return true;
    }
}