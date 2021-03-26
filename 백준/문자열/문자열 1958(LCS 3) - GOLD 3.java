import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Algorithm {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String firstInput = br.readLine();
		String secondInput = br.readLine();
		String thirdInput = br.readLine();

		int[][][] dp = new int[firstInput.length() + 1][secondInput.length() + 1][thirdInput.length() + 1];

		for (int i = 1; i <= firstInput.length(); i++) {
			for (int j = 1; j <= secondInput.length(); j++) {
				for (int z = 1; z <= thirdInput.length(); z++) {
					if (firstInput.charAt(i - 1) == secondInput.charAt(j - 1) 
							&& secondInput.charAt(j - 1) == thirdInput.charAt(z-1)) {
						dp[i][j][z] = dp[i - 1][j - 1][z - 1] + 1;
					} else {
						dp[i][j][z] = Math.max(dp[i][j][z-1] ,Math.max(dp[i][j - 1][z], dp[i - 1][j][z]));
					}
				}
			}
		}
		
		System.out.println(dp[firstInput.length()][secondInput.length()][thirdInput.length()]);
	}
}
