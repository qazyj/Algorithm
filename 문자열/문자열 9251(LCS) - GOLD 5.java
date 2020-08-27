import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Algorithm {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String firstInput = br.readLine();
		String secondInput = br.readLine();

		int[][] dp = new int[firstInput.length() + 1][secondInput.length() + 1]; // 각 문자열의 길이만큼 선언
		// 주의해야 할 점은 무엇을 기준으로 무엇과 비교할지를 정한 후 순서에 맞게 선언해주어야 한다.
		// 여기선 b를 기준으로 a와 비교하게 된다.

		for (int i = 1; i <= firstInput.length(); i++) { 
			for (int j = 1; j <= secondInput.length(); j++) { 
				if (firstInput.charAt(i - 1) == secondInput.charAt(j - 1)) { 
					dp[i][j] = dp[i - 1][j - 1] + 1; // 점화식
				} else { 
					dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]); // 점화식					
				}
			}
		}

		System.out.println(dp[firstInput.length()][secondInput.length()]); // 이곳 역시 선언과 똑같이 출력해주어야 런타임 에러가 나지 않는다.

	}
}
