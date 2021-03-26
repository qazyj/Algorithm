import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Algorithm {
	static int X, stick, answer;

	public static void main(String[] args) throws Exception {
		SetData();
		getStick();
		System.out.println(answer);
	}

	private static void SetData() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
        X = Integer.parseInt(br.readLine());
        stick = 64;
        answer = 0;
	}
	
    public static void getStick() {
		while (stick > 0) {
			if (stick <= X) {
				X -= stick;
				answer++;
			}
			if (X == 0) {
				break;
			}
			stick /= 2;
		}
    }

}
