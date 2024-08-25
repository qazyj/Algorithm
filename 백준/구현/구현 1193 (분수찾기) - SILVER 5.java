import java.io.*;
import java.util.StringTokenizer;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int a = 1;
		int b = 1;
		int count = 1;

		while(true) {
			if(n == count || n == 1) break;

			if(a == 1 && b == 1) {b++; count++;}

			if(a == 1 && n != count) {
				while(true) {
					a++;
					b--;
					count++;

					if(b == 1 || n == count) break;
				}
				if(b == 1 && n != count) {
					a++;
					count++;
				}
			}else if(a != 1 && n != count) {
				while(true) {
					a--;
					b++;
					count++;

					if(a == 1 || n == count) break;
				}
				if(a == 1 && n != count) {
					b++;
					count++;
				}
			}
		}
		System.out.println(a + "/" + b);
	}

}