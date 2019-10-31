import java.io.FileNotFoundException;
import java.util.Scanner;

enum Type {
    X, Y, Z
};

public class Robot {
    Robot(Type type) {

        this.masse_ = 0;
        switch (type) {
        case X:
            this.capacity_ = 5;
            break;
        case Y:
            this.capacity_ = 10;
            break;
        case Z:
            this.capacity_ = 25;
            break;
        default:

        }
    }

    public Robot(){
        this.masse_ = 0;
        this.capacity_ = 10;
    }

    /**
     * @return the timeSpend
     */
    public double getTimeSpend() {
        return timeSpend;
    }

    /**
     * @param timeSpend the timeSpend to set
     */
    public void setTimeSpend(double timeSpend) {
        this.timeSpend = timeSpend;
    }

    public double addTime(int distance){
        timeSpend += distance*constante_;
        return timeSpend;
    }


    public void setMasse(double masse) {
        this.masse_ = masse;
        switch (this.type_) {
        case X:
            this.constante_ = 1 + masse;
            break;
        case Y:
            this.constante_ = 1.5 + 0.6 * masse;
            break;
        case Z:
            this.constante_ = 2.5 + 0.2 * masse;
            break;
        default:

        }
    }

    public boolean addMasse(double masse) {
        if (masse + this.masse_ <= this.capacity_) {
            setMasse(masse + this.masse_);
            return true;
        }
        return false;
    }

    public boolean removeMasse(double masse){
        if(this.masse_ >= masse){
            this.masse_ -= masse;
            return true;
        }
        return false;
    }

    public void resetMasse() {
        setMasse(0);
    }

    /**
     * @return the constante_
     */
    public double getConstante_() {
        return constante_;
    }

    /**
     * @param constante_ the constante_ to set
     */
    public void setConstante_(double constante_) {
        this.constante_ = constante_;
    }

    public static void prendreCommande() {

       
        System.out.println("Veuillez saisir 3 entiers pour une commande des objets A, B et C séparés d'un espace et dans cet ordre:");
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 3; i++) {
            commande[i] = sc.nextInt();
        }
        sc.close();
    }

    public static void afficherCommande() {
        System.out.println("La commande pour ce robot est:");
        for (int i = 0; i < 3; i++) {
            switch (i) {
            case 0:
                System.out.println(commande[i] + " Objet(s) A");
                break;
            case 1:
                System.out.println(commande[i] + " Objet(s) B");
                break;
            case 2:
                System.out.println(commande[i] + " Objet(s) C");
                break;
            }
        }
    }


    public  boolean commandeEffectuee(){
        if (commande[0] == objetRecuperes[0]
            && commande[1] == objetRecuperes[1]
            && commande[2] == objetRecuperes[2])
            return true;
        else
            return false;
   }

   public  void recupererObjets(Noeud currentNode){

       int bonNbObjets = 0 ;

       if (!commandeEffectuee() && (masse_ <= capacity_)){

           for( int i = 0; i < 3; i++) {
               int masse = 0;

               switch (i){
                   case 0:
                       bonNbObjets = currentNode.getObjetA();
                        masse = bonNbObjets;  
                       break;
                   case 1:
                       bonNbObjets = currentNode.getObjetB();
                       masse = bonNbObjets*3; 
                       break;
                   case 2:
                       bonNbObjets = currentNode.getObjetC();
                       masse = bonNbObjets*6; 
                       break;
               }

               //Regler le probleme de masse !!!
               if ( commande[i] != 0  && bonNbObjets != 0 && objetRecuperes[i] < commande[i]){
                        objetRecuperes[i] += bonNbObjets ;  
                        if (objetRecuperes[i] > commande[i]){
                            objetRecuperes[i] = commande[i];
                         }

               }
               
               //System.out.println("nouvel objet : " + objetRecuperes[i]);
           }
       }

   }

   /**
    * @return the commande
    */
   public static int[] getCommande() {
       return commande;
   }


   public static void main(String args[]) throws FileNotFoundException {

       prendreCommande();

       afficherCommande();

       Noeud node1 = new Noeud(1,2,0,4);
       Noeud node2 = new Noeud(1,2,0,4);
       Noeud node3 = new Noeud(1,2,0,4);
       Noeud node4 = new Noeud(1,2,0,4);
       //Robot robot1 = new Robot(Type.Y);

       Robot rob = new Robot();
       rob.recupererObjets(node1);
       rob.recupererObjets(node2);
       rob.recupererObjets(node2);
       rob.recupererObjets(node2);
   }

    private  int objetRecuperes[] = new int[3];  // attribut tableau de int qui represente les objets recupérées afin de satisfaire la commande
    private static int commande[] = new int[3]; // attribut tableau de int ajouté pour gerer la commande d'un robot
    private Type type_;
    private double capacity_;
    private double masse_;
    private double constante_;
    private double timeSpend = 0;
}