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
		List< List<Integer> > currentRowSet = new ArrayList< List<Integer> >();
		
//		SRGsolver cycleGraph = new SRGsolver(5, 2, 0, 1);
//		currentRowSet.add(Arrays.asList(0, 1, 0, 0, 1));
//		cycleGraph.buildRowList2(currentRowSet);
		
//		SRGsolver petersonGraph = new SRGsolver(10, 3, 0, 1);
//		currentRowSet.add(Arrays.asList(0, 1, 0, 0, 1, 1, 0, 0, 0, 0));
//		petersonGraph.buildRowList2(currentRowSet);
		
		SRGsolver clebschGraph = new SRGsolver(16, 5, 0, 2);
		currentRowSet.add(Arrays.asList(0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0));
		clebschGraph.buildRowList2(currentRowSet);
		
	}//end main

}
