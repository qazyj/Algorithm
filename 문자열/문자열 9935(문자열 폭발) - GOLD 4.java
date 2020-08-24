import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub        
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Character> stack = new Stack<Character> ();
        
        String BombedString = br.readLine();
        String bombString = br.readLine(); 

        for(int i = BombedString.length() - 1; i >= 0;  i--){
            stack.push(BombedString.charAt(i));   

            if(stack.size() >= bombString.length() && stack.peek() == bombString.charAt(0)){
                boolean isBomb = true;
                for(int j = 1; j < bombString.length(); j++){
                    if(stack.get(stack.size()-j-1) != bombString.charAt(j)){
                        isBomb = false;
                        break;
                    }
                } 

                if(isBomb){  
                    for(int j = 0; j < bombString.length(); j++) 
                    	stack.pop();
                }
            }
        }
        

        int stackSize = stack.size();
        StringBuilder sb = new StringBuilder();
        
        if(stack.isEmpty()){
               System.out.println("FRULA");
        }else{
               for(int i = 0; i < stackSize; i++) sb.append(stack.pop());
        }

        System.out.println(sb);

    }

}