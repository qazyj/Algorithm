import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Algorithm {
	static long answer;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(answer%10);
	}

	private static void SetData() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		long input = Long.parseLong(br.readLine());
		answer =1;
		
		for(int i=1;i<=input;i++) {
			answer*=i;
			answer %= 1000000000;
			while (answer % 10 == 0)
			     answer /= 10;
		}
	}
}