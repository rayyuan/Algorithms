import java.io.*;
import java.util.*;

public class hps
{

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader("hps.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("hps.out")));

		String line=br.readLine();
		int numThrows=Integer.parseInt(line);

		Element[] gestures = new Element[numThrows];
		int hSum = 0;
		int pSum = 0;
		int sSum = 0;

		for (int i=0; i < numThrows; i++)
		{
			line = br.readLine();
			if (line.equals("H"))
			{
				hSum ++;
			}
			else if (line.equals("P"))
			{
				pSum ++;
			}
			else if (line.equals("S"))
			{
				sSum ++;
			}
			gestures[i] = new Element(hSum, pSum, sSum);
		}


		int max = 0;
		for (int j=0; j < numThrows; j++)
		{
			int prevMax = Math.max(gestures[j].hSum, Math.max(gestures[j].pSum, gestures[j].sSum));
			int afterMax = Math.max(gestures[numThrows-1].hSum-gestures[j].hSum, Math.max(gestures[numThrows-1].pSum-gestures[j].pSum, gestures[numThrows-1].sSum-gestures[j].sSum));
			if (max < prevMax + afterMax)
			{
				max = prevMax + afterMax;
			}
		}

		br.close();
		pw.println(max);
		pw.close();
	}

	public static class Element
	{
		int hSum;
		int pSum;
		int sSum;

		public Element(int h, int p, int s)
		{
			hSum = h;
			pSum = p;
			sSum = s;

		}
	}

}
