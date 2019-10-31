public class State {
    private double time = 0;
    private Robot robot1 = new Robot(Type.X);
    private Robot robot2 = new Robot(Type.Y);
    private Robot robot3 = new Robot(Type.Z);
    private int[] commande = new int[3];


    public void recupererObjets(Noeud currentNode){
        robot1.recupererObjets(currentNode);
        robot2.recupererObjets(currentNode);
        robot3.recupererObjets(currentNode);
    }

    /**
     * @return the commande
     */
    public int[] getCommande() {
        return commande;
    }

    /**
     * @param commande the commande to set
     */
    public void setCommande(int[] commande) {
        this.commande = commande;
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

}