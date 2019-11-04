enum TypeObjet{A,B,C};

public class Objet {
    Objet(TypeObjet type){
        this.type_ = type;
        switch (type){
            case A:
                this.masse_ = 1;
                break;
            case B:
                this.masse_ = 3;
                break;
            case C:
                this.masse_ = 6;
                break;
        }
    };

    private TypeObjet type_ ;
    private int masse_;

    public int getMasse_() {
        return masse_;
    }

    public TypeObjet getType_() {
        return type_;
    }

    public void setMasse_(int masse_) {
        this.masse_ = masse_;
    }

    public void setType_(TypeObjet type_) {
        this.type_ = type_;
    }
}