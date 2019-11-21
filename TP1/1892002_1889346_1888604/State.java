import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class State {
    // Le temps mis pour atteindre cet etat
    private double time = 0;
    private Robot robot;
    // Liste des neouds traversé
    private LinkedList<Noeud> shortestPath = new LinkedList<>();
    // Liste des noeuds collectés
    private Set<Noeud> inspectedNodes = new HashSet<>();

    State(Robot robot){
        this.robot = robot;
    }

    State(LinkedList<Noeud> shortestPath, Robot robot, Set<Noeud> inspectedNodes, double time){
        this.robot = new Robot(robot);
        this.shortestPath = new LinkedList<Noeud> (shortestPath);
        this.inspectedNodes =  new HashSet<Noeud>(inspectedNodes);
        this.time = time;

    }

    // Liste de getters et de setters
    public Set<Noeud> getInspectedNodes() {
        return inspectedNodes;
    }

    public void recupererObjets(Noeud currentNode){
        robot.recupererObjets(currentNode);
    }

    public LinkedList<Noeud> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(LinkedList<Noeud> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public void setRobot(Robot robot) {
        this.robot = robot;
    }

    public Robot getRobot() {
        return robot;
    }

    public int[] getCommande() {
        return robot.getObjetRecuperes();
    }

    public void setCommande(int[] commande) {
        robot.setObjetRecuperes(commande);
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    // Ajouter du temps a un etat
    public boolean addTime(double time) {
        this.time += time;
        if(time > 0)
            return true;
        else    
            return false;
    }

    // Collecter un noeud
    public int addCommande(Noeud noeud){
        return robot.recupererObjets(noeud);
    }

    public State clone(){
        return new State(shortestPath, robot, inspectedNodes, time);
    }
}