import java.io.*;
import java.util.StringTokenizer;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine())*2;
		Queue<String> q = new LinkedList<>();
		Set<String> set = new HashSet<>();
		Set<String> answer = new HashSet<>();
		for(int i = 0; i < n; i++) {
			String s = br.readLine();

			if(set.contains(s)) {
				while(!q.isEmpty() && answer.contains(q.peek())) {
					q.poll();
				}

				if(q.peek().equals(s)) q.poll();
				else {
					answer.add(s);
				}
				continue;
			}

			set.add(s);
			q.add(s);
		}
		System.out.println(answer.size());
	}
}