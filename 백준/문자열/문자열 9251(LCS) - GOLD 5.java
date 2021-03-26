import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int[][] dp;
	static int firstInputLength, secondInputLength;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		SetData();
		System.out.println(dp[firstInputLength][secondInputLength]);
	}
	
	private static void SetData() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String firstInput = br.readLine();
		String secondInput = br.readLine();

		firstInputLength = firstInput.length();
		secondInputLength = secondInput.length();
		dp = new int[firstInputLength + 1][secondInputLength + 1];
		
		for (int i = 1; i <= firstInputLength; i++) { 
			for (int j = 1; j <= secondInputLength; j++) { 
				if (firstInput.charAt(i - 1) == secondInput.charAt(j - 1)) { 
					dp[i][j] = dp[i - 1][j - 1] + 1;
				} else { 
					dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);				
				}
			}
		}
	}	
}