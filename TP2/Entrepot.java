import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.security.Key;
import java.util.*;


public class Entrepot  { //Classe principale qui gere tout le systeme

    private static Map<String,Objet> inventaire = new HashMap<String,Objet>();
    private static Node rootNoms = new Node(null, ' ');
    private static Node rootCodes = new Node(null, ' ');

    public static void readFile(String fileName) {
        try {
            //String fileName = "inventaire.txt";   //utilisateur entrera le nom du fichier
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

    public static void creerArbreNoms(){

        for(var entrySet: inventaire.entrySet()){
            Node currentNode = rootNoms;
            for(char val : entrySet.getValue().getName().toCharArray()){
                if(currentNode.nextChild(val) == null){
                    currentNode.getAdjaceNodes().add(new Node(currentNode, val));
                }
                currentNode.getAutoComplete().add(entrySet.getValue().getName());
                currentNode = currentNode.nextChild(val);
            }
        }
    } //fonctionne

    public static void creerArbreCodes() {
        for(var entrySet: inventaire.entrySet()){
            Node currentNode = rootCodes;
            for(char val : entrySet.getValue().getCode().toCharArray()){
                if(currentNode.nextChild(val) == null){
                    currentNode.getAdjaceNodes().add(new Node(currentNode, val));
                }
                currentNode.getAutoComplete().add(entrySet.getValue().getCode());
                currentNode = currentNode.nextChild(val);
                //System.out.println(entrySet.getValue().getName() + entrySet.getValue().getCode());
            }
        }


    }

    public static Node getRootNoms(){
        return rootNoms;
    }
    public static Node getRootCodes(){
        return rootCodes;
    }

    public static void main(String args[]) {
        System.out.println("Debut Main");
        //readFile("inventaire.txt");
        //creerArbreCodes();
    }





}  

