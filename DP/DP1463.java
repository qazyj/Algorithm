import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Algorithm {
    public static void main(String args[]) {
    	Scanner scanner = new Scanner(System.in);
        
    	int n = scanner.nextInt(); 
    	int k = scanner.nextInt();
    	int m = scanner.nextInt()-1;
    	ArrayList array = new ArrayList();
    	for(int i=1;i<n+1;i++){
    		array.add(i);	
    	}
    	while(array.size()!=1) {
    		System.out.println(m+" "+array.size());
    		System.out.println(array.get(m));
    		array.remove(m);
    		m = m+k/array.size();
    	}
    	System.out.println(array.get(0));
    	
    	//array.remove(3);
    	//for(int i=0;i<array.size();i++)
    	//	System.out.println(array.get(i));
    	//System.out.println(array[X]);
		
	}
}
