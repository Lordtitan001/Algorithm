import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JButton;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
 

public class Button extends JButton implements MouseListener {

  private static final long serialVersionUID = 1L;
  private String name = "new button";

   Button(String name){
       super(name);
       this.name = name;
   }
   public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        GradientPaint gradientPaint = new GradientPaint(0, 0, Color.RED, 50, 50, Color.BLACK);
        g2.setPaint(gradientPaint);
        g2.fillRect(0, 0, this.getWidth(), this.getHeight());
        g2.setColor(Color.white);
        g2.drawString(this.name, 0, 10);
   }

    //Méthode appelée lors du clic de souris
  public void mouseClicked(MouseEvent event) { }

  //Méthode appelée lors du survol de la souris
  public void mouseEntered(MouseEvent event) { }

  //Méthode appelée lorsque la souris sort de la zone du bouton
  public void mouseExited(MouseEvent event) { }

  //Méthode appelée lorsque l'on presse le bouton gauche de la souris
  public void mousePressed(MouseEvent event) { }

  //Méthode appelée lorsque l'on relâche le clic de souris
  public void mouseReleased(MouseEvent event) { }    
}