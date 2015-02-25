import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public final class IOMatrix {
	
	private IOMatrix(){}
	
	public static Matrix readMatrix(String path) {
		
		Matrix matrix = null;
		
		try(DataInputStream dis = new DataInputStream(new FileInputStream(new File(path)))) {
			int row = dis.readInt();
			int col = dis.readInt();
			
			double[][] arrForMatrix = new double[row][col];
			
			for(int i = 0 ; i < row ; i++) {
				for(int j = 0 ; j < col ; j++) {
					arrForMatrix[i][j] = dis.readDouble();
				}
			}
			
			matrix = new Matrix(arrForMatrix);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		return matrix;
	}
	
	public static void writeMatrix(Matrix matrix, String path) {
		try(DataOutputStream dos = new DataOutputStream( new FileOutputStream(new File(path)))) {

			dos.writeInt(matrix.getRow());
			dos.writeInt(matrix.getCol());
			
			for(int i = 0 ; i < matrix.getRow() ; ++i) {
				for(int j = 0 ; j < matrix.getCol() ; ++j) {
					dos.writeDouble(matrix.getItem(i, j));
				}
			}
			
			
		} catch ( IOException e) {
			e.printStackTrace();
		}
		
	}
}
