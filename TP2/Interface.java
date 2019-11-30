import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Interface extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel content = new JPanel();
    private JPanel blankField = new JPanel();
    private JPanel commanderC = new JPanel();
    private JPanel affichage = new JPanel();
    private JPanel fin = new JPanel();
    private JPanel verticalBox = new JPanel();
    private static JPanel panier = new JPanel();
    private Button retour = new Button("Retour");
    private static JList<String> listObjet = new JList<>();

    private JList<String> listObjetPannier = new JList<>();

    private JScrollPane scrollPane  = new JScrollPane(listObjet);
    private JScrollPane scrollPanePanier = new JScrollPane(listObjetPannier);
    
    private static DefaultListModel<String> modelPanier = new DefaultListModel<String>();

    private JTextField jtf = new JTextField();
    private JLabel label = new JLabel("Nom");
    private JTextField jtf2 = new JTextField();
    private JLabel label2 = new JLabel("Code");
    private JLabel label3 = new JLabel("Type");
    private String[] tab = {"Any", "A", "B", "C" };
    private JComboBox<String> combo = new JComboBox<>(tab);
    private JTextField autoComplete = new JTextField();

    
   

    private JPanel vertical = new JPanel();
    public Interface() {
        this.setTitle("Interface TP2");
        this.setSize(600, 450);
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
        
        Menu menu = new Menu();

        var model = new DefaultListModel<String>();

        listObjet.setModel(model);
        scrollPane.setPreferredSize(new Dimension(50, 200));
        
        affichage.setPreferredSize(new Dimension(200,  100));

        blankField.setLayout(new BoxLayout(blankField, BoxLayout.LINE_AXIS));
        blankField.setPreferredSize(new Dimension(200,  100));

        fin.setLayout(new BoxLayout(fin, BoxLayout.LINE_AXIS));
        fin.add(retour);

        verticalBox.setLayout(new BoxLayout(verticalBox, BoxLayout.PAGE_AXIS));
        verticalBox.add(commanderC);
        verticalBox.add(scrollPane);
        verticalBox.add(menu.getAjouterPanier());
        verticalBox.add(blankField);
        verticalBox.add(fin);
        
        
        listObjetPannier.setModel(modelPanier);
        scrollPanePanier.setPreferredSize(new Dimension(this.getHeight(), this.getHeight() - 50));
        panier.add(scrollPanePanier);

        

        menu.getSugession().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                content.getComponents()[1].setVisible(true);
                content.getComponents()[1].setSize(50, 50);
                content.getComponents()[0].setVisible(false);
                content.getComponents()[2].setVisible(false);
                Automate.setFinalObjets();
                Automate.afficherListeObjet(Automate.getFinalObjets());  
                
            }
        });  

        retour.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                content.getComponents()[0].setVisible(true);
                content.getComponents()[1].setVisible(false);
                content.getComponents()[2].setVisible(false);
            }
        }); 

        menu.getAfficherPanier().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                menu.getLabel().setText("panier afficher");
                content.getComponents()[0].setVisible(false);
                content.getComponents()[1].setVisible(false);
                content.getComponents()[2].setVisible(true);
                for(var entrySet : Entrepot.getPanier().entrySet()){
                    modelPanier.addElement(entrySet.getValue().getName() + " " + entrySet.getValue().getCode() + " " + entrySet.getValue().getType());
                }
            }
        });

        menu.getAjouterPanier().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                menu.getLabel().setText("ajout au panier");
                System.out.println(listObjet.getSelectedValue().split(" ", 3)[1]);
                Entrepot.ajouterAuPanier(listObjet.getSelectedValue().split(" ", 3)[1]);  
                Automate.setFinalObjets();
                Automate.afficherListeObjet(Automate.getFinalObjets());              
            }
        });


        BoxLayout cardLayout = new BoxLayout(content, BoxLayout.LINE_AXIS);
        content.setLayout(cardLayout);
        content.add("menu", menu);
        content.add("commander", verticalBox);
        content.add("panier", panier);
        content.getComponents()[1].setVisible(false);
        content.getComponents()[2].setVisible(false);

        vertical.add(content);
        vertical.add(retour);

		this.getContentPane().add(vertical);
        this.setVisible(true);
        }

        public static DefaultListModel<String> getModelPanier() {
            return modelPanier;
        }

        public JList<String> getListObjetPannier() {
            return listObjetPannier;
        }

        public JTextField getAutoComplete() {
            return autoComplete;
        }
        public JTextField getJtf() {
            return jtf;
        }

        public JTextField getFilePath(){
            return Menu.getJtf();
        }

        public Button getInitialiser() {
            return Menu.getInitialiser();
        }

        public JTextField getJtf2() {
            return jtf2;
        }

        public JComboBox<String> getCombo() {
            return combo;
        }

        public JList<String> getListObjet() {
            return listObjet;
        }
}
