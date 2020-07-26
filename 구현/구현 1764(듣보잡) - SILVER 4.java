import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        HashSet<String> a = new HashSet<>();
        ArrayList<String> list = new ArrayList<>();
        
        for(int i = 0; i < n; i++){
            a.add(br.readLine());
        }

		for(int i=0;i<m;i++) {
			String temp = br.readLine();
			if(a.contains(temp))
				list.add(temp);
		}
		
		Collections.sort(list);	
		bw.write(list.size() + "\n");
		for(String temp : list)
			bw.write(temp + "\n");

        bw.flush();
        br.close();
        bw.close();
		
	}
}
