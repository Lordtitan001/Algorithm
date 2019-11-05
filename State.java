import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class State {
    private double time = 0;
    private Robot robot;
    private LinkedList<Noeud> shortestPath = new LinkedList<>();
    private Set<Noeud> inspectedNodes = new HashSet<>();
    private static Integer count = 0;
    private Integer number = 0;

    State(Robot robot){
        this.robot = robot;
        this.number = count;
        count ++;
    }

    State(LinkedList<Noeud> shortestPath, Robot robot, Set<Noeud> inspectedNodes, double time, Integer number){
        this.robot = new Robot(robot);
        this.shortestPath = new LinkedList<Noeud> (shortestPath);
        this.inspectedNodes =  new HashSet<Noeud>(inspectedNodes);
        this.time = time;
        this.number = number;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber() {
        State.count++;
        this.number = count;
    }

    public static Integer getCount() {
        return count;
    }

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

    public boolean addTime(double time) {
        this.time += time;
        if(time > 0)
            return true;
        else    
            return false;
    }

    public int addCommande(Noeud noeud){
        return robot.recupererObjets(noeud);
    }

    public State clone(){
        return new State(shortestPath, robot, inspectedNodes, time, number);
    }
}