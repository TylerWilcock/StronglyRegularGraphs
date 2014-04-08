package stronglyRegularGraphs;

import java.util.*;

public class Testing
{
	public static void main(String args[])
	{
		boolean rowsAreFound = false;
		
		int rowsFound = 0;
		
		int degree = 2, muValue = 1, lambdaValue = 0, numOfVertices = 5;

		List< List<Integer> > currentRowSet = new ArrayList< List<Integer> >();
		
		//currentRowSet.add(Arrays.asList(0, 0, 1, 0, 1));
		
		currentRowSet.add(Arrays.asList(0, 1, 0, 0, 1));
//		
//		currentRowSet.add(Arrays.asList(1, 0, 0, 1, 0));
//		currentRowSet.add(Arrays.asList(0, 1, 0, 1, 0));
//		currentRowSet.add(Arrays.asList(1, 0, 1, 0, 0));
		
		while(rowsAreFound == false)
		{
			boolean lambdaCheck = true, muCheck = true, zeroPlaceCheck = true;
			List< List<Integer> > dotProductMatrix = new ArrayList< List<Integer> >();
			if(rowsFound == 5)
			{
				rowsAreFound = true;
			}
			else
			{
				List<Integer> randomRow = new ArrayList<Integer>();
	
				Random rand = new Random();
				int oneCounter = 0;
				int onesLeft = degree;
				
				for(int i = 0; i < numOfVertices; i++)
				{
					int randomNumber = rand.nextInt(2);
					if(onesLeft > numOfVertices - (i + 1))
					{
						randomNumber = 1;
						oneCounter++;
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
	
				currentRowSet.add(randomRow);
	
				List<Integer> innerCurrentRow = new ArrayList<Integer>();
				List<Integer> outerCurrentRow = new ArrayList<Integer>();
				
				int matrixResult = 0;
				for(int i = 0; i < currentRowSet.size(); i++)
				{	
					outerCurrentRow = currentRowSet.get(i);
					List<Integer> emptyList = new ArrayList<Integer>();
					dotProductMatrix.addAll(Arrays.asList(emptyList));
	
					for(int j = 0; j < currentRowSet.size(); j++)
					{
						innerCurrentRow = currentRowSet.get(j);
	
						matrixResult = 0;
						for(int g = 0; g < innerCurrentRow.size(); g++)
						{
							matrixResult += outerCurrentRow.get(g) * innerCurrentRow.get(g);
						}
	
						dotProductMatrix.get(i).add(matrixResult);
					}
				}
	
				for(int g = 0; g < dotProductMatrix.size(); g++)
				{
					for(int a = 0; a < dotProductMatrix.get(g).size(); a++)
					{
						if(currentRowSet.get(g).get(a) == 0 && (a != g))
						{
							if(dotProductMatrix.get(g).get(a) != 0)
							{
								lambdaCheck = false;
							}
						}
					}
				}
	
				for(int g = 0; g < dotProductMatrix.size(); g++)
				{
					for(int a = 0; a < dotProductMatrix.get(g).size(); a++)
					{
						if(currentRowSet.get(g).get(a) == 1 && (a != g))
						{
							if(dotProductMatrix.get(g).get(a) != muValue)
							{
								muCheck = false;
							}
						}
					}
				}
				
//				if(currentRowSet.get(currentRowSet.size() - 1).get(currentRowSet.size() - 1) != 0)
//				{
//					zeroPlaceCheck = false;
//				}
	
				if(!lambdaCheck || !muCheck)
				{
					currentRowSet.remove(currentRowSet.size() - 1);
				}
				else
				{
					System.out.println("Row found.");
					rowsFound++;
				}
			}
		}
	}
}
