import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class div7 {
	
	public static void main(String[] args) throws IOException {
		div7 instance = new div7();
		int result = instance.findCows();
		PrintWriter writer = new PrintWriter("div7.out");
		writer.println(result);
		writer.close();
		instance = null;
	}

	private int findCows() throws IOException {

		int numberOfCows = 0;
		long sum = 0;
		int result = 0;
		int modval;
		
		FileReader fileReader = new FileReader("div7.in");
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String line = bufferedReader.readLine();
		numberOfCows = Integer.parseInt(line);
		int[] cownums = new int[numberOfCows];
		long[] sumOfCows = new long[numberOfCows];
		int[] mods = new int[numberOfCows];
		Map<Integer, List<Integer>> amap = new HashMap<>();
		List<Integer> idxlist; 
		
		int counter = 0;
		for (counter = 0; counter < numberOfCows; counter++) {
			line = bufferedReader.readLine();
			int cowNumber = Integer.parseInt(line);
			cownums[counter] = cowNumber;
			sum += cowNumber;
			sumOfCows[counter] = sum;
			modval = (int) (sum % 7);
			mods[counter] = modval; 
			Integer key = new Integer(modval);
			idxlist = amap.get(key);
			if (idxlist == null){
				idxlist = new ArrayList<Integer>();
				amap.put(key, idxlist);
			}
			idxlist.add(new Integer(counter));			
		}
		fileReader.close();
	
		result = 0;
		int max = 0;
		int val=0;
		for (int i=0; i<7; i++){
			idxlist = amap.get(new Integer(i));
			if (idxlist != null){
				int len;
				len = idxlist.size();
				
				if (i == 0){
					result = (Integer)(idxlist.get(len-1)).intValue() + 1;
					if (result > max){
						max = result;
					}

				}else if (len > 1){
					result = ((Integer)(idxlist.get(len-1))).intValue() - 
							((Integer)(idxlist.get(0))).intValue();
					
					if (result > max){
						max = result;
					}
				}else if (len == 1)
				{
					val = cownums[idxlist.get(0).intValue()];
					if (val %7 == 0){
						result = 1;
						if (result > max){
							max = result;
						}
					}	
				}
			}		
		}
		return max;
	}

}