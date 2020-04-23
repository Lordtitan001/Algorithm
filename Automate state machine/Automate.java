import java.util.HashMap;
import java.util.LinkedList;
import javax.swing.DefaultListModel;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;

public class Automate {

    private static HashMap<String, Objet> finalObjets = new HashMap<>();
    private static HashMap<String, Objet> objetsNoms = Entrepot.getRootNoms().getListeObjets();
    private static HashMap<String, Objet> objetsCodes = Entrepot.getRootNoms().getListeObjets();
    private static HashMap<String, Objet> objetsTypes = Entrepot.getRootNoms().getListeObjets();
    private static int count = 0;
    private static Interface in;
    private static String fileName = "";

    public static KeyListener prediction(Node root, Interface in) {

        KeyListener listener = new KeyListener() {
            Node currentNode = root;
            Node lastNode = currentNode.clone();
            @Override
            public void keyPressed(KeyEvent event) {
                if (event.getKeyCode() != KeyEvent.VK_CAPS_LOCK && event.getKeyCode() != KeyEvent.VK_SHIFT) {
                    if (event.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE) {
                        if (currentNode == null) {
                            count--;
                            if (count < 1) {
                                currentNode = lastNode.clone();
                                count = 0;
                            }
                            if (currentNode != null) {
                                getCurrentNode(currentNode);
                            }
                        } else if (currentNode != null) {
                            if (currentNode.getParent() != null) {
                                currentNode = currentNode.getParent();
                                lastNode = currentNode.clone();
                                getCurrentNode(currentNode);
                            }
                        }
                    } else if (currentNode != null) {
                        currentNode = currentNode.nextChild(event.getKeyChar());
                        if (currentNode != null) {
                            lastNode = currentNode.clone();
                            getCurrentNode(currentNode);
                        } else {
                            count++;
                        }
                    } else {
                        count++;
                    }
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

    public static void getCurrentNode(Node node) {
        if (node.getType() == "nom") {
            objetsNoms = node.getListeObjets();
        } else if (node.getType() == "type") {
            objetsTypes = node.getListeObjets();
        } else if (node.getType() == "code") {
            objetsCodes = node.getListeObjets();
        }
        setFinalObjets();
        afficherListeObjet(finalObjets);
    }

    public static HashMap<String, Objet> getFinalObjets() {
        return finalObjets;
    }

    public static void afficherListeObjet(HashMap<String, Objet> objets) {
        DefaultListModel<Objet> model = new DefaultListModel<>();
        // objets.forEach((code, objet) -> {
        //     model.addElement(objet.getName() + " " +
        //      objet.getCode() + " " + objet.getType());
        //     System.out.println(objet.getName() + " " +
        //      objet.getCode() + " " + objet.getType());
        // });
        model.addAll(objets.values());
        in.getListObjet().setModel(model);
        
    }

    public static void setFinalObjets() {
        finalObjets = new HashMap<String, Objet>();
        for(var entry : objetsNoms.entrySet()){
            if (objetsCodes.containsKey(entry.getKey()) 
            && objetsTypes.containsKey(entry.getKey())
             && Entrepot.getInventaire().containsKey(entry.getKey())) {
                finalObjets.put(entry.getKey(), entry.getValue());
            }
        }
    }

    public static Interface setKeyListener(Interface in) {
        System.out.println(Entrepot.getRootCodes().getType());
        KeyListener codeListener = prediction(Entrepot.getRootCodes(), in);
        KeyListener nameListener = prediction(Entrepot.getRootNoms(), in);
        in.getJtf2().addKeyListener(codeListener);
        in.getJtf().addKeyListener(nameListener);
        var combo = in.getCombo();
        combo.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                String val = combo.getSelectedItem().toString();
               System.out.println(val);
               if(val == "Any"){
                    getCurrentNode(Entrepot.getRootTypes());
               }
               else{
                    getCurrentNode(Entrepot.getRootTypes().nextChild(val.charAt(0)));
               }
            }
        });
        return in;
    }

    //modifier les autres  DefaultListModel<String> pour DefaultListModel<Objet> dans la classe Menu et Interface ou Entrepot
    public static void afficherArbre(Node root) {
        if (!root.getAdjaceNodes().isEmpty()) {
            root.getAdjaceNodes().forEach((node) -> {
                node.getListeObjets().forEach((code, objet) -> {
                    System.out.println(node.getValue());
                    System.out.println(objet.getName() + " " 
                    + objet.getCode() + " " + objet.getType());
                });
                afficherArbre(node);
            });
        }
    }

    public static void main(String[] args) {
        
        in= new Interface();
        in.getInitialiser().addActionListener (new ActionListener () { 
            public void actionPerformed(ActionEvent e) {
                fileName = in.getFilePath().getText();
                System.out.println(fileName);
                Entrepot.readFile(fileName);
                Entrepot.creerAbresTypes();
                Entrepot.creerArbreCodes();
                Entrepot.creerArbreNoms();
                in = setKeyListener(in);  
            }
        });
        in.setVisible(true);
    }
}