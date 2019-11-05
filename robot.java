import java.util.Scanner;

enum Type {
    X, Y, Z
};

public class Robot {

    public Robot() {
        this.type_ = Type.X;
        this.masse_ = 0;
        this.capacity_ = 25;
        this.constante_ = 2.5;
    }

    public Robot(Robot rob) {
        this.capacity_ = rob.capacity_;
        this.masse_ = rob.masse_;
        this.constante_ = rob.constante_;
        timeSpend = rob.timeSpend;
        type_ = rob.type_;
        objetRecuperes = rob.objetRecuperes.clone();
    }

    Robot(Type type) {

        this.masse_ = 0;
        switch (type) {
        case X:
            this.capacity_ = 5;
            this.constante_ = 1;
            break;
        case Y:
            this.capacity_ = 10;
            this.constante_ = 1.5;
            break;
        case Z:
            this.capacity_ = 25;
            this.constante_ = 2.5;
            break;
        default:

        }
        this.type_ = type;
    }

    // Ajouter de la masse au robot
    public boolean addMasse(double masse) {
        if (masse + this.masse_ <= this.capacity_) {
            setMasse(masse + this.masse_);
            return true;
        }
        return false;
    }

    public boolean removeMasse(double masse) {
        if (this.masse_ >= masse) {
            this.masse_ -= masse;
            return true;
        }
        return false;
    }

    // Permet de prendre une commade rentrer par l'utilisateur
    public static void prendreCommande() {

        System.out.println(
                "Veuillez saisir 3 entiers pour une commande des objets A, B et C séparés d'un espace et dans cet ordre:");
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 3; i++) {
            Robot.commande[i] = sc.nextInt();
        }
    }

    // Permet d'afficher la commande renter
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

    // Permet d'afficher les objets déja récupérés
    public void afficherObjetsRecuperer() {
        System.out.println("La les objets pour ce robot sont:");
        for (int i = 0; i < 3; i++) {
            switch (i) {
            case 0:
                System.out.println(objetRecuperes[i] + " Objet(s) A");
                break;
            case 1:
                System.out.println(objetRecuperes[i] + " Objet(s) B");
                break;
            case 2:
                System.out.println(objetRecuperes[i] + " Objet(s) C");
                break;
            }
        }
    }

    // Retourne vrai si la commande est deja complété
    public boolean commandeEffectuee() {
        var commande = Robot.getCommande();
        if (commande[0] == objetRecuperes[0] && commande[1] == objetRecuperes[1] && commande[2] == objetRecuperes[2])
            return true;
        else
            return false;
    }

    // Permet de collecter des objets sur un noeud
    public int recupererObjets(Noeud currentNode) {

        int nombreAjouter = 0;
        // test de la capacité du robot
        if (!commandeEffectuee() && (masse_ <= capacity_)) {
            for (int i = 0; i < 3; i++) {
                int bonNbObjets = 0;
                int masseAjoutee = 0;
                int nbObjetsnecessaires = commande[i] - objetRecuperes[i];

                switch (i) {
                case 0:
                    // Test du nombre d'objet néccesaire
                    if (nbObjetsnecessaires >= currentNode.getObjetA()) 
                        bonNbObjets = currentNode.getObjetA();
                    else 
                        bonNbObjets = nbObjetsnecessaires;
                    
                    masseAjoutee = bonNbObjets;
                    nombreAjouter += bonNbObjets;
                    break;
                case 1:
                    // Test du nombre d'objet néccesaire
                    if (nbObjetsnecessaires >= currentNode.getObjetB())
                        bonNbObjets = currentNode.getObjetB();
                    else 
                        bonNbObjets = nbObjetsnecessaires;

                    masseAjoutee = bonNbObjets * 3;
                    nombreAjouter += bonNbObjets;
                    break;
                case 2:
                    // Test du nombre d'objet néccesaire
                    if (nbObjetsnecessaires >= currentNode.getObjetC())
                        bonNbObjets = currentNode.getObjetC();
                    else 
                        bonNbObjets = nbObjetsnecessaires;
    
                    masseAjoutee = bonNbObjets * 6;
                    nombreAjouter += bonNbObjets;
                    break;

                }
                if (commande[i] != 0 && bonNbObjets != 0 && objetRecuperes[i] < commande[i]) {
                    // Ajout de la masse et de l'objet au robot 
                    if (addMasse(masseAjoutee)) {
                        objetRecuperes[i] += bonNbObjets;
                    }

                }
            }
        }
        return nombreAjouter;
    }

    /**********************************
     * Liste de getters et de setters
     ***********************************/

    public Type getType_() {
        return type_;
    }

    public double getCapacity_() {
        return capacity_;
    }

    public double getMasse_() {
        return masse_;
    }

    public double getMasseCommande() {
        return (commande[0] + commande[1] * 3 + commande[2] * 6);
    }

    public static int[] getCommande() {
        return commande;
    }

    public int[] getObjetRecuperes() {
        return objetRecuperes;
    }

    public void setObjetRecuperes(int[] objetRecuperes) {
        this.objetRecuperes = objetRecuperes;
    }

    public double addTime(int distance) {
        timeSpend += distance * constante_;
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

    public void resetMasse() {
        setMasse(0);
    }

    public double getConstante_() {
        return constante_;
    }

    public void setConstante_(double constante_) {
        this.constante_ = constante_;
    }

        /**********************************
     ***********************************/

    // attribut tableau de int qui represente les objets recupérées afin de satisfaire la commande
    private int objetRecuperes[] = new int[3]; 
    // attribut tableau de int ajouté pour gerer la commande d'un robot
    private static int commande[] = new int[3]; 
    private Type type_;
    private double capacity_;
    private double masse_;
    private double constante_;
    private double timeSpend = 0;
}