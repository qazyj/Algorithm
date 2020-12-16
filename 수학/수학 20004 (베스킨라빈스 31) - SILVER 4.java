import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Algorithm {
	static int A;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(sb);
	}

	private static void SetData() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		A = Integer.parseInt(br.readLine());
		sb = new StringBuilder();

		for(int i = 1; i <= A; i++) {
			if(30 % (i+1) == 0)		// 내가 30을 부를 수 있으면 필승
				sb.append(i+"\n");
		}
	}
}
