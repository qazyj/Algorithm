import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[] bits = new int[n+1];
		for(int i = 0 ; i < m; i++) {
			String[] temp = br.readLine().split(" ");
			int command = Integer.parseInt(temp[0]);
			int train = Integer.parseInt(temp[1]);
			int chair = 0;
			if(command == 1 || command == 2) chair = Integer.parseInt(temp[2]);

			if(command == 1) {
				bits[train] |= (1 << chair);
			} else if (command == 2) {
				bits[train] &= ~(1 << chair);
			} else if (command == 3) {
				bits[train] = bits[train] << 1;
				bits[train] &= ((1 << 21) - 1);
			} else if (command == 4) {
				bits[train] = bits[train] >> 1;
				bits[train] &= ~1;
			}
		}

		Set<Integer> set = new HashSet<>();
		for(int i = 1; i <= n; i++) {
			if(set.contains(bits[i])) continue;

			set.add(bits[i]);
		}
		System.out.println(set.size());
	}
}