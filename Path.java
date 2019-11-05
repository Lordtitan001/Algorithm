import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Map.Entry;

public class Path {
    private static boolean commandeEffectuee = false;
    private static Integer count = 0;

    public static void calculateShortestPathFromSource(Noeud source, Robot robot) {

        source.setDistance(0);
        source.getStates().add(new State(robot));
        source.getStates().iterator().next().getShortestPath().add(source);

        Set<Noeud> settledNodes = new HashSet<>();
        Set<Noeud> unsettledNodes = new HashSet<>();
        unsettledNodes.add(source);

        while (!settledNodes.contains(source)) {

            Noeud currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);

            if (!settledNodes.contains(currentNode)) {
                for (Entry<Noeud, Integer> adjacencyPair : currentNode.getAdjacentNodes().entrySet()) {
                    Noeud adjacentNode = adjacencyPair.getKey();
                    Integer edgeWeigh = adjacencyPair.getValue();

                    CalculateMinimumTime(adjacentNode, edgeWeigh, currentNode, robot, settledNodes);
                    if (!(settledNodes.contains(adjacentNode))) {
                        unsettledNodes.add(adjacentNode);
                    }
                }
            }

            if (!currentNode.equals(source)) {
                currentNode.getStates().clear();
            }

            settledNodes.forEach((node) -> {
                if (!node.equals(source))
                    node.getStates().clear();
            });

        }
    }

    private static void CalculateMinimumTime(Noeud evaluationNode, Integer edgeWeigh, Noeud sourceNode, Robot robot,
            Set<Noeud> settledNodes) {

        evaluationNode.setDistance(sourceNode.getDistance() + edgeWeigh);
        var it = sourceNode.getStates().iterator();
        while (it.hasNext()) {

            State state = it.next().clone();

            state.getShortestPath().add(evaluationNode);
            state.addTime(edgeWeigh * state.getRobot().getConstante_());

            if (!state.getInspectedNodes().contains(evaluationNode)) {
                if(state.addTime(10 * state.addCommande(evaluationNode)))
                    state.getInspectedNodes().add(evaluationNode);
            }

            evaluationNode.addState(state);

            if (state.getRobot().commandeEffectuee()) {
                commandeEffectuee = true;
                if (!settledNodes.contains(sourceNode)) {
                    settledNodes.add(sourceNode);
                    break;
                }
            }
        }

    }

    private static Noeud getLowestDistanceNode(Set<Noeud> unsettledNodes) {
        Noeud lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;

        for (Noeud node : unsettledNodes) {

            int nodeDistance = node.getDistance();

            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        lowestDistanceNode.getShortestPath().add(lowestDistanceNode);
        return lowestDistanceNode;
    }

    private static void displayPath(State state, Noeud noeud) {
        
        System.out.println("");
        System.out.print( state.getShortestPath().removeFirst().getNumero() + " -> ");
        state.getShortestPath().forEach((node) -> {
            state.getInspectedNodes().forEach((iNode) ->{
                if(node.equals(iNode))
                    System.out.print( " Collecting -> " );
            });
            if(!node.equals(state.getShortestPath().getLast()))
                System.out.print( node.getNumero() + " -> ");
            else    
                System.out.print( node.getNumero());
        });
    }

    public static void menu() {
        System.out.println("\n" + "\t" + "\t" + "\t" + "\t" + "MENU: " + "\n");
        System.out.println("Entrez le chiffre correspondant à l'option que vous souhaitez effectuer:" + "\n"
                + "(1): creer le graphe" + "\n" + "(2): afficher le graphe" + "\n" + "(3): prendre une commande" + "\n"
                + "(4): afficher le commande" + "\n" + "(5): trouver le plus court le chemin" + "\n" + "(6): quitter"
                + "\n");
    }

    public static void afficherMenu() throws FileNotFoundException {

        Robot robot = new Robot(Type.Y);
        Graphe graphe = new Graphe();
        boolean quitter = false;
        int numeroOption = 0;

        while (!quitter) {
            Scanner sc = new Scanner(System.in);
            menu();
            numeroOption = sc.nextInt();

            // sc.close();
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

    public static void shortestPath() throws FileNotFoundException {

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
        if (robot.getMasseCommande() <= robot.getCapacity_()) {
            calculateShortestPathFromSource(graphe.getMapNoeud().get(0), robot);
            for (State state : graphe.getMapNoeud().get(0).getStates()) {
                if (state.getRobot().commandeEffectuee()) {
                    minState = (minState.getTime() > state.getTime()) ? state : minState;
                }
            }
        }

        Graphe graphe2 = new Graphe();

        if (robot2.getMasseCommande() <= robot2.getCapacity_()) {
            calculateShortestPathFromSource(graphe2.getMapNoeud().get(0), robot2);
            for (State state : graphe2.getMapNoeud().get(0).getStates()) {
                if (state.getRobot().commandeEffectuee()) {
                    minState2 = (minState2.getTime() > state.getTime()) ? state : minState2;
                }
            }

        }
        Graphe graphe3 = new Graphe();
        if (robot3.getMasseCommande() <= robot3.getCapacity_()) {
            calculateShortestPathFromSource(graphe3.getMapNoeud().get(0), robot3);
            for (State state : graphe3.getMapNoeud().get(0).getStates()) {
                if (state.getRobot().commandeEffectuee()) {
                    minState3 = (minState3.getTime() > state.getTime()) ? state : minState3;
                }
            }

            minState = (minState3.getTime() > minState.getTime()) ? minState : minState3;
            minState = (minState2.getTime() > minState.getTime()) ? minState : minState2;

            System.out.println("");
            System.out.println("");

            displayPath(minState, graphe3.getMapNoeud().get(0));
            System.out.println("");
            System.out.println("Robot utilisé: " + minState.getRobot().getType_());
            System.out.println("Le temps: " + minState.getTime());
        }
        else{
            System.out.println("Entrer une plus petite commande.");
        }
    }

    public static void main(String args[]) throws FileNotFoundException {

        afficherMenu();

    }

}
