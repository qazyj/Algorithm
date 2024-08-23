import java.io.*;
import java.util.StringTokenizer;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Map<String, Double> map = new HashMap<>();
		map.put("A+", 4.5);
		map.put("A0", 4.0);
		map.put("B+", 3.5);
		map.put("B0", 3.0);
		map.put("C+", 2.5);
		map.put("C0", 2.0);
		map.put("D+", 1.5);
		map.put("D0", 1.0);
		map.put("F", 0.0);

		double sum = 0;
		double total = 0;
		for(int i = 0; i < 20; i++) {
			String[] temp = br.readLine().split(" ");
			if(temp[2].equals("P")) continue;
			double a = Double.parseDouble(temp[1]);
			double b = map.get(temp[2]);

			total += a;
			sum += (a*b);
		}
		System.out.printf("%.6f", sum/total);
	}

}