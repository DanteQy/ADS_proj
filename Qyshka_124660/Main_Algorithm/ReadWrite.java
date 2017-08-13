/* Dante Qyshka 124660 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadWrite {

    /* Metodo per la lettura in input del grado tramite file .dot acquisito
       da Standard Input */
    public static String readGraph(Graph g) throws FileNotFoundException {
        String g_name = "";
        String s = "";
        try (InputStreamReader isr = new InputStreamReader(System.in)) {
            int ch;

            while ((ch = isr.read()) != -1)
                s += (char) ch;
        } catch (IOException e) {
            e.printStackTrace();
        }

        //divide il nome del grafo dal testo
        String[] input = s.split("=", 2);

        g_name = input[0];

        //esegue uno split sul testo dividendo le parole al carattere SPACE
        String[] words = input[1].split("\\s+");
        Node tempNode;
        int count = 0;
        GraphUtils.quickSort(words, 0, words.length - 1);

        /*Complessità Theta(n)*/
        for (int i = 1; i < words.length; i++) {
            tempNode = new Node(count);
            tempNode.setLabel(words[i]);
            //euristica per creare immediatamente liste senza ripetizioni
            if (g.verifyNode(tempNode)) { // Controllo che il nodo non sia già presente nella lista
                g.addNode(tempNode);
                g.getNode(tempNode).setLabel(words[i]);
                count++;
            }


        }


        return g_name;
    }


    /* Metodo per la stampa del grafo generato in formato .dot */
    public static String printGraph(Graph g, String graphName) {
        String res="";
        String labels = "";
        String rel = "";

        for (int i = 0; i < g.getSize(); i++) {
            labels += setLabelRow(g.getNode(i)) + "\n";
            for (int j = 0; j < g.getNode(i).getSizeAdj(); j++) {
                rel += setRelRow(g.getNode(i).getId(), g.getNode(i).getNodeAdj(j).getId()) + "\n";
            }
        }

        res += "digraph " + graphName + "{\n"+labels+"\n"+rel+"\n}";
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
