import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Algorithm {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int number = Integer.parseInt(br.readLine());
		long[] array = new long[number+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int index=1;
		while(st.hasMoreTokens()) {
			array[index++] = Long.parseLong(st.nextToken());
		}
		
		int ans=0;
		for(int i = 1;i <= number;i++){
			int temp=0;
			
			double angle = 99999999999L;
			for(int j = i-1;j >= 1;j--){
				double lean=(double)(array[j]-array[i])/(double)(j-i);
				if(lean<angle){
					temp++;
					angle=lean;
				}
			}
			
			angle = -99999999999L;
			for(int j = i+1;j <= number;j++){
				double lean=(double)(array[j]-array[i])/(double)(j-i);			
				if(lean>angle){
					temp++;
					angle=lean;
				}
			}
			
			ans=Math.max(ans,temp);
		}
	    System.out.println(ans);
	}
}
