import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class cbarn2 {

    public static void main(String[] args) throws IOException {
        cbarn2 instance = new cbarn2();
        instance.findD();
    }

    public void findD()
            throws IOException {
        int N=0;
        int K=0;

        FileReader fileReader = new FileReader("cbarn2.in");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String countStr[] = bufferedReader.readLine().split(" ");
        N = Integer.parseInt(countStr[0]);
        K = Integer.parseInt(countStr[1]);
        int[] rc = new int[N];
        int[] orig = new int[N];
        int val;
        int max = 0;
        int maxidx = 0;
        int result;
        int m=0;
        int min = Integer.MAX_VALUE;

        for (int i=0; i < N; i++){
            String line = bufferedReader.readLine();
            val = Integer.parseInt(line);
            orig[i] = val;
            if (val > max){
                max = val;
                maxidx = i;
            }
        }

        fileReader.close();

        for (maxidx=0; maxidx<N; maxidx++){
            m = 0;
            for (int i= maxidx; i<N; i++){
                rc[m++] = orig[i];
            }
            for (int i=0; i<maxidx; i++){
                rc[m++] = orig[i];
            }

            result = partition(rc, N, K);
            if (result < min){
                min = result;
            }
        }
        PrintWriter writer = new PrintWriter("cbarn2.out");

        writer.println(min);
        writer.close();
    }

    public int partition(int[] rc, int N, int K){
        int[][] dis = new int[N][N];
        int[][] dp = new int[N][K+1];

        for (int i=0; i < N; i++){
            dis[i][i] = 0;
            for (int j=i+1; j<N; j++){
                dis[i][j] = dis[i][j-1] + (j-i)*rc[j];
            }
        }

        for (int i=0; i< N; i++){
                dp[i][1] = dis[i][N-1];
        }

        if (K == 1){
            return dp[0][1];
        }
        for(int i= N-1; i >= 0; i--){
            for(int j=2; j <= K; j++){
                dp[i][j] = Integer.MAX_VALUE;
                for(int l=N-j; l >= i; l--){
                    dp[i][j]=Math.min(dp[i][j], dis[i][l]+dp[l+1][j-1]);
                }
            }
        }


        return (dp[0][K]);
    }


}
