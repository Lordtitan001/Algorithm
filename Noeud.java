import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;


public class Noeud{

    private int numero;
    private int objetA;
    private int objetB;
    private int objetC;
    private LinkedList<Arc> listeArc = new LinkedList<Arc>();
    private LinkedList<Noeud> shortestPath = new LinkedList<>();
    private Set<State> states = new HashSet<State>();
    private Map<Noeud, Integer> adjacentNodes = new HashMap<>();
    private Integer distance = Integer.MAX_VALUE;



    public Noeud(int numero, int objetA, int objetB, int objetC){
        this.numero = numero;
        this.objetA = objetA;
        this.objetC = objetC;
        this.objetB = objetB;

    }

    public Noeud clone() throws CloneNotSupportedException {

        return (Noeud) super.clone();
    }

    /**
     * @return the states
     */
    public Set<State> getStates() {
        return states;
    }

    /**
     * @param states the states to set
     */
    public void setStates(Set<State> states) {
        this.states = states;
    }

    public Noeud() {
    }

    public void addState( State state){
        states.add(state);
    }
    public void addAdjacentNode(Noeud destination, int distance) {
        adjacentNodes.put(destination, distance);
    }

    /**
     * @param adjacentNodes the adjacentNodes to set
     */
    public void setAdjacentNodes() {
        listeArc.forEach((el) ->{
            Noeud voisin = (numero == el.getNoeud1_().numero) ? el.getNoeud2_() : el.getNoeud1_();
            adjacentNodes.put(voisin , el.getDistance_());
        });
    }

    /**
     * @return the adjacentNodes
     */
    public Map<Noeud, Integer> getAdjacentNodes() {
        setAdjacentNodes();
        return adjacentNodes;
    }

    
    /**
     * @return the distance
     */
    public Integer getDistance() {
        return distance;
    }

    /**
     * @param distance the distance to set
     */
    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    /**
     * @return the shortestPath
     */
    public LinkedList<Noeud> getShortestPath() {
        return shortestPath;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     * @param shortestPath the shortestPath to set
     */
    public void setShortestPath(LinkedList<Noeud> shortestPath) {
        this.shortestPath = shortestPath;
    }

    /**
     * @return the listeArc
     */
    public LinkedList<Arc> getListeArc() {
        return listeArc;
    }

    // public LinkedList<Pair<Integer, Noeud>> getListeVoisinsDistance(){
    //     LinkedList<Pair<Integer, Noeud>> listeVoisins = new LinkedList<Pair<Integer, Noeud>>();
    //     listeArc.forEach((el) ->{
    //         Noeud voisin = (this.equals(el.getNoeud1_())) ? el.getNoeud2_() : el.getNoeud1_();
    //         listeVoisins.add(new Pair<Integer, Noeud>(el.getDistance_(), voisin));
    //     });
    //     return listeVoisins;
    // }

    // public LinkedList<Noeud> getListeVoisins(){
    //     LinkedList<Noeud> listeVoisins = new LinkedList<Noeud>();
    //     listeArc.forEach((el) ->{
    //         Noeud voisin = (this.equals(el.getNoeud1_())) ? el.getNoeud2_() : el.getNoeud1_();
    //         listeVoisins.add(voisin);
    //     });
    //     return listeVoisins;
    // }

    // public int getDistanceVoisins(Noeud node){
    //     LinkedList<Integer> distance = new LinkedList<Integer>();
    //     listeArc.forEach((el) ->{
    //         if(el.getNoeud1_().equals(node) || el.getNoeud2_().equals(node)){
    //             distance.add(el.getDistance_());
    //         }
    //     });
    //     return distance.getLast();
    // }

    /**
     * @param listeArc the listeArc to set
     */
    public void setListeArc(LinkedList<Arc> listeArc) {
        this.listeArc = listeArc;
    }

    public void addArc(Arc Arc) {
        this.listeArc.add(Arc);
    }
    
    /**
     * @return the objetA
     */
    public int getObjetA() {
        return objetA;
    }
    /**
     * @return the numero
     */
    public int getNumero() {
        return numero;
    }
    /**
     * @return the objetB
     */
    public int getObjetB() {
        return objetB;
    }
    /**
     * @return the objetC
     */
    public int getObjetC() {
        return objetC;
    }

    /**
     * @param objetA the objetA to set
     */
    public void setObjetA(int objetA) {
        this.objetA = objetA;
    }

    /**
     * @param objetB the objetB to set
     */
    public void setObjetB(int objetB) {
        this.objetB = objetB;
    }
    /**
     * @param objetC the objetC to set
     */
    public void setObjetC(int objetC) {
        this.objetC = objetC;
    }
}