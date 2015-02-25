import java.util.Arrays;

import exception.MatrixMultiplicationException;


public class Matrix {
	
	private double[][] values;
	
	
	
	
	
	public Matrix(){}
	
	public Matrix(double[][] values) {
		if(values != null)
			this.values = values;
	}
	
	public Matrix(Matrix matrix) {
		if(matrix != null && matrix.getValues() != null)
			this.values = matrix.getValues();
	}
	
	
	
		
	public int getRow() {
		return values.length;
	}
	public int getCol() {
		return values[0].length;
	}
	
	public double[][] getValues() {
		return values;
	}
	public void setValues(double[][] values) {
		this.values = values;
	}
	public double getItem(int row, int col){
		return values[row][col];
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(values);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		int count = 0;
		
		Matrix other = (Matrix) obj;
//		if (!Arrays.deepEquals(values, other.values))
//			return false;
		
		for(int i = 0 ; i < this.values.length ; i++) {
			for(int j = 0 ; j < this.values[0].length; ++j) {
				if(this.values[i][j] != other.getItem(i, j)) {
					System.err.println(" " + i + " " + j);
					return false;
				}
			}
		}
		
		return true;
	}

	@Override
	public String toString() {
		
		return "Matrix [values=" + Arrays.toString(values) + "]";
	}
	
	
}
