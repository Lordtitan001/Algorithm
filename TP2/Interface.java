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
    private JPanel blankField = new JPanel();
    private JPanel commanderC = new JPanel();
    private JPanel affichage = new JPanel();
    private JPanel fin = new JPanel();
    private JPanel verticalBox = new JPanel();
    private Button retour = new Button("Retour");

    ////////////////////// Pour la recherche ////////////////////////
    private JTextField jtf = new JTextField("               ");
    private JLabel label = new JLabel("Nom");
    private JTextField jtf2 = new JTextField("                  ");
    private JLabel label2 = new JLabel("Code");
    private JLabel label3 = new JLabel("Type");
    private String[] tab = { "A", "B", "C" };
    private JComboBox<String> combo = new JComboBox<>(tab);
    private JTextField autoComplete = new JTextField("AutoComplete");
   

    private JPanel vertical = new JPanel();
    public Interface() {
        this.setTitle("Interface TP2");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        vertical.setLayout(new BoxLayout(vertical, BoxLayout.PAGE_AXIS));
        commanderC.setLayout(new BoxLayout(commanderC, BoxLayout.LINE_AXIS));
        commanderC.add(this.label);
        commanderC.add(this.jtf);
        commanderC.add(this.label2);
        commanderC.add(this.jtf2);
        commanderC.add(this.label3);
        commanderC.add(combo);

        affichage.setLayout(new BoxLayout(affichage, BoxLayout.LINE_AXIS));
        affichage.add(autoComplete);
        affichage.setPreferredSize(new Dimension(200,  100));
        blankField.setLayout(new BoxLayout(blankField, BoxLayout.LINE_AXIS));
        blankField.setPreferredSize(new Dimension(200,  this.getHeight() - 150));

        fin.setLayout(new BoxLayout(fin, BoxLayout.LINE_AXIS));
        fin.add(retour);

        verticalBox.setLayout(new BoxLayout(verticalBox, BoxLayout.PAGE_AXIS));
        verticalBox.add(commanderC);
        verticalBox.add(affichage, BorderLayout.NORTH);
        verticalBox.add(blankField);
        verticalBox.add(fin);
        
        Menu menu = new Menu();

        menu.getSugession().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                content.getComponents()[1].setVisible(true);
                content.getComponents()[1].setSize(50, 50);
                content.getComponents()[0].setVisible(false);
                //cardLayout.show(content, "commander");
            }
        });  

        menu.getVider().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                // content.getComponents()[0].setVisible(true);
                // cardLayout.show(content, "menu");
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
                content.getComponents()[0].setVisible(true);
                content.getComponents()[1].setVisible(false);
                //cardLayout.show(content, "menu");
            }
        }); 



        BoxLayout cardLayout = new BoxLayout(content, BoxLayout.LINE_AXIS);
        content.setLayout(cardLayout);
        content.add("menu", menu);
        content.add("commander", verticalBox);
        content.getComponents()[1].setVisible(false);
        vertical.add(content);
        vertical.add(retour);
		this.getContentPane().add(vertical);

        this.setVisible(true);
        }

        public JTextField getAutoComplete() {
            return autoComplete;
        }
        public JTextField getJtf() {
            return jtf;
        }

        public static void main(String[] args) {
            Interface in = new Interface();
            //in.contentC.addNotify();;
        }
}