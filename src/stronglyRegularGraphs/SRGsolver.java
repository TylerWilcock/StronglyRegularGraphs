package stronglyRegularGraphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * The purpose of the TFSRGsolver class is to build the adjacency matrix of a Triangle Free Strongly Regular Graph of (x) number of vertices.
 * This is accomplished by randomly generating a row of (x) numbers.  Of these (x) numbers, there must be only as many one's as the degree of 
 * the graph, or (d).  All other numbers in the row must be 0.  After the random row is generated, it is added to the current row set of correct
 * rows, and then a dot product is performed on this new set.  
 * <p>
 * Using current number of rows (c) in the row set, the result of this dot product is
 * a (c) by (c) matrix.  The next step is to compare this matrix to the (c) by (c) matrix of the row set.  If the degree, lambda, and mu values
 * all properly match up, then we know that the random row we just generated is a correct row, and it is permanently added into the list of correct
 * rows.  This process is repeated until a maximal row set is formed.
 * <p>
 * Methods implemented:
 * <ul>
 * 		<li> generateRandomRow - Generates a random row of (x) number of vertices, and (d) degree number of one's
 * 		<li> dotProduct - Returns dot product of current (random row included) row set
 * 		<li> buildRowSet - Recursive method that calls itself until maximal row set is built
 * <ul>
 * 
 * @author Tyler Wilcock
 */

/*
 * Test cases to write:
 * 		Make sure inner and outer current rows are the same size; in dotProduct method
 */

/*
 * Disallow duplicate rows.
 */

public class SRGsolver 
{
	private int numOfVertices, degree, lambdaValue, muValue;
	private int foundRowCounter = 0;
	private boolean maximalRowSetFound = false;
	
	/**
	 * Initializes the class variables containing the number of vertices, the degree value, the lambda value, and the mu value.
	 * 
	 * @param numOfVertices
	 * @param degree
	 * @param lambdaValue
	 * @param muValue
	 */
	public SRGsolver(int numOfVertices, int degree, int lambdaValue, int muValue)
	{
		this.numOfVertices = numOfVertices;
		this.degree = degree;
		this.lambdaValue = lambdaValue;
		this.muValue = muValue;
	}//end SRGsolver constructor
	
	/**
	 * This function generates a List object that has (degree) one's randomly placed in a row of (numOfVertices), where the other spaces are 0's. 
	 * 
	 * @return List Randomly generated row of 1's and 0's 
	 */
	public List<Integer> generateRandomRow()
	{
		List<Integer> randomRow = new ArrayList<Integer>();
		Random rand = new Random();
		int oneCounter = 0;
		int onesLeft = this.degree;
		
		for(int i = 0; i < numOfVertices; i++)
		{
			int randomNumber = rand.nextInt(2);
			if(onesLeft > numOfVertices - (i + 1))
			{
				randomNumber = 1;
			}
			else
			{
				if(randomNumber == 1) 
				{
					if(oneCounter == degree) 
					{
						randomNumber = 0;
					}
					else
					{
						oneCounter++;
						onesLeft--;
					}
				}
			}
			randomRow.add(randomNumber);
		}
		
		return randomRow;
	}//end generateRandomRow method
	
	/**
	 * The dot product function takes the current row set, and returns a 2D List of the dot product.  It then multiplies this transposed version of the row set with the non-transposed version, 
	 * element by element.  The element by element result is modulus'd by 2 to ensure that the result is either 0 or 1.  Then, the result of these modulus operations is added 
	 * together to get a (c) by (c) matrix.
	 * 
	 * @param passedInRows
	 * @return 2D List; Returns a (c) by (c) matrix containing the results of the dot product.  
	 */
	public List< List<Integer> > dotProduct(List< List<Integer> > passedInRows)
	{
		List< List<Integer> > returnedMatrix = new ArrayList< List<Integer> >();
		List<Integer> innerCurrentRow = new ArrayList<Integer>();
		List<Integer> outerCurrentRow = new ArrayList<Integer>();
		
		int matrixResult = 0;
		for(int i = 0; i < passedInRows.size(); i++)
		{	
			outerCurrentRow = passedInRows.get(i);
			
			//Every time an outerRow is processed, we want to create a new row in the returnedResults 2D list.
			List<Integer> emptyList = new ArrayList<Integer>();
			returnedMatrix.addAll(Arrays.asList(emptyList));
			
			/*
			 * The idea behind both of these for loops of the same size is that each row (outerCurrentRow) needs to be multiplied 
			 * against every other row (innerCurrentRow) to get the proper result.
			 */
			for(int j = 0; j < passedInRows.size(); j++)
			{
				innerCurrentRow = passedInRows.get(j);

				matrixResult = 0;
				//Do the math to get matrixResult, which is the variable that stores the result for the returnMatrix at a certain spot

				for(int g = 0; g < innerCurrentRow.size(); g++)
				{
						matrixResult += outerCurrentRow.get(g) * innerCurrentRow.get(g);
				}
				
				returnedMatrix.get(i).add(matrixResult);
			}
		}

		return returnedMatrix;
	}//end dotProduct method
	
	/**
	 * This function checks to make sure that the matrix created by the dot product of the correct rows 
	 * and the new random row contains the lambda values in the right place.  Any time there is a '1' in
	 * the (c) by (c) rowset, the lambda value should be in that same position in the dotProductMatrix.  
	 * 
	 * @param currentRowSet - The 2D List of rows that contains rows that are currently known to be correct
	 * @param dotProductMatrix - The dot product of currentRowSet
	 * @return true(success) or false(failure)
	 */
	public boolean lambdaCheck(List< List<Integer> > currentRowSet, List< List<Integer> > dotProductMatrix)
	{
		
		for(int g = 0; g < dotProductMatrix.size(); g++)
		{
			for(int a = 0; a < dotProductMatrix.get(g).size(); a++)
			{
				if(currentRowSet.get(g).get(a) == 1 && (a != g))
				{
					if(dotProductMatrix.get(g).get(a) != this.lambdaValue)
					{
						return false;
					}
				}
			}
		}
		return true;
	}
	
	/**
	 * This function checks to make sure that the matrix created by the dot product of the correct rows 
	 * and the new random row contains the mu values in the right place.  Any time there is a '0' in
	 * the (c) by (c) rowset, the lambda value should be in that same position in the dotProductMatrix.  
	 * 
	 * @param currentRowSet - The 2D List of rows that contains rows that are currently known to be correct
	 * @param dotProductMatrix - The dot product of currentRowSet
	 * @return true(success) or false(failure)
	 */
	public boolean muCheck(List< List<Integer> > currentRowSet, List< List<Integer> > dotProductMatrix)
	{
		
		for(int g = 0; g < dotProductMatrix.size(); g++)
		{
			for(int a = 0; a < dotProductMatrix.get(g).size(); a++)
			{
				if(currentRowSet.get(g).get(a) == 0 && (a != g))
				{
					if(dotProductMatrix.get(g).get(a) != this.muValue)
					{
						return false;
					}
				}
			}
		}
		return true;
	}
	
	/**
	 * 
	 * @param currentRowSet The 2D List of rows that are currently known to be correct.
	 * @return 2D List; Maximal set of rows
	 */
	public List< List<Integer> > buildRowList(List< List<Integer> > currentRowSet)
	{

		List<Integer> randomRow = new ArrayList<Integer>();
		List< List<Integer> > dotProductMatrix = new ArrayList< List<Integer> >();
		
		randomRow = generateRandomRow();
		currentRowSet.add(randomRow);
		
		dotProductMatrix = dotProduct(currentRowSet);
		FileHandler fileHandler = new FileHandler();
		fileHandler.setFileName("maximalRowSetFile.txt");
		
		if(!lambdaCheck(currentRowSet, dotProductMatrix) || !muCheck(currentRowSet, dotProductMatrix))
		{
			currentRowSet.remove(currentRowSet.size() - 1);
		}
		if(currentRowSet.size() == this.numOfVertices)
		{
			System.out.println("test");
			fileHandler.write("OUTPUT MATRIX FROM SRGsolver.java: ");
			fileHandler.writeln();
			fileHandler.write("---------------------------------------------------------");
			fileHandler.writeln(2);
			fileHandler.write2DList(currentRowSet);
			fileHandler.writeln(3);
			dotProductMatrix = dotProduct(currentRowSet);
			fileHandler.write2DList(dotProductMatrix);
			return currentRowSet;
		}

		return buildRowList(currentRowSet);
	}
	
	/**
	 * 
	 * @param currentRowSet The 2D List of rows that are currently known to be correct.
	 * @return 2D List; Maximal set of rows
	 */
	public List< List<Integer> > buildRowList2(List< List<Integer> > currentRowSet)
	{
		while(!maximalRowSetFound)
		{
			if(currentRowSet.size() == this.numOfVertices)
			{
				FileHandler fileHandler = new FileHandler();
				fileHandler.setFileName("maximalRowSetFile.txt");
				fileHandler.write("OUTPUT MATRIX FROM SRGsolver.java: ");
				fileHandler.writeln();
				fileHandler.write("---------------------------------------------------------");
				fileHandler.writeln(2);
				fileHandler.write2DList(currentRowSet);
				maximalRowSetFound = true;	
				return currentRowSet;	
			}	
			else
			{
				List<Integer> randomRow = new ArrayList<Integer>();
				List< List<Integer> > dotProductMatrix = new ArrayList< List<Integer> >();
				
				randomRow = generateRandomRow();
				currentRowSet.add(randomRow);
				
				dotProductMatrix = dotProduct(currentRowSet);
				FileHandler fileHandler = new FileHandler();
				fileHandler.setFileName("maximalRowSetFile.txt");
				
				if(lambdaCheck(currentRowSet, dotProductMatrix) && muCheck(currentRowSet, dotProductMatrix))
				{
					
					System.out.println("Lambda mu check worked");
					
					fileHandler.write("OUTPUT MATRIX FROM SRGsolver.java: ");
					fileHandler.writeln();
					fileHandler.write("---------------------------------------------------------");
					fileHandler.writeln(2);
					fileHandler.write2DList(currentRowSet);
					fileHandler.writeln(3);
					fileHandler.write2DList(dotProductMatrix);
					
					fileHandler.writeln(8);
					
				}
				else
				{
					currentRowSet.remove(currentRowSet.size() - 1);
				}
			}
		}
		return currentRowSet;

	}

}//end public class SRGsolver
