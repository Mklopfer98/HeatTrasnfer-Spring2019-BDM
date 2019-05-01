
import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Driver {

	public static final String fileName = "Data.csv"; // File name we will be writing to

	/**
	 * This is the driver of the program. All of the operations carried out will be
	 * in this file. The operations can be traced back to other classes as well.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in); // Create a scanner to read the values
		System.out.println("How many iterations would you like to do?"); // Ask the user
		int iterate = scan.nextInt(); // Read user chosen value for the iteration

		scan.close(); // Close the scanner
		Analysis Data = new Analysis(); // Set up new Analysis object
		Data.analyze(iterate); // Calculate the results

		try {
			
			File file = new File(fileName); // New file object Java will write to
			BufferedWriter bw = new BufferedWriter(new FileWriter(file)); // Open up the file to write to

			int i = 0; // i value
			while (i < iterate) {
				
				String line = Data.getArrayLine(i, Data.Temp);
				bw.write(line);
				bw.newLine();
				i += 10;
			}
			Desktop.getDesktop().open(file); // Open the file
			bw.close(); // Close the writer

		} catch (Exception e) { // Catch the error thrown by the program

			e.printStackTrace();
			System.out.println(e.getMessage());
		}

	}
}