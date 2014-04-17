package stronglyRegularGraphs;

import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
*	 Class FileInput controls reading from a text file by opening the file,
*	 reading one line at a time, keeping track of the end of file, and handling
*	 I/O errors.  Please note that I did not personally write the openFileForRead, 
*	 closeFileForRead, or the readLine methods (I got them from example code written by a professor), 
*    so I don't take credit for those.
*/

public class FileHandler
{
	BufferedReader fileInput;
	private String fileName;
	boolean moreData;

	/**
	 * This constructor sets the file name that is passed in.
	 * 
	 * @param fileName Name of the file to read from or write to.
	 */
	public FileHandler(String fileName)
	{
		this.fileName = fileName;
	 	moreData = false;
	}
	
	/**
	 * Default constructor; assumes nothing is being passed in.
	 */
	public FileHandler() 
	{
		fileName = null;
		moreData = false;
	}
	
	
	/**
	 * This method allows the user to set the file name of the class.
	 * 
	 * @param fileName Name of file
	 */
	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}
	
	/**
	 * Checks to see if the open file has any more data.  Returns true or false
	 * @return True (There is more data) or False (There isn't anymore data)
	 */
	public boolean hasMoreData() 
	{
		  return moreData;
	}

	/**
	 * This method attempts to open the file for reading given by parameter fileName.
	 * @param fileName Name of file
	 */
	public void openFileForRead(String fileName) 
	{

		if(!fileName.equals(null)) 
		{
			this.fileName = fileName;
		 	try 
		 	{
		 		File inFile = new File(fileName);
		 	    FileReader fileReader = new FileReader(inFile);
				fileInput = new BufferedReader(fileReader);
				moreData = true;
			} 
		 	catch (IOException error) 
		 	{
		 		System.err.println ("An error occurred opening file " + this.fileName + "!\n");
		        System.exit(1);
		    }

		}
		  
	}// end openFile
	
	/**
	 * This method attempts to close the file if it has been read from.
	 * 
	 * @return true(success) or false(failure)
	 */
	public boolean closeFileFromRead() 
	{
		if(fileName != null)
		{
			try 
		    {
				fileInput.close();
				return true;
		    }
		    catch (IOException error) 
		    {
		    	System.err.println("An error occurred closing " + fileName+"!\n");
		    }
		}
		return false;
	}	

	/**
	 * This method reads and returns one line of the text file.  If the file is empty, an empty string is returned.
	 * 
	 * @return String; Next line of read file. 
	 */
	public String readLine()
	{
		String inputLine;

		if (fileName.equals(null)) 
		{
			System.err.println("There is no file opened. Please check the file name or open the file");
			System.exit(1);
		}

	    try
	    {
	    	inputLine = fileInput.readLine();
	       	if (inputLine == null) 
	       	{
	       		moreData = false;
	       	}
	        return inputLine;
	    }
	    catch (IOException error)
	    {
	    	System.err.println ("An error occurred reading from file " + fileName + "!\n");            
	        System.exit(1);
	    }
	    return null;
	}//end readLine() method
	
	/**
	 * This method writes the contents of a one dimensional integer list to a text file.  It returns True for success or False for failure.
	 * 
	 * @param <T> 
	 * @param inputList
	 * @return true(success) or false(failure)
	 */
	public <T> boolean writeList(List<T> inputList)
	{

		try 
		{
			PrintWriter writer = new PrintWriter(new FileWriter(fileName, true));
			for(int i = 0; i < inputList.size(); i++)
			{
				writer.print(inputList.get(i) + " ");
			}
			writer.close();
			return true;
		} 
		catch (IOException exception) 
		{
			System.err.println("IOException encountered in file " + fileName + " in method: writeList.");
			exception.printStackTrace();
			System.exit(1);
		}
		return false;
	}
	
	/**
	 * This method writes the contents of a two dimensional list to a text file.  It returns True for success or False for failure.
	 * 
	 * @param <T>
	 * @param inputList 2D List that will be written onto the file
	 * @return true(success) or false(failure)
	 */
	public <T> boolean write2DList(List< List<T> > inputList)
	{

		try 
		{
			PrintWriter writer = new PrintWriter(new FileWriter(fileName, true));
			for(int i = 0; i < inputList.size(); i++)
			{
				for(int j = 0; j < inputList.get(i).size(); j++)
				{
					writer.print(inputList.get(i).get(j) + " ");
				}
				writer.println();
			}
			writer.close();
			return true;
		} 
		catch (IOException exception) 
		{
			System.err.println("IOException encountered in file " + fileName + " in method: write2DList.");
			exception.printStackTrace();
			System.exit(1);
		}
		return false;
	}
	
	/**
	 * This method writes a line onto the file.
	 * 
	 * @param <T>
	 * @param Information that will be written onto file
	 * @return true(success) or false(failure)
	 */
	public <T> boolean write(T input)
	{

		try 
		{
			PrintWriter writer = new PrintWriter(new FileWriter(fileName, true));
			writer.print(input);
			writer.close();
			return true;
		} 
		catch (IOException exception) 
		{
			System.err.println("IOException encountered in file " + fileName + " in method: write.");
			exception.printStackTrace();
			System.exit(1);
		}
		return false;
	}
	
	/**
	 * This method writes a space onto the file.
	 * 
	 * @return true(success) or false(failure)
	 */
	public boolean writeSpace()
	{
		try 
		{
			PrintWriter writer = new PrintWriter(new FileWriter(fileName, true));
			writer.print(" ");
			writer.close();
			return true;
		} 
		catch (IOException exception) 
		{
			System.err.println("IOException encountered in file " + fileName + " in method write.");
			exception.printStackTrace();
			System.exit(1);
		}
		return false;
	}
	
	/**
	 * This method writes (n) number of spaces onto the file.
	 * 
	 * @param Integer Number of spaces to write onto the file.
	 * @return true(success) or false(failure)
	 */
	public boolean writeSpace(int numOfSpaces)
	{
		try 
		{
			PrintWriter writer = new PrintWriter(new FileWriter(fileName, true));
			for(int i = 0; i < numOfSpaces; i++)
			{
				writer.print(" ");
			}
			writer.close();
			return true;
		} 
		catch (IOException exception) 
		{
			System.err.println("IOException encountered in file " + fileName + " in method write.");
			exception.printStackTrace();
			System.exit(1);
		}
		return false;
	}

	/**
	 * This method writes a new blank line onto the file.
	 * 
	 * @return true(success) or false(failure)
	 */
	public boolean writeln()
	{

		try 
		{
			PrintWriter writer = new PrintWriter(new FileWriter(fileName, true));
			writer.println();
			writer.close();
			return true;
		} 
		catch (IOException exception) 
		{
			System.err.println("IOException encountered in file " + fileName + " in method write.");
			exception.printStackTrace();
			System.exit(1);
		}
		return false;
	}
	
	/**
	 * This method writes (x) new blank line(s) onto the file.
	 * 
	 * @param numOfBlankLines, Integer; Number of new blank lines to add
	 * @return true(success) or false(failure)
	 */
	public boolean writeln(int numOfBlankLines)
	{

		try 
		{
			PrintWriter writer = new PrintWriter(new FileWriter(fileName, true));
			for(int i = 0; i < numOfBlankLines; i++)
			{
				writer.println();
			}
			writer.close();
			return true;
		} 
		catch (IOException exception) 
		{
			System.err.println("IOException encountered in file " + fileName + " in method write.");
			exception.printStackTrace();
			System.exit(1);
		}
		return false;
	}
	
}//end FileHandler class