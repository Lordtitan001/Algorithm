import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;


public class Noeud{
    // Numero du noeud
    private int numero;
    // Nombre d'objet de chaque type
    private int objetA;
    private int objetB;
    private int objetC;
    // Liste des arcs incidents du noeud
    private LinkedList<Arc> listeArc = new LinkedList<Arc>();
    private LinkedList<Noeud> shortestPath = new LinkedList<>();
    // Liste des etats possible pour chaque noeud
    private Set<State> states = new HashSet<State>();
    private Map<Noeud, Integer> adjacentNodes = new HashMap<>();
    // Distance a partir d'un l'origine en suivant un chemin specifique
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

    /********************************** Liste de getters et de setters ***********************************/

    public Set<State> getStates() {
        return states;
    }

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

    public void setAdjacentNodes() {
        listeArc.forEach((el) ->{
            Noeud voisin = (numero == el.getNoeud1_().numero) ? el.getNoeud2_() : el.getNoeud1_();
            adjacentNodes.put(voisin , el.getDistance_());
        });
    }

    public Map<Noeud, Integer> getAdjacentNodes() {
        setAdjacentNodes();
        return adjacentNodes;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public LinkedList<Noeud> getShortestPath() {
        return shortestPath;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setShortestPath(LinkedList<Noeud> shortestPath) {
        this.shortestPath = (LinkedList<Noeud>) shortestPath.clone();
    }

    public LinkedList<Arc> getListeArc() {
        return listeArc;
    }


    public void setListeArc(LinkedList<Arc> listeArc) {
        this.listeArc = listeArc;
    }

    public int getNumero() {
        return numero;
    }

    public int getObjetA() {
        return objetA;
    }

    public int getObjetB() {
        return objetB;
    }

    public int getObjetC() {
        return objetC;
    }

    public void setObjetA(int objetA) {
        this.objetA = objetA;
    }

    public void setObjetB(int objetB) {
        this.objetB = objetB;
    }

    public void setObjetC(int objetC) {
        this.objetC = objetC;
    }

    
    public void addArc(Arc Arc) {
        this.listeArc.add(Arc);
    }
}