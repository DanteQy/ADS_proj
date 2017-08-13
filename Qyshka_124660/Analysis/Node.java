/* Dante Qyshka 124660 */

import java.util.ArrayList;

public class Node {

    /* Lista di adiacenza del nodo */
    private ArrayList<Node> adj;
    private ArrayList<Node> adj_parents;
    /* attributi descrittori del nodo */
    private String id;
    private String label;

    private boolean child;
    private boolean leaf;

    private int distance;

    /* Costruttore del nodo */
    public Node(int id) {
        this.id = String.valueOf(id);
        label = null;
        child = false;
        leaf = true;
        adj = new ArrayList<Node>();
        adj_parents = new ArrayList<Node>();
    }

    /* Metodi get e set */
    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public boolean isChild() {
        return child;
    }

    public void setChild() {
        this.child = true;
    }

    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf() {
        this.leaf = false;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getDistance() {
        return distance;
    }

    public int getSizeAdj() {
        return adj.size();
    }

    public Node getNodeAdj(int position) {
        return adj.get(position);
    }

    /* Metodo per aggiungere un nodo alla adj */
    public void addAdj(Node nodeForAdj) {

    /* controlla se è vuota  */
        boolean firstNode = true;

        for (int i = 0; i < adj.size(); i++) {
            if (((adj.get(i)).getId()).equals(nodeForAdj.getId())) {
                firstNode = false;
            }
        }

        if (firstNode) {
            adj.add(nodeForAdj);
        }
    }


    /******************************************
     *****FUNZIONI PARENT***Per creare il grafo invertito********************************/
    //Nodi adiacentidi cui è figlio. _P = parents
    public int getSizeAdj_P() {
        return adj_parents.size();
    }

    public Node getNodeAdj_P(int position) {
        return adj_parents.get(position);
    }

    public void addAdj_P(Node nodeForAdj) {

    /* Verifico prima che il nodo non sia già presente in adj */
        boolean firstNode = true;

        //complessità Theta(adj_parents.size())
        for (int i = 0; i < adj_parents.size(); i++) {
            if (((adj_parents.get(i)).getId()).equals(nodeForAdj.getId())) {
                firstNode = false;
            }
        }

        if (firstNode) {
            adj_parents.add(nodeForAdj);
        }
    }

    /*****************************************/

}
