import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		Map<Character, Integer> map = new HashMap<>();
		map.put('A', 0);
		map.put('C', 1);
		map.put('G', 2);
		map.put('T', 3);

		StringTokenizer st = new StringTokenizer(br.readLine());
		int s = Integer.parseInt(st.nextToken());
		int p = Integer.parseInt(st.nextToken());
		char[] arr = br.readLine().toCharArray();
		int[] sum = new int[4];
		int[] target = new int[4];
		String[] temp = br.readLine().split(" ");
		for (int i = 0; i < 4; i++) {
			target[i] = Integer.parseInt(temp[i]);
		}

		int answer = 0;
		for(int i = 0; i < p; i++) {
			sum[map.get(arr[i])]++;
		}
		if(isSame(sum, target)) answer++;
		for(int i = p; i < arr.length; i++) {
			sum[map.get(arr[i-p])]--;
			sum[map.get(arr[i])]++;
			if(isSame(sum, target)) answer++;
		}
		System.out.println(answer);
	}

	public static boolean isSame(int[] sum, int[] target) {
		for(int i = 0; i < 4; i++) {
			if(target[i] == 0) continue;
			if(sum[i] < target[i]) return false;
		}
		return true;
	}

}