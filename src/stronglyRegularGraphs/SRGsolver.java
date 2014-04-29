package stronglyRegularGraphs;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
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
 * Other TODO: Implement program run time counter.  Hours, minutes, seconds.  Write to text file
 * 					-Figure out any other changes to the text file writing
 * 					-Redo current text files so the above is included
 * 
 * 			   Check generated test files against the graphs I solved on paper to confirm algorithm accuracy
 * 
 * 			   Create Excel file containing 100% complete list of found SRGs
 * 					-Include whether or not I have built them using green/red boxes (or something that looks nice)
 * 
 * 				Parallelize slow parts of the code using ForkJoin
 */

public class SRGsolver 
{
	private int numOfVertices, degree, lambdaValue, muValue;
	private boolean maximalRowSetFound = false;
	private String fileName;
	
	/**
	 * Initializes the class variables containing the file name to write to, number of vertices in the SRG, 
	 * the degree value (which is the number of edges connected to each point), the lambda value (number of edges shared with each ponts adjacent vertices),
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
	 * @return List Randomly generated row of 1's and 0's 
	 */
	public List<Integer> generateRandomRow()
	{
		int[] tempValueArray = new int[numOfVertices];
		List<Integer> returnedRow = new ArrayList<Integer>(numOfVertices);
		List<Integer> placedIndices = new ArrayList<Integer>(numOfVertices);
		
		Random rand = new Random();
		int oneCounter = 0;
		int onesLeft = this.degree;
		
		//Generate (numOfVertices) random numbers
		for(int i = 0; i < numOfVertices; i++)
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
			
			/*
			 * To make the row generation truly random, we must also randomize the placement of the 
			 * random number we generated in the previous step.  This is accomplished by keeping track
			 * of spots that are already placed in List object 'placedIndices'.  A random place is generated,
			 * and is checked against the values in the 'placedIndices' List.  If it doesn't match any of those
			 * values, the previously generated random number is placed at the randomly generated index.
			 */
			boolean numberIsPlaced = false;
			
			while(!numberIsPlaced)
			{
				int randomPlace = rand.nextInt(numOfVertices);
				if(!placedIndices.isEmpty())
				{
					boolean indexNotAlreadyUsed = true;
					for(int x = 0; x < placedIndices.size(); x++)
					{
						if(randomPlace == placedIndices.get(x))
						{
							indexNotAlreadyUsed = false;
						}
					}
					if(indexNotAlreadyUsed)
					{
						tempValueArray[randomPlace] = randomNumber;
						placedIndices.add(randomPlace);
						numberIsPlaced = true;
					}
				}
				else
				{
					tempValueArray[randomPlace] = randomNumber;
					placedIndices.add(randomPlace);
					numberIsPlaced = true;	
				}
			}
		}
		
	   /*
		* Move all the values stored in the array into a List object.
		* While it was easier to work with an array in the previous steps,
		* we want to return a List object as the rest of the code works with
		* List objects.
		*/
		for(int k = 0; k < tempValueArray.length; k++)
		{
			returnedRow.add(tempValueArray[k]);
		}
		
		return returnedRow;
	}//end generateRandomRow method method
	
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
		int[] tempValueArray = new int[numOfVertices];
		List<Integer> returnedRow = new ArrayList<Integer>(numOfVertices);
		List<Integer> placedIndices = new ArrayList<Integer>(numOfVertices);
		
		Random rand = new Random();
		int oneCounter = 0;
		int onesLeft = this.degree;
		int numbersLeftToPlace = numOfVertices;
		
		//We already know the first values of this random row based on the corresponding column from currentRowSet, so this for loop adds those numbers in.
		//Example: if row 1 is: 0, 1, 0, 1, 0 - We know that the first number in row 2 is a '1'.
		for(int z = 0; z < currentRowSet.size(); z++)
		{
			tempValueArray[z] = currentRowSet.get(z).get(currentRowSet.size());
			if(tempValueArray[z] == 1)
			{
				oneCounter++;
				onesLeft--;
			}
			placedIndices.add(z);
			numbersLeftToPlace--;
		}
		
		/*
		* These 2 lines of code add in a '0' at the diagonal of this random row, as we know for a fact that in a correct row there will be a '0' in that spot.
		* Example:  The third row in current row set has the following dimensions in the 2D List/adjacency matrix:
		* Row 2, Column 2 (Remember counting starts from 0).
		* This means that in that spot, it is in the diagonal and therefore must be a '0'.
		*
		* foundRowCounter points to this spot, so I use that to set the value to 0.
		*/
		tempValueArray[currentRowSet.size()] = 0;
		placedIndices.add(currentRowSet.size());
		numbersLeftToPlace--;
		
		//Generate (numOfVertices) random numbers
		for(int i = 0; i < numbersLeftToPlace; i++)
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
			
			
			/*
			 * To make the row generation truly random, we must also randomize the placement of the 
			 * random number we generated in the previous step.  This is accomplished by keeping track
			 * of spots that are already placed in List object 'placedIndices'.  A random place is generated,
			 * and is checked against the values in the 'placedIndices' List.  If it doesn't match any of those
			 * values, the previously generated random number is placed at the randomly generated index.
			 */
			boolean numberIsPlaced = false;
			
			while(!numberIsPlaced)
			{
				int randomPlace = rand.nextInt(numOfVertices);
				if(!placedIndices.isEmpty())
				{
					boolean indexNotAlreadyUsed = true;
					for(int x = 0; x < placedIndices.size(); x++)
					{
						if(randomPlace == placedIndices.get(x))
						{
							indexNotAlreadyUsed = false;
						}
					}
					if(indexNotAlreadyUsed)
					{
						tempValueArray[randomPlace] = randomNumber;
						placedIndices.add(randomPlace);
						numberIsPlaced = true;
					}
				}
				else
				{
					tempValueArray[randomPlace] = randomNumber;
					placedIndices.add(randomPlace);
					numberIsPlaced = true;	
				}
			}
		}
		
		/*
		* Move all the values stored in the array into a List object.
		* While it was easier to work with an array in the previous steps,
		* we want to return a List object as the rest of the code works with
		* List objects.
		*/
		for(int k = 0; k < tempValueArray.length; k++)
		{
			returnedRow.add(tempValueArray[k]);
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
	 * and the new random row contains the lambda values in the right place.  Any time there is a '1' in
	 * the (c) by (c) row set, the lambda value should be in that same position in the dotProductMatrix.  
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
				fileHandler.writeSpace();
				fileHandler.write("|");
				fileHandler.writeSpace();
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
		
		if(!lambdaCheck(currentRowSet, dotProductMatrix) || !muCheck(currentRowSet, dotProductMatrix))
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
			}	
			else
			{
				List<Integer> randomRow = new ArrayList<Integer>();
				
				randomRow = generateRandomRowFromCurrentRowSet(currentRowSet);
				//randomRow = generateRandomRow();
				currentRowSet.add(randomRow);
				
				dotProductMatrix = dotProduct(currentRowSet);
						
				if(lambdaCheck(currentRowSet, dotProductMatrix) && muCheck(currentRowSet, dotProductMatrix))
				{
					runCounterWithoutRow = 0;
					System.out.println("Lambda and mu row checks passed.  Row " + currentRowSet.size() + " found.");
				}
				else
				{
					currentRowSet.remove(currentRowSet.size() - 1);
					runCounterWithoutRow++;
				}
				
				if(runCounterWithoutRow == 900000)
				{
					System.out.println("\n\n\n\n Backtracking... \n\n\n\n");
					while(currentRowSet.size() != 0)
					{
						currentRowSet.remove(currentRowSet.size() - 1);
					}
					runCounterWithoutRow = 0;
				}
			}
		}
		return currentRowSet;

	}

}//end public class SRGsolver
