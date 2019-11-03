import java.util.LinkedList;

public class State {
    private double time = 0;
    private Robot robot;
    private LinkedList<Noeud> shortestPath = new LinkedList<>();

    State(Robot robot){
        this.robot = robot;
    }

    State(LinkedList<Noeud> shortestPath, Robot robot){
        this.robot = new Robot(robot);
        this.shortestPath = (LinkedList<Noeud>) shortestPath.clone();
    }

    public void recupererObjets(Noeud currentNode){
        robot.recupererObjets(currentNode);
    }

    /**
     * @return the shortestPath
     */
    public LinkedList<Noeud> getShortestPath() {
        return shortestPath;
    }

    /**
     * @param shortestPath the shortestPath to set
     */
    public void setShortestPath(LinkedList<Noeud> shortestPath) {
        this.shortestPath = shortestPath;
    }


    /**
     * @param robot the robot to set
     */
    public void setRobot(Robot robot) {
        this.robot = robot;
    }

    /**
     * @return the robot
     */
    public Robot getRobot() {
        return robot;
    }

    
    /**
     * @return the commande
     */
    public int[] getCommande() {
        return robot.getObjetRecuperes();
    }

    /**
     * @param commande the commande to set
     */
    public void setCommande(int[] commande) {
        robot.setObjetRecuperes(commande);
    }

    /**
     * @return the time
     */
    public double getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(double time) {
        this.time = time;
    }

    public void addTime(double time) {
        this.time += time;
    }

    public void addCommande(Noeud noeud){
        robot.recupererObjets(noeud);
    }

    public State clone(){
        return new State(shortestPath, robot);
    }
}