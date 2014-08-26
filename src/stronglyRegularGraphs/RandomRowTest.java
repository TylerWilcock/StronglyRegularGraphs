package stronglyRegularGraphs;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RandomRowTest 
{

	public static void main(String args[])
	{
		long startTime = System.currentTimeMillis();
		SRGsolver srgSolver = new SRGsolver("RandomRowTest.txt", 20, 18, 16, 18);
		
		long totalCounter = 0;
		int[] spotCounter = new int[20];
		
		List<Integer> randomRow = new ArrayList<Integer>();
		List< List<Integer> > currentRowset = new ArrayList<List <Integer> >();
		currentRowset.addAll(Arrays.asList(srgSolver.generateRandomRow(true)));
		currentRowset.addAll(Arrays.asList(srgSolver.generateRandomRow(true)));

		while(totalCounter < 9000000)
		{
			randomRow = srgSolver.generateRandomRowFromCurrentRowSet(currentRowset);
			for(int i = 0; i < randomRow.size(); i++)
			{
				switch(i)
				{
					case 0:
						if(randomRow.get(i) == 1)
							spotCounter[0]++;
					break;
					case 1:
						if(randomRow.get(i) == 1)
							spotCounter[1]++;
					break;
					case 2:
						if(randomRow.get(i) == 1)
							spotCounter[2]++;
					break;
					case 3:
						if(randomRow.get(i) == 1)
							spotCounter[3]++;
					break;
					case 4:
						if(randomRow.get(i) == 1)
							spotCounter[4]++;
					break;
					case 5:
						if(randomRow.get(i) == 1)
							spotCounter[5]++;
					break;
					case 6:
						if(randomRow.get(i) == 1)
							spotCounter[6]++;
					break;
					case 7:
						if(randomRow.get(i) == 1)
							spotCounter[7]++;
					break;
					case 8:
						if(randomRow.get(i) == 1)
							spotCounter[8]++;
					break;
					case 9:
						if(randomRow.get(i) == 1)
							spotCounter[9]++;
					break;
					case 10:
						if(randomRow.get(i) == 1)
							spotCounter[10]++;
					break;
					case 11:
						if(randomRow.get(i) == 1)
							spotCounter[11]++;
					break;
					case 12:
						if(randomRow.get(i) == 1)
							spotCounter[12]++;
					break;
					case 13:
						if(randomRow.get(i) == 1)
							spotCounter[13]++;
					break;
					case 14:
						if(randomRow.get(i) == 1)
							spotCounter[14]++;
					break;
					case 15:
						if(randomRow.get(i) == 1)
							spotCounter[15]++;
					break;
					case 16:
						if(randomRow.get(i) == 1)
							spotCounter[16]++;
					break;
					case 17:
						if(randomRow.get(i) == 1)
							spotCounter[17]++;
					break;
					case 18:
						if(randomRow.get(i) == 1)
							spotCounter[18]++;
					break;
					case 19:
						if(randomRow.get(i) == 1)
							spotCounter[19]++;
					break;
				}
				totalCounter++;
			}
		}

		
		for(int i = 0; i < spotCounter.length; i++)
		{
			System.out.println("One's at spot " + (i + 1) + ": " + spotCounter[i]);
		}
		
		System.out.println("\n\nTotal row's counted: " + totalCounter);
		System.out.println("Program run time (milliseconds): " + (System.currentTimeMillis() - startTime));
	}

}
