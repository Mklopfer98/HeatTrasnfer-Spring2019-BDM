
import java.util.ArrayList;
import java.util.TreeMap;



public class Driver {
	
	public static void main(String[] args) {
		         
		TreeMap<Integer, ArrayList<Double>> Temp = new TreeMap<Integer, ArrayList<Double>>();
		
		/* All constants during the program */
		final int ambT = 25;  // Ambient temperature
		final int h_i = 6;  // Inside convection heat transfer coefficient
		final int h_o = 9;  // Outside convection heat transfer coefficient
		final double delta_x = 0.01;  // Node spacing
		final int delta_t = 1;  // time step=1s
		final double alpha = 0.36e-6;  // Thermal diffusion
		final double rho_air = 1.28;  // Density of the air
		final double cp_air = 1.006;  // Specific heat of the air
		final double cp_food = 3.6;  // Specific heat of the food
		final double k = 0.026;  // Wall conduction coefficient
		final double tau = alpha*delta_t/((delta_x)*(delta_x));  // Time step method
		
		for (int i = 0; i < 5; ++i) {  // Initialize each temperature as 3
			Temp.put(i, new ArrayList<Double>());  // Add each new ArrayList to the TreeMap Temp
			Temp.get(i).add(3.0);
		}
		
		for (int i = 0; i < 21600; ++i) {  // iterated for 6 hrs, which is 21600s
			
			/* Get the last temperature from the previous iteration */
			double T1 = Temp.get(0).get(i);
			double T2 = Temp.get(1).get(i);
			double T3 = Temp.get(2).get(i);
			double T4 = Temp.get(3).get(i);
			double T5 = Temp.get(4).get(i);
			
			/* Calculate the new temperatures */
			double node1 = (h_o*(ambT - T1)+(k*(T2-T1)/delta_x))*2*(delta_t/(1000*rho_air*delta_x*cp_air))+T1;
			double node2 = tau*(T1+T3)+(1-2*tau)*T2;
			double node3 = tau*(T2+T4)+(1-2*tau)*T3;
			double node4 = (h_i*(T5-T4)+(k*(T3-T4)/delta_x))*2*(delta_t/(1000*rho_air*delta_x*cp_air))+T4;
			double node5 = (((h_i*5.6135*(T4-T5)*delta_t)/1000)/(1.17*cp_air+10*cp_food))+T5;
			
			/* Access TreeMap to get the node ArrayList and add in the new temperature value */
			Temp.get(0).add(node1);
			Temp.get(1).add(node2);
			Temp.get(2).add(node3);
			Temp.get(3).add(node4);
			Temp.get(4).add(node5);
		
		} 
		
		int i = 21599; // Set position in matrix to print out temperature
		/* Iterate through last matrix entry and get the last temperature value */
		for (int j = 0; j < 5; ++j) {
			System.out.println(String.format("%.3f", Temp.get(j).get(i)));
		}		
	}
}