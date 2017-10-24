import java.io.*;
import java.util.*;

public class visitfj
{
	public static void main(String[] args) throws IOException
	{
		visitfj instance = new visitfj();
		instance.findMinTime();

		instance = null;
	}

	public void findMinTime() throws IOException
	{
		int	N;
		int T;

		BufferedReader br = new BufferedReader(new FileReader("visitfj.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("visitfj.out")));
		String line[] = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		T = Integer.parseInt(line[1]);

		int[][] grid = new int[N][N];

		for (int i=0; i<N; i++)
		{
			line = br.readLine().split(" ");
			for (int j=0; j<N; j++)
			{
				grid[i][j] = Integer.parseInt(line[j]);
			}
		}

		int[][] costTime = new int[N][N];

		for (int i=0; i<N; i++)
			for (int j=0; j<N; j++)
				costTime[i][j] = Integer.MAX_VALUE;

		int[] dx = {-3, -2, -2, -1, -1, -1, 0, 0, 0, 0, 1, 1, 1, 2, 2, 3};
	  int[] dy = {0, -1, 1, -2, 2, 0, -3, 3, -1, 1, 0, -2, 2, -1, 1, 0};

	    TreeSet<cell> treeSet = new TreeSet<cell>(
	    		new Comparator<cell>()
	    			{
	    				public int compare(cell a, cell b)
	    				{
	    					if (a.time == b.time)
	    			    	{
	    			    		if (a.x != b.x)
	    			    			return (a.x - b.x);
	    			    		else
	    			    			return (a.y - b.y);
	    			    	}
	    					return (a.time - b.time);
	    				}
	    			}

	    		);

	    treeSet.add(new cell(0,0,0));
	    costTime[0][0] = 0;

	    while (!treeSet.isEmpty())
	    {
	    	cell loc = treeSet.pollFirst();

	    	for (int i=0; i<16; i++)
	    	{
	    		int x = loc.x + dx[i];
	    		int y = loc.y + dy[i];

	    		if (x<0 || x>=N || y<0 || y>=N)
	    		{
	    			if (x == N-1 && y >= N)
	    			{
	    				if (costTime[N-1][N-1] > costTime[loc.x][loc.y] + (N-1-loc.y)*T + Math.abs(dx[i])*T)
	    				{
	    					if (costTime[N-1][N-1] != Integer.MAX_VALUE)
	    					{
	    						treeSet.remove(new cell(N-1, N-1, costTime[N-1][N-1]));
	    					}

	    					costTime[N-1][N-1] = costTime[loc.x][loc.y] + (N-1-loc.y)*T +  Math.abs(dx[i])*T;

	    					treeSet.add(new cell(N-1, N-1, costTime[N-1][N-1]));
	    				}
	    			}else if (y== N-1 && x >= N)
	    			{
	    				if (costTime[N-1][N-1] > costTime[loc.x][loc.y] + (N-1-loc.x)*T + Math.abs(dy[i])*T)
	    				{
	    					if (costTime[N-1][N-1] != Integer.MAX_VALUE)
	    					{
	    						treeSet.remove(new cell(N-1, N-1, costTime[N-1][N-1]));
	    					}

	    					costTime[N-1][N-1] = costTime[loc.x][loc.y] + (N-1-loc.x)*T + Math.abs(dx[i])*T;

	    					treeSet.add(new cell(N-1, N-1, costTime[N-1][N-1]));
	    				}
	    			}
	    			continue;
	    		}
	    		if (costTime[x][y] > costTime[loc.x][loc.y] + grid[x][y] + 3*T)
				{
					if (costTime[x][y] != Integer.MAX_VALUE)
					{
						treeSet.remove(new cell(x,y,costTime[x][y]));
					}
					costTime[x][y] = costTime[loc.x][loc.y] + grid[x][y] + 3*T;
					treeSet.add(new cell(x, y, costTime[x][y]));
				}
	    	}

	    }

		pw.println(costTime[N-1][N-1]);
		pw.close();

	}

	public class cell
	{
	    int x, y;
	    int time;
	    cell(int x, int y, int time)
	    {
	    	this.x = x;
	    	this.y = y;
	    	this.time = time;
	    }
	};
}
