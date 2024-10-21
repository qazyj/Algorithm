import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		char[] arr = br.readLine().toCharArray();
		int answer = 0;
		for(char a: arr) {
			if(a!='1') continue;

			answer++;
		}
		System.out.println(answer);
	}
}