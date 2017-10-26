import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class cbarn {

    public static void main(String[] args) throws IOException {
        cbarn instance = new cbarn();
        instance.findE();
    }

    public void findE()
            throws IOException {
        int N=0;
        ArrayList<Integer> rlist = new ArrayList<Integer>();

        FileReader fileReader = new FileReader("cbarn.in");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String countStr = bufferedReader.readLine();
        N = Integer.parseInt(countStr);
        String line;
        int val;
        int lastempty = -1;
        int    totalempty = 0;
        int j=0;
        int[] a = new int[N];
        long energy = 0;
        int lastval = 0;
        long subes = 0;

        for (int i=0; i < N; i++){
            line = bufferedReader.readLine();

            val = Integer.parseInt(line);
            if (val == 0){
                subes ++;
                if (subes >= lastval ){
                    lastempty = i;
                    lastval = 0;
                }
                totalempty ++;
            }else {
                lastval += val - 1;
                subes = 0;
            }

            rlist.add(new Integer(val));

        }

        fileReader.close();
        j=0;
        if (lastempty >= 0 && lastempty < N-1){
            for (int i= lastempty + 1; i<N; i++){
                a[j++] = rlist.get(i).intValue();
            }
            for (int i=0; i<=lastempty; i++){
                a[j++] = rlist.get(i).intValue();
            }
        }else{
            for(Integer m : rlist){
                a[j++] = m;
            }
        }

        subes = 0;
        if (totalempty > 0){
        for (int i=N-1; i >= 0; i--){
            val = a[i];
            if (val == 0){
                subes ++;
            }else{
                if (val == subes){
                    a[i] = 0;
                    i++;
                    totalempty -= val - 1;
                }else if (val < subes){
                    a[i] = 0;
                    i += subes - val + 1;
                    totalempty -= val - 1;
                }else{
                    val --;
                    totalempty -= val;
                }

                for (j=val; j>0; j--){
                    energy += subes * subes;
                    subes --;
                }
                subes = 0;
            }
            if (totalempty == 0)
            {
                break;
            }
        }
        }
        PrintWriter writer = new PrintWriter("cbarn.out");

        writer.println(energy);
        writer.close();
    }


}
