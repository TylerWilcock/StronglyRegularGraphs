package stronglyRegularGraphs;


import java.util.ArrayList;
import java.util.List;

public class RandomRowTest 
{

	public static void main(String args[])
	{
		SRGsolver srgSolver = new SRGsolver(5, 2, 0, 1);
		
		int oneSpotCounter = 0, twoSpotCounter = 0, threeSpotCounter = 0, fourSpotCounter = 0, fiveSpotCounter = 0;
		long totalCounter = 0;
		List<Integer> randomRow = new ArrayList<Integer>();
		
		
		
		while(totalCounter < 10000000)
		{
			randomRow = srgSolver.generateRandomRow();
			for(int i = 0; i < randomRow.size(); i++)
			{
				switch(i)
				{
					case 0:
						if(randomRow.get(i) == 1)
						oneSpotCounter++;
					break;
					case 1:
						if(randomRow.get(i) == 1)
						twoSpotCounter++;
					break;
					case 2:
						if(randomRow.get(i) == 1)
						threeSpotCounter++;
					break;
					case 3:
						if(randomRow.get(i) == 1)
						fourSpotCounter++;
					break;
					case 4:
						if(randomRow.get(i) == 1)
						fiveSpotCounter++;
					break;
				}
				totalCounter++;
			}
		}
		System.out.println("\nOne's at spot 1: " + oneSpotCounter);
		System.out.println("\nOne's at spot 2: " + twoSpotCounter);
		System.out.println("\nOne's at spot 3: " + threeSpotCounter);
		System.out.println("\nOne's at spot 4: " + fourSpotCounter);
		System.out.println("\nOne's at spot 5: " + fiveSpotCounter);
		
		System.out.println("\n\nTotal number's counted: " + totalCounter);
	}

}
