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
		
		SRGsolver SRGsolver = new SRGsolver(10, 3, 0, 2);
		currentRowSet.add(Arrays.asList(0, 1, 0, 0, 1, 1, 0, 0, 0, 0));

		SRGsolver.buildRowList2(currentRowSet);
		
	}//end main

}
