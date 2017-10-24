import java.io.*;
import java.util.*;

public class cowdance
{
	public static int maxTime;
	public static int numCows;
	static ArrayList<Integer> dtlist;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("cowdance.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cowdance.out")));
		StringTokenizer st=new StringTokenizer(br.readLine());
		numCows = Integer.parseInt(st.nextToken());
		maxTime= Integer.parseInt(st.nextToken());

		String line;
		int val;
		dtlist = new ArrayList<Integer>();

		for (int i=0; i < numCows; i++)
		{
			line = br.readLine();
			val = Integer.parseInt(line);
			dtlist.add(val);
		}


		int l = 1;
		int r = numCows;
		int k = 0;

		while (r > l)
		{
			k = l + (r-l)/2;

			if (tryLength(k))
			{
				r = k;
			}
			else
			{
				if (k == l)
				{
					k ++;
					break;
				}
				l = k;
			}
		}

		br.close();
		pw.println(k);
		pw.close();
	}



	public static boolean tryLength(int k)
	{
		PriorityQueue<Integer> stage = new PriorityQueue<Integer>(k);
		for (int i=0; i<k; i++)
		{
			stage.add(dtlist.get(i));
		}

		for (int j=k; j<numCows; j++)
		{
			int sum = stage.poll() + dtlist.get(j);
			if (sum > maxTime)
			{
				return false;
			}

			stage.add(sum);
		}
		return true;
	}
}
