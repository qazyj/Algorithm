import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        Queue<Task> task = new LinkedList<>();
        for(int i=0; i<priorities.length; i++){
            task.add(new Task(i, priorities[i]));
        }

        int now = 0;
        while(!task.isEmpty()) {
            Task cur = task.poll();
            boolean flag = false;
            for(Task t : task){
                if(t.priority>cur.priority){
                    flag = true;
                }
            }

            if(flag) {
                task.add(cur);
            }else{
                now++;
                if(cur.index == location) {
                    answer = now;
                    break;
                }

            }
        }
        return answer;
    }
}

class Task {
    int index;
    int priority;

    public Task(int index, int priority) {
        this.index = index;
        this.priority = priority;
    }
}