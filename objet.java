enum TypeObjet{A,B,C};

public class Objet {

    //Objet(){};

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

    /**
     * @return the masse_
     */
    public int getMasse_() {
        return masse_;
    }

    /**
     * @return the type_
     */
    public TypeObjet getType_() {
        return type_;
    }
    /**
     * @param masse_ the masse_ to set
     */
    public void setMasse_(int masse_) {
        this.masse_ = masse_;
    }
    /**
     * @param type_ the type_ to set
     */
    public void setType_(TypeObjet type_) {
        this.type_ = type_;
    }
}