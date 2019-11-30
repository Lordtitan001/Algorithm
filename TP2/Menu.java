import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Menu extends JPanel {

    private static final long serialVersionUID = 1L;
    private Box verticalBox = new Box(BoxLayout.PAGE_AXIS);
    private static JTextField jtf = new JTextField();
    private static Button initialiser = new Button("Ouvrir le fichier");
    private Button sugession = new Button("Rechercher objet");
    private Button vider = new Button("Vider le panier");
    private Button passerCommande = new Button("passer commande");
    private Button afficherPanier = new Button("afficher panier");
    private Button ajouterPanier = new Button("ajouter au panier");
    private Button quitter = new Button("Quitter");
    private JLabel label = new JLabel("");

    public Menu() {
        jtf.setSize(20, 20);
        verticalBox.add(jtf);
        verticalBox.add(Box.createRigidArea(new Dimension(0, 20)));
        verticalBox.add(initialiser);
        verticalBox.add(Box.createRigidArea(new Dimension(0, 20)));
        verticalBox.add(sugession);
        verticalBox.add(Box.createRigidArea(new Dimension(0, 20)));
        verticalBox.add(vider);
        verticalBox.add(Box.createRigidArea(new Dimension(0, 20)));
        verticalBox.add(passerCommande);
        verticalBox.add(Box.createRigidArea(new Dimension(0, 20)));
        verticalBox.add(afficherPanier);
        verticalBox.add(Box.createRigidArea(new Dimension(0, 20)));
        verticalBox.add(quitter);
        verticalBox.add(Box.createRigidArea(new Dimension(0, 20)));
        verticalBox.add(label);
        this.add(verticalBox, BorderLayout.WEST);

        passerCommande.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if (Entrepot.commander())
                    label.setText("Commande passee, les objets commandes ne"
                     +  " sont plus disponible dans l'inventaire");
                else
                    label.setText(
                            "Commande impossible (Poids superieur a 25 kg), videz le panier"
                            + " ou bien retirez certains objets");

                Interface.getModelPanier().clear();
            }
        });

        vider.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                label.setText("panier vide");
                Entrepot.viderPanier();
                Interface.getModelPanier().clear();
            }
        });

        quitter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                label.setText("close on sunday");
            }
        });

    }

    public JLabel getLabel() {
        return label;
    }

    public static JTextField getJtf() {
        return jtf;
    }

    public Button getSugession() {
        return sugession;
    }

    public Button getVider() {
        return vider;
    }

    public Button getQuitter() {
        return quitter;
    }

    public Button getPasserCommande() {
        return passerCommande;
    }

    public Button getAfficherPanier() {
        return afficherPanier;
    }

    public Button getAjouterPanier() {
        return ajouterPanier;
    }

    public static Button getInitialiser() {
        return initialiser;
    }
}