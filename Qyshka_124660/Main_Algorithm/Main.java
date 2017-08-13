/* Dante Qyshka 124660 */

import java.io.FileNotFoundException;

public class Main {

    private static Graph g = new Graph();
    private static String g_Name = null;

    public static void main(String[] args) throws FileNotFoundException {

        g_Name = ReadWrite.readGraph(g); 
        GraphUtils.connectGraph(g);
        String dot = ReadWrite.printGraph(g, g_Name);
        g.setnode_list(GraphUtils.longestPath(g));
        System.out.println(GraphUtils.getMaxDist()+"\n"+dot);


    }
}
