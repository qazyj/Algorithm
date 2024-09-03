import java.io.*;
import java.util.StringTokenizer;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] temp = br.readLine().split(" ");
		int n = Integer.parseInt(temp[0]);
		int k = Integer.parseInt(temp[1]);
		Queue<Integer> q = new LinkedList<>();
		for(int i = 1; i <= n; i++){
			q.add(i);
		}

		int count = 1;
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		while(!q.isEmpty()){
			if(count == k) {
				sb.append(q.poll()).append(", ");
				count=1;
			} else {
				count++;
				q.add(q.poll());
			}
		}
		sb.replace(sb.length()-2, sb.length(), ">");
		System.out.println(sb);
	}
}