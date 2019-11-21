import java.util.LinkedList;

public class Node{
    private Node parent;
    private LinkedList<Node> adjaceNodes = new LinkedList<>();
    private LinkedList<String> autoComplete = new LinkedList<>();

    public Node(Node parent){
        this.parent = parent;
    }

    public Node(Node parent, LinkedList<Node> adjaceNodes, LinkedList<String> autoComplete){
        this.adjaceNodes = adjaceNodes;
        this.parent = parent;
        this.autoComplete = autoComplete;
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
        return new Node(this.parent, this.adjaceNodes, this.autoComplete);
    }
}