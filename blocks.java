import java.io.*;
import java.util.*;


public class blocks
{

  public static void main(String[] args)throws IOException
  {
    BufferedReader br = new BufferedReader(new FileReader("blocks.in"));
	PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("blocks.out")));
	StringTokenizer st;

	int numBoards=Integer.parseInt(br.readLine());
	String str;
	String word1;
	String word2;
	int[]array1=new int[26];
	int[]array2=new int[26];
	int[]finalArray=new int[26];

	for(int i=0;i<numBoards;i++)
	{

		str=br.readLine();
		word1=str.substring(0, str.indexOf(" "));
		word2=str.substring(str.indexOf(" ")+1,str.length());

		array1= getArray(word1);
		array2=getArray(word2);

		for (int j = 0; j < 26; ++j)
		{
		    finalArray[j] = finalArray[j]+Math.max(array1[j], array2[j]);
		}

	}

	for(int i=0;i<26;i++)
	{
		pw.println(finalArray[i]);
	}

	pw.close();
  }

  //get array of characters from line read in
  public static int[] getArray(String words)
  {
	  int[] counts = new int[26];

		for (int i = 0; i < words.length(); i++) {
		    char charAt = words.charAt(i);
		    counts[(int) charAt-97]++;
		}
	/*	for(int i=0;i<26;i++)
		{
			if(counts[i]>1)
			{
				counts[i]=1;
			}
		}
		*/
		return counts;
  }
}
