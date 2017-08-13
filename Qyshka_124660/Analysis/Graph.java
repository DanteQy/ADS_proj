/* Dante Qyshka 124660 */

import java.util.ArrayList;

public class Graph {

    private ArrayList<Node> node_list;

    /* Costruttore della lista dei nodi vuota */
    public Graph() {
        node_list = new ArrayList<Node>();
    }

    /* Metodo per ottenere la dimensione della lista */
    public int getSize() {
        return node_list.size();
    }

    /*Modifica la lista col metodo set*/
    public void setnode_list(ArrayList<Node> nl) {
        this.node_list = nl;
    }

    /*Metodo per ottenere il nodo basandosi sulla posizione passata*/
    public Node getNode(int position) {
        return node_list.get(position);
    }

    /*Ritorna il nodo ricercato basandosi sul paramtero Node passato
    * Complessità: O(n)*/
    public Node getNode(Node nodeRes) {
        Node temp;
        for (int i = 0; i < node_list.size(); i++) {
            temp = node_list.get(i);
            if ((temp.getId()).equals(nodeRes.getId())) {
                return temp;
            }
        }
        return null;
    }

    /* controlla che il nodo passato esista nella lista controllandone l'etichetta
    Complessità: O(n)*/
    public boolean verifyNode(Node nodeCheck) {
        Node temp;
        for (int i = 0; i < node_list.size(); i++) {
            temp = node_list.get(i);
            if ((temp.getLabel()).equals(nodeCheck.getLabel())) {
                return false;
            }
        }
        return true;
    }

    /* Aggiunge un nodo alla lista */
    public void addNode(Node nodeToAdd) {
        node_list.add(nodeToAdd);
    }

}
