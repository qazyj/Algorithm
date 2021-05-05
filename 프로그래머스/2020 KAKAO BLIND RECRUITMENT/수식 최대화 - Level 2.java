import java.util.*;

class Solution {    
	private static char[] calculate = { '+', '-', '*' };
	private static char[] temp = new char[3]; 
	private static boolean[] check = new boolean[3]; 
	private static ArrayList<Long> number = new ArrayList<Long>();
	private static ArrayList<Character> symbol = new ArrayList<Character>();
	private static long answer;

	public long solution(String expression) {
		answer = 0;
		String temp = "";
        
		for (int i = 0; i < expression.length(); i++) {
			if (expression.charAt(i) >= '0' && expression.charAt(i) <= '9') {
				temp += expression.charAt(i);
			} else {
				number.add(Long.parseLong(temp));
				temp = "";
				symbol.add(expression.charAt(i));
			}
		}
		number.add(Long.parseLong(temp));

		dfs(0);

		return answer;
	}

	public void dfs(int start) {

		if (start == 3) {
			math();
		} else {
			for (int i = 0; i < 3; i++) {
				if (!check[i]) {
					check[i] = true;
					temp[start] = calculate[i];
					dfs(start + 1);
					check[i] = false;
				}
			}
		}
	}

	public void math() {
		// 주어진 숫자와 연산 그대로 복사
		ArrayList<Long> copyNumber = new ArrayList<>(number);
		ArrayList<Character> copySymbol = new ArrayList<Character>(symbol);

		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < copySymbol.size(); j++) {
				if (temp[i] == copySymbol.get(j)) {
					// 숫자2개, 연산자1개 보내서 연산
					Long res = calc(copyNumber.remove(j), copyNumber.remove(j), temp[i]);
					// 연산결과값 넣기
					copyNumber.add(j, res);
					copySymbol.remove(j);
					j--;
				}
			}
		}
		answer = Math.max(answer, Math.abs(copyNumber.get(0)));

	}

	public static Long calc(Long number1, Long number2, char symbol) {
		switch (symbol) {
		case '+': {
			return number1 + number2;
		}
		case '-': {
			return number1 - number2;
		}
		case '*': {
			return number1 * number2;
		}
		}
		return (long) 0;
	}

}