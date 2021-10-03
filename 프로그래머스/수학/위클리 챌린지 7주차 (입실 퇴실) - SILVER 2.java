import java.util.*;

class Solution {
    public int[] solution(int[] enter, int[] leave) {
        int[] answer = new int[enter.length];
        ArrayList<Integer>list = new ArrayList();
        int index=0;
        for(int i=0; i<answer.length;i++){
            list.add(enter[i]);
            for(int j=0; j<list.size(); j++){
                if(enter[i]==list.get(j)){
                    answer[list.get(j)-1] = list.size()-1;
                }else{
                    answer[list.get(j)-1]++;
                }
            }
            while(index<answer.length && list.contains(leave[index])){
                list.remove(Integer.valueOf(leave[index++]));
            }
        }

        return answer;
    }
}