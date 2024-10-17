import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s = br.readLine();
		List<String> list = new ArrayList<>();
		for(int i = 0 ; i < s.length(); i++){
			list.add(s.substring(i,s.length()));
		}

		Collections.sort(list);
		StringBuilder sb = new StringBuilder();
		for(String cur : list) {
			sb.append(cur).append("\n");
		}
		System.out.println(sb);
	}

}