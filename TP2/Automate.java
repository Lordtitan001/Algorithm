import java.util.LinkedList;
import java.util.Scanner;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class Automate {

    public static void prediction() {

        /////////////////////////////////////////////////////////////////////////////
        Node initNode = new Node(null);
        LinkedList<String> word1 = new LinkedList<>();
        LinkedList<String> word2 = new LinkedList<>();
        word1.push("avi");
        word1.push("av");
        word1.push("avod");
        word2.push("con");
        word2.push("com");

        LinkedList<Node> nodesA = new LinkedList<>();
        LinkedList<Node> nodesC = new LinkedList<>();
        LinkedList<Node> nodesV = new LinkedList<>();
        LinkedList<Node> nodesI = new LinkedList<>();
        LinkedList<Node> nodesO = new LinkedList<>();
        LinkedList<Node> nodesN = new LinkedList<>();
        LinkedList<Node> nodesM = new LinkedList<>();

        Node nodeA = new Node(initNode, nodesA, word1, 'a');
        Node nodeV = new Node(nodeA, nodesV, word1, 'v');
        Node nodeI = new Node(nodeV, nodesI, new LinkedList<>(word1.subList(0, 2)), 'i');
        Node nodeC = new Node(initNode, nodesC, word2, 'c');
        Node nodeO = new Node(nodeC, nodesO, word2, 'o');
        Node nodeN = new Node(nodeO, nodesN, new LinkedList<>(word2.subList(1, 2)), 'n');
        Node nodeM = new Node(nodeO, nodesM, new LinkedList<>(word2.subList(0, 1)), 'm');

        initNode.getAdjaceNodes().add(nodeA);
        initNode.getAdjaceNodes().add(nodeC);
        nodesA.add(nodeV);
        nodesV.add(nodeI);
        nodesC.add(nodeO);
        nodesO.add(nodeN);
        nodesO.add(nodeM);
        // afficherArbre(initNode);
        ///////////////////////////////////////////////////////////////////////////////

        KeyListener listener = new KeyListener() {
            Node currentNode = initNode;
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
                        }
                    } else if (currentNode != null) {
                        if (currentNode.getParent() != null) {
                            currentNode = currentNode.getParent();
                            lastNode = currentNode.clone();
                            currentNode.afficherAutoComplete();
                        }
                    }
                } else if (currentNode != null) {
                    currentNode = currentNode.nextChild(event.getKeyChar());
                    if (currentNode != null) {
                        lastNode = currentNode.clone();
                        System.out.println(lastNode.getValue());
                        currentNode.afficherAutoComplete();
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

            private void printEventInfo(String str, KeyEvent e) {
                System.out.print(e.getKeyChar());
            }

        };

        //Interface in = new Interface();
        JFrame frame = new JFrame("Key Listener");
        Container contentPane = frame.getContentPane();
        JTextField textField = new JTextField();
        //in.getJtf().addKeyListener(listener);
        textField.addKeyListener(listener);
        contentPane.add(textField);
        frame.pack();
        frame.setVisible(true);

    }

    public static void afficherArbre(Node root) {
        if (!root.getAdjaceNodes().isEmpty()) {
            root.getAdjaceNodes().forEach((node) -> {
                System.out.println(node.getAutoComplete());
                afficherArbre(node);
            });
        }
    }

    public static void main(String[] args) {
        prediction();
    }
}