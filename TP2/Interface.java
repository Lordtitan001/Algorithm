import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Interface extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel content = new JPanel();
    private JPanel commanderC = new JPanel();
    private CardLayout cardLayout = new CardLayout();
    private Button retour = new Button("Retour");

    ////////////////////// Pour la recherche ////////////////////////
    private JTextField jtf = new JTextField("               ");
    private JLabel label = new JLabel("Nom");
    private JTextField jtf2 = new JTextField("                  ");
    private JLabel label2 = new JLabel("Code");
    private JLabel label3 = new JLabel("Type");
    private String[] tab = { "A", "B", "C" };
    private JComboBox<String> combo = new JComboBox<>(tab);
    //////////////////// Pour Ajouter la commande ////////////////////
    // private Box verticalBox = new Box(BoxLayout.PAGE_AXIS);
    // private JTextField jtfA = new JTextField("                  ");
    // private JLabel labelA = new JLabel("Type A");
    // private JPanel contentA = new JPanel();
    // private JTextField jtfB = new JTextField("                  ");
    // private JLabel labelB = new JLabel("Type B");
    // private JPanel contentB = new JPanel();
    // private JTextField jtfC = new JTextField("                  ");
    // private JLabel labelC = new JLabel("Type C");
    // private JPanel contentC = new JPanel();
    // private Button buttonCommader = new Button("Ok");
    
     private Box vertical = new Box(BoxLayout.PAGE_AXIS);
    public Interface() {
        this.setTitle("Interface TP2");
        this.setSize(700, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        commanderC.add(this.label);
        commanderC.add(this.jtf);
        commanderC.add(Box.createRigidArea(new Dimension(10, 0)));
        commanderC.add(this.label2);
        commanderC.add(this.jtf2);
        commanderC.add(Box.createRigidArea(new Dimension(10, 0)));
        commanderC.add(this.label3);
        commanderC.add(combo);
        commanderC.add(retour);

        // contentA.add(labelA);
        // contentA.add(jtfA);
        // contentB.add(labelB);
        // contentB.add(jtfB);
        // contentC.add(labelC);
        // contentC.add(jtfC);
        // contentC.add(buttonCommader);
        // verticalBox.add(contentB);
        // verticalBox.add(contentA);
        // verticalBox.add(contentC);

        Menu menu = new Menu();

        menu.getSugession().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                cardLayout.show(content, "commander");
            }
        });  

        menu.getVider().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                cardLayout.show(content, "commander");
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

        vertical.add(content, BorderLayout.WEST);
        vertical.add(retour);
		this.getContentPane().add(vertical, BorderLayout.WEST);

        this.setVisible(true);
        }

        public JTextField getJtf() {
            return jtf;
        }

        public static void main(String[] args) {
            Interface in = new Interface();
            //in.contentC.addNotify();;
        }
}