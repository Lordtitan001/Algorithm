import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.Map.Entry;

public class Path {

    private static void displayPath(Noeud noeud) {
        System.out.println("");
        System.out.print("(" + noeud.getNumero() );
        noeud.getShortestPath().forEach((node) ->{
            System.out.print(" (" + node.getNumero() + ")");
        });
        System.out.print(" )" + " distance : " + noeud.getDistance());
    }


    public static void calculateShortestPathFromSource(Noeud source) {
        source.setDistance(0);

        System.out.println("source node : " + source.getNumero() + " distance: " + source.getDistance() );

        Set<Noeud> settledNodes = new HashSet<>();
        Set<Noeud> unsettledNodes = new HashSet<>();
        unsettledNodes.add(source);

        while (unsettledNodes.size() != 0) {
            Noeud currentNode = getLowestDistanceNode(unsettledNodes);
            
            unsettledNodes.remove(currentNode);
            for (Entry<Noeud, Integer> adjacencyPair : currentNode.getAdjacentNodes().entrySet()) {
                
                Noeud adjacentNode = adjacencyPair.getKey();
                Integer edgeWeigh = adjacencyPair.getValue();

                if (!settledNodes.contains(adjacentNode)) {
                    CalculateMinimumDistance(adjacentNode, edgeWeigh, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }
            
            settledNodes.add(currentNode);
        }


        settledNodes.forEach((node) ->{
            node.getShortestPath().add(node);
            Robot robot = new Robot();
            getCommade(robot, node);
            if(robot.commandeEffectuee())
                displayPath(node);
        });
    }

    private static void getCommade(Robot robot, Noeud node){
        node.getShortestPath().forEach((noeud) ->{
            robot.recupererObjets(noeud);
        });
    }
    
    private static void CalculateMinimumDistance(Noeud evaluationNode, Integer edgeWeigh, Noeud sourceNode) {
        Integer sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeigh);
            LinkedList<Noeud> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
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

    public static void main(String args[]) throws FileNotFoundException {
        Graphe graphe = new Graphe();
        Robot.prendreCommande();
        Robot.afficherCommande();
        calculateShortestPathFromSource(graphe.getMapNoeud().get(0));
    }   

    // public static void calculateShortestPathFromSource(Noeud source) {
    //     source.setDistance(0);

    //     System.out.println("source node : " + source.getNumero() + " distance: " + source.getDistance() );

    //     Set<Noeud> settledNodes = new HashSet<>();
    //     Set<Noeud> unsettledNodes = new HashSet<>();
    //     unsettledNodes.add(source);

    //     while (unsettledNodes.size() != 0) {
    //         Noeud currentNode = getLowestDistanceNode(unsettledNodes);
            
    //         unsettledNodes.remove(currentNode);
    //         for (Entry<Noeud, Integer> adjacencyPair : currentNode.getAdjacentNodes().entrySet()) {
                
    //             Noeud adjacentNode = adjacencyPair.getKey();
    //             Integer edgeWeigh = adjacencyPair.getValue();
    //            // System.out.println("new AdjacentNodes : " + adjacentNode.getNumero() + " distance: " + edgeWeigh);

    //             if (!settledNodes.contains(adjacentNode)) {
    //                 CalculateMinimumDistance(adjacentNode, edgeWeigh, currentNode);
    //                 unsettledNodes.add(adjacentNode);
    //             }
    //         }
            
    //         settledNodes.add(currentNode);
    //     }


    //     settledNodes.forEach((node) ->{
    //         node.getShortestPath().add(node);
    //         Robot robot = new Robot();
    //         getCommade(robot, node);
    //         if(robot.commandeEffectuee())
    //             displayPath(node);
    //     });
    // }

    // private static void CalculateMinimumDistance(Noeud evaluationNode, Integer edgeWeigh, Noeud sourceNode) {
    //     Integer sourceDistance = sourceNode.getDistance();
    //     if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
    //         evaluationNode.setDistance(sourceDistance + edgeWeigh);
    //         LinkedList<Noeud> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
    //         shortestPath.add(sourceNode);
    //         evaluationNode.setShortestPath(shortestPath);
    //     }
    // }


    // private static Noeud getLowestDistanceNode(Set<Noeud> unsettledNodes) {
    //     Noeud lowestDistanceNode = null;
    //     int lowestDistance = Integer.MAX_VALUE;
    //     for (Noeud node : unsettledNodes) {
    //         int nodeDistance = node.getDistance();
    //         if (nodeDistance < lowestDistance) {
    //             lowestDistance = nodeDistance;
    //             lowestDistanceNode = node;
    //         }
    //     }
    //     return lowestDistanceNode;
    // }


}