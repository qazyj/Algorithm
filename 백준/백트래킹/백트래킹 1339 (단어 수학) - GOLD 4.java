import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://qazyj.tistory.com/80

public class Main {
	static int answer;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(answer);
	}
	
	private static void SetData() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		answer = 0;
		int[] array = new int[26];
		
		for(int i = 0; i < N; i++) {
			String s = br.readLine();
			int temp = 1;
			for(int j = s.length() - 1; j >= 0; j--) {
				array[s.charAt(j) - 'A'] += temp;
				temp *= 10;
			}
		}
		
		Arrays.sort(array);
		int temp = 25;
		for(int i = 9; i >= 1; i--) {
			answer += array[temp--]*i;
		}
	}
	
}
