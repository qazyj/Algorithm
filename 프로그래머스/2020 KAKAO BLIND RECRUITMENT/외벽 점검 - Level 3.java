import java.util.*;

class Solution {
    static int n;
    static boolean[][] pillar, beams;
    
    public int[][] solution(int n, int[][] build_frame) {
        this.n = n;
       	pillar = new boolean[n+1][n+1];
		beams = new boolean[n+1][n+1];
		

		for(int i = 0 ; i < build_frame.length; i++) {
			int x = build_frame[i][0]; 	
			int y = build_frame[i][1];
			int a = build_frame[i][2];	// 0 기둥, 1 보
			int b = build_frame[i][3];	// 0 삭제, 1 설치
			
			// 설치
			if(b == 1) {
				// 기둥
				if(a == 0 && ConstructPillar(x,y)) {
					pillar[x][y] = true;
				} // 보
				else if(a == 1 && ConstructBeams(x,y)) {
					beams[x][y] = true;
				}
				
			}	//삭제 
			else if(b == 0) {
				if(Destroy(x,y,a)) continue;
				
				if(a == 0) {
					pillar[x][y] = true;
				} else if (a == 1) {
					beams[x][y] = true;
				}
			}
		}
		
		ArrayList<int []> structure = new ArrayList<>();
		for(int i = 0; i <= n; i++) {
			for(int j = 0; j <= n; j++) {
				if(pillar[i][j]) structure.add(new int[] {i,j,0});
				if(beams[i][j]) structure.add(new int[] {i,j,1});
			}
		}
		
		int[][] answer = new int[structure.size()][3];
		for(int i = 0; i < structure.size(); i++) {
			answer[i][0] = structure.get(i)[0];
			answer[i][1] = structure.get(i)[1];
			answer[i][2] = structure.get(i)[2];
			System.out.println(answer[i][0] + " " + answer[i][1] + " " + answer[i][2]);
		}
		
        return answer;
	}
	
	private static boolean Destroy(int x, int y, int structure) {
		if(structure == 0) {
			pillar[x][y] = false;
		} else if (structure == 1) {
			beams[x][y] = false;
		}
		
		for(int i = 0; i <= n; i++) {
			for(int j = 0; j <= n; j++) {
				if(pillar[i][j] && !ConstructPillar(i,j))
					return false;
				if(beams[i][j] && !ConstructBeams(i,j))
					return false;
			}
		}
		return true;
	}
    
	private static boolean ConstructPillar(int x, int y) {
		if(y == 0 || (x>= 1 &&beams[x-1][y]) || beams[x][y] || (y>= 1 && pillar[x][y-1]))
			return true;
		return false;
	}
	
	private static boolean ConstructBeams(int x, int y) {
		if((y>= 1 && pillar[x][y-1]) ||  (y>= 1 && x<n && pillar[x+1][y-1]) || (x>= 1 && beams[x-1][y] && x<n && beams[x+1][y]))
			return true;
		return false;
	}
}