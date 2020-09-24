import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Algorithm {
	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		backtracking("");
	}

	private static void backtracking(String s) {
		if (s.length() == N) {
			System.out.println(s);
			System.exit(0);
		}

		for (int i = 1; i <= 3; i++) {
			if (PossibleNumber(s + i))
				backtracking(s + i);
		}
	}

	private static boolean PossibleNumber(String s) {
		int length = s.length();
		for (int i = 1; i <= length/2; i++) {
			String frontString = s.substring(length-i*2, length-i);
			String backString = s.substring(length-i, length);
			if(length == 7)
				System.out.println(frontString + " " + backString);
			if (frontString.equals(backString))
				return false;
		}

		return true;
	}
}
