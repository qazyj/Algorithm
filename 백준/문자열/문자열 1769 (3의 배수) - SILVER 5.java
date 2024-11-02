import java.io.*;
import java.util.*;
import java.util.function.Function;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String X = br.readLine();
		if(X.length() == 1) {
			System.out.println(0);
			int v = Integer.parseInt(X);
			System.out.println((v%3==0)?"YES":"NO");
			return;
		}
		int answer = 0;
		while(true) {
			int Y = 0;
			for(int i = 0; i < X.length(); i++) {
				Y += X.charAt(i) - '0';
			}

			answer++;
			if(Y < 10) {
				System.out.println(answer);
				int v = Integer.parseInt(X);
				System.out.println((v%3==0)?"YES":"NO");
				break;
			}

			X = String.valueOf(Y);
		}
	}
}