import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Solution {
    public int solution(String dartResult) {
		Pattern pattern = Pattern.compile("([0-9]+)([SDT])([*#]?)");
		Matcher matcher = pattern.matcher(dartResult);

		Stack<Integer> score = new Stack<>();
		while (matcher.find()) {
			int dart = Integer.parseInt(matcher.group(1));

			switch (matcher.group(2)) {
			case "D":
				dart *= dart;
				break;
			case "T":
				dart *= dart * dart;
			}

			switch (matcher.group(3)) {
			case "*":
				if (!score.isEmpty())
					score.push(score.pop() * 2);
				dart *= 2;
				break;
			case "#":
				dart *= -1;
				break;
			}
			score.push(dart);
		}

		int answer = 0;
		while (!score.isEmpty())
			answer += score.pop();

        return answer;
    }
}