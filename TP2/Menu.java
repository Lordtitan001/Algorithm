import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Menu  extends JPanel{
    private JPanel initComp = new JPanel();
    private Box verticalBox = new Box(BoxLayout.PAGE_AXIS);

    private JTextField jtf = new JTextField("Entrer le Nom ici");
    // private JLabel label = new JLabel("Nom du fichier");
    private JButton initialiser = new JButton("OK");

    private JButton sugession = new JButton("Rechercher objet");
    private JButton ajouter = new JButton("Ajouter au panier");
    private JButton vider = new JButton("Vider le panier");
    private JButton quitter = new JButton("Quitter");
    

    public Menu(){
        // initComp.add(label);
        initComp.add(jtf);
        initComp.add(initialiser);

        verticalBox.add(initComp);
        verticalBox.add(sugession);
        verticalBox.add(ajouter);
        verticalBox.add(vider);
        verticalBox.add(quitter);
        
        this.add(verticalBox);
    }

    /**
     * @return the sugession
     */
    public JButton getSugession() {
        return sugession;
    }

    /**
     * @return the ajouter
     */
    public JButton getAjouter() {
        return ajouter;
    }

    /**
     * @return the vider
     */
    public JButton getVider() {
        return vider;
    }

    /**
     * @return the quitter
     */
    public JButton getQuitter() {
        return quitter;
    }

    /**
     * @return the initialiser
     */
    public JButton getInitialiser() {
        return initialiser;
    }

    
}