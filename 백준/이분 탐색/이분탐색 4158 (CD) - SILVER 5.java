import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		String[] sn = new String[n];
		for(int i = 0; i < n; i++) {
			sn[i] = br.readLine();
		}

		Arrays.sort(sn);
		int answer = 0;
		for(int i = 0; i < m; i++) {
			String s = br.readLine();
			int left = 0;
			int right = n-1;
			while(left <= right) {
				int mid = (left + right) / 2;
				int gap = sn[mid].compareTo(s);

				if(s.length() <= sn[mid].length() && sn[mid].substring(0,s.length()).equals(s)) {
					answer++;
					break;
				}

				if(gap > 0) {
					right = mid - 1;
				} else {
					left = mid + 1;
				}
			}

		}
		System.out.println(answer);
	}
}