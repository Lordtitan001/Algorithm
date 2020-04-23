import java.util.HashMap;
import java.util.LinkedList;

public class Node{
    private Node parent;
    private LinkedList<Node> adjaceNodes = new LinkedList<>();
    private LinkedList<String> autoComplete = new LinkedList<>();
    //Modification
    private HashMap<String, Objet> listeObjets = new HashMap<String, Objet>();
    private char value = ' ';
    private String type;

    public Node(Node parent, char value,String type){
        this.parent = parent;
        this.value = value;
        this.type = type;
    }

    public Node(Node parent, LinkedList<Node> adjaceNodes, LinkedList<String> autoComplete, char value,String type){
        this.adjaceNodes = adjaceNodes;
        this.parent = parent;
        this.autoComplete = autoComplete;
        this.value = value;
        this.type = type;
    }

    public void afficherAutoComplete() {
       System.out.println(autoComplete);   
   }

    public Node nextChild(char val) {
        for(Node node : this.adjaceNodes){
            if(node.getValue() == val){
                return node;
            }
        }
        return null;
    }

    public HashMap<String, Objet> getListeObjets() {
        return listeObjets;
    }

    public void setListeObjets(HashMap<String, Objet> listeObjets) {
        this.listeObjets = listeObjets;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public char getValue() {
        return value;
    }
    public void setAdjaceNodes(LinkedList<Node> adjaceNodes) {
        this.adjaceNodes = adjaceNodes;
    }

    public void setAutoComplete(LinkedList<String> autoComplete) {
        this.autoComplete = autoComplete;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public LinkedList<Node> getAdjaceNodes() {
        return adjaceNodes;
    }

    public LinkedList<String> getAutoComplete() {
        return autoComplete;
    }

    public Node getParent() {
        return parent;
    }


    public Node clone(){
        return new Node(this.parent, this.adjaceNodes, this.autoComplete, this.value, this.type);
    }



}