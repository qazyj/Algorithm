import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Algorithm {
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        
        String [][] temp = new String[M][2]; 
        HashSet<String> friend = new HashSet<>();
        
        for(int i=0;i<M;i++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	temp[i][0]=st.nextToken();
        	temp[i][1]=st.nextToken();
        	
        	if(temp[i][0].equals("1") 
        			&& !temp[i][1].equals("1") 
        			&& !friend.contains(temp[i][1])) {
        		friend.add(temp[i][1]);
        	}
        	else if(temp[i][1].equals("1") 
        			&& !temp[i][0].equals("1") 
        			&& !friend.contains(temp[i][0])) {
        		friend.add(temp[i][0]);
        	}
        }
        
        ArrayList arrayList=new ArrayList();
    	arrayList.addAll(friend);
    	ArrayList remove = new ArrayList();
        
    	int count = arrayList.size();
    	
        for(int i=arrayList.size();i<M;i++) {
        	for(int j=0;j<arrayList.size();j++) {
        		if(temp[i][0].equals(arrayList.get(j)) 
        				&& !arrayList.contains(temp[i][1])  
        				&& !remove.contains(temp[i][1])
        				&& !temp[i][1].equals("1")) {
        			remove.add(temp[i][1]);
        			count++;
        		}
        		else if(temp[i][1].equals(arrayList.get(j)) 
        				&& !arrayList.contains(temp[i][0])  
        				&& !remove.contains(temp[i][0])
        				&& !temp[i][0].equals("1")) {
        			remove.add(temp[i][0]);
        			count++;
        		}
        	}
        }
        
        System.out.println(count);
	}
}