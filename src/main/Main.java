package main;

import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class Main {
 
public static void main(String[] args) throws FileNotFoundException{	
double[] przedzial = {1, 7};	
//Podanie wartości x-ów	
double[] x = {2, 3, 5, 6};
int count = 0;
//Sprawdzanie wielkości macierzy
int dlugosc = x.length;
double[] y = new double[dlugosc];
double[][] matrix = new double[dlugosc][dlugosc];
double outcome[] = new double[dlugosc];
double tmp = 0;
double variable = 0;
String tmp1;
String funkcja2="";

	//Podanie wartości funkcji f(x)
	String funkcja1 = "x+x^2";


	//Obliczanie y poprzez funkcje f(x)
	for(int i = 0 ; i < dlugosc ; i++){
		y[i]=Math.cos(x[i]);
	}

	
	//Tworzenie macierzy
	for(int i = 0 ; i < dlugosc ; i++){
		matrix[i][0]=1;
	}
	for(int i = 0 ; i < dlugosc ; i++){
		for(int j = 1 ; j < dlugosc ; j++){
		matrix[i][j]=Math.pow(x[count],j);
		
		}
		count++;
	}

	//Odwrocenie macierzy
	double matrixrevers[][] = invertMatrix(matrix);	
	
	//Mnożenie macierzy
	for(int i = 0 ; i < dlugosc ; i++){
		for(int j = 0 ; j < dlugosc ; j++){
			tmp = matrixrevers[i][j]*y[j];
			variable = variable + tmp;
		}
	outcome[i] = variable;
	variable = 0;		
	}

	//Utworzenie wielomianu interpolujacego
	for(int j = 0 ; j < dlugosc ; j++){	
	
		if(j==(dlugosc-1)){
			tmp1 = "x^"+j+"*"+outcome[j];
			funkcja2+=tmp1;
		}else{
			tmp1 = "x^"+j+"*"+outcome[j]+"+";
			funkcja2+=tmp1;	
		}
	
	}

	//Wypisanie wilomianu interpolujacego	
	System.out.println(funkcja2);


	//Stworzenie skryptu R
	PrintWriter zapis = new PrintWriter("plik.R");
	zapis.println("fun1<-function(x) "+ funkcja1);
	zapis.println("fun2<-function(x) "+ funkcja2);
	zapis.println("x<-seq("+przedzial[0]+", "+przedzial[1]+", by = 0.0001)");
	zapis.println("matplot(x,cbind(fun1(x),fun2(x)), ylim=c(-20, 20), type=\"l\") ; abline(h=0)");
	zapis.close();
}



public static double [][] invertMatrix(double a[][]) {
	int i,j,k,m; 
	double temp; 
	int numRows = a.length; 
	int numCols = a[0].length; 
	int index[][] = new int[numRows][2];  

	partialPivot(a, new double[numRows], index); 
	
	//Dzielenie obecnego wersu przez a[i][i]
	for(i=0; i<numRows; ++i) {
		
		temp = a[i][i]; 
		for(j=0; j<numCols; ++j) {
			a[i][j] /= temp; 
			} 
		a[i][i] = 1.0/temp;
		//Redukcja pozostalych wersow poprzez odejmowanie pomnozonej wartosci obecnej lini
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
	//Odwracanie kolumn w macierzy na podstawie wartosci index
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
		  
	return a; 
	}



private static void partialPivot( double a[][], double b[], int index[][]) {
	double temp;
	double tempRow[];
	int i,j,m;
	int numRows = a.length; 
	int numCols = a[0].length; 
	double scale[] = new double[numRows];  
	//Znalezienie najwiekszego elementu w wierszu
	//Inicjac index[][] zapamietującego układ macierzy
	for(i=0; i<numRows; ++i) { 
		index[i][0] = i;
		index[i][1] = i; 
		for(j=0; j<numCols; ++j) { 
			scale[i] = Math.max(scale[i],Math.abs(a[i][j])); 
			} 
		}
	//Znajdowanie elementu osiowego dla kazdej kolumny
	//Zamian wierszy w macierzy 
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