import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s = br.readLine();
		Set<String> set = new HashSet<>();
		for(int i = 1; i <= s.length(); i++) {
			for(int j = 0; j <= s.length()-i; j++) {
				String cur = s.substring(j, j+i);
				if(!set.contains(cur)) {set.add(cur);}
			}
		}
		System.out.println(set.size());
	}

}