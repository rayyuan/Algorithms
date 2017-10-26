import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;

public class fencedin {

    public static void main(String[] args) throws IOException {
        fencedin instance = new fencedin();
        instance.findD();
    }

    public void findD()
            throws IOException {
        int A=0;
        int B=0;
        int n=0;
        int m=0;

        FileReader fileReader = new FileReader("fencedin.in");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String countStr[] = bufferedReader.readLine().split(" ");
        A = Integer.parseInt(countStr[0]);
        B = Integer.parseInt(countStr[1]);
        n = Integer.parseInt(countStr[2]);
        m = Integer.parseInt(countStr[3]);

        TreeSet<Integer> anSet = new TreeSet<Integer>();
        TreeSet<Integer> bmSet = new TreeSet<Integer>();


        int val;
        for (int i=0; i < n; i++){
            String line = bufferedReader.readLine();
            val = Integer.parseInt(line);
            anSet.add(new Integer(val));
        }

        for (int i=0; i < m; i++){
            String line = bufferedReader.readLine();
            val = Integer.parseInt(line);
            bmSet.add(new Integer(val));
        }

        fileReader.close();

        int[] vns = new int[n+2];
        int[] hms = new int[m+2];

        vns[0] = 0;
        vns[n+1] = A;
        hms[0] = 0;
        hms[m+1] = B;
        int idx = 1;
        for(Integer aval: anSet){
            vns[idx++] = aval.intValue();
        }
        idx = 1;
        for(Integer aval: bmSet){
            hms[idx++] = aval.intValue();
        }

        int vs = (m+1) * (n+1);
        Set<Integer> idSet = new HashSet<Integer>();

        Node[] nodes = new Node[vs];
        int ids = 0;
        int ds = -(n+1);
        int dw = -1;
        long fences = 0;
        PriorityQueue<Node> leftset = new PriorityQueue<Node>(vs, new Comparator<Node>(){
            public int compare(Node n1, Node n2)
            {
                return (n1.weight - n2.weight);
            }
        });

        for (int i=1; i < (m+2); i++){
            for (int j=1; j<(n+2); j++){
                nodes[ids] = new Node(ids, Integer.MAX_VALUE);
                if (ids == 0){
                    nodes[ids].weight = 0;
                }
                leftset.add(nodes[ids]);
                idSet.add(new Integer(ids));

                int newids = ids + dw;
                int westids = ids%(n+1) + dw;
                if (westids >= 0 && nodes[ids]!= null){
                    nodes[ids].addEdge(newids, hms[i]-hms[i-1]);
                    nodes[newids].addEdge(ids, hms[i]-hms[i-1]);
                }

                newids = ids + ds;
                if (newids >= 0 && nodes[ids]!= null){
                    nodes[ids].addEdge(newids, vns[j]- vns[j-1]);
                    nodes[newids].addEdge(ids, vns[j]- vns[j-1]);
                }
                ids ++;
            }
        }

        while(leftset.size() > 0){
            Node tmpnode = leftset.poll();
            fences += tmpnode.weight;
            idSet.remove(new Integer(tmpnode.id));
            for(int i=0; i<tmpnode.egidx; i++){
                Edge eg = tmpnode.elist[i];
                if (eg == null){
                    continue;
                }
                if (idSet.contains(new Integer(eg.id))){
                    if (nodes[eg.id].weight > eg.weight){
                        nodes[eg.id].weight = eg.weight;
                        leftset.remove(nodes[eg.id]);
                        leftset.add(nodes[eg.id]);
                    }
                }
            }
/*
            leftset.clear();

            for (int x : idSet){
                    leftset.add(nodes[x]);
            }
*/
        }

        PrintWriter writer = new PrintWriter("fencedin.out");

        writer.println(fences);
        writer.close();
    }

    public class Node{
        public int id;
        public int weight;
        public Edge[] elist;
        public int egidx;

        public Node(int id, int edge) {
            this.id = id;
            this.weight = edge;
            elist = new Edge[4];
            for (int i=0; i<4; i++){
                elist[i] = new Edge(0, 0);
            }
            egidx = 0;
        }

        public void addEdge(int dest, int edge){
            elist[egidx].id = dest;
            elist[egidx].weight = edge;
            egidx ++;

        }

    }

    public class Edge{
        public int id;
        public int weight;

        public Edge(int dest, int edge) {
            this.id = dest;
            this.weight = edge;
        }
    }

}
