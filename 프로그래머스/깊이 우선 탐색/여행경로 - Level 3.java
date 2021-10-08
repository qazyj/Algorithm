import java.util.*;

class Solution {
    static boolean[] check;
    static PriorityQueue<String> answer;

    public String[] solution(String[][] tickets) {
        check = new boolean[tickets.length];
        answer = new PriorityQueue<>();
        dfs("ICN", "ICN", 0, tickets);

        return answer.poll().split(" ");
    }

    static void dfs(String now, String nodes, int count, String[][] tickets) {
        if(count == tickets.length) {
            answer.add(nodes);
            return;
        }

        for(int i = 0; i < tickets.length; i++) {
            if(!check[i] && tickets[i][0].equals(now)) {
                check[i] = true;
                dfs(tickets[i][1], nodes + " " + tickets[i][1],count+1, tickets);
                check[i] = false;
            }
        }
    }
}