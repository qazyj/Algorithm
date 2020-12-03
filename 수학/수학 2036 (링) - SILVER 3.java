import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Algorithm {
	static int N;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		SetData();
		System.out.println(sb);
	}

	private static void SetData() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		sb = new StringBuilder();		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B,C;
		for(int i = 0; i < N-1; i++) {
			B = Integer.parseInt(st.nextToken());
			C = GCD(A,B);
			sb.append(A/C + "/"+ B/C + "\n");
		}
	}
	
    private static int GCD(int x, int y)
    {
        if(x % y == 0) return y;
        return GCD(y, x%y);
    }
}
