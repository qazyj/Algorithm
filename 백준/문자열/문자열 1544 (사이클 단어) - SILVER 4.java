import java.io.*;
import java.util.*;
import java.util.function.Function;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		List<String> list = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			list.add(br.readLine());
		}

		for (int i = 0; i < n; i++) {
			String listi = list.get(i);
			for (int j = i + 1; j < n; j++) {
				if (listi.length() != list.get(j).length()) continue;

				String listj = list.get(j) + list.get(j);
				if (!listj.contains(listi)) continue;

				list.remove(j);
				n--;
				j--;
			}
		}
		System.out.println(n);
	}
}