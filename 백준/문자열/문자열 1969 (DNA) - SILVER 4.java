import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		String[] s = new String[n];
		for(int i = 0; i < n; i++) {
			s[i] = br.readLine();
		}

		StringBuilder sb = new StringBuilder();
		int answer = 0;
		for(int i = 0; i < m; i++) {
			Map<Character, Integer> map = new HashMap<>();
			for(int j = 0; j < n; j++) {
				map.put(s[j].charAt(i), map.getOrDefault(s[j].charAt(i), 0) + 1);
			}


			Map<Character, Integer> sortedMap = map.entrySet().stream()
					.sorted((e1, e2) -> {
						// value가 다르면 value 기준 내림차순
						int compareByValue = e2.getValue().compareTo(e1.getValue());
						// value가 같으면 key 기준 오름차순
						if (compareByValue == 0) {
							return e1.getKey().compareTo(e2.getKey());
						}
						return compareByValue;
					})
					.collect(Collectors.toMap(
							Map.Entry::getKey,
							Map.Entry::getValue,
							(e1, e2) -> e1,
							LinkedHashMap::new
					));

			int index = 0;
			for(Map.Entry<Character, Integer> entry : sortedMap.entrySet()) {
				if(index > 0) {
					answer += entry.getValue();
					continue;
				}
				sb.append(entry.getKey());
				index++;
			}
		}
		System.out.println(sb);
		System.out.println(answer);
	}
}