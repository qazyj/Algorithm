import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Algorithm {
	static int T;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(sb);
	}

	private static void SetData() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

        T = Integer.parseInt(br.readLine());
        sb = new StringBuilder();

        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            int[] candidates = new int[N + 1];
            for (int j = 0; j < N; j++) {
            	st = new StringTokenizer(br.readLine());
                candidates[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
            }

            int count = 1;
            int interviewRank = candidates[1];

            for (int j = 2; j <= N; j++) {
                if (interviewRank >= candidates[j]) {
                    interviewRank = candidates[j];
                    count++;
                }
            }

            sb.append(count + "\n");
        }
	}
}