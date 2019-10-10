enum Type {X, Y, Z};

public class Robot{
    Robot(Type type){

        this.masse_ = 0;
        switch(type) {
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

    public void setMasse(double masse){
        this.masse_ = masse;
        switch(this.type) {
            case X:
                this.constante_ = 1 + masse;
                 break;
            case Y:
                this.constante_ = 1.5 + 0.6*masse;
                break;
            case Z:
                this.constante_ = 2.5 + 0.2*masse;
                break;
            default:
              
          }
    }

    public boolean addMasse(double masse){
        if(masse + this.masse_ <= this.capacity_){
            setMasse(masse + this.masse_);
            return true;
        }
        return false;
    }

    public void resetMasse(){
        setMasse(0);
    }

    private Type type_;
    private double capacity_;
    private double masse_;
    private double constante_;
}