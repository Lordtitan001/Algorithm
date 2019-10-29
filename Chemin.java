import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Chemin {

    private Map<Integer, Noeud> testedMap =  new TreeMap<Integer, Noeud>();
    private Map<Integer, Noeud> notTestedMap =  new TreeMap<Integer, Noeud>();
    private LinkedList<Pair<Integer, LinkedList<Noeud>>> distanceNoeud = new LinkedList<Pair<Integer, LinkedList<Noeud>>>();
    private boolean commandeOK = true;
    private Noeud initialNode = new Noeud(0, 0, 0, 0);
    
    public void initialisation(){
        Graphe graphe= new Graphe();
        notTestedMap = graphe.getMapNoeud();
        graphe.getMapNoeud().values().forEach((node) ->{
            LinkedList<Noeud> list = new LinkedList<Noeud>();
            list.add(node);
            distanceNoeud.add(new Pair<Integer, LinkedList<Noeud>>(Integer.MAX_VALUE, list));
        });
    }

    public Noeud sommetMinimal(){

        return initialNode;
    }

    public void plusCourtChemin(){
        Graphe graphe= new Graphe();
        // while(!testedMap.containsKey(0) || commandeOK){
        //     //graphe.getMapNoeud()
        // }
    }

    public static void main(String args[]) throws FileNotFoundException {
        Graphe graphe = new Graphe();
    }
}