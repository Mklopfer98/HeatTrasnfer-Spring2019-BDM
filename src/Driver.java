
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;

public class Driver {

	public final String fileName = "Data.csv"; // File name we will be writing to

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

			BufferedWriter bw = new BufferedWriter(new FileWriter("src/Data.csv"));

			int i = 0; // i value
			while (i < iterate) {
				
				String line = Data.getArrayLine(i, Data.Temp);
				bw.write(line);
				bw.newLine();
				i += 240;
			}
			bw.close();

		} catch (Exception e) { // Catch the error thrown by the program

			e.printStackTrace();
			System.out.println(e.getMessage());
		}

	}
}