import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Fenetre extends JFrame {
    private Panneau pan = new Panneau();
    CardLayout cl = new CardLayout();
    JPanel content = new JPanel();
    //Liste des noms de nos conteneurs pour la pile de cartes
    String[] listContent = {"CARD_1", "CARD_2", "CARD_3"};
    int indice = 0;
    JPanel boutonPane = new JPanel();

    public Fenetre(){ 
      this.setTitle("Animation");
      this.setSize(300, 300);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setLocationRelativeTo(null);

      JPanel card1 = new JPanel();
      card1.setBackground(Color.blue);		
      JPanel card2 = new JPanel();
      card2.setBackground(Color.red);		
      JPanel card3 = new JPanel();
      card3.setBackground(Color.green);

      Button button1 = new Button("Next page");
      button1.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent event){
            cl.next(content);
          }
      });
      Button button2 = new Button("Indice");
      button2.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent event){
              if(indice++ > 2)
                indice =0;

                cl.show(content, listContent[indice]);
          }
      });
     
      this.boutonPane.add(button1);
      this.boutonPane.add(button2);

      content.setLayout(cl);

      this.content.add(card1, listContent[0]);
      this.content.add(card2, listContent[1]);
      this.content.add(card3, listContent[2]);

      this.getContentPane().add(boutonPane, BorderLayout.NORTH);
      this.getContentPane().add(content, BorderLayout.CENTER);
    
      this.setVisible(true);
    }
  
    private void go(){
        boolean xChange = false, yChange = false;
        int x = pan.getPosX(), y = pan.getPosY();
      for(;;){

        if(x > pan.getWidth() - 50) 
        { 
            xChange =true;
        }
        if(y > pan.getHeight() - 50){
            yChange = true;
        } 

        if(x < 1){
            xChange = false;
        }

        if(y < 1){
            yChange =false;
        }

        if(!xChange && !yChange){
            x++;
            y++;
        }
        else if(xChange && yChange){
            y--;
            x--;
        }
        else if(!xChange){
            x++;
            y--;
        }
        else if(!yChange){
            x--;
            y++;
        }

        pan.setPosX(x);
        pan.setPosY(y);
        pan.repaint();

        try {
            Thread.sleep(10);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
    }  
    public static void main (String args[]){
        var fen = new Fenetre();
    }
}
  