import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import java.awt.*;

public class ObjetListCellRenderer extends DefaultListCellRenderer {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
            boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (value instanceof Objet) {
            Objet objet = (Objet) value;
            setText(objet.getName() + " " +objet.getCode()  + " "+ objet.getType().toString());
            // setToolTipText(ingredi());
            // setIcon(ingredient.getIcon());
        }
        return this;
    }
}