class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        for (String skillTree : skill_trees) {
            int learningIndex = 0;
            boolean canUse = true;
            for (char curSkill : skillTree.toCharArray()) {
                int skillIndex = skill.indexOf(curSkill);
                if (skillIndex == -1)
                    continue;
                else if (skillIndex == learningIndex)
                    learningIndex++;
                else {
                    canUse = false;
                    break;
                }
            }
            if (canUse)  answer++;
        }
        return answer;
    }
}