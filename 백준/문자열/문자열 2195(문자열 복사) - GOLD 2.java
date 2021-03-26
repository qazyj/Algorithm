import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main{
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String S = br.readLine();
		String P = br.readLine();
		
		String temp="";
		int count=P.length();
		
		for(int i=0;i<P.length();i++) {
			temp+=P.charAt(i);
			if(i==P.length()-1 && S.contains(temp))
				count-=temp.length()-1;
			if(!S.contains(temp)) {
				if(temp.length()>2)
					count-=temp.length()-2;
				temp=P.charAt(i)+"";
			}
		}
		System.out.println(count);
	}
}