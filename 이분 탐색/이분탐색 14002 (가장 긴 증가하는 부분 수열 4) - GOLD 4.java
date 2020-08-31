import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] inputArray = new int[N];
		int[] LIS = new int[N];
		int[] arrayIndexes = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			inputArray[i] = Integer.parseInt(st.nextToken());
		}

		LIS[0] = inputArray[0];
		arrayIndexes[0] = 0;
		int j = 0;
		for (int i = 1; i < N; i++) {
			if (LIS[j] < inputArray[i]) {
				LIS[++j] = inputArray[i];
				arrayIndexes[i] = j;
			} else {
				int index = binary(LIS, 0, j, inputArray[i]);
				LIS[(int) index] = Math.min(LIS[index], inputArray[i]);
				arrayIndexes[i] = index;
			}
		}

		System.out.println(j + 1);

		String printString = "";
		for (int i = N - 1; 0 <= i; i--) {
			if (arrayIndexes[i] == j) {
				printString = inputArray[i] + " " + printString;
				j--;
			}
		}
		System.out.print(printString);
	}

	static int binary(int[] LIS, int startIndex, int endIndex, int key) {
		while (startIndex <= endIndex) {
			int mid = (int) ((startIndex + endIndex) / 2);
			if (LIS[mid] < key) {
				startIndex = mid + 1;
			} else {
				endIndex = mid - 1;
			}
		}
		return startIndex;
	}

}