/*
* Mountain Paths Greedy Algorithm
* Math 140 - Data Structures
* Spring 2018
*/
public class ElevationArray {
	
	private static int[][] grid = new int[][] {
		{ 3011, 2800, 2852, 2808, 2791, 2818 },
		{ 2972, 2937, 2886, 2860, 2830, 2748 },
		{ 2937, 2959, 2913, 2864, 2791, 2742 },
		{ 2999, 2888, 2986, 2910, 2821, 2754 },
		{ 2909, 3816, 2893, 2997, 2962, 2798 } };
		
	public static void main(String[] args) {
		
		//int currentRow = 2;
		//int smallest = grid[currentRow][0];
		  int r, c, max,min;
		  c = 1;
		  
		//System.out.println("[" + currentRow + "]" +"[0]"); 
	//	System.out.println("Num elements in col " + c + " is ..." +  grid[0].length );
		//System.out.println("Minimum in row " + r + " is ..." );
		
		int searchCol = 3;

		min=max = grid[0][searchCol];
	//	System.out.println("Num elements in col " + c + " is ..." +  grid[0].length );

		for (r = 0; r < grid.length; r++) {
			  if (grid[r][searchCol] > max ) max = grid[r][searchCol];
			  if (grid[r][searchCol] < min ) min = grid[r][searchCol];
				  // System.out.printf("a[%d][%d] is: %d \n",r,c, a[r][c] );
		}
	//	System.out.println("Maximum in col " + searchCol + " is ..." + max );
	//	System.out.println("Minimum in col " + searchCol + " is ..." + min );
		
	//	indexOfMinInCol(searchCol);
		indexOfMinInCol(searchCol);

	
	}
	  public static int indexOfMinInCol(int searchCol){
			  int r, c, max,min, locationFoundMin=0, locationFoundMax=0;
			  
			//System.out.println("[" + currentRow + "]" +"[0]"); 
		//	System.out.println("Num elements in col " + c + " is ..." +  grid[0].length );
			//System.out.println("Minimum in row " + r + " is ..." );
			
		
			c = searchCol;// = 5;
			min=max = grid[0][searchCol];
			System.out.println("Num elements in col " + searchCol + " is ..." +  grid.length );

			for (r = 0; r < grid.length; r++) {
				  if (grid[r][searchCol] > max ) {
					  max = grid[r][searchCol]; 
					  locationFoundMax = r;
				  }
				  if (grid[r][searchCol] < min ) {
					  min = grid[r][searchCol];
					  locationFoundMin = r;
				  }
					  // System.out.printf("a[%d][%d] is: %d \n",r,c, a[r][c] );
			}
			System.out.println("Minimum in col " + searchCol + " is  at " + locationFoundMin + "," + searchCol);
			System.out.println("Maximum in col " + searchCol + " is  at " + locationFoundMax + "," + searchCol);
		  
	      return locationFoundMin;
	  }
}