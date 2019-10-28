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

    public static void main(String args[]) throws FileNotFoundException {
        prendreCommande();
        afficherCommande();
    }

    private static int commande[] = new int[3]; // attribut tableau de int ajouté pour gerer la commande d'un robot
    private Type type_;
    private double capacity_;
    private double masse_;
    private double constante_;
}