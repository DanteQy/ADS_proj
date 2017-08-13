/* Dante Qyshka 124660 */


import java.io.IOException;
import java.io.InputStreamReader;

public class ReadWrite {

    public static String getString(int numberOfWords){
        RNG r = new RNG(17);
        return r.generateInput(numberOfWords);
    }
    /* Metodo per la lettura in input del grado tramite file .dot acquisito
       da Standard Input */
    public static String readGraph(Graph myGraph, int n) {
        String g_name = "";
        String s= "";


        g_name = "T";
        String w = getString(n);
        //esegue uno split sul testo dividendo le parole al carattere SPACE
        String[] words = w.split("\\s+");
        Node tempNode;
        int count = 0;
        GraphUtils.quickSort(words, 0, words.length - 1);

        /*Complessità Theta(n)*/
        for (int i = 1; i < words.length; i++) {
            tempNode = new Node(count);
            tempNode.setLabel(words[i]);
            //euristica per creare immediatamente liste senza ripetizioni
            if (myGraph.verifyNode(tempNode)) { // Controllo che il nodo non sia già presente nella lista
                myGraph.addNode(tempNode);
                myGraph.getNode(tempNode).setLabel(words[i]);
                count++;
            }


        }


        return g_name;
    }


    /* Metodo per la stampa del grafo generato in formato .dot */
    public static String printGraph(Graph myGraph, String graphName) {
        String res="";
        String labels = "";
        String rel = "";

        for (int i = 0; i < myGraph.getSize(); i++) {
            labels += setLabelRow(myGraph.getNode(i)) + "\n";
            for (int j = 0; j < myGraph.getNode(i).getSizeAdj(); j++) {
                rel += setRelRow(myGraph.getNode(i).getId(), myGraph.getNode(i).getNodeAdj(j).getId()) + "\n";
            }
        }

        res += "digraph " + graphName + "{\n"+labels+"\n"+rel+"\n}";
        res = "";
        return res;
    }

    //crea le label
    private static String setLabelRow(Node n) {
        /*a [label="se"];*/
        String s = "";
        s += n.getId() + " ";
        s += "[label=\"" + n.getLabel() + "\"];";
        return s;
    }

    //crea le righe delle relazioni
    private static String setRelRow(String id1, String id2) {
        /*e -> a;*/
        String s = id1 + " -> " + id2 + ";";
        return s;
    }
}
