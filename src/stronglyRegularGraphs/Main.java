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
/*
 *  
 * 		TRIANGLE-FREE
 * 		
 */
//		CYCLE GRAPH	- 5 vertices	
//		SRGsolver cycleGraph = new SRGsolver("(5)CycleGraph.txt", 5, 2, 0, 1);
//		currentRowSet.add(Arrays.asList(0, 1, 0, 0, 1));
//		cycleGraph.buildRowListWhileLoop(currentRowSet);
		
//		PETERSON GRAPH - 10 vertices
//		SRGsolver petersonGraph = new SRGsolver("(10)PetersonGraph.txt", 10, 3, 0, 1);
//		currentRowSet.add(Arrays.asList(0, 1, 0, 0, 1, 1, 0, 0, 0, 0));
//		petersonGraph.buildRowListWhileLoop(currentRowSet);

//		CLEBSCH GRAPH - 16 vertices
//		SRGsolver clebschGraph = new SRGsolver("(16)ClebschGraph", 16, 5, 0, 2);
//		currentRowSet.add(Arrays.asList(0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0));
//		clebschGraph.buildRowListWhileLoop(currentRowSet);
		
//		HUFFMAN-SINGLETON GRAPH	- 50 vertices	
//		SRGsolver huffmanSingletonGraph = new SRGsolver("(50)HuffmanSingletonGraph.txt", 50, 7, 0, 1);
//		currentRowSet.add(Arrays.asList(0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
//		currentRowSet.add(huffmanSingletonGraph.generateRandomRow());
//		huffmanSingletonGraph.buildRowListWhileLoop(currentRowSet);	
		
//-------------------------------------------------------------------------------------------------------------
		
/*
 * 
 * 		NON TRIANGLE-FREE
 * 
 */		
//		UTILITY GRAPH - 6 vertices
		SRGsolver utilityGraph = new SRGsolver("(6)UtilityGraph.txt", 6, 3, 0, 3);
		currentRowSet.add(Arrays.asList(0, 1, 0, 0, 1, 1));
		utilityGraph.buildRowListWhileLoop(currentRowSet);
		
//		OCTAHEDRAL GRAPH - 6 vertices
//		SRGsolver octahedralGraph = new SRGsolver("(6)OctahedralGraph.txt", 6, 4, 2, 4);
//		currentRowSet.add(Arrays.asList(0, 1, 1, 0, 1, 1));
//		octahedralGraph.buildRowListWhileLoop(currentRowSet);

//		8-COMPLETE BIPARTITE GRAPH - 8 vertices
//		SRGsolver eightBipartiteGraph = new SRGsolver("(8)BipartiteGraph.txt", 8, 4, 0, 4);
//		currentRowSet.add(Arrays.asList(0, 0, 0, 0, 1, 1, 1, 1));
//		eightBipartiteGraph.buildRowListWhileLoop(currentRowSet);
		
//		16-CELL GRAPH - 8 vertices
//		SRGsolver sixteenCellGraph = new SRGsolver("(8)SixteenCellGraph.txt", 8, 6, 4, 6);
//		currentRowSet.add(Arrays.asList(0, 1, 1, 1, 0, 1, 1, 1));
//		sixteenCellGraph.buildRowListWhileLoop(currentRowSet);
		
//		9-GENERALIZED QUADRANGLE GRAPH - 9 vertices
//		SRGsolver nineGeneralizedQuadrangleGraph = new SRGsolver("(9)GeneralizedQuadrangleGraph.txt", 9, 4, 1, 2);
//		currentRowSet.add(Arrays.asList(0, 1, 1, 0, 1, 0, 0, 0, 1));
//		nineGeneralizedQuadrangleGraph.buildRowListWhileLoop(currentRowSet);
		
//		10-COMPLETE BIPARTITE GRAPH - 10 vertices
//		SRGsolver tenCompleteBipartiteGraph = new SRGsolver("(10)BipartiteGraph.txt", 10, 5, 0, 5);
//		currentRowSet.add(Arrays.asList(0, 0, 0, 0, 0, 1, 1, 1, 1, 1));
//		tenCompleteBipartiteGraph.buildRowListWhileLoop(currentRowSet);
		
//		5-TRIANGULAR GRAPH - 10 vertices
//		SRGsolver fiveTriangularGraph = new SRGsolver("(10)FiveTriangularGraph.txt", 10, 6, 3, 4);
//		currentRowSet.add(Arrays.asList(0, 1, 1, 0, 0, 1, 0, 1, 1, 1));
//		fiveTriangularGraph.buildRowListWhileLoop(currentRowSet);
		
//		5-COCKTAIL PARTY GRAPH - 10 vertices
//		SRGsolver fiveCocktailPartyGraph = new SRGsolver("(10)FiveCocktailPartyGraph.txt", 10, 8, 6, 8);
//		currentRowSet.add(Arrays.asList(0, 1, 1, 1, 1, 0, 1, 1, 1, 1));
//		fiveCocktailPartyGraph.buildRowListWhileLoop(currentRowSet);
		
//		12-COMPLETE BIPARTITE GRAPH - 12 vertices
//		SRGsolver twelveBipartiteGraph = new SRGsolver("(12)BipartiteGraph.txt", 12, 6, 0, 6);
//		currentRowSet.add(Arrays.asList(0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1));
//		twelveBipartiteGraph.buildRowListWhileLoop(currentRowSet);

//		12-COMPLETE TRIPARTITE GRAPH - 12 vertices
//		SRGsolver twelveTripartiteGraph = new SRGsolver("(12)TripartiteGraph.txt", 12, 8, 4, 8);
//		currentRowSet.add(Arrays.asList(0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1));
//		twelveTripartiteGraph.buildRowListWhileLoop(currentRowSet);
	}//end main

}
