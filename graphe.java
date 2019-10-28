import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Graphe {

    private static Map<Integer, Noeud> mapNoeud =  new TreeMap<Integer, Noeud>();
    private static Map<Integer, Arc> mapArc =  new TreeMap<Integer, Arc>();

    /**
     * @param mapArc the mapArc to set
     */
    public void setMapArc(Map<Integer, Arc> mapArc) {
        this.mapArc = mapArc;
    }
    /**
     * @return the mapArc
     */
    public Map<Integer, Arc> getMapArc() {
        return mapArc;
    }
    /**
     * @return the map
     */
    public Map<Integer, Noeud> getMapNoeud() {
        return mapNoeud;
    }

    public  void creerArcs() throws FileNotFoundException {

        String fileName = "entrepot.txt";
        File textFile = new File(fileName);
        
        Scanner in = new Scanner(textFile);
        int ligne = 0;

        while( in.hasNext() && ligne < 22){
            in.next();
            ligne++;
        }

        int counter = 0;
        while(in.hasNext()){
           String value = in.next();
            String temp = "";
            List<Integer> tab = new LinkedList<Integer>();
            int pos = 0;
           for(int i = 0 ; i < value.length(); i++){
                if(value.charAt(i) != ',')
                    temp += value.charAt(i);
                else if( value.charAt(i) == ','){
                    if(temp.length() == 2)
                        tab.add((temp.charAt(0) - 48)*10 +  temp.charAt(1) - 48);
                    else
                        tab.add(temp.charAt(0) - 48);
                    temp ="";
                }

                if( i == value.length() - 1){
                    if(temp.length() == 2)
                        tab.add((temp.charAt(0) - 48)*10 +  temp.charAt(1) - 48);
                    else
                        tab.add(temp.charAt(0) - 48);
                }
           }

            this.mapArc.put(counter, new Arc(getMapNoeud().get(tab.get(0)), getMapNoeud().get(tab.get(1)), tab.get(2)));
            counter++;
           
        }

        getMapArc().values().forEach((arc) ->{
            arc.getNoeud1_().addArc(arc);
            arc.getNoeud2_().addArc(arc);
        });

        in.close();
        

    }

    public  void creerNoeuds() throws FileNotFoundException {

        String fileName = "entrepot.txt";
        File textFile = new File(fileName);
        
        Scanner in = new Scanner(textFile);
        int val1 = 0;
        int val2 = 0;
        int val3 = 0;
        int val4 = 0;

        int ligne = 0;

        while(in.hasNext() && ligne < 21){
            String value = in.next();
            value = value.replace("," , "");
            
            if(value.length() == 4){
                val1 = value.charAt(0) - 48; 
                val2 = value.charAt(1) - 48; 
                val3 = value.charAt(2) - 48; 
                val4 = value.charAt(3) - 48; 
            }
            else if( value.length() == 5){
                val1 = (value.charAt(0) - 48)*10 +  value.charAt(1) - 48; 
                val2 = value.charAt(2) - 48; 
                val3 = value.charAt(3) - 48; 
                val4 = value.charAt(4) - 48; 
            }
            this.mapNoeud.put(val1, new Noeud(val1, val2, val3, val4));
            ligne++;
        }

        in.close();
        
    }

    /**
     * @param map the map to set
     */
    public void setMapNoeud(Map<Integer, Noeud> map) {
        this.mapNoeud = map;
    }

    public void creerGraphe() throws FileNotFoundException {
        Graphe graphe = new Graphe();
        graphe.creerNoeuds();
        graphe.creerArcs();
    }

    public void afficherGraphe(){
        mapNoeud.values().forEach((noeud) ->{
            System.out.println("");
            System.out.print("(" + noeud.getNumero() + "," + noeud.getObjetA() + "," + noeud.getObjetB() +
             "," + noeud.getObjetC() + ", ");
            noeud.getListeVoisins().forEach((voisin) ->{
                System.out.print(" (" + voisin.getR().getNumero() + "," + voisin.getL() + ")");
            });
            System.out.print(" )");
        });
    }


    public static void main(String args[]) throws FileNotFoundException {
        Graphe graphe = new Graphe();
        graphe.creerGraphe();
        graphe.afficherGraphe();
    }
}