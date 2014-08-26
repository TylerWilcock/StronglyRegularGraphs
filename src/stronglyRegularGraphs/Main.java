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
//		currentRowSet.add(cycleGraph.generateRandomRow(true));
//		cycleGraph.buildRowListWhileLoop(currentRowSet);
		
//		PETERSON GRAPH - 10 vertices
//		SRGsolver petersonGraph = new SRGsolver("(10)PetersonGraph.txt", 10, 3, 0, 1);
//		currentRowSet.add(petersonGraph.generateRandomRow(true));
//		petersonGraph.buildRowListWhileLoop(currentRowSet);

//		CLEBSCH GRAPH - 16 vertices
//		SRGsolver clebschGraph = new SRGsolver("(16)ClebschGraph", 16, 5, 0, 2);
//		currentRowSet.add(clebschGraph.generateRandomRow(true));
//		clebschGraph.buildRowListWhileLoop(currentRowSet);
		
//		HUFFMAN-SINGLETON GRAPH	- 50 vertices	
//		SRGsolver huffmanSingletonGraph = new SRGsolver("(50)HuffmanSingletonGraph.txt", 50, 7, 0, 1);
//		currentRowSet.add(huffmanSingletonGraph.generateRandomRow(true));
//		huffmanSingletonGraph.buildRowListWhileLoop(currentRowSet);	
		
//-------------------------------------------------------------------------------------------------------------
		
/*
 * 
 * 		NON TRIANGLE-FREE
 * 
 */		
//		SQUARE GRAPH - 4 vertices
//		SRGsolver squareGraph = new SRGsolver("(4)SquareGraph.txt", 4, 2, 0, 2);
//		currentRowSet.add(squareGraph.generateRandomRow(true));
//		squareGraph.buildRowListWhileLoop(currentRowSet);

//		UTILITY GRAPH - 6 vertices
//		SRGsolver utilityGraph = new SRGsolver("(6)UtilityGraph.txt", 6, 3, 0, 3);
//		currentRowSet.add(utilityGraph.generateRandomRow(true));
//		utilityGraph.buildRowListWhileLoop(currentRowSet);
		
//		OCTAHEDRAL GRAPH - 6 vertices
//		SRGsolver octahedralGraph = new SRGsolver("(6)OctahedralGraph.txt", 6, 4, 2, 4);
//		currentRowSet.add(octahedralGraph.generateRandomRow(true));
//		octahedralGraph.buildRowListWhileLoop(currentRowSet);

//		8-COMPLETE BIPARTITE GRAPH - 8 vertices
//		SRGsolver eightBipartiteGraph = new SRGsolver("(8)BipartiteGraph.txt", 8, 4, 0, 4);
//		currentRowSet.add(eightBipartiteGraph.generateRandomRow(true));
//		eightBipartiteGraph.buildRowListWhileLoop(currentRowSet);
		
//		16-CELL GRAPH - 8 vertices
//		SRGsolver sixteenCellGraph = new SRGsolver("(8)SixteenCellGraph.txt", 8, 6, 4, 6);
//		currentRowSet.add(sixteenCellGraph.generateRandomRow(true));
//		sixteenCellGraph.buildRowListWhileLoop(currentRowSet);
		
//		9-GENERALIZED QUADRANGLE GRAPH - 9 vertices
//		SRGsolver nineGeneralizedQuadrangleGraph = new SRGsolver("(9)GeneralizedQuadrangleGraph.txt", 9, 4, 1, 2);
//		currentRowSet.add(nineGeneralizedQuadrangle.generateRandomRow(true));
//		nineGeneralizedQuadrangleGraph.buildRowListWhileLoop(currentRowSet);
		
//		9-COMPLETE TRIPARTITE GRAPH - 9 vertices
//		SRGsolver nineTripartiteGraph = new SRGsolver("(9)CompleteTripartiteGraph.txt", 9, 6, 3, 6);
//		currentRowSet.add(nineTripartiteGraph.generateRandomRow(true));
//		nineTripartiteGraph.buildRowListWhileLoop(currentRowSet);		
		
//		10-COMPLETE BIPARTITE GRAPH - 10 vertices
//		SRGsolver tenCompleteBipartiteGraph = new SRGsolver("(10)BipartiteGraph.txt", 10, 5, 0, 5);
//		currentRowSet.add(tenCompleteBipartiteGraph.generateRandomRow(true));
//		tenCompleteBipartiteGraph.buildRowListWhileLoop(currentRowSet);
		
//		5-TRIANGULAR GRAPH - 10 vertices
//		SRGsolver fiveTriangularGraph = new SRGsolver("(10)FiveTriangularGraph.txt", 10, 6, 3, 4);
//		currentRowSet.add(fiveTriangularGraph.generateRandomRow(true));
//		fiveTriangularGraph.buildRowListWhileLoop(currentRowSet);
		
//		5-COCKTAIL PARTY GRAPH - 10 vertices
//		SRGsolver fiveCocktailPartyGraph = new SRGsolver("(10)FiveCocktailPartyGraph.txt", 10, 8, 6, 8);
//		currentRowSet.add(fiveCocktailPartyGraph.generateRandomRow(true));
//		fiveCocktailPartyGraph.buildRowListWhileLoop(currentRowSet);
		
//		12-COMPLETE BIPARTITE GRAPH - 12 vertices
//		SRGsolver twelveBipartiteGraph = new SRGsolver("(12)BipartiteGraph.txt", 12, 6, 0, 6);
//		currentRowSet.add(twelveBipartiteGraph.generateRandomRow(true));
//		twelveBipartiteGraph.buildRowListWhileLoop(currentRowSet);

//		12-COMPLETE TRIPARTITE GRAPH - 12 vertices
//		SRGsolver twelveTripartiteGraph = new SRGsolver("(12)TripartiteGraph.txt", 12, 8, 4, 8);
//		currentRowSet.add(twelveTripartiteGraph.generateRandomRow(true));
//		twelveTripartiteGraph.buildRowListWhileLoop(currentRowSet);
		
//		6-COCKTAIL PARTY GRAPH - 12 vertices	
//		SRGsolver sixCocktailPartyGraph = new SRGsolver("(12)6-CocktailPartyGraph.txt", 12, 10, 8, 10);
//		currentRowSet.add(sixCocktailPartyGraph.generateRandomRow(true));
//		sixCocktailPartyGraph.buildRowListWhileLoop(currentRowSet);	
		
//		13-PALEY GRAPH - 13 vertices
//		SRGsolver thirteenPaleyGraph = new SRGsolver("(13)13-PaleyGraph.txt", 13, 6, 2, 3);
//		currentRowSet.add(thirteenPaleyGraph.generateRandomRow(true));
//		thirteenPaleyGraph.buildRowListWhileLoop(currentRowSet);	
		
//		14-COMPLETE BIPARTITE GRAPH - 14 vertices
//		SRGsolver fourteenBipartiteGraph = new SRGsolver("(14)BipartiteGraph.txt", 14, 7, 0, 7);
//		currentRowSet.add(fourteenBipartiteGraph.generateRandomRow(true));
//		fourteenBipartiteGraph.buildRowListWhileLoop(currentRowSet);
		
//		7-COCKTAIL PARTY GRAPH - 14 vertices	
//		SRGsolver sevenCocktailPartyGraph = new SRGsolver("(14)7-CocktailPartyGraph.txt", 14, 12, 10, 12);
//		currentRowSet.add(sevenCocktailPartyGraph.generateRandomRow(true));
//		sevenCocktailPartyGraph.buildRowListWhileLoop(currentRowSet);
		
//		(2,2)-GENERALIZED QUADRANGLE GRAPH - 15 vertices	
//		SRGsolver secondGeneralizedQuadrangleGraph = new SRGsolver("(15)2,2-GeneralizedQuadrangleGraph.txt", 15, 6, 1, 3);
//		currentRowSet.add(secondGeneralizedQuadrangleGraph.generateRandomRow(true));
//		secondGeneralizedQuadrangleGraph.buildRowListWhileLoop(currentRowSet);
		
//		6-TRIANGULAR GRAPH - 15 vertices	
//		SRGsolver sixTriangularGraph = new SRGsolver("(15)6-TriangularGraph.txt", 15, 8, 4, 4);
//		currentRowSet.add(sixTriangularGraph.generateRandomRow(true));
//		sixTriangularGraph.buildRowListWhileLoop(currentRowSet);
		
//		15-COMPLETE TRIPARTITE GRAPH - 15 vertices
//		SRGsolver fifteenTripartiteGraph = new SRGsolver("(15)TripartiteGraph.txt", 15, 10, 5, 10);
//		currentRowSet.add(fifteenTripartiteGraph.generateRandomRow(true));
//		fifteenTripartiteGraph.buildRowListWhileLoop(currentRowSet);
		
//		15-COMPLETE 5-PARTITE GRAPH - 15 vertices
//		SRGsolver fivePartiteGraph = new SRGsolver("(15)Five-PartiteGraph.txt", 15, 12, 9, 12);
//		currentRowSet.add(fivePartiteGraph.generateRandomRow(true));
//		fivePartiteGraph.buildRowListWhileLoop(currentRowSet);
		
//		SHRIKHANDE GRAPH - 16 vertices	
//		SRGsolver shrikhandeGraph = new SRGsolver("(16)ShrikhandeGraph.txt", 16, 6, 2, 2);
//		currentRowSet.add(shrikhandeGraph.generateRandomRow(true));
//		shrikhandeGraph.buildRowListWhileLoop(currentRowSet);
		
//		16-COMPLETE BIPARTITE GRAPH - 16 vertices
//		SRGsolver sixteenBipartiteGraph = new SRGsolver("(16)BipartiteGraph.txt", 16, 8, 0, 8);
//		currentRowSet.add(sixteenBipartiteGraph.generateRandomRow(true));
//		sixteenBipartiteGraph.buildRowListWhileLoop(currentRowSet);
		
//		COMPLEMENT OF (4,4) LATTICE GRAPH - 16 vertices	
//		SRGsolver fourFourLatticeComplementGraph = new SRGsolver("(16)4,4-LatticeComplementGraph.txt", 16, 9, 4, 6);
//		currentRowSet.add(fourFourLatticeComplementGraph.generateRandomRow(true));
//		fourFourLatticeComplementGraph.buildRowListWhileLoop(currentRowSet);
		
//		5-HALVED CUBE GRAPH - 16 vertices	
//		SRGsolver fiveHalvedCubeGraph = new SRGsolver("(16)fiveHalvedCubeGraph.txt", 16, 10, 6, 6);
//		currentRowSet.add(fiveHalvedCubeGraph.generateRandomRow(true));
//		fiveHalvedCubeGraph.buildRowListWhileLoop(currentRowSet);
		
//		16-COMPLETE 4-PARTITE GRAPH - 16 vertices
//		SRGsolver sixteenFourPartiteGraph = new SRGsolver("(16)4-PartiteGraph.txt", 16, 12, 8, 12);
//		currentRowSet.add(sixteenFourPartiteGraph.generateRandomRow(true));
//		sixteenFourPartiteGraph.buildRowListWhileLoop(currentRowSet);
		
//		8-COCKTAIL PARTY GRAPH - 16 vertices
//		SRGsolver eightCocktailPartyGraph = new SRGsolver("(16)8-CocktailPartyGraph.txt", 16, 14, 12, 14);
//		currentRowSet.add(eightCocktailPartyGraph.generateRandomRow(true));
//		eightCocktailPartyGraph.buildRowListWhileLoop(currentRowSet);
		
//		17-PALEY GRAPH - 17 vertices	
//		SRGsolver seventeenPaleyGraph = new SRGsolver("(17)PaleyGraph.txt", 17, 8, 3, 4);
//		currentRowSet.add(seventeenPaleyGraph.generateRandomRow(true));
//		seventeenPaleyGraph.buildRowListWhileLoop(currentRowSet);
		
//		9-COCKTAIL PARTY GRAPH - 18 vertices	
//		SRGsolver nineCocktailPartyGraph = new SRGsolver("(18)9-CocktailPartyGraph.txt", 18, 16, 14, 16);
//		currentRowSet.add(nineCocktailPartyGraph.generateRandomRow(true));
//		nineCocktailPartyGraph.buildRowListWhileLoop(currentRowSet);
		
//		18-COMPLETE BIPARTITE GRAPH - 18 vertices
//		SRGsolver eighteenBipartiteGraph = new SRGsolver("(18)BipartiteGraph.txt", 18, 9, 0, 9);
//		currentRowSet.add(eighteenBipartiteGraph.generateRandomRow(true));
//		eighteenBipartiteGraph.buildRowListWhileLoop(currentRowSet);
		
//		18-COMPLETE TRIPARTITE GRAPH - 18 vertices
//		SRGsolver eighteenTripartiteGraph = new SRGsolver("(18)TripartiteGraph.txt", 18, 12, 6, 12);
//		currentRowSet.add(eighteenTripartiteGraph.generateRandomRow(true));
//		eighteenTripartiteGraph.buildRowListWhileLoop(currentRowSet);
		
//		20-COMPLETE BIPARTITE GRAPH - 20 vertices
//		SRGsolver twentyBipartiteGraph = new SRGsolver("(20)BipartiteGraph.txt", 20, 10, 0, 10);
//		currentRowSet.add(twentyBipartiteGraph.generateRandomRow(true));
//		twentyBipartiteGraph.buildRowListWhileLoop(currentRowSet);
		
//		10-COCKTAIL PARTY GRAPH - 20 vertices	
//		SRGsolver tenCocktailPartyGraph = new SRGsolver("(20)10-CocktailPartyGraph.txt", 20, 18, 16, 18);
//		currentRowSet.add(tenCocktailPartyGraph.generateRandomRow(true));
//		tenCocktailPartyGraph.buildRowListWhileLoop(currentRowSet);
		
//		(7, 2) KNESER GRAPH - 21 vertices	
//		SRGsolver sevenTwoKneserGraph = new SRGsolver("(21) (7,2)-KneserGraph.txt", 21, 10, 3, 6);
//		currentRowSet.add(sevenTwoKneserGraph.generateRandomRow(true));
//		sevenTwoKneserGraph.buildRowListWhileLoop(currentRowSet);
		
//		7-TRIANGULAR GRAPH - 21 vertices	
//		SRGsolver sevenTriangularGraph = new SRGsolver("(21)7-TriangularGraph.txt", 21, 10, 5, 4);
//		currentRowSet.add(sevenTriangularGraph.generateRandomRow(true));
//		sevenTriangularGraph.buildRowListWhileLoop(currentRowSet);
		
//		22-COMPLETE BIPARTITE GRAPH - 22 vertices
//		SRGsolver twentyTwoBipartiteGraph = new SRGsolver("(22)BipartiteGraph.txt", 22, 11, 0, 11);
//		currentRowSet.add(twentyTwoBipartiteGraph.generateRandomRow(true));
//		twentyTwoBipartiteGraph.buildRowListWhileLoop(currentRowSet);
		
//		11-COCKTAIL PARTY GRAPH - 22 vertices	
//		SRGsolver elevenCocktailPartyGraph = new SRGsolver("(22)11-CocktailPartyGraph.txt", 22, 20, 18, 20);
//		currentRowSet.add(elevenCocktailPartyGraph.generateRandomRow(true));
//		elevenCocktailPartyGraph.buildRowListWhileLoop(currentRowSet);
		
//		24-COMPLETE BIPARTITE GRAPH - 24 vertices
//		SRGsolver twentyFourBipartiteGraph = new SRGsolver("(24)BipartiteGraph.txt", 24, 12, 0, 12);
//		currentRowSet.add(twentyFourBipartiteGraph.generateRandomRow(true));
//		twentyFourBipartiteGraph.buildRowListWhileLoop(currentRowSet);
		
//		12-COCKTAIL PARTY GRAPH - 24 vertices	
//		SRGsolver twelveCocktailPartyGraph = new SRGsolver("(24)12-CocktailPartyGraph.txt", 24, 22, 20, 22);
//		currentRowSet.add(twelveCocktailPartyGraph.generateRandomRow(true));
//		twelveCocktailPartyGraph.buildRowListWhileLoop(currentRowSet);
		
//		(5,5) LATTICE GRAPH - 25 vertices
//		SRGsolver fiveFiveLatticeGraph = new SRGsolver("(25)5,5-LatticeGraph.txt", 25, 8, 3, 2);
//		currentRowSet.add(fiveFiveLatticeGraph.generateRandomRow(true));
//		fiveFiveLatticeGraph.buildRowListWhileLoop(currentRowSet);
		
//		25-PALEY GRAPH - 25 vertices	
//		SRGsolver twentyFivePaleyGraph = new SRGsolver("(25)PaleyGraph.txt", 25, 12, 5, 6);
//		currentRowSet.add(twentyFivePaleyGraph.generateRandomRow(true));
//		twentyFivePaleyGraph.buildRowListWhileLoop(currentRowSet);
		
//		26-PAULUS GRAPH - 25 vertices	
//		SRGsolver twentySixPaulusGraph = new SRGsolver("(26)PaulusGraph.txt", 26, 10, 3, 4);
//		currentRowSet.add(twentySixPaulusGraph.generateRandomRow(true));
//		twentySixPaulusGraph.buildRowListWhileLoop(currentRowSet);
		
//		(2,4)-GENERALIZED QUADRANGLE GRAPH - 27 vertices	
//		SRGsolver twoFourGeneralizedQuadrangleGraph = new SRGsolver("(27)(2,4)-GeneralizedQuadrangleGraph.txt", 27, 10, 1, 5);
//		currentRowSet.add(twoFourGeneralizedQuadrangleGraph.generateRandomRow(true));
//		twoFourGeneralizedQuadrangleGraph.buildRowListWhileLoop(currentRowSet);
		
//		29-PALEY GRAPH - 29 vertices	
		SRGsolver twentyNinePaleyGraph = new SRGsolver("(29)PaleyGraph.txt", 29, 14, 6, 7);
		currentRowSet.add(twentyNinePaleyGraph.generateRandomRow(true));
		twentyNinePaleyGraph.buildRowListWhileLoop(currentRowSet);
	}//end main

}
