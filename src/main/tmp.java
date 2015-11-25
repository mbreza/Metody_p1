package main;

public class tmp {

	public static void invertMatrix(double a[][]) {
		int i,j,k,m; 
		double temp; 
		int numRows = a.length; 
		int numCols = a[0].length; 
		int index[][] = new int[numRows][2];  
		// Perform an implicit partial pivoting of the 
		// a[][] array. We will provide a dummy b array 
		// to the partialPivot() method.
		
		partialPivot(a, new double[numRows], index); 
		
		// Perform the elimination row by row. First dividing 
		// the current row by a[i][i]  
		for(i=0; i<numRows; ++i) { 
			temp = a[i][i]; 
			for(j=0; j<numCols; ++j) {
				a[i][j] /= temp; 
				} 
			a[i][i] = 1.0/temp; 
			// Reduce the other rows by subtracting a multiple 
			// of the current row from them. Don't reduce the 
			// current row. As each column of the a[][] matrix 
			// is reduced its elements are replaced with the 
			// inverse a[][] matrix.  
			for(k=0; k<numRows; ++k) { 
				if (k != i) { 
					temp = a[k][i];
					for(j=0; j<numCols; ++j) { 
						a[k][j] -= temp*a[i][j]; 
						} 
					a[k][i] = -temp*a[i][i];
					} 
				} 
			}  
		// Unscramble the inverse a[][] matrix. 
		// The columns are swapped in the opposite order 
		// that the rows were during the pivoting.  
		for(j=numCols-1; j>=0; --j) { 
			k = index[j][0]; 
			m = index[j][1]; 
			if (k != m) { 
				for(i=0; i<numRows; ++i) { 
					temp = a[i][m];
					a[i][m] = a[i][k];
					a[i][k] = temp;
} 
				} 
			}  
		return; 
		}
	
	
	
	private static void partialPivot( double a[][], double b[], int index[][]) {
		double temp;
		double tempRow[];
		int i,j,m;
		int numRows = a.length; 
		int numCols = a[0].length; 
		double scale[] = new double[numRows];  
		// Determine the scale factor (the largest element) 
		// for each row to use with implicit pivoting. 
		// Initialize the index[][] array for an unmodified 
		// array.  
		for(i=0; i<numRows; ++i) { 
			index[i][0] = i;
			index[i][1] = i; 
			for(j=0; j<numCols; ++j) { 
				scale[i] = Math.max(scale[i],Math.abs(a[i][j])); 
				} 
			}  
		// Determine the pivot element for each column and 
		// rearrange the rows accordingly. The m variable 
		// stores the row number that has the maximum 
		// scaled value below the diagonal for each column. 
		// The index[][] array stores the history of the row 
		// swaps and is used by the Gauss-Jordan method to 
		// unscramble the inverse a[][] matrix  
		for(j=0; j<numCols-1; ++j) {
			m=j; 
			for(i=j+1; i<numRows; ++i) {
				if ( Math.abs(a[i][j])/scale[i] > Math.abs(a[m][j])/scale[m] ){
					m=i; 
					} 
				} if ( m != j ) {
					index[j][0] = j;
					index[j][1] = m;  
					tempRow = a[j];
					a[j] = a[m]; 
					a[m] = tempRow;  
					temp = b[j]; 
					b[j] = b[m]; 
					b[m] = temp;  
					temp = scale[j]; 
					scale[j] = scale[m]; 
					scale[m] = temp; 
					}
				} 
		return; 
		}
	}
		
