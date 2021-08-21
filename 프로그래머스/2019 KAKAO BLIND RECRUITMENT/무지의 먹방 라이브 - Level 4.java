import java.util.ArrayList;

class Solution {
    public int solution(int[] food_times, long k) {
        ArrayList<Food> food = new ArrayList<>();
        
        long sum = 0;
        for(int i = 1; i <= food_times.length; i++)  {
            food.add(new Food (i, food_times[i-1]));
            sum += food_times[i-1];
        }
        if(sum <= k) return -1;
        
        int index = 0;
        while(k > 0) {
            k--;
            if(food.get(index).time == 1) {
                food.remove(index);
            } else {
                food.get(index).time -= 1;
                index++;
            }
            index %= food.size();
        }
        return food.get(index).index;
    }
}

class Food {
    int index;
    int time;
    
    public Food(int index, int time) {
        this.index = index;
        this.time = time;
    }
}