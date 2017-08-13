/* Dante Qyshka 124660 */

import java.util.ArrayList;

public class GraphUtils {
    //rappresenta la distanza massima corrente
    private static int maxDist = 0;


    //funzione per definire le relazioni tra i nodi
    //Complessità O(n^2): perchè nel caso medio scorre tutti i nodi ogni volta per vedere eventuali relazioni
    public static void connectGraph(Graph g) {
        int next = 1;
        for (int i = 0; i < g.getSize(); i++) {

            while (next != g.getSize()) {
                //Evita controlli inutili
                if (g.getNode(i).getLabel().length() == g.getNode(next).getLabel().length()) {
                } else {
                    //Se il test del confronto tra le parole ha successo crea le relazioni
                    if (test(g.getNode(next).getLabel(), g.getNode(i).getLabel())) {
                        //euristica per cui individuo immediatamente i nodi senza genitore o figli
                        //in modo da poter trovare i nodi da cui partire e arrivare.
                        g.getNode(next).addAdj(g.getNode(i));
                        g.getNode(i).addAdj_P(g.getNode(next));
                        g.getNode(i).setChild();
                        g.getNode(next).setLeaf();

                    }
                }
                next++;
            }
            next = i + 1;
        }
    }

    //test per confrontare le due stringhe
    public static boolean test(String searchIn, String searchFor) {

        for (char c : searchFor.toCharArray()) {
            char[] c1 = searchIn.toCharArray();
            if (searchIn.contains(String.valueOf(c))) {
                c1[searchIn.indexOf(c)] = ' ';
                searchIn = new String(c1);
            } else {
                return false;
            }
        }
        return true;
    }

    /* Procedura per riordinare la lista di nodi
       (in maniera crescente)
        Complessità: O(n*logn)*/
    public static void quickSort(String[] words, int floor, int ceil) {
        if (words == null || words.length == 0)
            return;
        if (floor >= ceil)
            return;

        // pick the pivot
        int middle = floor + (ceil - floor) / 2;
        String pivot = words[middle];

        // make left < pivot and right > pivot
        int i = floor;
        int j = ceil;
        while (i <= j) {
            while (words[i].length() < pivot.length()) {
                i++;
            }
            while (words[j].length() > pivot.length()) {
                j--;
            }
            if (i <= j) {
                String temp = words[i];
                words[i] = words[j];
                words[j] = temp;
                i++;
                j--;
            }
        }

        // recursively sort two sub parts
        if (floor < j)
            quickSort(words, floor, j);

        if (ceil > i)
            quickSort(words, i, ceil);
    }

    /*metodo per trovare il cammino massimo*/
    public static ArrayList<Node> longestPath(Graph g) {
        ArrayList<Node> L = new ArrayList<Node>();
        ArrayList<Node> S = new ArrayList<Node>();

        /*complessità Theta(n+m) - (V+E)
        * -- n = numero nodi
        * -- m = numero di relazioni adiacenti*/
        for (int i = 0; i < g.getSize(); i++) {
            if (g.getNode(i).isLeaf()) {
                S.add(g.getNode(i));
            }
        }

        for (Node n : S) {
            visit(n, L);
            for (int i = 0; i < g.getSize(); i++) {
                g.getNode(i).setDistance(0);
            }
        }

        return L;
    }
    /*funzione sviluppata dal topological sort per ottenere il cammino di distanza massimo*/
    public static void visit(Node n, ArrayList<Node> L) {

        for (int i = 0; i < n.getSizeAdj_P(); i++) {
            if (n.getNodeAdj_P(i).isChild() == true) {
                n.getNodeAdj_P(i).setDistance(n.getDistance() + 1);
                visit(n.getNodeAdj_P(i), L);
            } else {
                n.getNodeAdj_P(i).setDistance(n.getDistance() + 1);
                if(maxDist < n.getNodeAdj_P(i).getDistance()){
                    maxDist = n.getNodeAdj_P(i).getDistance();
                }
            }


        }

        L.add(n);
    }

    //ritorna la distanza massima corrente
    public static int getMaxDist(){
        return maxDist;
    }


}
