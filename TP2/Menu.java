import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Menu  extends JPanel{

    private static final long serialVersionUID = 1L;
    private Box verticalBox = new Box(BoxLayout.PAGE_AXIS);
    private JTextField jtf = new JTextField("               ");
    private Button initialiser = new Button("Ouvrir le fichier");
    private Button sugession = new Button("Rechercher objet");
    private Button vider = new Button("Vider le panier");
    private Button quitter = new Button("Quitter");

    public Menu(){
        verticalBox.add(jtf);
        verticalBox.add(Box.createRigidArea(new Dimension(0, 20)));
        verticalBox.add(initialiser);
        verticalBox.add(Box.createRigidArea(new Dimension(0, 20)));
        verticalBox.add(sugession);
        verticalBox.add(Box.createRigidArea(new Dimension(0, 20)));
        verticalBox.add(vider);
        verticalBox.add(Box.createRigidArea(new Dimension(0, 20)));
        verticalBox.add(quitter);
        this.add(verticalBox, BorderLayout.WEST);
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

    public Button getInitialiser() {
        return initialiser;
    }
}