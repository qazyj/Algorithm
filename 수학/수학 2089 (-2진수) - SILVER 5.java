import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

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
		ArrayList<Integer> list = new ArrayList<>();
        
        if(N==0) { 
            System.out.println(N);
            System.exit(0);
        }
    
        while(N!=0) {
            list.add(Math.abs(N%-2));
            N=(int)Math.ceil((double)N/-2);
        }
        
        for(int i=list.size()-1; i>=0; i--)
            sb.append(list.get(i));
	}
}
