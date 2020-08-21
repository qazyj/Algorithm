import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Algorithm {
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub        
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Character> stack = new Stack<Character> ();
        
        String input = br.readLine();
        String bomb = br.readLine(); 

        for(int i = input.length() - 1; i >= 0;  i--){
            stack.push(input.charAt(i));   

            if(stack.size() >= bomb.length() && stack.peek() == bomb.charAt(0)){
                boolean isBomb = true;
                for(int j = 1; j < bomb.length(); j++){
                    if(stack.get(stack.size()-j-1) != bomb.charAt(j)){
                             isBomb = false;
                        break;
                    }
                } 

                if(isBomb){  
                    for(int j = 0; j < bomb.length(); j++) stack.pop();
                }
            }
        }
        

        int size = stack.size();
        StringBuffer sb = new StringBuffer();
        
        if(stack.isEmpty()){
               System.out.println("FRULA");
        }else{
               for(int i = 0; i < size; i++) sb.append(stack.pop());
        }

        System.out.println(sb);

    }

}
