import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import exception.MatrixMultiplicationException;


public class Main {

	public static Matrix resultFromFile ;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		String sep = File.separator;
		
		
		String path = "D:"+ sep +"SU-2014-2015-4god"+ sep +"JavaSuvremJavaKOmp"+ sep +"Assignments"+ sep 
				+ "Assignments"+ sep +"1. Matrix multiplication"+ sep +"Assignment"+ sep +"TestData"+ sep +"Ex2";
		
		Matrix leftMatrix = IOMatrix.readMatrix(path + sep + "left");
		Matrix rightMatrix = IOMatrix.readMatrix(path + sep + "right");
		
		resultFromFile = IOMatrix.readMatrix(path + sep + "result");


		
		long startTime = System.currentTimeMillis();
		
		StringBuilder sb = new StringBuilder();
//		
		sb.append("линейната версия на алгоритъма: " + timeMsLinear(leftMatrix, rightMatrix, 1) + "ms.\n");
		long T = timeMsLinear(leftMatrix, rightMatrix, 10)/10;
		sb.append("T = " + T + "ms.\n");
		sb.append("\nпаралелната версия на алгоритъма:\n");
//		
//		
		
//		
		int k = 2*2*4;
		long []t = new long[k];
		double []a = new double[k];
		
		for(int i = 1 ; i <= k ; i++) {
			t[i-1] =  timeMsParallel(leftMatrix, rightMatrix, i , 10)/10;
			
			sb.append(" t("+i+")="+ t[i-1] + "ms.\n");
			
			a[i-1] = (double) T/t[i-1];
			
			sb.append(" a("+i+") = "+ String.format("%.4f",a[i-1]) + "ms.\n");
			
		}
		
		System.err.println(sb);
		
		
		long endTime = System.currentTimeMillis();
//		
		System.out.println(" " + (endTime-startTime) + "ms.");
		
//		System.out.println(timeMsParallel(leftMatrix, rightMatrix, 8, 1));
	
		
		
		/*
		                                            68047ms.
		линейната версия на алгоритъма: 1127ms.
		T = 1086ms.
		
		паралелната версия на алгоритъма:
		 t(1)=1086ms.
		 a(1) = 1,0000ms.
		 t(2)=567ms.
		 a(2) = 1,0000ms.
		 t(3)=398ms.
		 a(3) = 2,0000ms.
		 t(4)=324ms.
		 a(4) = 3,0000ms.
		 t(5)=321ms.
		 a(5) = 3,0000ms.
		 t(6)=291ms.
		 a(6) = 3,0000ms.
		 t(7)=259ms.
		 a(7) = 4,0000ms.
		 t(8)=246ms.
		 a(8) = 4,0000ms.
		 t(9)=264ms.
		 a(9) = 4,0000ms.
		 t(10)=263ms.
		 a(10) = 4,0000ms.
		 t(11)=263ms.
		 a(11) = 4,0000ms.
		 t(12)=267ms.
		 a(12) = 4,0000ms.
		 t(13)=267ms.
		 a(13) = 4,0000ms.
		 t(14)=263ms.
		 a(14) = 4,0000ms.
		 t(15)=258ms.
		 a(15) = 4,0000ms.
		 t(16)=259ms.
		 a(16) = 4,0000ms.
		 */
		
	}
	
	public static long timeMsLinear(Matrix leftMatrix, Matrix rightMatrix, int num ) {
		long returnValue = 0;
		
		try {
			long startTime = System.currentTimeMillis();
			
			for(int i = 0; i < num; ++i) {
				Matrix w = MatrixMultiplication.multiplication(leftMatrix, rightMatrix);
//				System.out.println(resultFromFile.equals( w ));
				
			}
				
			long endTime = System.currentTimeMillis();
				
			returnValue = endTime - startTime;
			
		} catch (MatrixMultiplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return returnValue;
		
	}
	
	public static long timeMsParallel(Matrix leftMatrix, Matrix rightMatrix , int numOfThreads, int num) {
		long returnValue = 0;
		
		try {
			long startTime = System.currentTimeMillis();
			
			for (int i = 0; i < num; i++) {
				
				Matrix w = MatrixMultiplication.multiplicationWithThreads(leftMatrix, rightMatrix, numOfThreads);
//				System.out.println(resultFromFile.equals( w ));
				
				
			}
			
			
			long endTime = System.currentTimeMillis();
			
			returnValue = endTime - startTime;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return returnValue;
		
	}
	
	
	
}
