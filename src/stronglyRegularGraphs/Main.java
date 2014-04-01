package stronglyRegularGraphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The purpose of this class is to serve as the entry point for the SRGsolver application.
 * 
 * @author Tyler Wilcock
 */

public class Main 
{

	public static void main(String[] args) 
	{
		List< List<Integer> > currentRowSet = new ArrayList<>();
		
		SRGsolver SRGsolver = new SRGsolver(6, 3, 0, 1);
		currentRowSet.add(Arrays.asList(1, 0, 1, 1, 0, 0));
		currentRowSet.add(Arrays.asList(0, 0, 1, 1, 1, 0));
		currentRowSet.add(Arrays.asList(1, 1, 0, 0, 0, 1));
		SRGsolver.dotProduct(currentRowSet);
		
		FileHandler fileHandler = new FileHandler();
		
		fileHandler.setFileName("SRGsolver.txt");
		fileHandler.write("OUTPUT MATRIX FROM SRGsolver.java: ");
		fileHandler.writeln();
		fileHandler.write("----------------------------------------------------------------------------");
		fileHandler.writeln(2);
		fileHandler.write2DList(currentRowSet);
	}//end main

}
