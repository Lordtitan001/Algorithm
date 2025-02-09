import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Graphe {

    // Map contant tous les noeuds
    private static Map<Integer, Noeud> mapNoeud = new TreeMap<Integer, Noeud>();
    // Map contant tous les ars
    private static Map<Integer, Arc> mapArc = new TreeMap<Integer, Arc>();

    public Graphe() throws FileNotFoundException {
        Graphe.mapNoeud = new TreeMap<Integer, Noeud>();
        Graphe.mapArc = new TreeMap<Integer, Arc>();
        creerGraphe();
    }

    // Permet de creer le graphe en entier
    public void creerGraphe() throws FileNotFoundException {
        creerNoeuds();
        creerArcs();
    }

    // Permet d'afficher le graphe
    public void afficherGraphe() {
        mapNoeud.values().forEach((noeud) -> {
            System.out.println("");
            System.out.print("(" + noeud.getNumero() + "," + noeud.getObjetA() + "," + noeud.getObjetB() + ","
                    + noeud.getObjetC() + ", ");
            noeud.getAdjacentNodes().entrySet().forEach((voisin) -> {
                System.out.print(" (" + voisin.getKey().getNumero() + "," + voisin.getValue() + ")");
            });
            System.out.print(" )");
        });
    }

    // Permet de creer le graphe
    public void creerArcs() throws FileNotFoundException {

        String fileName = "entrepot.txt";
        File textFile = new File(fileName);

        // Ouvrir le fichier texte
        Scanner in = new Scanner(textFile);
        int ligne = 0;

        // Sauter les 20 premieres lignes
        while (in.hasNext() && ligne < 21) {
            in.next();
            ligne++;
        }

        int counter = 0;
        while (in.hasNext()) {
            String value = in.next();
            String temp = "";
            List<Integer> tab = new LinkedList<Integer>();

            // Pour valeurs de la ligne
            for (int i = 0; i < value.length(); i++) {
                // Sauter les virgules et convertir les carracteres en entier
                if (value.charAt(i) != ',')
                    temp += value.charAt(i);
                else if (value.charAt(i) == ',') {
                    if (temp.length() == 2)
                        tab.add((temp.charAt(0) - 48) * 10 + temp.charAt(1) - 48);
                    else
                        tab.add(temp.charAt(0) - 48);
                    temp = "";
                }

                if (i == value.length() - 1) {
                    if (temp.length() == 2)
                        tab.add((temp.charAt(0) - 48) * 10 + temp.charAt(1) - 48);
                    else
                        tab.add(temp.charAt(0) - 48);
                }
            }
            // Creer un arc avec les valeurs lues et ajouter dans la map des arcs
            mapArc.put(counter++, new Arc(getMapNoeud().get(tab.get(0)), getMapNoeud().get(tab.get(1)), tab.get(2)));

        }

        // Ajouter chaque arc aux noeuds qu'il relients
        getMapArc().values().forEach((arc) -> {
            arc.getNoeud1_().addArc(arc);
            arc.getNoeud2_().addArc(arc);
        });

        in.close();

    }

    // Permet de creer les noeuds
    public void creerNoeuds() throws FileNotFoundException {

        String fileName = "entrepot.txt";
        File textFile = new File(fileName);
        // Ouvrir le fichier
        Scanner in = new Scanner(textFile);
        int val1 = 0;
        int val2 = 0;
        int val3 = 0;
        int val4 = 0;

        int ligne = 0;

        // Pour les 20 premieres lignes
        while (in.hasNext() && ligne < 21) {
            String value = in.next();
            // Retirer les virgules
            value = value.replace(",", "");

            // Transformer les carracteres en entiers
            if (value.length() == 4) {
                val1 = value.charAt(0) - 48;
                val2 = value.charAt(1) - 48;
                val3 = value.charAt(2) - 48;
                val4 = value.charAt(3) - 48;
            } else if (value.length() == 5) {
                val1 = (value.charAt(0) - 48) * 10 + value.charAt(1) - 48;
                val2 = value.charAt(2) - 48;
                val3 = value.charAt(3) - 48;
                val4 = value.charAt(4) - 48;
            }
            // Ajouter chaque noeuds a la liste de noeuds
            mapNoeud.put(val1, new Noeud(val1, val2, val3, val4));
            ligne++;
        }

        in.close();

    }

    /**********************************
     * Liste de getters et de setters
     ***********************************/

    public void setMapArc(Map<Integer, Arc> maparc) {
        mapArc = maparc;
    }

    public Map<Integer, Arc> getMapArc() {
        return mapArc;
    }

    public Map<Integer, Noeud> getMapNoeud() {
        return mapNoeud;
    }

    public void setMapNoeud(Map<Integer, Noeud> map) {
        mapNoeud = map;
    }
}