/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dicetrackeranalysis.graphanalysis;

import dicetrackeranalysis.datasourceinfo.DataSourceInfoBuilder;
import dicetrackeranalysis.graphhandling.AnalysisMainWindow;
import dicetrackeranalysis.graphhandling.GraphBuilder;
import dicetrackeranalysis.graphhandling.TaintEdge;
import dicetrackeranalysis.graphhandling.TaintIDTreeNode;
import dicetrackeranalysis.graphhandling.TaintNode;
import edu.uci.ics.jung.graph.Graph;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Stack;
import javax.swing.JTextArea;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author lee
 */
public class AccessPathAnalysis {
    private GraphBuilder gb;
//    private DataSourceInfoBuilder dsib;
    private JTextArea out;
//    private DefaultMutableTreeNode taintIDTree;
    private HashMap<String, LinkedList<DefaultMutableTreeNode>> taintIDToTreeNodeMap;
//    private AnalysisMainWindow analysisMainWindow;
//    private String appPackagePrefix;

    public AccessPathAnalysis(GraphBuilder gb, DataSourceInfoBuilder dsib, JTextArea out, DefaultMutableTreeNode taintIDTree, AnalysisMainWindow analysisMainWindow, String appPackagePrefix) {
        this.out = out;
        this.gb = gb;
//        this.dsib = dsib;
//        this.taintIDTree = taintIDTree;
//        this.analysisMainWindow = analysisMainWindow;
//        this.appPackagePrefix = appPackagePrefix;

        taintIDToTreeNodeMap = new HashMap<String, LinkedList<DefaultMutableTreeNode>>();
        Enumeration<DefaultMutableTreeNode> childNodes = taintIDTree.depthFirstEnumeration();
        while (childNodes.hasMoreElements()) {
            DefaultMutableTreeNode childNode = childNodes.nextElement();
            if (childNode.getUserObject() instanceof TaintIDTreeNode) {
                String taintID = ((TaintIDTreeNode)childNode.getUserObject()).getTaintID();
                LinkedList<DefaultMutableTreeNode> list = taintIDToTreeNodeMap.get(taintID);
                if (list == null) {
                    list = new LinkedList<DefaultMutableTreeNode>();
                    taintIDToTreeNodeMap.put(taintID, list);
                }
                list.add(childNode);
            }
        }
    }

    /*
     * New Version Code
     */

    public void analyze() {
        out.append("Starting Access Path Refactor Analysis\n");
        LinkedList<TaintNode> inputNodes = new LinkedList<TaintNode>(gb.getInputNodes());
        int numPartitions = PartitionSimulator.getPartitionCount();
        ArrayList<Placement> placements = new ArrayList<Placement>();
        HashMap<TaintEdge, LinkedList<SourceSizePair>> edgeDependencies = getEdgeDataDependencies();
        HashSet<TaintEdge> borderEdges = getBorderEdges();
        Graph<TaintNode, TaintEdge> fullGraph = gb.getMultiGraph();

        PriorityQueue<CostedPlacements> bestPlacements = new PriorityQueue<CostedPlacements>();
        for (TaintNode inputNode : inputNodes) {
            placements.add(new Placement(inputNode, 0));
        }

        do {
            int cost = getPlacementsCost(fullGraph, placements, borderEdges, edgeDependencies, numPartitions);
            CostedPlacements costedPlacements = new CostedPlacements(placements, cost);

            bestPlacements.add(costedPlacements);
                if (bestPlacements.size() > 3)
                    bestPlacements.poll();
        } while (incPlacements(placements, numPartitions));

        for (CostedPlacements bestPlacement : bestPlacements) {
            out.append("Placement: \n");
            out.append(bestPlacement.toString() + "\n");
        }
        out.append("Finished Access Path Refactor Analysis\n");
        // Have a function which evaluates cost of config
    }

    private boolean incPlacements(ArrayList<Placement> relocationCounters, int base) {
        int current = 0;
        while (relocationCounters.get(current).incTargetPartition(base)) {
            current++;
            if (current >= relocationCounters.size()) {
                return false;
            }
        }
        return true;
    }

    private int getPlacementsCost(Graph<TaintNode, TaintEdge> fullGraph, ArrayList<Placement> placements, HashSet<TaintEdge> borderEdges, HashMap<TaintEdge, LinkedList<SourceSizePair>> edgeDependencies, int numPartitions) {
        // Only select edges on boundaries. Get the edge source partition.
        int cost = 0;
        HashMap<TaintNode, Integer> placementsLookup = new HashMap<TaintNode, Integer>();
        for (Placement placement : placements) {
            placementsLookup.put(placement.getSourceNode(), placement.getTargetPartition());
        }
//       These edges will indicate a need for data. Say Edge needs A from 2 and B from 1 earlier.
        /*
         * Each data need will indicate border crossings. This will tell what partitions the data influences.
         *
         * Check the edge Data Dependencies map. If all dependencies local, 0 cost. If some dependencies are not local,
         * pick a rally point. Cost is price to send all needed data to the rally. Pick cheapest rally.
         *
         * TODO: If data travels through multiple partitions, ignore for now, can beef this up later
         */

        for (TaintEdge borderEdge : borderEdges) {
            BorderCrossing crossing = getBorderCrossing(fullGraph, borderEdge);
            int localPartition = crossing.getDestPartition();
            LinkedList<SourceSizePair> dependencies = edgeDependencies.get(borderEdge);

            /*
             * Check if all data for edge dependencies is now local. If so, no cost for this border edge
             */
            boolean allLocal = true;
            for (SourceSizePair dependency : dependencies) {
                TaintNode sourceNode = dependency.getSourceNode();
                int targetPartition = placementsLookup.get(sourceNode);

                if (targetPartition != localPartition) {
                    allLocal = false;
                    break;
                }
            }

            if (allLocal)
                continue;

            /*
             * Otherwise some dependencies are not local. All data must rally in one place for computation
             * (heuristic)
             */

            int bestRallyPartition;
            int bestRallyCost = -1;
            for (int rallyPartition = 0; rallyPartition < numPartitions; rallyPartition++) {
                int newRallyCost = 0;

                for (SourceSizePair dependency : dependencies) {
                    TaintNode sourceNode = dependency.getSourceNode();
                    int targetPartition = placementsLookup.get(sourceNode);

                    if (targetPartition != rallyPartition) {
                        newRallyCost += dependency.getDataSize();
                    }
                }

                if (bestRallyCost == -1 || newRallyCost < bestRallyCost) {
                    bestRallyCost = newRallyCost;
                    bestRallyPartition = rallyPartition;
                }
            }

            cost += bestRallyCost;
        }

        return cost;
    }

//    private HashMap<String, TaintNode> getTaintIDToInputMap() {
//
//        return null;
//    }
//
//    private HashSet<TaintNode> getInputNodes(TaintEdge edge) {
//        HashSet<String> superTaintIDs = getSuperTaintIDs(edge.getAllTaintIDs());
//
//        return gb.getInputNodesFromTaintIDs(superTaintIDs);
//    }
//
//    private HashSet<String> getSuperTaintIDs(HashSet<String> input) {
//        HashSet<String> output = new HashSet<String>(input);
//
//        for (String taintID : input) {
//            LinkedList<DefaultMutableTreeNode> treeNodes = taintIDToTreeNodeMap.get(taintID);
//            if (treeNodes != null) {
//                for (DefaultMutableTreeNode treeNode : treeNodes) {
//                    DefaultMutableTreeNode current = treeNode;
//                    while (current.getParent() != null && current.getParent() instanceof DefaultMutableTreeNode &&
//                            ((DefaultMutableTreeNode)current.getParent()).getUserObject() instanceof TaintIDTreeNode) {
//                        current = (DefaultMutableTreeNode)treeNode.getParent();
//                    }
//                    String topLevelID = ((TaintIDTreeNode)current.getUserObject()).getTaintID();
//                    output.add(topLevelID);
//                }
//            }
//        }
//
//        return output;
//    }

    private HashSet<TaintEdge> getBorderEdges() {
        Graph<TaintNode, TaintEdge> fullGraph = gb.getMultiGraph();
        HashSet<TaintEdge> output = new HashSet<TaintEdge>();

        for (TaintEdge edge : fullGraph.getEdges()) {
            int sourcePartitionID = fullGraph.getSource(edge).getPartitionID();
            int destPartitionID = fullGraph.getDest(edge).getPartitionID();

            if (sourcePartitionID != destPartitionID) {
                output.add(edge);
            }
        }

        return output;
    }

    /*
     * TODO: Add sideeffects to this later
     */
    private HashMap<TaintEdge, LinkedList<SourceSizePair>> getEdgeDataDependencies() {
        HashMap<TaintEdge, LinkedList<SourceSizePair>> output = new HashMap<TaintEdge, LinkedList<SourceSizePair>>();

        LinkedList<TaintNode> inputNodes = new LinkedList<TaintNode>(gb.getInputNodes());
        Graph<TaintNode, TaintEdge> fullGraph = gb.getMultiGraph();

        for (TaintNode inputNode : inputNodes) {
            for (TaintEdge outputEdge : fullGraph.getOutEdges(inputNode)) {
                int dataSize = outputEdge.estimateTaintCommunicationCost();
                getEdgeDataDependenciesHelper(fullGraph, outputEdge, inputNode, dataSize, output, new HashSet<TaintEdge>(), new Stack<BorderCrossing>());
            }
        }

        return output;
    }

    private void getEdgeDataDependenciesHelper(Graph<TaintNode, TaintEdge> fullGraph, TaintEdge currentEdge, TaintNode sourceNode, int dataSize, HashMap<TaintEdge, LinkedList<SourceSizePair>> output, HashSet<TaintEdge> visited, Stack<BorderCrossing> crossingStack) {
        if (visited.contains(currentEdge))
            return;
        visited.add(currentEdge);

        LinkedList<SourceSizePair> outputList = output.get(currentEdge);
        if (outputList == null) {
            outputList = new LinkedList<SourceSizePair>();
            output.put(currentEdge, outputList);
        }

        
        outputList.add(new SourceSizePair(sourceNode, dataSize, crossingStack));
        BorderCrossing crossing = getBorderCrossing(fullGraph, currentEdge);
        if (crossing != null) {
            crossingStack.push(crossing);
        }

        TaintNode destNode = fullGraph.getDest(currentEdge);
        for (TaintEdge nextEdge : fullGraph.getOutEdges(destNode)) {
            if (nextEdge.getOutputContextCounter().equals(currentEdge.getInputContextCounter())) {
                getEdgeDataDependenciesHelper(fullGraph, nextEdge, sourceNode, dataSize, output, visited, crossingStack);
            }
        }

        if (crossing != null) {
            crossingStack.pop();
        }
    }

    private BorderCrossing getBorderCrossing(Graph<TaintNode, TaintEdge> fullGraph, TaintEdge edge) {
        int sourcePartitionID = fullGraph.getSource(edge).getPartitionID();
        int destPartitionID = fullGraph.getDest(edge).getPartitionID();

        if (sourcePartitionID != destPartitionID) {
            return new BorderCrossing(sourcePartitionID, destPartitionID);
        }

        return null;
    }


    public static class SourceSizePair {
        private TaintNode sourceNode;
        private int dataSize;
        private LinkedList<BorderCrossing> borderCrossings;

        public SourceSizePair(TaintNode sourceNode, int dataSize, Collection<BorderCrossing> crossings) {
            this.sourceNode = sourceNode;
            this.dataSize = dataSize;
            this.borderCrossings = new LinkedList<BorderCrossing>(crossings);
        }

        public TaintNode getSourceNode() {
            return this.sourceNode;
        }

        public int getDataSize() {
            return this.dataSize;
        }

        public LinkedList<BorderCrossing> getBorderCrossings() {
            return this.borderCrossings;
        }
    }

    public static class BorderCrossing {
        private int sourcePartition;
        private int destPartition;

        public BorderCrossing(int sourcePartition, int destPartition) {
            this.sourcePartition = sourcePartition;
            this.destPartition = destPartition;
        }

        public int getSourcePartition() {
            return this.sourcePartition;
        }

        public int getDestPartition() {
            return this.destPartition;
        }
    }

    public static class Placement {
        private TaintNode sourceNode;
        private int targetPartition;

        public Placement(TaintNode sourceNode, int targetPartition) {
            this.sourceNode = sourceNode;
            this.targetPartition = targetPartition;
        }

        public TaintNode getSourceNode() {
            return this.sourceNode;
        }

        public int getTargetPartition() {
            return this.targetPartition;
        }

        public boolean incTargetPartition(int base) {
            this.targetPartition++;
            if (this.targetPartition == base) {
                this.targetPartition = 0;
                return true;
            }
            return false;
        }
        
        public Placement copy() {
            return new Placement(sourceNode, targetPartition);
        }

        public String toString() {
            return this.sourceNode + " -> Partition: " + targetPartition;
        }
    }

    public static class CostedPlacements implements Comparable<CostedPlacements>{
        private ArrayList<Placement> placements;
        private int cost;

        public CostedPlacements(ArrayList<Placement> inputPlacements, int cost) {
            this.cost = cost;

            this.placements = new ArrayList<Placement>();
            for (Placement inputPlacement : inputPlacements) {
                this.placements.add(inputPlacement.copy());
            }
        }
        
        public int getCost() {
            return this.cost;
        }

        public int compareTo(CostedPlacements o) {
            if (this.getCost() > o.getCost())
                return 1;
            if (this.getCost() < o.getCost())
                return -1;
            return 0;
        }

        public String toString() {
            String retString = "\tInternal Cost Metric: " + cost + "\n";
            for (Placement placement : placements) {
                retString += "\t" + placement.toString() + "\n";
            }

            return retString;
        }
    }
}


//    public void analyze() {
//        System.out.println("STARTING APR ANALYSIS");
//        Graph<TaintNode, TaintEdge> fullGraph = gb.getMultiGraph();
//        int nodeCount = fullGraph.getVertexCount();
//        LinkedList<TaintNode> inputNodes = new LinkedList<TaintNode>(gb.getInputNodes());
//
//        /*
//         * This is a map of Nodes->Context->Input Nodes contributing data to this node
//         */
//        HashMap<TaintNode, HashMap<String, HashSet<TaintNode>>> dataMixMap = getDataMixMap(fullGraph);
//
//        /*
//         * Was used to check if relocations were to new nodes, but I'm gonna ignore this for now
//         */
////        HashMap<TaintNode, HashSet<TaintNode>> firstReceiversToSourceMap = getFirstReceiversToSourceMap(fullGraph, gb.getInputNodes());
//
//        /*
//         * List of lists of all vertices. One list for each input, along with an index determines where input
//         * is remapped to
//         */
//        ArrayList<LinkedList<TaintNode>> possibleRelocations = new ArrayList<LinkedList<TaintNode>>();
//
//        /*
//         * Provides the remapping index. One integer per input, value in this array maps into possibleRelocations to
//         * get target.
//         */
//        ArrayList<Integer> relocationCounters = new ArrayList<Integer>(inputNodes.size());
//
//        /*
//         * Initialize possible relocations
//         */
//        for (int i = 0; i < inputNodes.size(); i++) {
//            possibleRelocations.set(i, new LinkedList<TaintNode>(fullGraph.getVertices()));
//            relocationCounters.set(i, 0);
//        }
//
//        /*
//         * When done, this will contain the results. A priority queue is used to only keep the ones with
//         * the best cost
//         */
//        PriorityQueue<RelocationCounter> bestRelocations = new PriorityQueue<RelocationCounter>();
//
//        outer:
//        do {
//            /*
//             * All relocations must be to classes within the app package. Arguably doesn't make much sense
//             * to modify library code in this way.
//             */
//            for (int i = 0; i < inputNodes.size(); i++) {
//                if (!possibleRelocations.get(i).get(relocationCounters.get(i)).getName().startsWith(appPackagePrefix)) {
//                    continue outer;
//                }
//            }
//
//            /*
//             * targettedTo contains a map of input nodes to the nodes they are retargetted to.
//             * This data is already encoded the relocation maps, but this makes it easier to lookup
//             */
//            HashMap<TaintNode, TaintNode> targettedTo = new HashMap<TaintNode, TaintNode>();
//            for (int i = 0; i < inputNodes.size(); i++) {
//                TaintNode inputNode = inputNodes.get(i);
//                TaintNode inputNewTarget = possibleRelocations.get(i).get(relocationCounters.get(i));
//                targettedTo.put(inputNode, inputNewTarget);
//            }
//
//            /*
//             * Used to see if a particular retargetting is worth it or not
//             */
//            long benefit = 0;
//            long cost = 0;
//
//            for (int i = 0; i < inputNodes.size(); i++) {
//                TaintNode inputNode = inputNodes.get(i);
//                TaintNode inputNewTarget = targettedTo.get(inputNode);
//
//                /*
//                 * inputNode is retargetted to inputNewTarget. alsoOnTarget is a set of input nodes that are ALSO
//                 * targetted to inputNewTarget
//                 */
//                HashSet<TaintNode> alsoOnTarget = new HashSet<TaintNode>();
//                for (int j = 0; j < inputNodes.size(); j++) {
//                    if (j != i) {
//                        TaintNode otherInputNode = inputNodes.get(i);
//                        TaintNode otherInputNewTarget = possibleRelocations.get(i).get(relocationCounters.get(i));
//                        if (otherInputNewTarget == inputNewTarget) {
//                            alsoOnTarget.add(otherInputNode);
//                        }
//                    }
//                }
//
//                /*
//                 * This takes the new target and checks its sources (in the unmodified graph) to see if this is
//                 * a new targetting. Removing this for now.
//                 */
////                HashSet<TaintNode> firstReceiverSources = firstReceiversToSourceMap.get(inputNewTarget);
////                if (firstReceiverSources.contains(inputNode)) {
////                    continue outer;
////                }
//
//                /*
//                 * Avoid targetting an input to another input, which would be strange
//                 */
//                if (inputNodes.contains(inputNewTarget))
//                    continue outer;
//
//                /*
//                 * Simple benefit calculation
//                 * Get all the input edges to the new target,
//                 */
//                for (TaintEdge inEdge : fullGraph.getInEdges(inputNewTarget)) {
//                    HashSet<TaintNode> targetInputNodes = getInputNodes(inEdge.getAllTaintIDs());
//                    if (targetInputNodes.contains(inputNode)) {
//                        // TODO: This isn't quite right. If an edge carries taint from multiple sources it will over-estimate
//                        benefit += inEdge.estimateTaintCommunicationCost();
//                    }
//                }
//
//                HashSet<TaintNode> preNodes = new HashSet<TaintNode>();
//                getNodesBackToSource(preNodes, fullGraph, inputNewTarget, inputNode, new HashSet<TaintNode>());
//
//                HashSet<TaintNode> targetSources = getMixedSourcesAcrossContexts(dataMixMap, inputNewTarget);
//                // Need to remove from targetSources any sources that have relocated away from reachability
//                for (TaintNode targetSource : new LinkedList<TaintNode>(targetSources)) {
//                    if (targetSource != inputNewTarget) {
//                        TaintNode targetMappedTo = targettedTo.get(targetSource);
//                        if (!preNodes.contains(targetMappedTo)) {
//                            targetSources.remove(targetSource);
//                        }
//                    }
//                }
//
//                for (TaintNode preNode : preNodes) {
//                    HashMap<String, HashSet<TaintNode>> preNodeMixings = dataMixMap.get(preNode);
//                    for (HashSet<TaintNode> preNodeMixing : preNodeMixings.values()) {
//                        if (preNodeMixing.contains(inputNode) && preNodeMixing.size() > 1) {
//                            preNodeMixing.remove(inputNode);
//                            for (TaintNode mixedNode : preNodeMixing) {
//                                if (alsoOnTarget.contains(mixedNode))
//                                    continue;
//                                if (targetSources.contains(mixedNode))
//                                    continue;
//                                for (TaintEdge preEdge : fullGraph.getInEdges(preNode)) {
//                                    HashSet<TaintNode> preInputNodes = getInputNodes(preEdge.getAllTaintIDs());
//                                    if (preInputNodes.contains(mixedNode)) {
//                                        // TODO: This isn't quite right. If an edge carries taint from multiple sources it will over-estimate
//                                        cost += preEdge.estimateTaintCommunicationCost();
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//
//            // Benefit = a relocation node uses the data source in question (check mix map), else continue
//            // If so, check if node is directly connected to data (is a first receiver of it, if so, continue.
//            // Else, scan input nodes to see if you get data from the node. Now you access it directly, so cost uses these input nodes.
//
//            // Cost = relocation has a source node associated with a target node. Scan up from target node to actual source node.
//            // Could then check each for mixing between mine and other nodes using mixmap (simple, but it works)
//            // If mixing occurs, then check if original node has access to that (can also use mixmap), if not, cost is data read from
//            // other source. UNLESS, mixed souce is already there.
//
//
//            cost -= benefit;
//
//            bestRelocations.add(new RelocationCounter(relocationCounters, cost));
//            if (bestRelocations.size() > 3)
//                bestRelocations.poll();
//        } while (incCounters(relocationCounters, nodeCount));
//
//        System.out.println("FINISHED APR ANALYSIS");
//    }
//
//
//
//    private boolean getNodesBackToSource(HashSet<TaintNode> foundNodes, Graph<TaintNode, TaintEdge> graph, TaintNode startNode, TaintNode sourceNode, HashSet<TaintNode> visited) {
//        if (visited.contains(startNode))
//            return false;
//        visited.add(startNode);
//
//        if (startNode == sourceNode)
//            return true;
//
//        boolean found = false;
//        for (TaintEdge inEdge : graph.getInEdges(startNode)) {
//
//            if (getNodesBackToSource(foundNodes, graph, graph.getSource(inEdge), sourceNode, visited))
//                found = true;
//        }
//
//        if (found)
//            foundNodes.add(startNode);
//
//        return found;
//    }
//
//    private HashMap<TaintNode, HashSet<TaintNode>> getFirstReceiversToSourceMap(Graph<TaintNode, TaintEdge> graph, HashSet<TaintNode> sourceNodes) {
//        HashMap<TaintNode, HashSet<TaintNode>> output = new HashMap<TaintNode, HashSet<TaintNode>>();
//
//        for (TaintNode sourceNode : sourceNodes) {
//            HashSet<TaintNode> receivers = new HashSet<TaintNode>();
//            for (TaintEdge outEdge : graph.getOutEdges(sourceNode)) {
//                getFirstReceiversToSourceMapHelper(graph, graph.getDest(outEdge), receivers, new HashSet<TaintNode>());
//            }
//
//            for (TaintNode receiver : receivers) {
//                HashSet<TaintNode> mappedReceievers = output.get(receiver);
//                if (mappedReceievers == null) {
//                    mappedReceievers = new HashSet<TaintNode>();
//                    output.put(receiver, mappedReceievers);
//                }
//                mappedReceievers.add(sourceNode);
//            }
//        }
//
//        return output;
//    }
//
//    private void getFirstReceiversToSourceMapHelper(Graph<TaintNode, TaintEdge> graph, TaintNode root, HashSet<TaintNode> found, HashSet<TaintNode> visited) {
//        if (visited.contains(root))
//            return;
//        visited.add(root);
//
//        if (!root.getName().startsWith("java.sql.ResultSet") && !root.getName().startsWith("com.mysql.jdbc")) {
//            found.add(root);
//        } else {
//            for (TaintEdge outEdge : graph.getOutEdges(root)) {
//                getFirstReceiversToSourceMapHelper(graph, graph.getDest(outEdge), found, visited);
//            }
//        }
//    }
//
//    private HashSet<TaintNode> getMixedSourcesAcrossContexts(HashMap<TaintNode, HashMap<String, HashSet<TaintNode>>> mixMap, TaintNode targetNode) {
//        HashSet<TaintNode> output = new HashSet<TaintNode>();
//
//        HashMap<String, HashSet<TaintNode>> contextMap = mixMap.get(targetNode);
//        for (HashSet<TaintNode> nodes : contextMap.values()) {
//            output.addAll(nodes);
//        }
//
//        return output;
//    }
//
//    private HashMap<TaintNode, HashMap<String, HashSet<TaintNode>>> getDataMixMap(Graph<TaintNode, TaintEdge> graph) {
//        HashMap<TaintNode, HashMap<String, HashSet<TaintNode>>> dataMixMap = new HashMap<TaintNode, HashMap<String, HashSet<TaintNode>>>();
//
//        for (TaintNode node : graph.getVertices()) {
//            HashMap<String, HashSet<TaintNode>> contextToSourcesMap = new HashMap<String, HashSet<TaintNode>>();
//
//            for (TaintEdge mixingEdge : node.getDataMixingEdges()) {
//                String contextCounter = mixingEdge.getOutputContextCounter();
//
//                HashSet<TaintNode> sourcesMap = contextToSourcesMap.get(contextCounter);
//                if (sourcesMap == null) {
//                    sourcesMap = new HashSet<TaintNode>();
//                    contextToSourcesMap.put(contextCounter, sourcesMap);
//                }
//
//                sourcesMap.addAll(getInputNodes(mixingEdge.getAllMixingTaintIDs()));
//            }
//            for (TaintEdge inputEdge : graph.getInEdges(node)) {
//                String contextCounter = inputEdge.getInputContextCounter();
//
//                HashSet<TaintNode> sourcesMap = contextToSourcesMap.get(contextCounter);
//                if (sourcesMap == null) {
//                    sourcesMap = new HashSet<TaintNode>();
//                    contextToSourcesMap.put(contextCounter, sourcesMap);
//                }
//
//                sourcesMap.addAll(getInputNodes(inputEdge.getAllTaintIDs()));
//            }
//            for (TaintEdge outputEdge : graph.getOutEdges(node)) {
//                String contextCounter = outputEdge.getOutputContextCounter();
//
//                HashSet<TaintNode> sourcesMap = contextToSourcesMap.get(contextCounter);
//                if (sourcesMap == null) {
//                    sourcesMap = new HashSet<TaintNode>();
//                    contextToSourcesMap.put(contextCounter, sourcesMap);
//                }
//
//                sourcesMap.addAll(getInputNodes(outputEdge.getAllTaintIDs()));
//            }
//
//            dataMixMap.put(node, contextToSourcesMap);
//        }
//
//        return dataMixMap;
//    }

//    private HashSet<String> getSubTaintIDs(HashSet<String> input) {
//        HashSet<String> output = new HashSet<String>(input);
//
//        for (String taintID : input) {
//            LinkedList<DefaultMutableTreeNode> treeNodes = taintIDToTreeNodeMap.get(taintID);
//            if (treeNodes != null) {
//                for (DefaultMutableTreeNode treeNode : treeNodes) {
//                    Enumeration<DefaultMutableTreeNode> childNodes = treeNode.depthFirstEnumeration();
//                    while (childNodes.hasMoreElements()) {
//                        DefaultMutableTreeNode childNode = childNodes.nextElement();
//                        String childTaintID = ((TaintIDTreeNode)childNode.getUserObject()).getTaintID();
//                        output.add(childTaintID);
//                    }
//                }
//            }
//        }
//
//        return output;
//    }
