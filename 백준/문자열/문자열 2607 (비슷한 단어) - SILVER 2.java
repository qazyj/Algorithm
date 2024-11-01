import java.io.*;
import java.util.*;
import java.util.function.Function;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		char[] sr = br.readLine().toCharArray();
		int[] arr = new int[26];
		for(int i = 0; i < sr.length; i++) {
			arr[sr[i] - 'A']++;
		}
		int answer = 0;
		for(int i = 1; i < n; i++) {
			char[] ir = br.readLine().toCharArray();
			int[] irr = new int[26];
			for(int j = 0; j < ir.length; j++) {
				irr[ir[j] - 'A']++;
			}

			int gap = 0;
			for(int j = 0; j < 26; j++) {
				gap += Math.abs(arr[j] - irr[j]);
			}
			if(gap <= 1) answer++;
			if(gap == 2 && sr.length == ir.length) {
				//System.out.println("check");
				answer++;
			}

		}
		System.out.println(answer);
	}
}