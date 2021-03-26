import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Algorithm {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String firstInput = br.readLine();
		String secondInput = br.readLine();

		int[][] dp = new int[firstInput.length() + 1][secondInput.length() + 1];

		for (int i = 1; i <= firstInput.length(); i++) { 
			for (int j = 1; j <= secondInput.length(); j++) { 
				if (firstInput.charAt(i - 1) == secondInput.charAt(j - 1)) { 
					dp[i][j] = dp[i - 1][j - 1] + 1;
				} else { 
					dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);				
				}
			}
		}
		
		int x = firstInput.length();
		int y = secondInput.length();
		StringBuilder sb = new StringBuilder();
		while(x != 0 && y != 0) {
			if(firstInput.charAt(x-1) == secondInput.charAt(y-1)) {
				sb.append(firstInput.charAt(x-1));
				x--;
				y--;
			}
			else if(dp[x][y] == dp[x-1][y])
				x--;
			else if(dp[x][y] == dp[x][y-1]) 
				y--;
		}
		

		System.out.println(dp[firstInput.length()][secondInput.length()]);
		System.out.print(sb.reverse().toString());

	}
}