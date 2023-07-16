class Solution {
    public int solution(int n, int m, int[] sections) {
        int start = sections[0];
        int end = sections[0] + (m-1);
        int answer = 1;

        for(int section : sections) {
            if (section >= start && section <= end)
                continue;
            else {
                start = section;
                end = section + (m - 1);
                answer++;
            }
        }
        return answer;
    }
}