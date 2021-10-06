import java.util.*;

class Solution {
    static ArrayList[] array;
    static boolean[] check;

    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        array = new ArrayList[n+1];
        for(int i = 1 ; i <= n ; i++){
            array[i] = new ArrayList<Integer>();
        }

        for(int i = 0 ; i < wires.length; i++) {
            array[wires[i][0]].add(wires[i][1]);
            array[wires[i][1]].add(wires[i][0]);
        }

        for(int i = 0 ; i < wires.length; i++) {
            check = new boolean[n+1];
            check[wires[i][0]] = true;
            check[wires[i][1]] = true;
            int a = bfs(wires[i][0]);
            int b = bfs(wires[i][1]);
            answer = Math.min(answer, Math.abs(a-b));
        }

        return answer;
    }

    private static int bfs(int index) {
        int sum = 1;
        check[index] = true;
        for(int i = 0 ; i < array[index].size(); i++){
            if(check[(int)array[index].get(i)]) continue;
            sum += bfs((int)array[index].get(i));
        }

        return sum;
    }
}