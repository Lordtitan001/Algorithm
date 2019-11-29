import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class Entrepot  { //Classe principale qui gere tout le systeme

    private static Map<String,Objet> inventaire = new HashMap<String,Objet>();
    private static Node rootNoms = new Node(null, ' ', "nom");
    private static Node rootCodes = new Node(null, ' ', "code");
    private static Node rootTypes = new Node(null, ' ', "type");


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
                    currentNode.getAdjaceNodes().add(new Node(currentNode, val, "nom"));
                }
                currentNode.getListeObjets().add(entrySet.getValue());
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
                    currentNode.getAdjaceNodes().add(new Node(currentNode, val, "code"));
                }
                currentNode.getListeObjets().add(entrySet.getValue());
                currentNode.getAutoComplete().add(entrySet.getValue().getCode());
                currentNode = currentNode.nextChild(val);
            }
        }


    }

    public static void rechercheParType(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("entrez un type d'objet");
        String type2 = scanner.next();
        Type type = null;
        switch(type2) {
            case "A": type = Type.A; break;
            case "B": type = Type.B; break;
            case "C": type = Type.C; break;
        }
        switch (type){
            case A:
                for(var i : inventaire.entrySet()) {
                    if (i.getValue().getType() == Type.A)
                        System.out.println(i.getValue().getName());
                }
                break;
            case B:
                for(var i : inventaire.entrySet()) {
                    if (i.getValue().getType() == Type.B)
                        System.out.println(i.getValue().getName());
                }
                break;
            case C:
                for(var i : inventaire.entrySet()) {
                    if (i.getValue().getType() == Type.C)
                        System.out.println(i.getValue().getName());
                }
                break;
        }
        scanner.close();
    }

    public static void creerAbresTypes(){
        for(var entrySet: inventaire.entrySet()){
            Node currentNode = rootTypes;
            for(char val : entrySet.getValue().getType().toString().toCharArray()){
                if(currentNode.nextChild(val) == null){
                    currentNode.getAdjaceNodes().add(new Node(currentNode, val,"type"));
                }
                currentNode.getListeObjets().add(entrySet.getValue());
                currentNode.getAutoComplete().add(entrySet.getValue().getType().toString());
                currentNode = currentNode.nextChild(val);
            }
        }
    }

    public static  void initialisation(String fileName){

        readFile(fileName);
        creerArbreNoms();
        creerArbreNoms();
        creerAbresTypes();

    }

    public static Node getRootNoms(){
        return rootNoms;
    }
    public static Node getRootCodes(){
        return rootCodes;
    }
    public static Node getRootTypes(){
        return rootTypes;
    }

    private static Map<String,Objet> panier = new HashMap<String,Objet>();

    public static void main(String args[]) {
        System.out.println("Debut Main");
        initialisation("inventaire.txt");
        interfaceCommande();

    }

    public static void ajouterAuPanier(String nomObjet){

        for (var entrySet : inventaire.entrySet()){

            if(entrySet.getValue().getName().equals(nomObjet)){
                panier.put(entrySet.getValue().getCode(),entrySet.getValue());
                inventaire.remove(entrySet.getValue().getCode(),entrySet.getValue());
                break;
            }
        }
    }

    public static void afficherPanier(){
        Objet objet;
        System.out.println("le contenu du panier est le suivant :");
        //panier.forEach((k,v)->System.out.println(v.getName()));
        for (Objet auto : panier.values()){
            System.out.println(auto.getName());
        }
    }
    public static  void viderPanier(){
        for (Map.Entry<String, Objet> auto : panier.entrySet()){
            panier.remove(auto.getValue().getCode(),auto.getValue());
            inventaire.put(auto.getValue().getCode(),auto.getValue());
            if(auto == null)
                break;
        }
    }
    public static  void retirerDuPanier(String nomObjet){
        for (Map.Entry<String, Objet> auto : panier.entrySet()){
            if(auto.getValue().getName().equals(nomObjet)){
                panier.remove(auto.getValue().getCode(),auto.getValue());
                inventaire.put(auto.getValue().getCode(),auto.getValue());
                break;
            }
        }
    }
    public static void commander(){
        int poidsCommande = 0;
        for (Map.Entry<String, Objet> auto : panier.entrySet()){
            switch (auto.getValue().getType()){
                case A:
                    poidsCommande += 1;
                    break;
                case B:
                    poidsCommande += 3;
                    break;
                case C:
                    poidsCommande += 5;
                    break;
            }
        }
        if (poidsCommande > 25){
            System.out.println("Commande impossible (Poids superieur a 25 kg), videz le panier ou bien retirez certains objets");
        }
        else{
            System.out.println("Commande passee, les objets commandes ne sont plus disponible dans l'inventaire");
            viderPanier();
        }
    }

    public static void interfaceCommande(){

        int numero = 0;
        while(numero != 6) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("1: Ajouter un objet au panier" +"\n"
                    +"2: Retirer un objet du panier" + "\n"
                    +"3: Vider le panier" + "\n"
                    +"4: Afficher le panier" + "\n"
                    +"5: Passer la commande" + "\n"
                    +"6: Quitter");

            System.out.println("Interface commande, veuillez selectionnez une option en entrant un chiffre :");
            numero = scanner.nextInt();
            //scanner.close();
            switch (numero) {
                case 1:
                    System.out.println("Entrez le nom d'un objet a ajouter");
                    scanner = new Scanner(System.in);
                    String nom = scanner.next();
                    //scanner.close();
                    ajouterAuPanier(nom);
                    break;
                case 2:
                    System.out.println("Entrez le nom d'un objet a retirer");
                    scanner = new Scanner(System.in);
                    String nom2 = scanner.next();
                    //scanner.close();
                    retirerDuPanier(nom2);
                    break;
                case 3:
                    viderPanier();
                    break;
                case 4:
                    afficherPanier();
                    break;
                case 5:
                    commander();
                    break;
                case 6: break;

            }
        }
    }

}  

