import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Entrepot {

    private static HashMap<String, Objet> inventaire = new HashMap<String, Objet>();
    private static Node rootNoms = new Node(null, ' ', "nom");
    private static Node rootCodes = new Node(null, ' ', "code");
    private static Node rootTypes = new Node(null, ' ', "type");

    public static void readFile(String fileName) {
        try {
            File source = new File(fileName);
            Scanner scanner = new Scanner(source);
            LinkedList<String> lines = new LinkedList<>();
            int index = 0;

            while (scanner.hasNextLine()) {

                // lecture de chaque ligne et les place dans un tableau
                lines.add(scanner.nextLine()); 
                Scanner scannerLignes = new Scanner(lines.get(index++));

                String name = scannerLignes.next();
                String code = scannerLignes.next();
                String type = scannerLignes.next();

                inventaire.put(code, new Objet(name, code, type)); // remplissage de notre inventaire

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    } // lis le fichier et remplis l'inventaire avec les
      // objets du fichier

    public static void creerArbreNoms() {

        for (var entrySet : inventaire.entrySet()) {
            Node currentNode = rootNoms;
            for (char val : entrySet.getValue().getName().toCharArray()) {
                if (currentNode.nextChild(val) == null) {
                    currentNode.getAdjaceNodes().add(new Node(currentNode, val, "nom"));
                }
                currentNode.getListeObjets().put(entrySet.getKey(), entrySet.getValue());
                currentNode.getAutoComplete().add(entrySet.getValue().getName());
                currentNode = currentNode.nextChild(val);
            }
        }
    }

    public static void creerArbreCodes() {
        for (var entrySet : inventaire.entrySet()) {
            Node currentNode = rootCodes;
            for (char val : entrySet.getValue().getCode().toCharArray()) {
                if (currentNode.nextChild(val) == null) {
                    currentNode.getAdjaceNodes().add(new Node(currentNode, val, "code"));
                }
                currentNode.getListeObjets().put(entrySet.getKey(), entrySet.getValue());
                currentNode.getAutoComplete().add(entrySet.getValue().getCode());
                currentNode = currentNode.nextChild(val);
            }
        }
    }

    public static HashMap<String, Objet> getInventaire() {
        return inventaire;
    }

    public static void creerAbresTypes() {
        for (var entrySet : inventaire.entrySet()) {
            rootTypes.getListeObjets().put(entrySet.getKey(), entrySet.getValue());
            Node currentNode = rootTypes;
            char val = entrySet.getValue().getType().toString().toCharArray()[0];
            if (currentNode.nextChild(val) == null) {
                System.out.println(val);
                currentNode.getAdjaceNodes().add(new Node(currentNode, val, "type"));
            }
            currentNode.getAdjaceNodes().forEach((node) -> {
                if (node.getValue() == val) {
                    node.getListeObjets().put(entrySet.getKey(), entrySet.getValue());
                    node.getAutoComplete().add(entrySet.getValue().getType().toString());
                }
            });

        }
    }

    public static Node getRootNoms() {
        return rootNoms;
    }

    public static Node getRootCodes() {
        return rootCodes;
    }

    public static Node getRootTypes() {
        return rootTypes;
    }

    private static HashMap<String, Objet> panier = new HashMap<String, Objet>();

    public static void ajouterAuPanier(String code) {

        HashMap<String, Objet> tempMap = new HashMap<String, Objet>();
        for (var auto : inventaire.entrySet()) {
            if (auto.getKey().equals(code))
                tempMap.put(auto.getKey(), auto.getValue());
        }

        tempMap.forEach((codeVal, objet) -> {
            inventaire.remove(codeVal, objet);
        });

        panier.putAll(tempMap);
    }

    public static Map<String, Objet> getPanier() {
        return panier;
    }

    public static HashMap<String, Objet> viderPanier() {
        HashMap<String, Objet> tempMap = new HashMap<String, Objet>();
        for (var auto : panier.entrySet()) {
            tempMap.put(auto.getKey(), auto.getValue());
        }

        tempMap.forEach((code, objet) -> {
            panier.remove(code, objet);
        });

        inventaire.putAll(tempMap);
        return tempMap;
    }

    public static void retirerDuPanier(String nomObjet) {
        for (Map.Entry<String, Objet> auto : panier.entrySet()) {
            if (auto.getValue().getName().equals(nomObjet)) {
                panier.remove(auto.getValue().getCode(), auto.getValue());
                inventaire.put(auto.getValue().getCode(), auto.getValue());
                break;
            }
        }
    }

    public static boolean commander() {
        int poidsCommande = 0;
        for (Map.Entry<String, Objet> auto : panier.entrySet()) {
            switch (auto.getValue().getType()) {
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
        if (poidsCommande > 25) {
            return false;
        } else {
            viderPanier().forEach((code, objet) -> {
                inventaire.remove(code, objet);
            });
            return true;
        }
    }

}
