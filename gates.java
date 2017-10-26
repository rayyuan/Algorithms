import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class gates {

	public static void main(String[] args) throws IOException {
		gates instance = new gates();
		instance.findGates();
		instance = null;
	}

	private void findGates() throws IOException {

		int numberOfSteps = 0;
		int x1 = 0;
		int y1 = 0;
		int x2 = 0;
		int y2 = 0;
		int gates = 0;
		String[] pos = new String[2];
		String pos1 = "";
		String pos2 = "";
		int noOverlap = 1;
		int enclosed = 0;

		Map<String, List<Fenceline> > positionGatesMap = new HashMap<>();
		List<Fenceline> linelist;

		FileReader fileReader = new FileReader("gates.in");
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String line = bufferedReader.readLine();
		numberOfSteps = Integer.parseInt(line);
		String steps = bufferedReader.readLine();
		
		for (int i = 0; i < numberOfSteps; i++) {
			switch (steps.charAt(i)) {
			case 'E':
				x2 = x1 + 1;
				break;
			case 'S':
				y2 = y1 - 1;
				break;
			case 'W':
				x2 = x1 - 1;
				break;
			case 'N':
				y2 = y1 + 1;
				break;
			}
			pos1 = new Integer(x1).toString() + new Integer(y1).toString();
			pos2 = new Integer(x2).toString() + new Integer(y2).toString();
			pos[0] = pos1;
			pos[1] = pos2;
			
			for (int j=0; j<2; j++){
				linelist = positionGatesMap.get(pos[j]);
				if ( linelist == null) {
					linelist = new ArrayList<Fenceline>();
					linelist.add(new Fenceline(x1, y1, x2, y2));
					positionGatesMap.put(pos[j], linelist);
				}else
				{
					for (Fenceline ln : linelist){
						if ((ln.x1 == x1 && ln.y1 == y1 && ln.x2 == x2 && ln.y2 == y2) ||
								(ln.x2 == x1 && ln.y2 == y1 && ln.x1 == x2 && ln.y1 == y2))	{
							noOverlap = 0; 
							break;
						}
					}
					if (noOverlap == 1){
						linelist.add(new Fenceline(x1, y1, x2, y2));
					}else{
						break;
					}
					if (noOverlap == 1 && j == 1){
						enclosed ++;
					}
				}
			
			}

			if (enclosed > 0){
				   gates ++;
			}
			enclosed = 0;
			noOverlap = 1;
			x1 = x2;
			y1 = y2;
		}

		fileReader.close();

		PrintWriter writer = new PrintWriter("gates.out");
		writer.println(gates);
		writer.close();
	}
	
	private class Fenceline{
		public int x1, y1, x2, y2;
		
		public Fenceline(int x1, int y1, int x2, int y2){
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}
	}
}