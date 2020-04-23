import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Map.Entry;

public class Path {

    // Fonction qui permet de trouver le chemin le plus rapide
    public static void calculateShortestPathFromSource(Noeud source, Robot robot) {

        // initialisation de du noeud de depart 
        source.setDistance(0);

        // Ajout du premier etat
        source.getStates().add(new State(robot));
        source.getStates().iterator().next().getShortestPath().add(source);

        Set<Noeud> settledNodes = new HashSet<>();
        Set<Noeud> unsettledNodes = new HashSet<>();

        // Ajout de la source dans les noeuds a tester
        unsettledNodes.add(source);

        while (!settledNodes.contains(source)) {

            // Recherche du noeud le plus proche
            Noeud currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);

            if (!settledNodes.contains(currentNode)) {
                // Pour chaque noeud voisin 
                for (Entry<Noeud, Integer> adjacencyPair : currentNode.getAdjacentNodes().entrySet()) {
                    Noeud adjacentNode = adjacencyPair.getKey();
                    Integer edgeWeigh = adjacencyPair.getValue();

                    // Ajouter les etat du noeud source a noeud voisin
                    CalculateMinimumTime(adjacentNode, edgeWeigh, currentNode, robot, settledNodes);
                    if (!(settledNodes.contains(adjacentNode))) {
                        // Ajout du noeud voisin dans les noeuds a tester
                        unsettledNodes.add(adjacentNode);
                    }
                }
            }

            if (!currentNode.equals(source)) {
                // Vider les etats du noeud actuel
                currentNode.getStates().clear();
            }

            settledNodes.forEach((node) -> {
                if (!node.equals(source))
                // Vider les etats des noeuds qui ont deja ete tester
                    node.getStates().clear();
            });

        }
        System.out.println("");
    }

    private static void CalculateMinimumTime(Noeud evaluationNode, Integer edgeWeigh, Noeud sourceNode, Robot robot,
            Set<Noeud> settledNodes) {

        // Calcul de la distacnce du noeud
        evaluationNode.setDistance(sourceNode.getDistance() + edgeWeigh);
        var it = sourceNode.getStates().iterator();

        // Pour chaque etat du noeud source
        while (it.hasNext()) {
            State state = it.next().clone();

            if (state.getRobot().commandeEffectuee()) {
                if (!settledNodes.contains(sourceNode)) {
                    // Ajout du noeud parmis les noeuds deja testés si la commande est effectuer
                    settledNodes.add(sourceNode);
                    break;
                }
            }

            // Ajout du noeud vosin a l'etat cloner
            state.getShortestPath().add(evaluationNode);
            state.addTime(edgeWeigh * state.getRobot().getConstante_());

            // Test de la presence du noeud parmis les noeuds deja collectés
            if (!state.getInspectedNodes().contains(evaluationNode)) {
                // Ajout du temps du ramassage de la commande 
                if(state.addTime(10 * state.addCommande(evaluationNode)))
                    // Ajout du noeud parmis les noeuds deja collectés
                    state.getInspectedNodes().add(evaluationNode);
            }

            // Ajout de l'etat dans la liste des etat du noeud voisin
            evaluationNode.addState(state);
        }

    }

    private static Noeud getLowestDistanceNode(Set<Noeud> unsettledNodes) {
        // Initialisation du noeud le plus proche
        Noeud lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;

        for (Noeud node : unsettledNodes) {

            int nodeDistance = node.getDistance();
            // Comparaison des distances
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        // Ajout du noeud dans la liste des noeuds traversé
        lowestDistanceNode.getShortestPath().add(lowestDistanceNode);
        return lowestDistanceNode;
    }

    // Fonction pour afficher le parcours
    private static void displayPath(State state, Noeud noeud) {
        System.out.println("");
        // Affichage du premier noeud
        System.out.print( state.getShortestPath().removeFirst().getNumero() + " -> ");

        // Affichage noeuds a collecter 
        state.getShortestPath().forEach((node) -> {
            state.getInspectedNodes().forEach((iNode) ->{
                if(node.equals(iNode))
                    System.out.print( " Collecting -> " );
            });
            // Affichage des autres noeuds de la liste
            if(!node.equals(state.getShortestPath().getLast()))
                System.out.print( node.getNumero() + " -> ");
            else    
                System.out.print( node.getNumero());
        });

    }

    // Fonction pour afficher le menu
    public static void menu() {
        System.out.println("\n" + "\t" + "\t" + "\t" + "\t" + "MENU: " + "\n");
        System.out.println("Entrez le chiffre correspondant à l'option que vous souhaitez effectuer:" + "\n"
                + "(1): creer le graphe" + "\n" + "(2): afficher le graphe" + "\n" + "(3): prendre une commande" + "\n"
                + "(4): afficher le commande" + "\n" + "(5): trouver le plus court le chemin" + "\n" + "(6): quitter"
                + "\n");
    }

    // Fonction qui permet de choisir des options dans le menu
    public static void afficherMenu() throws FileNotFoundException {
        Graphe graphe = new Graphe();
        boolean quitter = false;
        int numeroOption = 0;

        while (!quitter) {
            Scanner sc = new Scanner(System.in);
            menu();
            numeroOption = sc.nextInt();


            switch (numeroOption) {
            case 1:
                graphe.creerGraphe();
                break;
            case 2:
                graphe.afficherGraphe();
                break;
            case 3:
                Robot.prendreCommande();
                break;
            case 4:
                Robot.afficherCommande();
                break;
            case 5:
                shortestPath();
                break;
            case 6:
                quitter = true;
                System.out.println("Vous avez quitté le menu.");
                System.exit(1);
                break;
            default:
                System.out.println(" Chiffre invalide, reessayer");

            }
        }
    }

    // Fonction qui permet de trouver le robot le plus rapide 
    public static void shortestPath() throws FileNotFoundException {

        // Initialisation des robots et des etats minimum
        Robot robot = new Robot(Type.X);
        State minState = new State(robot);
        minState.setTime(Double.MAX_VALUE);

        Robot robot2 = new Robot(Type.Y);
        State minState2 = new State(robot2);
        minState2.setTime(Double.MAX_VALUE);

        Robot robot3 = new Robot(Type.Z);
        State minState3 = new State(robot3);
        minState3.setTime(Double.MAX_VALUE);

        Graphe graphe = new Graphe();

        // Test de la capacité du robot et calcul du chemin 
        if (robot.getMasseCommande() <= robot.getCapacity_()) {
            calculateShortestPathFromSource(graphe.getMapNoeud().get(0), robot);
            for (State state : graphe.getMapNoeud().get(0).getStates()) {
                if (state.getRobot().commandeEffectuee()) {
                    minState = (minState.getTime() > state.getTime()) ? state : minState;
                }
            }
        }

        Graphe graphe2 = new Graphe();

         // Test de la capacité du robot et calcul du chemin 
        if (robot2.getMasseCommande() <= robot2.getCapacity_()) {
            calculateShortestPathFromSource(graphe2.getMapNoeud().get(0), robot2);
            for (State state : graphe2.getMapNoeud().get(0).getStates()) {
                if (state.getRobot().commandeEffectuee()) {
                    minState2 = (minState2.getTime() > state.getTime()) ? state : minState2;
                }
            }

        }
        Graphe graphe3 = new Graphe();
         // Test de la capacité du robot et calcul du chemin 
        if (robot3.getMasseCommande() <= robot3.getCapacity_()) {
            calculateShortestPathFromSource(graphe3.getMapNoeud().get(0), robot3);
            for (State state : graphe3.getMapNoeud().get(0).getStates()) {
                if (state.getRobot().commandeEffectuee()) {
                    minState3 = (minState3.getTime() > state.getTime()) ? state : minState3;
                }
            }
            // Comparaison des trois etat minimun
            minState = (minState3.getTime() > minState.getTime()) ? minState : minState3;
            minState = (minState2.getTime() > minState.getTime()) ? minState : minState2;

            System.out.println("");
            System.out.println("");

            // Affichage du parcour et du robot
            displayPath(minState, graphe3.getMapNoeud().get(0));
            System.out.println("");
            System.out.println("Robot utilisé: " + minState.getRobot().getType_());
            System.out.println("Le temps: " + minState.getTime());
        }
        else{
            //En cas de commade impossible 
            System.out.println("Entrer une plus petite commande.");
        }
    }

    public static void main(String args[]) throws FileNotFoundException {

        afficherMenu();

    }

}
