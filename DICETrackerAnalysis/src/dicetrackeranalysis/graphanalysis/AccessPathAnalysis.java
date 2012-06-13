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
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import javax.swing.JTextArea;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author lee
 */
public class AccessPathAnalysis {
    private GraphBuilder gb;
    private DataSourceInfoBuilder dsib;
    private JTextArea out;
    private DefaultMutableTreeNode taintIDTree;
    private HashMap<String, LinkedList<DefaultMutableTreeNode>> taintIDToTreeNodeMap;
    private AnalysisMainWindow analysisMainWindow;
    private String appPackagePrefix;

    public AccessPathAnalysis(GraphBuilder gb, DataSourceInfoBuilder dsib, JTextArea out, DefaultMutableTreeNode taintIDTree, AnalysisMainWindow analysisMainWindow, String appPackagePrefix) {
        this.out = out;
        this.gb = gb;
        this.dsib = dsib;
        this.taintIDTree = taintIDTree;
        this.analysisMainWindow = analysisMainWindow;
        this.appPackagePrefix = appPackagePrefix;

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

    public void analyze() {
        Graph<TaintNode, TaintEdge> fullGraph = gb.getMultiGraph();
        int nodeCount = fullGraph.getVertexCount();
        LinkedList<TaintNode> inputNodes = new LinkedList<TaintNode>(gb.getInputNodes());
        HashMap<TaintNode, HashMap<String, HashSet<TaintNode>>> dataMixMap = getDataMixMap(fullGraph);
        HashMap<TaintNode, HashSet<TaintNode>> firstReceiversToSourceMap = getFirstReceiversToSourceMap(fullGraph, gb.getInputNodes());

        ArrayList<LinkedList<TaintNode>> possibleRelocations = new ArrayList<LinkedList<TaintNode>>();
        ArrayList<Integer> relocationCounters = new ArrayList<Integer>(inputNodes.size());

        for (int i = 0; i < inputNodes.size(); i++) {
            possibleRelocations.set(i, new LinkedList<TaintNode>(fullGraph.getVertices()));
            relocationCounters.set(i, 0);
        }

        PriorityQueue<RelocationCounter> bestRelocations = new PriorityQueue<RelocationCounter>();

        do {
            boolean continueCheck = false;
            for (int i = 0; i < inputNodes.size(); i++) {
                if (!possibleRelocations.get(i).get(relocationCounters.get(i)).getName().startsWith(appPackagePrefix)) {
                    continueCheck = true;
                    break;
                }
            }
            if (continueCheck)
                continue;


            HashMap<TaintNode, TaintNode> targettedTo = new HashMap<TaintNode, TaintNode>();
            for (int i = 0; i < inputNodes.size(); i++) {
                TaintNode inputNode = inputNodes.get(i);
                TaintNode inputNewTarget = possibleRelocations.get(i).get(relocationCounters.get(i));
                targettedTo.put(inputNode, inputNewTarget);
            }


            long benefit = 0;
            long cost = 0;
            
            for (int i = 0; i < inputNodes.size(); i++) {
                TaintNode inputNode = inputNodes.get(i);
                TaintNode inputNewTarget = targettedTo.get(inputNode);

                HashSet<TaintNode> alsoOnTarget = new HashSet<TaintNode>();
                for (int j = 0; j < inputNodes.size(); j++) {
                    if (j != i) {
                        TaintNode otherInputNode = inputNodes.get(i);
                        TaintNode otherInputNewTarget = possibleRelocations.get(i).get(relocationCounters.get(i));
                        if (otherInputNewTarget == inputNewTarget) {
                            alsoOnTarget.add(otherInputNode);
                        }
                    }
                }

                HashSet<TaintNode> firstReceiverSources = firstReceiversToSourceMap.get(inputNewTarget);
                if (firstReceiverSources.contains(inputNode)) {
                    continueCheck = true;
                    break;
                }

                for (TaintEdge inEdge : fullGraph.getInEdges(inputNewTarget)) {
                    HashSet<TaintNode> targetInputNodes = getInputNodes(inEdge.getAllTaintIDs());
                    if (targetInputNodes.contains(inputNode)) {
                        // TODO: This isn't quite right. If an edge carries taint from multiple sources it will over-estimate
                        benefit += inEdge.estimateTaintCommunicationCost();
                    }
                }

                HashSet<TaintNode> preNodes = new HashSet<TaintNode>();
                getNodesBackToSource(preNodes, fullGraph, inputNewTarget, inputNode, new HashSet<TaintNode>());

                HashSet<TaintNode> targetSources = getMixedSourcesAcrossContexts(dataMixMap, inputNewTarget);
                // Need to remove from targetSources any sources that have relocated away from reachability
                for (TaintNode targetSource : new LinkedList<TaintNode>(targetSources)) {
                    if (targetSource != inputNewTarget) {
                        TaintNode targetMappedTo = targettedTo.get(targetSource);
                        if (!preNodes.contains(targetMappedTo)) {
                            targetSources.remove(targetSource);
                        }
                    }
                }

                for (TaintNode preNode : preNodes) {
                    HashMap<String, HashSet<TaintNode>> preNodeMixings = dataMixMap.get(preNode);
                    for (HashSet<TaintNode> preNodeMixing : preNodeMixings.values()) {
                        if (preNodeMixing.contains(inputNode) && preNodeMixing.size() > 1) {
                            preNodeMixing.remove(inputNode);
                            for (TaintNode mixedNode : preNodeMixing) {
                                if (alsoOnTarget.contains(mixedNode))
                                    continue;
                                if (targetSources.contains(mixedNode))
                                    continue;
                                for (TaintEdge preEdge : fullGraph.getInEdges(preNode)) {
                                    HashSet<TaintNode> preInputNodes = getInputNodes(preEdge.getAllTaintIDs());
                                    if (preInputNodes.contains(mixedNode)) {
                                        // TODO: This isn't quite right. If an edge carries taint from multiple sources it will over-estimate
                                        cost += preEdge.estimateTaintCommunicationCost();
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (continueCheck)
                continue;

            // Benefit = a relocation node uses the data source in question (check mix map), else continue
            // If so, check if node is directly connected to data (is a first receiver of it, if so, continue.
            // Else, scan input nodes to see if you get data from the node. Now you access it directly, so cost uses these input nodes.

            // Cost = relocation has a source node associated with a target node. Scan up from target node to actual source node.
            // Could then check each for mixing between mine and other nodes using mixmap (simple, but it works)
            // If mixing occurs, then check if original node has access to that (can also use mixmap), if not, cost is data read from
            // other source. UNLESS, mixed souce is already there.

            cost -= benefit;

            bestRelocations.add(new RelocationCounter(relocationCounters, cost));
            if (bestRelocations.size() > 3)
                bestRelocations.poll();
        } while (incCounters(relocationCounters, nodeCount));
    }

    private boolean incCounters(ArrayList<Integer> relocationCounters, int base) {
        relocationCounters.set(0, relocationCounters.get(0) + 1);

        for (int i = 0; i < relocationCounters.size(); i++) {
            if (relocationCounters.get(i) >= base) {
                if (i < relocationCounters.size() - 1) {
                    relocationCounters.set(i + 1, relocationCounters.get(i + 1) + 1);
                    relocationCounters.set(i, 0);
                } else {
                    return false;
                }
            } else {
                break;
            }

        }

        return true;
    }

    private boolean getNodesBackToSource(HashSet<TaintNode> foundNodes, Graph<TaintNode, TaintEdge> graph, TaintNode startNode, TaintNode sourceNode, HashSet<TaintNode> visited) {
        if (visited.contains(startNode))
            return false;
        visited.add(startNode);

        if (startNode == sourceNode)
            return true;

        boolean found = false;
        for (TaintEdge inEdge : graph.getInEdges(startNode)) {

            if (getNodesBackToSource(foundNodes, graph, graph.getSource(inEdge), sourceNode, visited))
                found = true;
        }

        if (found)
            foundNodes.add(startNode);

        return found;
    }

    private HashMap<TaintNode, HashSet<TaintNode>> getFirstReceiversToSourceMap(Graph<TaintNode, TaintEdge> graph, HashSet<TaintNode> sourceNodes) {
        HashMap<TaintNode, HashSet<TaintNode>> output = new HashMap<TaintNode, HashSet<TaintNode>>();

        for (TaintNode sourceNode : sourceNodes) {
            HashSet<TaintNode> receivers = new HashSet<TaintNode>();
            for (TaintEdge outEdge : graph.getOutEdges(sourceNode)) {
                getFirstReceiversToSourceMapHelper(graph, graph.getDest(outEdge), receivers, new HashSet<TaintNode>());
            }

            for (TaintNode receiver : receivers) {
                HashSet<TaintNode> mappedReceievers = output.get(receiver);
                if (mappedReceievers == null) {
                    mappedReceievers = new HashSet<TaintNode>();
                    output.put(receiver, mappedReceievers);
                }
                mappedReceievers.add(sourceNode);
            }
        }

        return output;
    }

    private void getFirstReceiversToSourceMapHelper(Graph<TaintNode, TaintEdge> graph, TaintNode root, HashSet<TaintNode> found, HashSet<TaintNode> visited) {
        if (visited.contains(root))
            return;
        visited.add(root);

        if (!root.getName().startsWith("java.sql.ResultSet") && !root.getName().startsWith("com.mysql.jdbc")) {
            found.add(root);
        } else {
            for (TaintEdge outEdge : graph.getOutEdges(root)) {
                getFirstReceiversToSourceMapHelper(graph, graph.getDest(outEdge), found, visited);
            }
        }
    }

    private HashSet<TaintNode> getMixedSourcesAcrossContexts(HashMap<TaintNode, HashMap<String, HashSet<TaintNode>>> mixMap, TaintNode targetNode) {
        HashSet<TaintNode> output = new HashSet<TaintNode>();

        HashMap<String, HashSet<TaintNode>> contextMap = mixMap.get(targetNode);
        for (HashSet<TaintNode> nodes : contextMap.values()) {
            output.addAll(nodes);
        }

        return output;
    }

    private HashMap<TaintNode, HashMap<String, HashSet<TaintNode>>> getDataMixMap(Graph<TaintNode, TaintEdge> graph) {
        HashMap<TaintNode, HashMap<String, HashSet<TaintNode>>> dataMixMap = new HashMap<TaintNode, HashMap<String, HashSet<TaintNode>>>();

        for (TaintNode node : graph.getVertices()) {
            HashMap<String, HashSet<TaintNode>> contextToSourcesMap = new HashMap<String, HashSet<TaintNode>>();
            
            for (TaintEdge mixingEdge : node.getDataMixingEdges()) {
                String contextCounter = mixingEdge.getOutputContextCounter();

                HashSet<TaintNode> sourcesMap = contextToSourcesMap.get(contextCounter);
                if (sourcesMap == null) {
                    sourcesMap = new HashSet<TaintNode>();
                    contextToSourcesMap.put(contextCounter, sourcesMap);
                }

                sourcesMap.addAll(getInputNodes(mixingEdge.getAllMixingTaintIDs()));
            }
            for (TaintEdge inputEdge : graph.getInEdges(node)) {
                String contextCounter = inputEdge.getInputContextCounter();

                HashSet<TaintNode> sourcesMap = contextToSourcesMap.get(contextCounter);
                if (sourcesMap == null) {
                    sourcesMap = new HashSet<TaintNode>();
                    contextToSourcesMap.put(contextCounter, sourcesMap);
                }

                sourcesMap.addAll(getInputNodes(inputEdge.getAllTaintIDs()));
            }
            for (TaintEdge outputEdge : graph.getOutEdges(node)) {
                String contextCounter = outputEdge.getOutputContextCounter();

                HashSet<TaintNode> sourcesMap = contextToSourcesMap.get(contextCounter);
                if (sourcesMap == null) {
                    sourcesMap = new HashSet<TaintNode>();
                    contextToSourcesMap.put(contextCounter, sourcesMap);
                }

                sourcesMap.addAll(getInputNodes(outputEdge.getAllTaintIDs()));
            }

            dataMixMap.put(node, contextToSourcesMap);
        }

        return dataMixMap;
    }

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
    
    private HashSet<String> getSuperTaintIDs(HashSet<String> input) {
        HashSet<String> output = new HashSet<String>(input);

        for (String taintID : input) {
            LinkedList<DefaultMutableTreeNode> treeNodes = taintIDToTreeNodeMap.get(taintID);
            if (treeNodes != null) {
                for (DefaultMutableTreeNode treeNode : treeNodes) {
                    DefaultMutableTreeNode current = treeNode;
                    while (current.getParent() != null && current.getParent() instanceof DefaultMutableTreeNode &&
                            ((DefaultMutableTreeNode)current.getParent()).getUserObject() instanceof TaintIDTreeNode) {
                        current = (DefaultMutableTreeNode)treeNode.getParent();
                    }
                    String topLevelID = ((TaintIDTreeNode)current.getUserObject()).getTaintID();
                    output.add(topLevelID);
                }
            }
        }

        return output;
    }

    private HashSet<TaintNode> getInputNodes(HashSet<String> taintIDs) {
        HashSet<String> superTaintIDs = getSuperTaintIDs(taintIDs);

        return gb.getInputNodesFromTaintIDs(superTaintIDs);
    }

    
}
