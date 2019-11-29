import java.util.LinkedList;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class Automate {

    private static Node current ;
    private static Node rootNoms = Entrepot.getRootNoms();
    private static Node rootCode = Entrepot.getRootCodes();
    private static Node rootType = Entrepot.getRootTypes();
    private static int count = 0;
    public  void setCurrent(Node current) {
        Automate.current = current;
    }

    public Node getCurrent() {
        return current;
    }
    public static KeyListener prediction(Node root, Interface in) {

        KeyListener listener = new KeyListener() {
            
           
            Node currentNode = root;
            Node lastNode = currentNode.clone();
            
            @Override
            public void keyPressed(KeyEvent event) {
                if (event.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    if (currentNode == null) {
                        count--;
                        if (count < 1) {
                            currentNode = lastNode.clone();
                            count = 0;
                        }
                        if (currentNode != null) {
                            currentNode.afficherAutoComplete();
                            Automate.current = currentNode;
                            // in.getAutoComplete().
                            // setText(transformArrayIntoString(currentNode.getAutoComplete()));
                        }
                    } else if (currentNode != null) {
                        if (currentNode.getParent() != null) {
                            currentNode = currentNode.getParent();
                            lastNode = currentNode.clone();
                            Automate.current = currentNode;
                            currentNode.afficherAutoComplete();
                            // in.getAutoComplete().
                            // setText(transformArrayIntoString(currentNode.getAutoComplete()));
                        }
                    }
                } else if (currentNode != null) {
                    currentNode = currentNode.nextChild(event.getKeyChar());
                    if (currentNode != null) {
                        lastNode = currentNode.clone();
                        Automate.current = currentNode;
                         currentNode.afficherAutoComplete();
                        // in.getAutoComplete().
                        //     setText(transformArrayIntoString(currentNode.getAutoComplete()));
                    } else {
                        count++;
                    }
                } else {
                    count++;
                }
            }

            @Override
            public void keyReleased(KeyEvent event) {
            }

            @Override
            public void keyTyped(KeyEvent event) {

            }
        
        };
        return listener;
    }

    public static Interface setKeyListener(Interface in) {
        KeyListener codeListener = prediction(rootCode, in);
        KeyListener nameListener = prediction(rootNoms, in);
        in.getJtf().addKeyListener(nameListener);
        in.getJtf2().addKeyListener(codeListener); 
        return in;  

    }

    public static void setAllRoots(){
        //current
    }

    public static String transformArrayIntoString(LinkedList<String> array){
        String builder = "" ;

        for(String word : array){
            builder += word + "\n";

        }
        return builder;
    }
    public static void afficherArbre(Node root) {
        if (!root.getAdjaceNodes().isEmpty()) {
            root.getAdjaceNodes().forEach((node) -> {
                System.out.println(node.getListeObjets().size());
                // node.getListeObjets().forEach((objet)->{
                //     System.out.println(node.getValue());
                //     System.out.println(objet.getName() + " " + objet.getCode() + " " + objet.getType());
                // });
                afficherArbre(node);
            });
        }
    }

    public static void main(String[] args) {
        Entrepot.readFile("inventaire.txt");
        Entrepot.creerAbresTypes();
        // Entrepot.creerArbreCodes();
        // Entrepot.creerArbreNoms();

        afficherArbre(Entrepot.getRootTypes());
        // Interface in = new Interface();
        // in = setKeyListener(in);
        // in.setVisible(true);
        //Entrepot.rechercheParType();
        //afficherArbre(Entrepot.getRootTypes());
    }
}