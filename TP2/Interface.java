import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Interface extends JFrame {

    private JPanel content = new JPanel();
    private JPanel commanderC = new JPanel();
    private JPanel passeCommande = new JPanel();
    private JPanel ajoutPanier = new JPanel();
    private JPanel retraitPanier = new JPanel();

    private CardLayout cardLayout = new CardLayout();
    private JButton retour = new JButton("Retour");

    ////////////////////// Pour la recherche ////////////////////////
    private JTextField jtf = new JTextField("               ");
    private JLabel label = new JLabel("Nom");
    private JTextField jtf2 = new JTextField("                  ");
    private JLabel label2 = new JLabel("Code");
    private JLabel label3 = new JLabel("Type");
    private String[] tab = { "A", "B", "C" };
    private JComboBox<String> combo = new JComboBox<>(tab);
    //////////////////// Pour Ajouter la commande ////////////////////
    private Box verticalBox = new Box(BoxLayout.PAGE_AXIS);
    private JTextField jtfA = new JTextField("                  ");
    private JLabel labelA = new JLabel("Type A");
    private JPanel contentA = new JPanel();
    private JTextField jtfB = new JTextField("                  ");
    private JLabel labelB = new JLabel("Type B");
    private JPanel contentB = new JPanel();
    private JTextField jtfC = new JTextField("                  ");
    private JLabel labelC = new JLabel("Type C");
    private JPanel contentC = new JPanel();
    private JButton buttonCommader = new JButton("Ok");
    //////////////////// Pour Vider la commande ////////////////////
    private Box verticalBox2 = new Box(BoxLayout.PAGE_AXIS);
    private JTextField jtfA2 = new JTextField("                         ");
    private JLabel labelA2 = new JLabel("Type A");
    private JPanel contentA2 = new JPanel();
    private JTextField jtfB2 = new JTextField("                         ");
    private JLabel labelB2 = new JLabel("Type B");
    private JPanel contentB2 = new JPanel();
    private JTextField jtfC2 = new JTextField("                         ");
    private JLabel labelC2 = new JLabel("Type C");
    private JPanel contentC2 = new JPanel();
    private JButton buttonVider = new JButton("Ok");
    ////////////////////////////////////////////////////////////////
    

    private Box vertical = new Box(BoxLayout.PAGE_AXIS);
    public Interface() {
        this.setTitle("Interface TP 2");
        this.setSize(700, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);


        commanderC.add(this.label);
        commanderC.add(this.jtf);
        commanderC.add(this.label2);
        commanderC.add(this.jtf2);
        commanderC.add(this.label3);
        commanderC.add(combo);


        contentA.add(labelA);
        contentA.add(jtfA);
        contentB.add(labelB);
        contentB.add(jtfB);
        contentC.add(labelC);
        contentC.add(jtfC);
        contentC.add(buttonCommader);
        verticalBox.add(contentB);
        verticalBox.add(contentA);
        verticalBox.add(contentC);

        contentA2.add(labelA2);
        contentA2.add(jtfA2);
        contentB2.add(labelB2);
        contentB2.add(jtfB2);
        contentC2.add(labelC2);
        contentC2.add(jtfC2);
        contentC2.add(buttonVider);
        verticalBox2.add(contentB2);
        verticalBox2.add(contentA2);
        verticalBox2.add(contentC2);

 

        Menu menu = new Menu();

        menu.getSugession().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                cardLayout.show(content, "commander");
            }
        });  
        
        menu.getAjouter().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                cardLayout.show(content, "passerCommande");
            }
        });

        menu.getVider().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                cardLayout.show(content, "viderCommande");
            }
        });

        menu.getQuitter().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.out.println("close on sunday");
            }
        }); 

        menu.getInitialiser().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.out.println("Oh boy looks like i am useless");
            }
        }); 

        retour.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                cardLayout.show(content, "menu");
            }
        }); 
        content.setLayout(cardLayout);
        content.add("menu", menu);
        content.add("commander", commanderC);
        content.add("passerCommande", verticalBox);
        content.add("viderCommande", verticalBox2);

        CardLayout mrg = new CardLayout();

        vertical.add(content);
        vertical.add(retour);
		this.getContentPane().add(vertical, BorderLayout.WEST);



        this.setVisible(true);
        }

        /**
         * @return the jtf
         */
        public JTextField getJtf() {
            return jtf;
        }

}