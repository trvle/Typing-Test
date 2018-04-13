package application;

import java.io.*;
import java.util.*;

/*
 * This object contains the information for the most recent Typing Test session and all functionality handling
 *  the words, and the calculation of words per minute.
 */
public class TestSession {
	
	private List<String> allWords;				//raw list of words from source text file in their original order
	private List<String> wordList;				//list of words that are shuffled every new session
	private int time;							//time measured in seconds
	private int numCharInList;					//the total number of characters that make up the words in the word list
	private int numTyped;						//the current number of characters the user has typed
	private boolean isComplete;					//tracks if the test session has finished or not
	private double finalWPM;					//the final words-per-minute after the test session has concluded
	

	//Constructor class; initializes all the relevant starting variables as well as 
	public TestSession() throws IOException
	{
		time = 0;
		isComplete = false;
		
		allWords = readFile("words.txt");
		wordList = allWords;
		Collections.shuffle(wordList);
		
		numCharInList = calculateNumCharInList(wordList);
	}
	
	//Calculates the total number of characters in the word list, and also adds in the number of spaces
	// that the user will be typing in
	private int calculateNumCharInList(List<String> wordList)
	{
		for (String element : wordList)
		{
			numCharInList = numCharInList + element.length() + 1;
		}
		numCharInList--;
		return numCharInList;
	}
	
	public List<String> getWordList()
	{
		return wordList;
	}
	
	public int getTime()
	{
		return time;
	}
	
	public void incremTime()
	{
		time++;
	}
	
	public int getNumCharInList()
	{
		return numCharInList;
	}

	public boolean isComplete()
	{
		return isComplete;
	}

	public void setComplete(boolean isComplete)
	{
		this.isComplete = isComplete;
	}
	
	//used to calculate how many words have been typed (and store the number of characters typed) by taking the number of
	// characters typed and averaged out by dividing by 5. This accounts for some words being short and some being long.
	public int calcWordsTyped(int numTyped)
	{
		this.numTyped = numTyped;
		return numTyped/5;
	}
	
	//calculates words per minute (WPM). WPM are calculated by taking the total number of characters typed
	// (including spaces and punctuation) averaged out and then divided by the length of the test duration in minutes.
	//Variables are doubles instead of ints for accuracy
	public double calcWPM()
	{
		double avgWords = (double)numTyped/5;
		double timeConvMin = (double)time/60;
		finalWPM = avgWords/timeConvMin;
		return finalWPM;
	}
	
	public double getFinalWPM()
	{
		return finalWPM;
	}
	
	//reads the .txt file containing list of words to be typed and returns them as a list of strings
	public List<String> readFile(String fileName) throws IOException
	{
	    BufferedReader br = new BufferedReader(new FileReader(fileName));
	    try
	    {
	        List<String> allWords = new ArrayList<String>();
	        String line = br.readLine();
	        
	        while (line != null)
	        {
	            allWords.add(line);
	            line = br.readLine();
	        }
	        return allWords;
	    } catch (IOException e)
	    {
	        System.out.println("Caught IOException: " +  e.getMessage());
	        e.printStackTrace();
	    } finally
	    {
	        br.close();
	    }
	    return null;
	}
}
