import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int X;
	static int[] array;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(array[X]);
	}

	private static void SetData() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		X = Integer.parseInt(br.readLine());
		array = new int[X + 1];
		
		for (int i = 2; i <= X; i++) {
			array[i] = array[i - 1] + 1;
			if (i % 2 == 0)
				array[i] = Math.min(array[i], array[i / 2] + 1);
			if (i % 3 == 0)
				array[i] = Math.min(array[i], array[i / 3] + 1);
		}
	}
}
