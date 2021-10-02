import java.util.*;

class Solution {
    public String solution(String[] table, String[] languages, int[] preference)     {
        PriorityQueue<Job> job = new PriorityQueue<>();
        for(String s : table){
            String[] temp = s.split(" ");
            int score = 0;
            int index = 5;
            for(int i = 1; i < temp.length; i++){
                for(int j = 0; j < languages.length; j++){
                    if(temp[i].equals(languages[j])){
                        score += index*preference[j];
                        break;
                    }
                }
                index--;
            }
            job.add(new Job(temp[0], score));
        }

        return job.poll().name;
    }
}

class Job implements Comparable <Job> {
    String name;
    int score;

    public Job(String name, int score){
        this.name = name;
        this.score = score;
    }

    @Override
    public int compareTo(Job job){
        if(job.score == this.score) {
            if(job.name.length() == this.name.length()) {
                return this.name.compareTo(job.name);	// 사전 순 정렬
            }
            // 그 외의 경우
            else {
                return job.name.length() - this.name.length();
            }
        }
        return job.score - this.score;
    }
}