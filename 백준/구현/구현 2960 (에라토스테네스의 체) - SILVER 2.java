import java.io.*;
import java.util.StringTokenizer;
import java.util.*;

public class Solution {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] temp = br.readLine().split(" ");
		int n = Integer.parseInt(temp[0]);
		int k = Integer.parseInt(temp[1]);

		boolean[] arr = new boolean[n+1];
		for(int i = 2; i <= n; i++) {
			if(arr[i]) continue;
			arr[i] = true;
			k--;
			if(k == 0) System.out.println(i);
			for(int j = i+i; j <=n; j+= i) {
				if(arr[j]) continue;
				k--;
				arr[j] = true;
				if(k == 0) System.out.println(j);
			}
		}
	}

}