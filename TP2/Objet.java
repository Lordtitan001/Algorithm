import java.lang.Enum;

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

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @return the type
     */
    public Type getType() {
        return type;
    }
    
    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @param type the type to set
     */
    public void setType(Type type) {
        this.type = type;
    }
}