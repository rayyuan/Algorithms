import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.TreeSet;


public class angry {

    public static void main(String[] args) throws IOException {
        angry instance = new angry();
        instance.findR();
        instance = null;
    }

    public int findR()
            throws IOException {
        int N=0;
        int K=0;
        TreeSet<Integer> baseSet = new TreeSet<Integer>();

        FileReader fileReader = new FileReader("angry.in");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String countStr[] = bufferedReader.readLine().split(" ");
        N = Integer.parseInt(countStr[0]);
        K = Integer.parseInt(countStr[1]);
        String line;
        int val;
        long minGap=Integer.MAX_VALUE;
        long sumGap;



        for (int i=0; i < N; i++)
        {
            line = bufferedReader.readLine();

            val = Integer.parseInt(line);
            baseSet.add(new Integer(val));
        }

        fileReader.close();

        Integer[] bases = baseSet.toArray(new Integer[N]);
        sumGap = bases[N-1] - bases[0];
        for (int i=0; (i+1)<N; i++){
            if ((bases[i+1].intValue() - bases[i].intValue()) < minGap){
                minGap = bases[i+1].intValue() - bases[i].intValue();
            }
        }

        int s;
        long mid =0;
        if (K >= N){
            mid = 0;
        }else if (K > 1){
            while (minGap <= sumGap)
            {
                mid = minGap + (sumGap - minGap)/2;
                s = solve(bases, K, N, mid);
                if (s == 0){
                    minGap = mid + 1;
                }else{
                    if (sumGap == mid){
                        break;
                    }
                    sumGap = mid;
                }
            }
        }else{
            mid = sumGap;
        }

        int radius = (int)Math.ceil((double)mid / 2);
        PrintWriter writer = new PrintWriter("angry.out");

        writer.println(radius);
        writer.close();
        return 1;
    }

    public int solve(Integer[] bases, int cows, int hays, long result)
    {
        int sum=0;
        int tmp, segs;

        segs = 0;
        for (int i=1; i < hays; i++){
            tmp = bases[i]-bases[i-1];
            if ((sum+tmp) > result){
                segs ++;
                sum = 0;
                if (segs > (cows-1)){
                    return 0;
                }
            }else{
                sum += tmp;
            }

        }
        return 1;
    }
}
