import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;
import java.util.Map.Entry;

public class Path {
    private static boolean commandeEffectuee = false;

    public static void calculateShortestPathFromSource(Noeud source, Robot robot) {

        source.setDistance(0);
        source.getStates().add(new State(robot));
        source.getStates().iterator().next().getShortestPath().add(source);

        Set<Noeud> settledNodes = new HashSet<>();
        Set<Noeud> unsettledNodes = new HashSet<>();
        unsettledNodes.add(source);

        
        while (!settledNodes.contains(source) || !commandeEffectuee) {

            Noeud currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);

            for (Entry<Noeud, Integer> adjacencyPair : currentNode.getAdjacentNodes().entrySet()) {
                Noeud adjacentNode = adjacencyPair.getKey();
                Integer edgeWeigh = adjacencyPair.getValue();

                CalculateMinimumTime(adjacentNode, edgeWeigh, currentNode, robot, settledNodes);
                if (!(settledNodes.contains(adjacentNode))) {
                    unsettledNodes.add(adjacentNode);
                }
            }
            for (State state : currentNode.getStates()) {
                if (state.getRobot().commandeEffectuee()) {
                    settledNodes.add(currentNode);
                    break;
                }
            }
        }
    }

    private static void CalculateMinimumTime(Noeud evaluationNode, Integer edgeWeigh, Noeud sourceNode, Robot robot,
            Set<Noeud> settledNodes) {

        // ajout des etats dans le prochain node
        var it = sourceNode.getStates().iterator();
        while (it.hasNext()) {

            State state = it.next().clone();
            state.getShortestPath().add(evaluationNode);
            state.addTime(edgeWeigh * state.getRobot().getConstante_());

            if (!state.getInspectedNodes().contains(evaluationNode)) {
                state.addCommande(evaluationNode);
                state.getInspectedNodes().add(evaluationNode);
            }

            if (state.getRobot().commandeEffectuee()) {
                commandeEffectuee = true;
            }

            evaluationNode.setDistance(sourceNode.getDistance() + edgeWeigh);
            evaluationNode.addState(state);
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

        return lowestDistanceNode;
    }

    private static void displayPath(State state, Noeud noeud) {
        System.out.print("le temps: " + state.getTime());

        state.getShortestPath().forEach((node) -> {
            System.out.print(" (" + node.getNumero() + ")");
        });
    }

    public static void main(String args[]) throws FileNotFoundException {
   
        Robot.prendreCommande();
        Robot.afficherCommande();

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
            System.out.println("");
            System.out.println("Test du robot X");
            calculateShortestPathFromSource(graphe.getMapNoeud().get(0), robot);
            for (State state : graphe.getMapNoeud().get(0).getStates()) {
                if (state.getRobot().commandeEffectuee()) {
                    minState = (minState.getTime() > state.getTime()) ? state : minState;
                }
            }
            displayPath(minState, graphe.getMapNoeud().get(0));
        } else {
            System.out.println("le robot X n'as pas la capacite suffisante");
        }

        Graphe graphe2 = new Graphe();

        if (robot2.getMasseCommande() <= robot2.getCapacity_()) {
            System.out.println("");
            System.out.println("Test du Robot Y");
            calculateShortestPathFromSource(graphe2.getMapNoeud().get(0), robot2);
            for (State state : graphe2.getMapNoeud().get(0).getStates()) {
                if (state.getRobot().commandeEffectuee()) {
                    minState2 = (minState2.getTime() > state.getTime()) ? state : minState2;
                }
            }
            displayPath(minState2, graphe2.getMapNoeud().get(0));
        } else {
            System.out.println("le robot Y n'as pas la capacite suffisante");
        }

        Graphe graphe3 = new Graphe();
        if (robot3.getMasseCommande() <= robot3.getCapacity_()) {
            System.out.println("");
            System.out.println("Test du robot Z");
            calculateShortestPathFromSource(graphe3.getMapNoeud().get(0), robot3);
            for (State state : graphe3.getMapNoeud().get(0).getStates()) {
                if (state.getRobot().commandeEffectuee()) {
                    minState3 = (minState3.getTime() > state.getTime()) ? state : minState3;
                }
            }
            displayPath(minState3, graphe3.getMapNoeud().get(0));
        } else {
            System.out.println("le robot Z n'as pas la capacite suffisante");
        }

        minState = (minState3.getTime() > minState.getTime()) ? minState : minState3;
        minState = (minState2.getTime() > minState.getTime()) ? minState : minState2;

        System.out.println("");
        System.out.println("");
        System.out.println("le robot le plus rapide est celui de type " + minState.getRobot().getType_() 
        + " le temps mis est " + minState.getTime());
    }

}
