import java.util.*;

class Solution {
    public int[] solution(int N, int[] stages) {
		int[] answer = new int[N];
		PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
		
		for(int i = 1 ; i <= N; i++) {
			int pass = 0;
			int fail = 0;
			
			for(int j = 0 ; j < stages.length; j++) {
				if(stages[j] >= i) {
					if(stages[j] == i)
						fail++;
					pass++;
				}
			}
			
			if(pass == 0)
				pass++;
			
			double ratio = (double) fail / pass;
			priorityQueue.add(new Node(i, ratio));
		}
		
		for(int i = 0 ; i < N; i++) {
			answer[i] = priorityQueue.poll().number;
		}
        
        return answer;
    }
}

class Node implements Comparable<Node> {
	int number;
	double fail;
	
	public Node(int number, double fail) {
		this.number = number;
		this.fail = fail;
	}

	@Override
	public int compareTo(Node o) {
		// TODO Auto-generated method stub
		if(this.fail == o.fail)
			return Integer.compare(this.number, o.number);
		return -Double.compare(this.fail, o.fail);
	}
}
