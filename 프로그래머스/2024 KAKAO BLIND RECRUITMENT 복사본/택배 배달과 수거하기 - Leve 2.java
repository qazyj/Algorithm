import java.util.*;

class Solution {
	public long solution(int cap, int n, int[] deliveries, int[] pickups) {
		long answer = 0;
		PriorityQueue<Parcel> del = new PriorityQueue<>();
		for(int i = 0; i < n; i++) {
			if(deliveries[i] == 0) continue;
			del.add(new Parcel(i+1, deliveries[i]));
		}

		PriorityQueue<Parcel> pic = new PriorityQueue<>();
		for(int i = 0; i < n; i++) {
			if(pickups[i] == 0) continue;
			pic.add(new Parcel(i+1, pickups[i]));
		}

		while(!del.isEmpty() || !pic.isEmpty()) {
			if(!del.isEmpty() && !pic.isEmpty()) {
				answer += Math.max(del.peek().index, pic.peek().index)*2;
			}
			else if(!del.isEmpty()) {
				answer += del.peek().index*2;
			}
			else if(!pic.isEmpty()){
				System.out.println(pic.peek().index);
				answer += pic.peek().index*2;
			}

			int dCap = cap;
			while(!del.isEmpty() && dCap > 0) {
				Parcel par = del.poll();
				if(par.count > dCap) {
					del.add(new Parcel(par.index, par.count-dCap));
					break;
				} else if(par.count == dCap) {
					break;
				} else {
					dCap -= par.count;
				}
			}


			int pCap = cap;
			while(!pic.isEmpty() && pCap > 0) {
				Parcel par = pic.poll();
				if(par.count > pCap) {
					pic.add(new Parcel(par.index, par.count-pCap));
					break;
				} else if(par.count == pCap) {
					break;
				} else {
					pCap -= par.count;
				}
			}
		}

		return answer;
	}
}

class Parcel implements Comparable<Parcel> {
	int index;
	int count;

	public Parcel(int index, int count) {
		this.index = index;
		this.count = count;
	}

	@Override
	public int compareTo(Parcel p) {
		return p.index - this.index;
	}
}