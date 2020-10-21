import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Algorithm {	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int num = Integer.parseInt(br.readLine());
		
		for( int i = 0 ; i < num ; i++ ) {
			st = new StringTokenizer(br.readLine());
			int country = Integer.parseInt(st.nextToken());
			int plane = Integer.parseInt(st.nextToken());
			
			for( int j = 0 ; j < plane ; j++ ) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
			}
			
			sb.append(country-1 + "\n");
		}
		
		System.out.println(sb);
	}
}