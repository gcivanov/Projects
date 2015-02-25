package exception;

public class MatrixMultiplicationException extends Exception {
	
	public MatrixMultiplicationException() {
		super();
		System.err.println("MatrixMultiplicationException check rows and columns");
		
	}

    public MatrixMultiplicationException(String message)
    {
       super(message);
       System.err.println("MatrixMultiplicationException check rows and columns");
    }
	
	
}
