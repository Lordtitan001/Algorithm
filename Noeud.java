import java.util.LinkedList;


public class Noeud{

    public class Pair<L,R> {
        private L l;
        private R r;
        public Pair(L l, R r){
            this.l = l;
            this.r = r;
        }
        public L getL(){ return l; }
        public R getR(){ return r; }
        public void setL(L l){ this.l = l; }
        public void setR(R r){ this.r = r; }
    }

    public Noeud(int numero, int objetA, int objetB, int objetC){
        this.numero = numero;
        this.objetA = objetA;
        this.objetC = objetC;
        this.objetB = objetC;

    }
    private int numero;
    private int objetA;
    private int objetB;
    private int objetC;
    private LinkedList<Arc> listeArc = new LinkedList<Arc>();

    /**
     * @return the listeArc
     */
    public LinkedList<Arc> getListeArc() {
        return listeArc;
    }

    public LinkedList<Pair<Integer, Noeud>> getListeVoisins(){
        LinkedList<Pair<Integer, Noeud>> listeVoisins = new LinkedList<Pair<Integer, Noeud>>();
        listeArc.forEach((el) ->{
            Noeud voisin = (this.equals(el.getNoeud1_())) ? el.getNoeud2_() : el.getNoeud1_();
            listeVoisins.add(new Pair<Integer, Noeud>(el.getDistance_(), voisin));
        });
        return listeVoisins;
    }

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