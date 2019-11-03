import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.Map.Entry;

public class Path {

    private static void displayPath(State state, Noeud noeud) {
        System.out.println("");
        System.out.print("le temps: "  + state.getTime() );
        state.getShortestPath().add(noeud);
        state.getShortestPath().forEach((node) ->{
            System.out.print(" (" + node.getNumero() + ")");
        });
    }

    public static void calculateShortestPathFromSource(Noeud source, Robot robot) {
        source.setDistance(0);
        source.getStates().add(new State(robot));

        System.out.println("source node : " + source.getNumero() + " nombre d'etats: " + source.getStates().size() );

        Set<Noeud> settledNodes = new HashSet<>();
        Set<Noeud> unsettledNodes = new HashSet<>();
        boolean commandeEffectuee = false;
        unsettledNodes.add(source);
        

        while (settledNodes.size() != 21 || !commandeEffectuee) {
            Noeud currentNode = getLowestDistanceNode(unsettledNodes);
            Noeud currentNodeTest = currentNode;
            //Set<Noeud> nodeToRemove = new HashSet<>();
           
            // for(Noeud used : unsettledNodes){
            //     boolean test2 = true;
            //     for( var node : used.getAdjacentNodes().keySet()){
            //         if(!settledNodes.contains(node))
            //             test2 = false;
            //     }
            //     if(test2){
            //         nodeToRemove.add(used);
            //     }
            // }

            unsettledNodes.remove(currentNode);

            var it = settledNodes.iterator();
            while(it.hasNext()){
                var it2 = it.next().getStates().iterator();
                while(it2.hasNext()){
                    if(it2.next().getRobot().commandeEffectuee())
                        commandeEffectuee = true;
                }
            }
            for (Entry<Noeud, Integer> adjacencyPair : currentNode.getAdjacentNodes().entrySet()) {
                
                Noeud adjacentNode = adjacencyPair.getKey();
                Integer edgeWeigh = adjacencyPair.getValue();
                boolean test = true;

                for( var node : adjacentNode.getAdjacentNodes().keySet()){
                    if(!settledNodes.contains(node))
                        test = false;
                }

                CalculateMinimumTime(adjacentNode, edgeWeigh, currentNode, robot, settledNodes);

                if (!(settledNodes.contains(adjacentNode) && test)) {
                    unsettledNodes.add(adjacentNode);
                }


            }

                settledNodes.add(currentNode);
        }

        var it = settledNodes.iterator();
        State st = new State(robot);
        st.setTime(Double.MAX_VALUE);
        while(it.hasNext()){
            State temp = new State(robot);
            Noeud node  = it.next();
            for (State state : node.getStates()) {
                if(state.getRobot().commandeEffectuee()){
                    displayPath(state, node);   
                    temp = state;            
                }
            }
            st = (temp.getTime() < st.getTime()) ? temp : st;   
        }
        System.out.println("");
        System.out.println("le temp minimal est : " + st.getTime());
    }

    private static void getCommade(Robot robot, Noeud node){
        node.getShortestPath().forEach((noeud) ->{
            robot.recupererObjets(noeud);
        });
    }

    public static boolean areEquals(int[] tab1, int[] tab2) {
        for (int i = 0; i < tab1.length; i++) {
            if (tab1[i] != tab2[i])
                return false;
        }
        return true;
    }

    private static void CalculateMinimumTime(Noeud evaluationNode, Integer edgeWeigh, Noeud sourceNode, Robot robot, Set<Noeud> settledNodes) {
        //ajout des etats dans le prochain node
        var it = sourceNode.getStates().iterator();
        while(it.hasNext()){
            State state = it.next().clone();
            state.getShortestPath().add(evaluationNode);
            state.addTime( edgeWeigh * state.getRobot().getConstante_());
            if (!settledNodes.contains(evaluationNode))
                state.addCommande(evaluationNode);
            evaluationNode.setDistance(sourceNode.getDistance() + edgeWeigh);
            evaluationNode.addState(state);
        }
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
        Robot robot = new Robot();
        calculateShortestPathFromSource(graphe.getMapNoeud().get(0), robot);
    }  
    
}
