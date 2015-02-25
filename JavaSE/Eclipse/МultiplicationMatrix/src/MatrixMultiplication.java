import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import exception.MatrixMultiplicationException;


public abstract class MatrixMultiplication {
	
	public static Matrix multiplication(Matrix leftMatrix, Matrix rightMatrix ) throws MatrixMultiplicationException {
		
		if( checkMatrixForMultiplication( leftMatrix, rightMatrix) ) {
			double[][] result = new double[leftMatrix.getRow()][rightMatrix.getCol()];
			
			
			for(int i = 0 ; i < leftMatrix.getRow() ; i++) {
				
				double[] row = calcolateRowForMultiplication(leftMatrix, rightMatrix, i);
				if(row != null) {
					result[i] = row;
				}
			}
			
			return new Matrix(result);
		} 
		
		
		return null;
	}
	
	public static Matrix multiplicationWithThreads(Matrix leftMatrix, Matrix rightMatrix, int numOfThreads) throws MatrixMultiplicationException{
		if(leftMatrix != null && rightMatrix != null) {
			if( checkMatrixForMultiplication( leftMatrix, rightMatrix) ) {
				
				Thread []threads;
				
				if(numOfThreads <= leftMatrix.getRow()) {
					threads = new Thread[numOfThreads];
				} else {
					threads = new Thread[leftMatrix.getRow()];
				}
				
				double[][] result = new double[leftMatrix.getRow()][rightMatrix.getCol()];
				
			    
				for(int index = 0 ; index < threads.length ; index++) {
					final int i = index;
					
					threads[index] = new Thread(){
						@Override
						public void run() {
							for(int j = i ; j < leftMatrix.getRow() ; j += threads.length  ) {
								
								double[] row = calcolateRowForMultiplication(leftMatrix, rightMatrix, j);
								if(row != null)
									result[j] = row;
								
							}
						}
					};
					
					threads[index].start();
				}
				
				for(int i = 0 ; i < threads.length ; ++i) {
					try {
						threads[i].join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				return new Matrix(result);
			}
		}
			
		return null;
	}
	
	

	

	
	private static double[] calcolateRowForMultiplication( Matrix leftMatrix, Matrix rightMatrix, int row ) {

		double[] result = new double[rightMatrix.getCol()];
		
		for(int j = 0 ; j < leftMatrix.getCol(); ++j) {
			for(int k = 0 ; k < rightMatrix.getCol(); ++k ) {
					result[k] += leftMatrix.getValues()[row][j] * rightMatrix.getValues()[j][k]; 
				}
			}
		return result ;
	}
	
//	with getItem row col .... 
//	
//	private static double[] calcolateRowForMultiplication( Matrix leftMatrix, Matrix rightMatrix, int row ) {
//	
//		double[] result = new double[rightMatrix.getCol()];
//		
//		for(int j = 0 ; j < leftMatrix.getCol(); ++j) { //in left on right
//			for(int k = 0 ; k < rightMatrix.getCol(); ++k ) {
//				result[k] += leftMatrix.getItem(row,j) * rightMatrix.getItem(j, k); 
//			}
//		}
//		return result ;
//	}
	
	
	private static boolean checkMatrixForMultiplication(Matrix leftMatrix, Matrix rightMatrix) throws MatrixMultiplicationException {
		if(leftMatrix.getCol() == rightMatrix.getRow()) {
			return true;
		} else {
			throw new MatrixMultiplicationException();
		}
	}
	
}
