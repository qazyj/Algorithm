import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Algorithm {
    static int N, Kim, Lim;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(GoTournament());
	}

	private static void SetData() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Kim = Integer.parseInt(st.nextToken());
        Lim = Integer.parseInt(st.nextToken());
	}

	private static int GoTournament() {
		int count = 0;
		
		while(Kim != Lim) {
			Kim = Kim/2 + Kim%2;
			Lim = Lim/2 + Lim%2;
			count++;
		}
		
		return count;
	}
}
