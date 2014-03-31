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

package stronglyRegularGraphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SRGsolver 
{
	private int numOfVertices, degree, lambdaValue, muValue;
	private boolean maximalRowSetFound = false;

	public static void main(String[] args) 
	{
		List< List<Integer> > currentRowSet = new ArrayList<>();
		
		SRGsolver SRGsolver = new SRGsolver(6, 3, 0, 1);
		currentRowSet.add(Arrays.asList(1, 0, 1, 1, 0, 0));
		currentRowSet.add(Arrays.asList(0, 0, 1, 1, 1, 0));
		currentRowSet.add(Arrays.asList(1, 0, 1, 0, 1, 0)); 
		SRGsolver.dotProduct(currentRowSet);
	}//end main
	
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
	 * The dot product function takes the current row set, and then transposes it.  It then multiplies this transposed version of the row set with the non-transposed version, 
	 * element by element.  The element by element result is modulus'd by 2 to ensure that the result is either 0 or 1.  Then, the result of these modulus operations is added 
	 * together to get a (c) by (c) matrix.
	 * 
	 * @param passedInRows
	 * @return 2D List; Returns a (c) by (c) matrix containing the results of the dot product.  
	 */
	public List< List<Integer> > dotProduct(List< List<Integer> > passedInRows)
	{
		List< List<Integer> > returnedMatrix = new ArrayList<>();
		
//		for(List<Integer> currentRow : passedInRows)
//		{
//			currentRow
//		}
		
		int matrixResult = 0;
		for(int i = 0; i < passedInRows.size(); i++)
		{
			List<Integer> currentRow = new ArrayList<Integer>();
			currentRow = passedInRows.get(i);
			List< List<Integer> > allMatricesExceptI = new ArrayList<>();
			
			for(int s = 0; s < passedInRows.size(); s++)
			{
				if(s != i)
				{
					allMatricesExceptI.add(passedInRows.get(s));			
				}
			}
			
			for(int g = 0; g < allMatricesExceptI.size(); g++)
			{
				for(int j = 0; j < currentRow.size(); j++)
				{
					int currentElement = currentRow.get(j);
					System.out.println("Element at: " + j + " is " + currentElement + "*" + currentElement + "=" + (currentElement*currentElement));
					matrixResult += currentElement * currentElement;	
				}
			}
		}
		
		System.out.println("Matrix result = " + matrixResult);
		
		return returnedMatrix;
	}//end dotProduct method
	
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
		
		for(int i = 0; i < numOfVertices; i++)
		{
			int randomNumber = rand.nextInt(2);
			if(randomNumber == 1) 
			{
				if(oneCounter == degree) 
				{
					randomNumber = 0;
				}
				else
				{
					oneCounter++;
				}
			}
			randomRow.add(randomNumber);
		}
		
		return randomRow;
	}//end generateRandomRow method
	
	/**
	 * 
	 * @param currentRowSet The 2D List of rows that are currently known to be correct.
	 * @return 2D List; Maximal set of rows
	 */
	public List< List<Integer> > buildRowList(List< List<Integer> > currentRowSet)
	{
		//Code to check for maximal row set here
		
		//End code that checks for maximal row set
		
		//Base case; check to see if maximal row set has been built
		if(maximalRowSetFound)
		{
			return currentRowSet;
		}	
		List<Integer> randomRow = new ArrayList<Integer>();
		randomRow = generateRandomRow();
		
		currentRowSet.add(randomRow);
		
		dotProduct(currentRowSet);
		
		//matrix check logic goes here
			//check to see if random row works by checking matrix, if so add it to currentRowSet.  else throw away
		//end matrix check logic
		
		return buildRowList(currentRowSet);
	}

}//end public class SRGsolver
