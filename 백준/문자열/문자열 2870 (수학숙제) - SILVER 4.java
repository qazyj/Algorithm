import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.function.Function;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		PriorityQueue<BigInteger> pq = new PriorityQueue<>();
		int n = Integer.parseInt(br.readLine());
		for(int i = 0; i < n; i++) {
			String s = br.readLine();
			StringBuilder sbb = new StringBuilder();
			for(int j = 0; j < s.length(); j++) {
				if(s.charAt(j) >= '0' && s.charAt(j) <= '9') sbb.append(s.charAt(j));
				else {
					if(sbb.length() > 0) {
						pq.add(new BigInteger(sbb.toString()));
					}
					sbb = new StringBuilder();
				}
			}
			if(sbb.length() > 0) {
				pq.add(new BigInteger(sbb.toString()));
			}
		}


		StringBuilder sb = new StringBuilder();
		while(!pq.isEmpty()) {
			sb.append(pq.poll()).append("\n");
		}
		System.out.println(sb);
	}
}