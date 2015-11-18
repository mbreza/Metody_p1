package main;



public class Main {
 
public static void main(String[] args){	

double[] x = {1, 3};

int count = 0;

int dlugosc = x.length;

double[] y = new double[dlugosc];

double[][] matrix = new double[dlugosc][dlugosc];



for(int i = 0 ; i < dlugosc ; i++){

	y[i]=Math.cos(x[i]);
	System.out.print(y[i]+",   ");
}

System.out.println();
System.out.println("");

for(int i = 0 ; i < dlugosc ; i++){
	matrix[i][0]=1;
}

for(int i = 0 ; i < dlugosc ; i++){
	for(int j = 1 ; j < dlugosc ; j++){
		matrix[i][j]=Math.pow(x[count],j);
		
	}
	count++;

}

double det = determinant(matrix);


if(det==0){
	System.out.println("Wyznacznik 0.");
}else{
	





for(int i = 0 ; i < dlugosc ; i++){
	for(int j = 0 ; j < dlugosc ; j++){

		System.out.print(matrix[i][j]+", ");
		
	}

	System.out.println(" ");
}

double matrixrevers[][] = invert(matrix);

System.out.println(" ");
System.out.println(" ");
System.out.println(" ");

for(int i = 0 ; i < dlugosc ; i++){
	for(int j = 0 ; j < dlugosc ; j++){

	System.out.print(matrixrevers[i][j]+", ");
		
	}

	System.out.println(" ");
}

System.out.println(" ");
System.out.println(" ");
System.out.println(" ");

double outcome[] = new double[dlugosc];

double tmp = 0;
double variable = 0;



for(int i = 0 ; i < dlugosc ; i++){
	for(int j = 0 ; j < dlugosc ; j++){

			tmp = matrixrevers[i][j]*y[j];
			variable = variable + tmp;
		}
	outcome[i] = variable;
	System.out.print("variable="+i+":"+variable);
	variable = 0;
	
	System.out.println(" ");
}

System.out.println("///////////////////////");
for(int j = 0 ; j < dlugosc ; j++){

System.out.print(outcome[j]+", ");

}

}


}

public static double determinant(double[][] matrix){ 
    int sum=0; 
    int s;
    if(matrix.length==1){  
      return(matrix[0][0]);
    }
    for(int i=0;i<matrix.length;i++){ 
      double[][]smaller= new double[matrix.length-1][matrix.length-1]; 
      for(int a=1;a<matrix.length;a++){
        for(int b=0;b<matrix.length;b++){
          if(b<i){
            smaller[a-1][b]=matrix[a][b];
          }
          else if(b>i){
            smaller[a-1][b-1]=matrix[a][b];
          }
        }
      }
      if(i%2==0){ 
        s=1;
      }
      else{
        s=-1;
      }
      sum+=s*matrix[0][i]*(determinant(smaller));
    }
    return(sum);
  }


public static double[][] invert(double a[][]) 
{
    int n = a.length;
    double x[][] = new double[n][n];
    double b[][] = new double[n][n];
    int index[] = new int[n];
    for (int i=0; i<n; ++i) 
        b[i][i] = 1;


    gaussian(a, index);

    for (int i=0; i<n-1; ++i)
        for (int j=i+1; j<n; ++j)
            for (int k=0; k<n; ++k)
                b[index[j]][k]
                	    -= a[index[j]][i]*b[index[i]][k];


    for (int i=0; i<n; ++i) 
    {
        x[n-1][i] = b[index[n-1]][i]/a[index[n-1]][n-1];
        for (int j=n-2; j>=0; --j) 
        {
            x[j][i] = b[index[j]][i];
            for (int k=j+1; k<n; ++k) 
            {
                x[j][i] -= a[index[j]][k]*x[k][i];
            }
            x[j][i] /= a[index[j]][j];
        }
    }
    return x;
}



public static void gaussian(double a[][], int index[]) 
{
    int n = index.length;
    double c[] = new double[n];


    for (int i=0; i<n; ++i) 
        index[i] = i;


    for (int i=0; i<n; ++i) 
    {
        double c1 = 0;
        for (int j=0; j<n; ++j) 
        {
            double c0 = Math.abs(a[i][j]);
            if (c0 > c1) c1 = c0;
        }
        c[i] = c1;
    }


    int k = 0;
    for (int j=0; j<n-1; ++j) 
    {
        double pi1 = 0;
        for (int i=j; i<n; ++i) 
        {
            double pi0 = Math.abs(a[index[i]][j]);
            pi0 /= c[index[i]];
            if (pi0 > pi1) 
            {
                pi1 = pi0;
                k = i;
            }
        }


        int itmp = index[j];
        index[j] = index[k];
        index[k] = itmp;
        for (int i=j+1; i<n; ++i) 	
        {
            double pj = a[index[i]][j]/a[index[j]][j];


            a[index[i]][j] = pj;

            for (int l=j+1; l<n; ++l)
                a[index[i]][l] -= pj*a[index[j]][l];
        }
    }
}

} 
 




 