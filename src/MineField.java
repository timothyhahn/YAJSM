import java.util.Random;

public class MineField {
	public int boardHeight;
	public int boardWidth;
	public int mineCount;
	int mines[][];
    int minePos[][];
    
	public MineField() {
		//Default is "beginner" difficulty
		boardHeight = 9;
		boardWidth = 9;
		mineCount = 10;
		mines = new int[boardHeight][boardWidth];
		minePos = new int[mineCount][2];
        for(int i = 0; i < boardHeight; i++) {
            for(int j= 0 ; j < boardWidth; j++) {
                 mines[i][j] = 0;
            }
        }
	}
	public MineField(int height, int width, int numberOfMines) {
		boardHeight = height;
		boardWidth = width;
		mines = new int[boardHeight][boardWidth];
		minePos = new int[mineCount][2];
		mineCount = numberOfMines;
        for(int i = 0; i < boardHeight; i++) {
            for(int j= 0 ; j < boardWidth; j++) {
                 mines[i][j] = 0;
            }
        }
	}
	
	public void generateMines() {
		int until = mineCount;
        for(int i = 0; i < boardHeight; i++) {
            for(int j= 0 ; j < boardWidth; j++) {
                 mines[i][j] = 0;
            }
        }
        while(until > 0) {
            for(int i = 0; i < boardHeight; i++) {
                for(int j  = 0; j < boardWidth; j++) {
                    if(until > 0) {
                        final Random randomGenerator = new Random();
                        if(randomGenerator.nextInt(boardHeight * boardWidth) < (boardWidth / 2) && mines[i][j] != 9) {
                            mines[i][j]  = 9;
                            until--;
                            minePos[until][0] = i;
                            minePos[until][1] = j;
                        }
                    }
                }
            }
        }
	}
	public boolean[] checkSurrounding(int i, int j) {
		/** Returns an array of booleans that check if the surrounding buttons are safe
		*   The array is from 0 to 8, reading left to right, top to bottom
		*   For example, the array [1,0,1,1,1,0,0,1] would return:
		*   0 9 0
		*   0 X 0
		*   9 9 0
		*   (9 is mine, 0 is not mine, X is position being checked)
		**/
		boolean[] f = {false, false, false, false, false, false, false, false};
		
		//Get Row Above
			if(i > 0) { //Ensure we aren't on the top row
				if(j > 0)
					if(mines[i - 1][j - 1] != 9) {
						f[0] = true;
					}
				if(mines[i - 1][j] != 9) {
					f[1] = true;
				}
				if(j < (boardWidth - 1))
					if(mines[i - 1][j + 1] != 9) {
						f[2] = true;
					}
			}
		//Get Row On
			if(j > 0)
				if (mines[i][j-1] != 9) {
					f[3] = true;
				}
			if(j < (boardWidth - 1))
				if (mines[i][j+1] != 9) {
					f[4] = true;
				}
		//Get Row Below
			if(i < (boardHeight - 1)) { // Ensure we aren't the bottom row
				if(j > 0)
					if(mines[i+1][j-1] != 9){
						f[5] = true;
					}
				if(mines[i+1][j] != 9){
					f[6] = true;
				}
				if(j < (boardWidth - 1))
				if(mines[i+1][j+1] != 9){
					f[7] = true;
				}
			}
		return f;
	}
	public void generateNumbers() {
		int until = mineCount;
	       for(int i = 0;i < until; i++) {
	            //get row above
	    	   boolean f[] = checkSurrounding(minePos[i][0], minePos[i][1]);
	    	   if(f[0]) //NW
	    		   mines[minePos[i][0] - 1][minePos[i][1] - 1]++;
	    	   if(f[1]) //N
	    		   mines[minePos[i][0] - 1][minePos[i][1]]++;
	    	   if(f[2]) //NE
	    		   mines[minePos[i][0] - 1][minePos[i][1]  + 1]++;
	    	   if(f[3]) //W
	    		   mines[minePos[i][0]][minePos[i][1] - 1]++;
	    	   if(f[4]) //E
	    		   mines[minePos[i][0]][minePos[i][1] + 1]++;
	    	   if(f[5]) //SW
	    		   mines[minePos[i][0] + 1][minePos[i][1] - 1]++;
	    	   if(f[6]) //S
	    		   mines[minePos[i][0] + 1][minePos[i][1]]++;
	    	   if(f[7]) //SE
	    		   mines[minePos[i][0] + 1][minePos[i][1] + 1]++;
	    	   		
	        }
	}
	public void display() {
        //Distance to mines:

        System.out.println("Board generated: ");

        for(int i = 0; i < boardHeight; i++) {
            for(int j = 0; j < boardWidth; j++) {
                System.out.print("|" + mines[i][j]);
            }
            System.out.println("");
        }
	}
}