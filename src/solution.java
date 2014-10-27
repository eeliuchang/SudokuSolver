import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class solution {
	public static void main(String[] args) throws IOException{
		System.out.println("test");
		BufferedReader CSVFile = new BufferedReader(new FileReader("mat.csv"));
		
		//read in CSV file into a matrix
		int[][] matrix = new int[9][9];
		int i=0;
				while (i < 9){
					String	dataRow = CSVFile.readLine();
					int j = 0;		
					while (j < 9 ){
						String s = "";
						s = "" + dataRow.charAt(2*j);
						matrix[i][j] = Integer.parseInt(s);
						//System.out.print(matrix[i][j]); //print out the matrix
						j = j+1;			
					}			
					//System.out.println("");
				i++;					
		}
				
		// Now we fill in the zero entries, we will write a method to achieve this
		// if there is a solution, then we print out the matrix, otherwise return "Sorry, I can't isSolved it"
		
		if (isSolved(matrix, 0, 0)) {
				System.out.println("The solution is:");
			for (int m=0; m<9; m++){
				for(int k=0; k<8; k++){
					System.out.print(matrix[m][k] + ",");
				}
				System.out.println(matrix[m][8]);
			}
		}
		else
			System.out.println("Sorry, I can't isSolved it!");
		
	} 
	
	//This is a method to check if we have found a good solution
	public static boolean isSolved(int[][] matrix, int i, int j) {

		
		//If the current column is filled, we reset and move to the next column
		if ( i > 8 ){
			if (j == 8)
				return true;
			else {
				i = 0;
				j = j+1;
			}
		}
		
		// If the spot was filled, we skip it and move to the next row.
		if (matrix[i][j] != 0)  
			return isSolved(matrix, i+1,j);
	
		//If the spot needs to be filled, we try every number 1-9 and check if it is OK to put in there
		for (int testInt = 1; testInt <= 9; testInt++) {
			if (isOK(matrix, testInt, i, j)) {
				matrix[i][j] = testInt;
				if (isSolved(matrix, i+1, j))
					return true;
			}
		}
		//When the program can not proceeds at a certain spot, it will come to this point
		// This ensures that in that particular trial loop, all the filled spots reset to zero.
		matrix[i][j] = 0; 
		return false;

	}
	
	//This is a function to check if <testInt> has been used in row/col/box
    public static boolean isOK(int[][] matrix,  int testInt, int i, int j) {
       //check if <testInt> is equal to any number in that row
    	for (int x = 0; x < 9; x++) 
            if (testInt == matrix[x][j])
                return false;
    	//check if <testInt> is equal to any number in that column
        for (int y = 0; y < 9; y++) 
            if (testInt == matrix[i][y])
                return false;
        //check if <testInt> is in that 3*3 box
        int initRowIndex = (i / 3)*3;
        int initColIndex = (j / 3)*3;
        int p=0;
        int q=0;        
        	while(p < 3){
        		while(q < 3){
        			if (testInt == matrix[initRowIndex + p][initColIndex + q])
                        return false;
        			q++;
        		}
        		p++;
        	}
                

        return true; // no violations, so it's isOK
    }
	
	
	
			
	

}
