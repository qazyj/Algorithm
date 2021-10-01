import java.util.*;

class Solution {
    public int[] solution(int[] weights, String[] head2head) {
        int[] answer = new int[weights.length];
        PriorityQueue<Boxer> boxers = new PriorityQueue<>();

        for(int i = 0; i < head2head.length; i++){
            int overWeightWinCount = 0, winCount = 0, loseCount = 0;
            for(int j = 0 ; j < head2head[i].length(); j++){
                if(head2head[i].charAt(j) == 'W') {
                    if(weights[i] < weights[j])
                        overWeightWinCount++;
                    winCount++;
                } else if(head2head[i].charAt(j) == 'L'){
                    loseCount++;
                }
            }
            if(winCount > 0)
                boxers.add(new Boxer(i+1, weights[i], winCount/(double)(winCount+loseCount),overWeightWinCount));
            else
                boxers.add(new Boxer(i+1, weights[i], 0.0,overWeightWinCount));
        }


        int index = 0;
        while(!boxers.isEmpty()){
            Boxer box = boxers.poll();
            answer[index++] = box.number;
            System.out.println(box.rate + " " + box.overWeightWinCount+ " " + box.weight + " " + box.number);
        }

        return answer;
    }
}

class Boxer implements Comparable<Boxer> {
    int number;
    int weight;
    double rate = 0.0;
    int overWeightWinCount;

    public Boxer(int number, int weight, double rate, int overWeightWinCount){
        this.number = number;
        this.weight = weight;
        this.rate = rate;
        this.overWeightWinCount = overWeightWinCount;
    }

    @Override
    public int compareTo(Boxer boxer){
        if(boxer.rate == this.rate){
            if(boxer.overWeightWinCount == this.overWeightWinCount){
                if(boxer.weight == this.weight) {
                    return this.number - boxer.number;
                }
                return Integer.compare(boxer.weight,this.weight);
            }
            return boxer.overWeightWinCount - this.overWeightWinCount;
        }
        return Double.compare(boxer.rate, this.rate);
    }
}