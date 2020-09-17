import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int []number = new int[26];
		
		int N = Integer.parseInt(br.readLine());
		for(int i = 0; i < N; i++) {
			String temp = br.readLine();
			int tmp = 0;
			for(int j = temp.length()-1; j >= 0 ;j--) {
				number[temp.charAt(j) - 'A'] += Math.pow(10,tmp++);
			}			
		}
		Arrays.sort(number);
		
		int result = 0, num=9;
		for(int i = 25; i >= 0; i--) {
			if(number[i]==0) break;
			result+=number[i]*num--;
		}
		System.out.println(result);		
    }
}
