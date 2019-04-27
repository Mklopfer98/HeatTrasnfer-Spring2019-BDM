import org.apache.commons.math3.linear.*;

public class Matrix {

	/** Method will print out the contents of the Matrix that it is given
	 * 
	 * @param m is the RealMatrix object that is passed to the program
	 */
	public static void printMatrix(RealMatrix m) {
		
		System.out.println(m.getClass().getTypeName());
		
		for (int i = 0; m.getRowDimension() > i; ++i) {
			System.out.print("|\t");
			
			for (int j = 0; m.getColumnDimension() > j; ++j) {
				System.out.print(String .format("%.3f" + "\t\t", m.getEntry(i, j)));
			}
			System.out.println("|");
			System.out.println();
		}
	}
}
