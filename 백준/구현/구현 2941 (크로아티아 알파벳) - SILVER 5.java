import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] croatias = {"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};
		String str = sc.next();

		for (int i = 0; i < croatias.length; i++) {
			if (str.contains(croatias[i]))
				str = str.replace(croatias[i], "#");
		}
		System.out.println(str.length());
		sc.close();
	}
}