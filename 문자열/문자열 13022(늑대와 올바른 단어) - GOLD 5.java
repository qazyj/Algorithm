import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String S = br.readLine();
		String test = "(w+o+l+f+)+";
		int wCount=0,oCount=0,lCount=0,fCount=0;
		boolean check = false;
		
		for(int i=0;i<S.length();i++) {			
			if(S.charAt(i)=='w')
				wCount++;
			else if(S.charAt(i)=='o')
				oCount++;
			else if(S.charAt(i)=='l')
				lCount++;
			else
				fCount++;
			
			if(i<S.length()-1 && S.charAt(i)=='f' && S.charAt(i+1)=='w') {
				if(!(wCount==oCount && oCount==lCount&& lCount==fCount && fCount == wCount)) {
					check = true;
					break;
				}
			}
		}
		
		if(!check && S.matches(test) && wCount==oCount && oCount==lCount&& lCount==fCount && fCount == wCount)
			System.out.println(1);
		else
			System.out.println(0);
	}
}