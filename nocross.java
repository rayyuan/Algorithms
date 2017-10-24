import java.io.*;
import java.util.*;

public class nocross {

	public static void main(String[]args)throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader("nocross.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("nocross.out")));

		int N=Integer.parseInt(br.readLine());
		int [][] dp= new int[N+1][N+1];
		int[]arr1=new int[N+1];
		int[]arr2=new int[N+1];

		for(int i=1;i<=N;i++)
		{
			arr1[i]=Integer.parseInt(br.readLine());
		}
		for(int i=1;i<=N;i++)
		{
			arr2[i]=Integer.parseInt(br.readLine());
		}

		for(int i=1;i<=N;i++)
		{
			for(int j=1;j<=N;j++)
			{
				if(Math.abs(arr1[i]-arr2[j])<=4)
					{

					dp[i][j]=Math.max(dp[i-1][j-1]+1, dp[i][j]);

					}

				dp[i][j]=Math.max(dp[i][j],(Math.max(dp[i-1][j],dp[i][j-1])));

			}
		}

			pw.println(dp[N][N]);
			pw.close();

	}
}
