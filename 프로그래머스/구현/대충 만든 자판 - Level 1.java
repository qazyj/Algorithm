class Solution {
    public int[] solution(String[] keymap, String[] targets) {
        int[] answer = new int[targets.length];
        for(int i = 0; i < targets.length; i++) {
            for(int j = 0; j < targets[i].length(); j++) {
                char cur = targets[i].charAt(j);

                int min = 102;
                for(int k = 0; k < keymap.length; k++) {
                    int index = keymap[k].indexOf(cur)+1;

                    if(index == 0) continue;
                    min = Math.min(index, min);
                }

                if(min == 102) {
                    answer[i] = -1;
                    break;
                }
                else answer[i] += min;
            }
        }
        return answer;
    }
}