
public class Arc {

    Arc(Noeud noeud1, Noeud noeud2, int distance){
        this.noeud1_ = noeud1;
        this.noeud2_ = noeud2;
        this.distance_ = distance;
    }

     private Noeud noeud1_;
     private Noeud noeud2_;
     private int distance_;

     /**
      * @return the distance_
      */
     public int getDistance_() {
         return distance_;
     }
     
     /**
      * @return the noeud1_
      */
     public Noeud getNoeud1_() {
         return noeud1_;
     }

     /**
      * @return the noeud2_
      */
     public Noeud getNoeud2_() {
         return noeud2_;
     }

     /**
      * @param distance_ the distance_ to set
      */
     public void setDistance_(int distance_) {
         this.distance_ = distance_;
     }

     /**
      * @param noeud1_ the noeud1_ to set
      */
     public void setNoeud1_(Noeud noeud1_) {
         this.noeud1_ = noeud1_;
     }
     /**
      * @param noeud2_ the noeud2_ to set
      */
     public void setNoeud2_(Noeud noeud2_) {
         this.noeud2_ = noeud2_;
     }
}