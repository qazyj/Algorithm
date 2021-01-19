import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int answer, number;
	static long[] array;

	public static void main(String[] args) throws Exception {
		SetData();
		Calculate();
		System.out.println(answer);
	}

	private static void SetData() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		number = Integer.parseInt(br.readLine());
		array = new long[number+1];
		answer = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int index=1;
		while(st.hasMoreTokens()) {
			array[index++] = Long.parseLong(st.nextToken());
		}
	}
	
	private static void Calculate() {
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
			
			answer=Math.max(answer,temp);
		}
	}
}