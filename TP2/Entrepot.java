import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.security.Key;
import java.util.*;


public class Entrepot  { //Classe principale qui gere tout le systeme

    private static Map<String,Objet> inventaire = new HashMap<String,Objet>();
    private static Node root = new Node(null, ' ');
    public static void readFile() {
        try {
            String fileName = "inventaire.txt";
            File source = new File(fileName);
            Scanner scanner = new Scanner(source);
            LinkedList<String> lines = new LinkedList<>();
            int index=0;
        
            while(scanner.hasNextLine()){

                //lecture de chaque ligne et les place dans un tableau
                lines.add(scanner.nextLine()); // fonctionne
                Scanner scannerLignes = new Scanner(lines.get(index++));

                String name = scannerLignes.next();
                String code =scannerLignes.next();
                String type = scannerLignes.next();

                inventaire.put(code, new Objet(name, code, type)); //remplissage de notre inventaire
                //System.out.println("Objet lu :"+inventaire.get(code).getName() +" "+ inventaire.get(code).getType()+" "+ inventaire.get(code).getCode());

            }
        }
        catch (FileNotFoundException e) { e.printStackTrace();}
    } //Fonctionne parfaitement : lis le fichier et remplis l'inventaire avec les objets du fichier

    public static void creerArbre(){

        for(var entrySet: inventaire.entrySet()){
            Node currentNode = root;
            for(char val : entrySet.getValue().getName().toCharArray()){
                if(currentNode.nextChild(val) == null){
                    currentNode.getAdjaceNodes().add(new Node(currentNode, val));
                    currentNode.getAutoComplete().add(entrySet.getValue().getName());
                }
                currentNode = currentNode.nextChild(val);
            }
        }
    }

    public static Node getRoot(){
        return root;
    }

    public static void main(String args[]) {
        System.out.println("Debut Main");
        readFile();
        creerArbre();
    }





}  

