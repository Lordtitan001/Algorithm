enum TypeObjet{A,B,C};

public class Objet {

    //Objet(){};

    Objet(TypeObjet type){
        this.type = type;
        switch (type){
            case A:
                this.masse=1;
                break;
            case B:
                masse=3;
                break;
            case C:
                masse=6;
                break;
        }
    };

    private TypeObjet type;
    private int masse_;
}