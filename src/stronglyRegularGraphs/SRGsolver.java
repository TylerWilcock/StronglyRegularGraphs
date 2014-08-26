package stronglyRegularGraphs;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
 * Other TODO: 
 * 
 * 			   Check generated test files against the graphs I solved on paper to confirm algorithm accuracy
 * 
 * 				Parallelize slow parts of the code using ForkJoin
 * 
 * 				Ensure that all comments are up to date
 * 
 * 				In Main.java, organize all the graphs.   
 * 					-By sections (cocktail party graphs, bipartite, tripartite, etc..)
 * 					-By number of vertices
 */

public class SRGsolver 
{
	private boolean firstRowKnown;
	private int numOfVertices, degree, lambdaValue, muValue;
	private boolean maximalRowSetFound = false;
	private String fileName;
	
	/**
	 * Initializes the class variables containing the file name to write to, number of vertices in the SRG, 
	 * the degree value (which is the number of edges connected to each point), 
	 * the lambda value (number of edges shared with each ponts adjacent vertices),
	 * and the mu value (the number of edges a vertex shares with it's non adjacent vertices). 
	 * 
	 * @param fileName
	 * @param numOfVertices
	 * @param degree
	 * @param lambdaValue
	 * @param muValue
	 */
	public SRGsolver(String fileName, int numOfVertices, int degree, int lambdaValue, int muValue)
	{
		this.fileName = fileName;
		this.numOfVertices = numOfVertices;
		this.degree = degree;
		this.lambdaValue = lambdaValue;
		this.muValue = muValue;
	}//end SRGsolver constructor
	
	/**
	 * This function generates a List object that has (degree) one's randomly placed in a row of (numOfVertices), where the other spaces are 0's. 
	 * 
	 * @param rowsetIsEmpty A true or false variable that flags whether or not the calling row set is empty.
	 * @return List Randomly generated row of 1's and 0's 
	 */
	public List<Integer> generateRandomRow(boolean rowsetIsEmpty)
	{
		List<Integer> returnedRow = new ArrayList<Integer>(numOfVertices);
		List<Integer> randomNumbers = new ArrayList<Integer>(numOfVertices);
		Random rand = new Random();
		int oneCounter = 0;
		int onesLeft = this.degree;
		int startingPoint = 0;
		
		/*
		 * If the 'currentRowset' 2D List currently has no rows at the time of this function call, we mathematically know that there needs to be '0' in the first
		 * position.  This also means that if this is the case, we need to change the starting point to skip the first position in the array so a number
		 * is not placed over it. 
		 */
		if(rowsetIsEmpty)
		{
			returnedRow.add(0);
			startingPoint = 1;
		}
		else
		{
			startingPoint = 0;
		}
		
		//Generate (numOfVertices) random numbers
		for(int i = startingPoint; i < numOfVertices; i++)
		{
			int randomNumber;
			
			/* If the number of ones left to place is greater than the number of total spots left to place
			* minus the number of iterations of the loop, then force the next number to be one.
			* Example: numOfVertices = 5, onesLeft = 2.  Current random row = (0, 0, 0)
			* There's only two spots left in the random row, and two one's left to place.  Force the next number (and the one after) to be one.
			*/
			if(onesLeft > numOfVertices - (i + 1))
			{
				randomNumber = 1;
			}
			else
			{
				//generate a random number that is either 0 or 1
				randomNumber = rand.nextInt(2);
				if(randomNumber == 1) 
				{
					//Checks to make sure that the current number of one's isn't greater than the total
					//number of one's allowed by the shape, which is determined by the degree value.
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
			randomNumbers.add(randomNumber);
			
		}
		/*
		 * To ensure that the numbers in the row are truly random, a random starting position is generated.  The numbers generated above
		 * are then sequentially placed into the returned row starting from this position, resetting to the beginning if the end is reached.
		 */
		int randomStartingPosition = rand.nextInt(randomNumbers.size());
		
		for(int j = 0; j < randomNumbers.size(); j++)
		{
			if(randomStartingPosition > randomNumbers.size() - 1)
			{
				randomStartingPosition = 1;
			}
			returnedRow.add(randomNumbers.get(randomStartingPosition));
			randomStartingPosition++;
		}
		
		return returnedRow;
	}//end generateRandomRow method
	
	/**
	 * This function generates a random row in the form of a List object, using the current known rows in 'currentRowSet' to add the first 
	 * values of the row using the symmetry in currentRowSet.  Once these known values are placed, the remaining numbers are randomly 
	 * generated into the randomRow and the completed row is returned. 
	 * <p>
	 * Example:
	 * </p>
	 * If we know row 1 is '0, 1, 0, 0, 1' and we're randomly generating a random row for row 2 (counting beginning from '1'),
	 * we know that the first spot in the row is a '1' because row 1 - column 2 is a '1'.  We can also say that the second
	 * spot in this random row for row 2 is a 0, as that value lies in the diagonal.  This function places in these values,
	 * and then randomly generates numbers for the rest of the spots.  This is the task accomplished by this method.
	 * 
	 * @param currentRowSet - 2D List of Integers of currently known correct rows
	 * @return randomRow - 1D List containing a partially random row of numbers
	 */
	public List<Integer> generateRandomRowFromCurrentRowSet(List< List<Integer> > currentRowSet)
	{	
		List<Integer> randomNumbers = new ArrayList<Integer>(numOfVertices);
		List<Integer> returnedRow = new ArrayList<Integer>(numOfVertices);
		
		Random rand = new Random();
		int oneCounter = 0;
		int onesLeft = this.degree;
		int numbersLeftToPlace = numOfVertices;
		int startingPoint = 0;
		
		//We already know the first values of this random row based on the corresponding column from currentRowSet, so this for loop adds those numbers in.
		//Example: if row 1 is: 0, 1, 0, 1, 0 - We know that the first number in row 2 is a '1'.
		for(int z = 0; z < currentRowSet.size(); z++)
		{
			returnedRow.add(currentRowSet.get(z).get(currentRowSet.size()));
			if(returnedRow.get(z) == 1)
			{
				oneCounter++;
				onesLeft--;
			}
			numbersLeftToPlace--;
		}
		
		/*
		* These 2 lines of code add in a '0' at the diagonal of this random row, as we know for a fact that in a correct row there will be a '0' in that spot.
		* Example:  The third row in current row set has the following dimensions in the 2D List/adjacency matrix:
		* Row 2, Column 2 (Remember counting starts from 0).
		* This means that in that spot, it is in the diagonal and therefore must be a '0'.
		*
		* The size of the currentRowSet points to this spot, so that is used to set the value to 0.
		*/
		returnedRow.add(currentRowSet.size(), 0);
		numbersLeftToPlace--;
		
		startingPoint = this.numOfVertices - numbersLeftToPlace;
		
		//Generate (numOfVertices) random numbers
		for(int i = startingPoint; i < this.numOfVertices; i++)
		{
			int randomNumber;
			
			/* If the number of ones left to place is greater than the number of total spots left to place
			* minus the number of iterations of the loop, then force the next number to be one.
			* Example: numOfVertices = 5, onesLeft = 2.  Current random row = (0, 0, 0)
			* There's only two spots left in the random row, and two one's left to place.  Force the next number (and the one after) to be one.
			*/
			if(onesLeft > numOfVertices - (i + 1))
			{
				randomNumber = 1;
			}
			else
			{
				//generate a random number that is either 0 or 1
				randomNumber = rand.nextInt(2);
				if(randomNumber == 1) 
				{
					//Checks to make sure that the current number of one's isn't greater than the total
					//number of one's allowed by the shape, which is determined by the degree value.
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
			randomNumbers.add(randomNumber);
			
		}
		/*
		 * To ensure that the numbers in the row are truly random, a random starting position is generated.  The numbers generated above
		 * are then sequentially placed into the returned row starting from this position, resetting to the beginning if the end is reached.
		 */
		int randomStartingPosition = rand.nextInt(randomNumbers.size());
		
		for(int j = 0; j < randomNumbers.size(); j++)
		{
			if(randomStartingPosition > randomNumbers.size() - 1)
			{
				randomStartingPosition = 1;
			}
			returnedRow.add(randomNumbers.get(randomStartingPosition));
			randomStartingPosition++;
		}
		
		return returnedRow;
	}
	
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
	 * and the new random row contains the lambda and mu values in the right place.  Any time there is a '1' in
	 * the (c) by (c) row set, the lambda value should be in that same position in the dotProductMatrix.  Conversely,
	 * anytime there is a '0' in the (c) by (c) row set, the mu value should be in that same position in the dotProductMatrix. 
	 * 
	 * @param currentRowSet - The 2D List of rows that contains rows that are currently known to be correct
	 * @param dotProductMatrix - The dot product of currentRowSet
	 * @return true(success) or false(failure)
	 */
	public boolean lambdaMuCheck(List< List<Integer> > currentRowSet, List< List<Integer> > dotProductMatrix)
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
				else if(currentRowSet.get(g).get(a) == 0 && (a != g))
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
	 * This function checks to make sure that the matrix created by the dot product of the correct rows 
	 * and the new random row contains the mu values in the right place.  Any time there is a '0' in
	 * the (c) by (c) row set, the lambda value should be in that same position in the dotProductMatrix.  
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
	 * This function tests the number of digits of the integer that is passed into it.
	 * For my purposes, I won't need a function that returns anything close to 5 digits,
	 * so my function is limited in regards of applications that might need to check larger
	 * numbers.
	 * 
	 * @param passedNumber Integer that is being checked for number of digits
	 * @return Integer Number of digits of the passed in number
	 */
	public int testNumberOfDigits(int passedNumber)
	{
		if(passedNumber == 0) return 1;
		double testNumberOfDigits = passedNumber / 10;
		if(testNumberOfDigits < 1)
		{
			//Number is 1-9
			return 1;
		}
		else if(testNumberOfDigits < 10)
		{
			//Number is between 10-99
			return 2;
			
		}
		else if(testNumberOfDigits < 100)
		{
			//Number is between 100-1000
			return 3;
		}
		else if(testNumberOfDigits < 1000)
		{
			//Number is between 1000-10000
			return 4;
		}
		return 5;
	}
	
	/**
	 * This function rounds a decimal to a certain number of places.  This function was copied from
	 * StackOverflow at the following link: http://stackoverflow.com/questions/2808535/round-a-double-to-2-decimal-places
	 * 
	 * @param value
	 * @param places
	 * @return Formatted double with x number of places
	 */
	public static double round(double value, int places) 
	{
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	
	/**
	 * This function generates a text file containing a formatted and unformatted version
	 * of the maximal row set and dot product matrix that is passed into it.
	 * 
	 * @param currentRowSet The 2D list that contains the maximal row set.
	 * @param dotProductMatrix The 2D list that contains the dot product of the maximal row set.
	 * @param hours The integer value of hours the program took to run
	 * @param minutes The integer value of minutes the program took to run (not higher than 60)
	 * @param seconds The double value of seconds the program took to run (not higher than 60)
	 * @return void
	 */
	public void printMaximalRowsToFile(List< List<Integer> > currentRowSet, List< List<Integer> > dotProductMatrix, int hours, int minutes, double seconds)
	{
		int spacesToIndent = 6;
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		
		FileHandler fileHandler = new FileHandler();
		fileHandler.setFileName(this.fileName);
		
		/* Print formatted row set header */
		fileHandler.write("FILE NAME: ");
		fileHandler.write(this.fileName);
		fileHandler.writeln();
		fileHandler.write("Date: ");
		fileHandler.write(dateFormat.format(date));
		fileHandler.writeln();
		fileHandler.write("Author: ");
		fileHandler.write("Tyler Wilcock");
		fileHandler.writeln(2);
		//Print run time
		fileHandler.write("RUN TIME: ");
		fileHandler.write(hours);
		fileHandler.write(" HOURS, ");
		fileHandler.write(minutes);
		fileHandler.write(" MINUTES, ");
		fileHandler.write(seconds);
		fileHandler.write(" SECONDS");
		fileHandler.writeln(2);
		//End run time print
		fileHandler.write("ADJACENCY MATRIX FROM SRGsolver.java: ");
		fileHandler.writeln();
		fileHandler.write("---------------------------------------------------------");
		fileHandler.writeln(2);
		/* End header */
		
		writeFormatted2DList(currentRowSet, spacesToIndent);
		
		fileHandler.writeln(2);
		
		/* Print formatted dot product header */
		fileHandler.write("FORMATTED DOT PRODUCT MATRIX: ");
		fileHandler.writeln();
		fileHandler.write("---------------------------------------------------------");
		fileHandler.writeln(2);
		/* End header */
		
		writeFormatted2DList(dotProductMatrix, spacesToIndent);
		
		fileHandler.writeln(2);
		
		fileHandler.write("ADJACENCY MATRIX WITHOUT FORMATTING");
		fileHandler.writeln();
		fileHandler.write("---------------------------------------------------------");
		fileHandler.writeln(2);
		fileHandler.write2DList(currentRowSet);
		
		fileHandler.writeln(2);
		
		fileHandler.write("DOT PRODUCT WITHOUT FORMATTING");
		fileHandler.writeln();
		fileHandler.write("---------------------------------------------------------");
		fileHandler.writeln(2);
		fileHandler.write2DList(dotProductMatrix);
	}
	
	/**
	 * This function writes a formatted table from it's 2D List input.  I built this function mostly to tailor
	 * to my need for this specific project using adjacency matrices, so it probably won't work well with
	 * other applications.
	 * 
	 * @param twoDimensionalList - Passed in 2D List that will be written onto the text file
	 * @param spacesToIndent - Integer number of spaces to "indent"; not really intuitive right now, would like to change how this variable is used in the future
	 */
	public void writeFormatted2DList(List< List<Integer> > twoDimensionalList, int spacesToIndent)
	{
		FileHandler fileHandler = new FileHandler();
		fileHandler.setFileName(this.fileName);
		
		/* Print column numbers and top line of 2D List*/
		fileHandler.writeSpace(spacesToIndent);
		for(int i = 0; i < this.numOfVertices; i++)
		{
			fileHandler.write(i + 1);
			
			if(testNumberOfDigits(i+1) == 1)
			{
				fileHandler.writeSpace();
			}
			
			fileHandler.write("/");
			
			if(testNumberOfDigits(i+1) == 1 || testNumberOfDigits(i+1) == 2)
			{
				fileHandler.writeSpace();
			}
		}
		fileHandler.writeln();
		
		for(int j = 0; j < (this.numOfVertices * 4) + (spacesToIndent - 1); j++)
		{
			fileHandler.write("=");
		}
		/* End column headers */
		
		for(int k = 0; k < twoDimensionalList.size(); k++)
		{
			boolean firstTimeThroughRow = true;
			for(int z = 0; z < twoDimensionalList.get(k).size(); z++)
			{
				//If it's the first pass through a row, we want to print out the row number
				if(firstTimeThroughRow)
				{
					fileHandler.writeln();
					fileHandler.write((k + 1) + ")");
					
					if(testNumberOfDigits(k+1) == 1)
					{
						//Number is 1-9, print four spaces after row number
						fileHandler.writeSpace(4);
					}
					else if(testNumberOfDigits(k+1) == 2)
					{
						//Number is between 10-99
						fileHandler.writeSpace(3);
					}
					else if(testNumberOfDigits(k+1) == 3)
					{
						//Number is between 100-1000
						fileHandler.writeSpace(2);
					}
					else if(testNumberOfDigits(k+1) == 4)
					{
						//Number is between 1000-10000
						fileHandler.writeSpace();
					}

					firstTimeThroughRow = false;
				}
				
				fileHandler.write(twoDimensionalList.get(k).get(z));

				if(testNumberOfDigits(twoDimensionalList.get(k).get(z)) == 1)
				{
					fileHandler.writeSpace();
				}
				fileHandler.write("|");
				if(testNumberOfDigits(twoDimensionalList.get(k).get(z)) == 1 || testNumberOfDigits(twoDimensionalList.get(k).get(z)) == 2)
				{
					fileHandler.writeSpace();
				}
			}

			/* Write line of '='s between each row */
			fileHandler.writeln();
			for(int g = 0; g < (this.numOfVertices * 4) + (spacesToIndent - 1); g++)
			{
				fileHandler.write("=");
			}
					
		}
	}
	
	/**
	 * This function handles the amount of backtracking that occurs.  In some cases, where the graph completes
	 * (x)% of the way, we may not want to backtrack all the way to an empty row set, as there is good reason to
	 * believe that the beginning rows likely were okay, and it was the rows added later on that led to errors.
	 * To accommodate this, this function will allow you to specify how many rows in the row set you want to keep.
	 * 
	 * @param currentRowSet 2D List of rows found
	 * @param numOfRowsToKeep Integer value containing the number of rows to keep in the rowset
	 * @return returnedRowSet Row set cut down to the listed percentage
	 */
	public List< List<Integer> > backtrackRows(int numRowsToKeep, List< List<Integer> > currentRowSet)
	{	
		if(numRowsToKeep == 0)
		{
			currentRowSet.clear();
			currentRowSet.add(generateRandomRow(true));
			return currentRowSet;
		}
		
		while(currentRowSet.size() != numRowsToKeep)
		{
			currentRowSet.remove(currentRowSet.size() - 1);
		}

		return currentRowSet;
	}
	
	/**
	 * This function calculates the number of rows in the row set in the following manner.  It uses the passed in
	 * number of rows found, and divides that value by the number of vertices (the class variable).  If the resulting
	 * percentage is greater than or equal to the threshold value passed into the method, the percentToKeep value is used
	 * to determine the number of rows to keep.
	 * 
	 * @param numberOfRowsFound - Integer value of number of rows found by the row set
	 * @param percentToKeep - Percentage of rows to keep
	 * @param percentThreshold - Percentage threshold that needs to obtained to keep the (x) number of rows
	 * @return
	 */
	public int calculateNumberOfRowsToKeep(int numberOfRowsFound, double percentToKeep, double percentThreshold)
	{
		double percentOfMaxRowsFound = ((double)numberOfRowsFound / (double)this.numOfVertices) * 100.0;
		//If the percent of rows found is greater than or equal to the specified threshold, keep the 
		//'percentToKeep' percentage number of rows.
		if(percentOfMaxRowsFound >= percentThreshold)
		{
			int numberOfRowsToKeep = (int) Math.floor(numberOfRowsFound * (percentToKeep / 100));
			return numberOfRowsToKeep;
		}
		else
		{
			//Otherwise if the threshold value is not met, start the row set all the way from the beginning.
			return 0;
		}
	}
	
	/**
	 * This function uses a combination of other functions to build the list of known correct rows recursively.
	 * 
	 * @param currentRowSet The 2D List of rows that are currently known to be correct.
	 * @return 2D List; Maximal set of rows
	 */
	public List< List<Integer> > buildRowListRecursively(List< List<Integer> > currentRowSet)
	{

		List<Integer> randomRow = new ArrayList<Integer>();
		List< List<Integer> > dotProductMatrix = new ArrayList< List<Integer> >();
		
		randomRow = generateRandomRowFromCurrentRowSet(currentRowSet);
		currentRowSet.add(randomRow);
		
		dotProductMatrix = dotProduct(currentRowSet);
		
		if(!lambdaMuCheck(currentRowSet, dotProductMatrix) || !muCheck(currentRowSet, dotProductMatrix))
		{
			currentRowSet.remove(currentRowSet.size() - 1);
		}
		else
		{
			System.out.println("Lambda and mu row checks passed.  Row " + currentRowSet.size() + " found.");
		}
		if(currentRowSet.size() == this.numOfVertices)
		{
			dotProductMatrix = dotProduct(currentRowSet);
			System.out.println("\nFOUND MAXIMAL SET.");
			System.out.println("Writing to file...");
			printMaximalRowsToFile(currentRowSet, dotProductMatrix, 0, 0, 0);
			maximalRowSetFound = true;
			System.out.println("Done.");
			return currentRowSet;
		}

		return buildRowListRecursively(currentRowSet);
	}
	
	/**
	 * This function uses a combination of other functions to build the list of known correct rows using a while loop.
	 * 
	 * @param currentRowSet The 2D List of rows that are currently known to be correct.
	 * @return 2D List; Maximal set of rows
	 */
	public List< List<Integer> > buildRowListWhileLoop(List< List<Integer> > currentRowSet)
	{
		long startTime = System.nanoTime(); //Start program run timer
		int runCounterWithoutRow = 0;
		while(!maximalRowSetFound)
		{
			List< List<Integer> > dotProductMatrix = new ArrayList< List<Integer> >();
			if(currentRowSet.size() == this.numOfVertices)
			{
				dotProductMatrix = dotProduct(currentRowSet);
				System.out.println("\nFOUND MAXIMAL SET.");
				System.out.println("Writing to file...");
				maximalRowSetFound = true;
				
				long endTime = System.nanoTime() - startTime;
				int hours = 0, minutes = 0;
				double seconds = (double) endTime / 1000000000.0;
				//There are 3600 seconds in an hour; if there are more than 3600 seconds we want to convert
				//that to an hour.
				if(seconds > 3600)
				{
					while(seconds >= 3600)
					{
						seconds -= 3600;
						hours++;
					}
				}
				//After the hours have been converted, we now want to transfer any seconds value over 60 into
				//a minute.
				if(seconds > 60)
				{
					while(seconds > 60)
					{
						seconds -= 60;
						minutes++;
					}
				}
				seconds = round(seconds, 2);
				
				printMaximalRowsToFile(currentRowSet, dotProductMatrix, hours, minutes, seconds);
				System.out.println("Done.");
				System.out.println("Run time: ");
				System.out.println("           Hours:   " + hours);
				System.out.println("           Minutes: " + minutes);
				System.out.println("           Seconds: " + seconds);
				return currentRowSet;
			}	

			List<Integer> randomRow = new ArrayList<Integer>();
				
			randomRow = generateRandomRowFromCurrentRowSet(currentRowSet);
			currentRowSet.add(randomRow);
				
			dotProductMatrix = dotProduct(currentRowSet);
						
//			if(lambdaCheck(currentRowSet, dotProductMatrix) && muCheck(currentRowSet, dotProductMatrix))
//			{
//				runCounterWithoutRow = 0;
//				System.out.println("Lambda and mu row checks passed.  Row " + currentRowSet.size() + " found.");
//			}
			if(lambdaMuCheck(currentRowSet, dotProductMatrix))
			{
				runCounterWithoutRow = 0;
				System.out.println("Lambda and mu row checks passed.  Row " + currentRowSet.size() + " found.");
			}
			else
			{
				currentRowSet.remove(currentRowSet.size() - 1);
				runCounterWithoutRow++;
			}
				
			if(runCounterWithoutRow == 1000000)
			{
				System.out.println("\n\n\n Backtracking... \n\n\n");
					
				runCounterWithoutRow = 0;
				currentRowSet = backtrackRows(0, currentRowSet);
			}
		}//end while(!maximalRowSetFound)
		
		return currentRowSet;

	}//end buildRowListWhileLoop() method

}//end public class SRGsolver