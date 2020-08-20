import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String input = br.readLine();
		String[] minusSplit = input.split("-");
		int sum=0;
		
		for(int index=0;index<minusSplit.length;index++) {
			String[] number = minusSplit[index].split("[+]");
			for(int index2=0;index2<number.length;index2++) {
				if(index==0)
					sum+=Integer.parseInt(number[index2]);
				else
					sum-=Integer.parseInt(number[index2]);
			}
		}
		bw.write(sum + "\n");
		
		bw.flush();
		bw.close();
	}
}