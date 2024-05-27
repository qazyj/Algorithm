import java.util.Arrays;

class Solution {
    public int solution(String name) {
        int answer = 0;
        int move = name.length() - 1;
        int length = name.length();

        for(int i = 0; i < name.length(); i++) {
            answer += Math.min(name.charAt(i) - 'A', 26 - (name.charAt(i) - 'A'));
            int next = i+1;
            while(next < name.length() && name.charAt(next) == 'A')
                next++;
            move=Math.min(move,i+length-next+Math.min(i,length-next));
        }

        return answer + move;
    }
}