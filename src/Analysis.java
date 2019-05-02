import java.util.ArrayList;
import java.util.TreeMap;

public class Analysis {

	public TreeMap<Integer, ArrayList<Double>> Temp = new TreeMap<Integer, ArrayList<Double>>();
	public final double outTemp = -15.0; // Outside temperature
	public final double inTemp = 88.0; // Inside temperature

	public void analyze(int iterate) {

		/* All constants during the program */
		final double kSteel = 16.3; // Conduction coefficient of the steel around the cup
		final double kWater = 0.597; // Conduction coefficient of the water in the cup
		final double kInsu = 0.034; // Conduction coefficient of the insulation
		final double spacing = 0.002; // Node spacing for the transient heat transfer
		final double timeStep = 0.25; // Time spacing for the transient equations
		final double rhoWater = 999.0; // Density of the water
		final double rhoSteel = 7900.0; // Density of the steel 304
		final double rhoInsu = 15.0; // Density of the insulation foam
		final double cpWater = 4181.6; // Specific heat of the water
		final double cpSteel = 500.0; // Specific heat of the steel 304
		final double cpInsu = 1300.0; // Specific heat of the insulation
		final double tau = 0.03575 * timeStep; // Tau value of the water

		/* Outside temperature variables */
		final double outConv = 24.0; // Outside convection heat transfer coefficient

		for (int i = 0; i < 17; ++i) { // Initialize each temperature as 5
			Temp.put(i, new ArrayList<Double>()); // Add each new ArrayList to the TreeMap Temp
			Temp.get(i).add(inTemp);
		}

		for (int i = 17; i < 21; ++i) {
			Temp.put(i, new ArrayList<Double>()); // Add the last node
			Temp.get(i).add(outTemp); // Add last node with independent starting temperature
		}

		for (int i = 0; i < iterate; ++i) { // Iterate through the time step this number of times: time = (i * timeStep)
			
			/* Get the last temperature from the previous iteration */
			double T1 = Temp.get(0).get(i);
			double T2 = Temp.get(1).get(i);
			double T3 = Temp.get(2).get(i);
			double T4 = Temp.get(3).get(i);
			double T5 = Temp.get(4).get(i);
			double T6 = Temp.get(5).get(i);
			double T7 = Temp.get(6).get(i);
			double T8 = Temp.get(7).get(i);
			double T9 = Temp.get(8).get(i);
			double T10 = Temp.get(9).get(i);
			double T11 = Temp.get(10).get(i);
			double T12 = Temp.get(11).get(i);
			double T13 = Temp.get(12).get(i);
			double T14 = Temp.get(13).get(i);
			double T15 = Temp.get(14).get(i);
			double T16 = Temp.get(15).get(i);
			double T17 = Temp.get(16).get(i);
			double T18 = Temp.get(17).get(i);
			double T19 = Temp.get(18).get(i);
			double T20 = Temp.get(19).get(i);
			double T21 = Temp.get(20).get(i);

			/* Calculate the new temperatures */
			Temp.get(0).add(tau * (2 * T2) + (1 - (2 * tau)) * T1);
			Temp.get(1).add(tau * (T1 + T3) + (1 - (2 * tau)) * T2);
			Temp.get(2).add(tau * (T2 + T4) + (1 - (2 * tau)) * T3);
			Temp.get(3).add(tau * (T3 + T5) + (1 - (2 * tau)) * T4);
			Temp.get(4).add(tau * (T4 + T6) + (1 - (2 * tau)) * T5);
			Temp.get(5).add(tau * (T5 + T7) + (1 - (2 * tau)) * T6);
			Temp.get(6).add(tau * (T6 + T8) + (1 - (2 * tau)) * T7);
			Temp.get(7).add(tau * (T7 + T9) + (1 - (2 * tau)) * T8);
			Temp.get(8).add(tau * (T8 + T10) + (1 - (2 * tau)) * T9);
			Temp.get(9).add(tau * (T9 + T11) + (1 - (2 * tau)) * T10);
			Temp.get(10).add(tau * (T10 + T12) + (1 - (2 * tau)) * T11);
			Temp.get(11).add(tau * (T11 + T13) + (1 - (2 * tau)) * T12);
			Temp.get(12).add(tau * (T12 + T14) + (1 - (2 * tau)) * T13);
			Temp.get(13).add(tau * (T13 + T15) + (1 - (2 * tau)) * T14);
			Temp.get(14).add(tau * (T14 + T16) + (1 - (2 * tau)) * T15);
			Temp.get(15).add(tau * (T15 + T17) + (1 - (2 * tau)) * T16);
			Temp.get(16).add(tau * (T16 + T18) + (1 - (2 * tau)) * T17);

			Temp.get(17).add(((timeStep * ((kSteel * ((T19 - T18) / spacing)) + (kWater * ((T17 - T18) / spacing)))
					/ ((rhoWater * cpWater * (spacing / 2)) + (rhoSteel * cpSteel * (spacing / 2))))) + T18);

			Temp.get(18).add(((timeStep * ((kInsu * ((T20 - T19) / spacing)) + (kSteel * ((T18 - T19) / spacing)))
					/ ((rhoInsu * cpInsu * (spacing / 2)) + (rhoSteel * cpSteel * (spacing / 2))))) + T19);

			Temp.get(19).add(((timeStep * ((kSteel * ((T21 - T20) / spacing)) + (kInsu * ((T19 - T20) / spacing)))
					/ ((rhoInsu * cpInsu * (spacing / 2)) + (rhoSteel * cpSteel * (spacing / 2))))) + T20);

			double conv = (outConv * (outTemp - T21));
			double cond3 = (kSteel * ((T20 - T21) / spacing));
			double denom3 = (rhoSteel * cpSteel * (spacing / 2));

			Temp.get(20).add((((timeStep * (conv + cond3)) / denom3)) + T21);
			
			if (checkSteadyState(i)) {
				break; // Stop the iterations once the system has reached steady state
			}

		}
	}

	/**
	 * Get the set of temperature values in a line
	 * 
	 * @param map, i
	 * @return new String with the values of the nodes at a certain time
	 */
	public String getArrayLine(int i, TreeMap<Integer, ArrayList<Double>> map) {

		/* New String to hold the temperature at a certain time */
		String tempLinear = "";

		for (int j = 0; j < 21; ++j) { // Iterate through the
			tempLinear += String.format("%.4f,", map.get(j).get(i)); // Add the temperatures to the new ArrayList
		}
		tempLinear += String.format("%d", i); // Add the iteration time to the back of the line
		return tempLinear; // Return the temperature nodes
	}

	/** This method will return to us on the console if the temperature within side the cup has reached 
	 *  steady state or not.
	 * 
	 * @param i is the number of iterations in the program
	 * @return boolean value if the system has reached steady state or not
	 */
	public boolean checkSteadyState(int i) {

		if ((Math.abs(Temp.get(0).get(i) - outTemp)) < 0.5) {
			System.out.println(String.format("After %d number of iterations, our system has reached SS.\n", i));
			return true; // Tell the program this state has been reached
		} else {
			return false; // If it is not steady state continue the program
		}

	}
}
