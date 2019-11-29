import java.util.LinkedList;
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class Automate {

    public static void prediction(Node root) {
        Interface in = new Interface();
        KeyListener listener = new KeyListener() {
            Node currentNode = root;
            int count = 0;
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
                            in.getAutoComplete().
                            setText(transformArrayIntoString(currentNode.getAutoComplete()));
                        }
                    } else if (currentNode != null) {
                        if (currentNode.getParent() != null) {
                            currentNode = currentNode.getParent();
                            lastNode = currentNode.clone();
                            currentNode.afficherAutoComplete();
                            in.getAutoComplete().
                            setText(transformArrayIntoString(currentNode.getAutoComplete()));
                        }
                    }
                } else if (currentNode != null) {
                    currentNode = currentNode.nextChild(event.getKeyChar());
                    if (currentNode != null) {
                        lastNode = currentNode.clone();
                        System.out.println(lastNode.getValue());
                        currentNode.afficherAutoComplete();
                        in.getAutoComplete().
                            setText(transformArrayIntoString(currentNode.getAutoComplete()));
                    } else {
                        count++;
                    }
                } else {
                    count++;
                }
                System.out.println(count);
            }

            @Override
            public void keyReleased(KeyEvent event) {
            }

            @Override
            public void keyTyped(KeyEvent event) {

            }
        };

       
        JFrame frame = new JFrame("Key Listener");
        Container contentPane = frame.getContentPane();
        JTextField textField = new JTextField();
        in.getJtf().addKeyListener(listener);
        //textField.addKeyListener(listener);
        
        contentPane.add(in);
        frame.pack();
        frame.setVisible(true);

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
                System.out.println(node.getValue());
                afficherArbre(node);
            });
        }
    }

    public static void main(String[] args) {
        Entrepot.readFile();
        Entrepot.creerArbre();
        prediction(Entrepot.getRoot());
    }
}