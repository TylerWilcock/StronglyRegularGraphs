package stronglyRegularGraphs;

import java.util.List;
import java.util.ArrayList;

public class ListTest 
{
	public static void main(String args[])
	{
		List<Integer> testingList = new ArrayList<Integer>(10);
		
		
		testingList.add(1);
		testingList.add(2);
		testingList.add(3);
		testingList.add(4);
		testingList.add(0, 300);
		for(int i = 0; i < testingList.size(); i++)
		{
			System.out.println(testingList.get(i));
		}
	}

}
