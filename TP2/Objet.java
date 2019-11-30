enum Type {A, B, C};
public class Objet{

    private String name ;
    private String code ;
    private Type type;

    public Objet(String name, String code, String type){
        this.code = code;
        this.name = name;
        switch(type) {
            case "A": this.type = Type.A; break;
            case "B": this.type = Type.B; break;
            case "C": this.type = Type.C; break;
        }
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }
    
    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(Type type) {
        this.type = type;
    }
}