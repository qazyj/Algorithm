import java.util.*;

class Solution {

    public int solution(int[][] scores) {
        int answer = 0;
        List<Score> scoreList = new ArrayList<>();
        Score wanho = new Score(scores[0][0], scores[0][1]);
        for (int[] score : scores) {
            scoreList.add(new Score(score[0], score[1]));
        }
        Collections.sort(scoreList);
        int max = 0;
        for (Score s : scoreList) {
            if (max > s.peer) {
                if (s.equals(wanho)) {
                    return -1;
                }
            } else {
                max = Math.max(max, s.peer);
                if (wanho.getSum() < s.getSum()) {
                    answer += 1;
                }
            }
        }
        return answer+1;
    }
}

class Score implements Comparable<Score> {
    int attitude;
    int peer;

    public Score(int attitude, int peer) {
        this.attitude = attitude;
        this.peer = peer;
    }

    @Override
    public int compareTo(Score s) {
        if (this.attitude == s.attitude) {
            return this.peer - s.peer;
        }
        return s.attitude - this.attitude;
    }

    public boolean equals(Score s) {
        return this.attitude == s.attitude && this.peer == s.peer;
    }

    public int getSum() {
        return this.attitude + this.peer;
    }
}