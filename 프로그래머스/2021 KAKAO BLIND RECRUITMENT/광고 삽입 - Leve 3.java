import java.util.*;

public class Solution {
	public String solution(String play_time, String adv_time, String[] logs) {
		int playTime = StringToTime(play_time);
		int advTime = StringToTime(adv_time);

		long[] playCount = new long[playTime + 1];
		for (String log : logs) {
			String[] split = log.split("-");
			playCount[StringToTime(split[0])]++;
			playCount[StringToTime(split[1])]--;
		}

		for (int i = 1; i <= playTime; i++) playCount[i] += playCount[i - 1];
		for (int i = 1; i <= playTime; i++) playCount[i] += playCount[i - 1];

		long maxTime = playCount[advTime - 1];
		long maxStartTime = 0;
		for (int i = 0; i + advTime <= playTime; i++) {
			long temp = playCount[i + advTime] - playCount[i];

			if (temp > maxTime) {
				maxTime = temp;
				maxStartTime = i + 1;
			}
		}

		return String.format("%02d:%02d:%02d", maxStartTime / (3600), (maxStartTime / 60) % 60, maxStartTime % 60);
	}

	public static int StringToTime(String str) {
		int[] parse = Arrays.stream(str.split(":")).mapToInt(Integer::parseInt).toArray();
		return parse[0] * 60 * 60 + parse[1] * 60 + parse[2];
	}
}