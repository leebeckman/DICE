/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dicetrackeranalysis.graphhandling;

import dicetrackeranalysis.datasourceinfo.DataSourceInfoBuilder;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.Graph;
import java.io.File;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ProgressMonitor;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.commons.collections15.map.FastHashMap;
import org.w3c.dom.NodeList;

/**
 *
 * @author lee
 */
public class GraphBuilder {

    private HashSet<TaintEdge> edgeList;
    public HashMap<String, TaintNode> nodeMap;
    public LinkedHashSet<String> taintIDs;
    public LinkedList<TaintIDPropagationPair> taintIDPropagations;
    public HashMap<String, RequestCounterURIPair> requestCounters;

    private HashMap<String, TaintNode> taintIDToInputMap;

    public String debugName;

    private Graph<TaintNode, TaintEdge> unfilteredMultiGraph = null;
    private Graph<TaintNode, TaintEdge> unfilteredLightMultiGraph = null;
    private Graph<TaintNode, TaintEdge> unfilteredGraph = null;

    public GraphBuilder(File input) {
        edgeList = new HashSet<TaintEdge>();
        nodeMap = new HashMap<String, TaintNode>();
        taintIDs = new LinkedHashSet<String>();
        taintIDPropagations = new LinkedList<TaintIDPropagationPair>();
        taintIDToInputMap = new HashMap<String, TaintNode>();
        requestCounters = new HashMap<String, RequestCounterURIPair>();
        fillEdgeListFromFile(input);
        
        addSupplementaryEdges();
        connectReadsToResultSets();
    }

    public GraphBuilder(GraphBuilder input) {
        edgeList = new HashSet<TaintEdge>(input.edgeList);
        nodeMap = new HashMap<String, TaintNode>(input.nodeMap);
        taintIDs = new LinkedHashSet<String>();
        taintIDPropagations = new LinkedList<TaintIDPropagationPair>();
        taintIDToInputMap = new HashMap<String, TaintNode>();
        requestCounters = new HashMap<String, RequestCounterURIPair>();
    }

    public GraphBuilder(GraphBuilder input, boolean noEdgeCopy) {
        edgeList = new HashSet<TaintEdge>();
        nodeMap = new HashMap<String, TaintNode>(input.nodeMap);
        taintIDs = new LinkedHashSet<String>();
        taintIDPropagations = new LinkedList<TaintIDPropagationPair>();
        taintIDToInputMap = new HashMap<String, TaintNode>();
        requestCounters = new HashMap<String, RequestCounterURIPair>();
    }

    // In the graph data tainted data can come from resultsets. These resultsets themselves are also tainted and flow in the graph.
    // This method modifies the graph to indicate how the read data actually comes from these result sets.
    private void connectReadsToResultSets() {
        Graph<TaintNode, TaintEdge> fullGraph = this.getMultiGraph();

        // Simply look for resultset get string nodes, see where they go, in context, look for input resultset
        for (TaintNode node : fullGraph.getVertices()) {
            if (node.getName().startsWith("java.sql.ResultSet:getString") ||
                    node.getName().startsWith("com.mysql.jdbc.ResultSet:getString")) {
                String resultSetID = node.getID();
                for (TaintEdge dataEdge : fullGraph.getOutEdges(node)) {
                    TaintNode destNode = fullGraph.getDest(dataEdge);

                    String dataContext = dataEdge.getInputContextCounter();

                    for (TaintEdge rsEdge : fullGraph.getInEdges(destNode)) {
                        if (rsEdge != dataEdge) {
                            if (rsEdge.carriesTarget(resultSetID)) {
                                String rsContext = rsEdge.getInputContextCounter();

                                if (rsContext.equals(dataContext)) {
                                    // need to change rsEdge destination to be the data source for the getString
                                    TaintNode newDest = GraphBuilder.walkToSource(fullGraph, node, new HashSet<TaintNode>());
                                    System.out.println("RECONNECTING GRAPH: edge: " + rsEdge + " to new destnode: " + newDest + " walked from: " + node);
                                    rsEdge.setCalledNode(newDest);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private static TaintNode walkToSource(Graph<TaintNode, TaintEdge> graph, TaintNode start, HashSet<TaintNode> visited) {
        if (visited.contains(start))
            return null;
        visited.add(start);

        System.out.println("Walking " + start);

        Collection<TaintEdge> inEdges = graph.getInEdges(start);
        if (inEdges == null || inEdges.isEmpty())
            return start;

        TaintNode found = null;

        for (TaintEdge inEdge : inEdges) {
            TaintNode check = walkToSource(graph, graph.getSource(inEdge), visited);
            if (check != null) {
                found = check;
                break;
            }
        }

        return found;
    }

    private void addSupplementaryEdges() {
        Graph<TaintNode, TaintEdge> fullGraph = this.getMultiGraph();

        HashMap<String, LinkedList<String>> propagationMap = new HashMap<String, LinkedList<String>>();
        for (TaintIDPropagationPair propagationPair : taintIDPropagations) {
            LinkedList<String> propagations = propagationMap.get(propagationPair.getSourceID());
            if (propagations == null) {
                propagations = new LinkedList<String>();
                propagationMap.put(propagationPair.getSourceID(), propagations);
            }
            propagations.add(propagationPair.getDestID());
        }

        HashMap<String, LinkedList<TaintNode>> groupedByClassID = new HashMap<String, LinkedList<TaintNode>>();
        for (TaintNode node : fullGraph.getVertices()) {
            String key = node.getClassID();
            LinkedList<TaintNode> groupedNodes = groupedByClassID.get(key);
            if (groupedNodes == null) {
                groupedNodes = new LinkedList<TaintNode>();
                groupedByClassID.put(key, groupedNodes);
            }
            groupedNodes.add(node);
        }

        for (LinkedList<TaintNode> groupedNodes : groupedByClassID.values()) {
            for (int i = 0; i < groupedNodes.size(); i++) {
                for (int j = 0; j < groupedNodes.size(); j++) {
                    if (i != j) {
                        TaintNode inNode = groupedNodes.get(i);
                        TaintNode outNode = groupedNodes.get(j);

                        if (inNode.getClassID().contains("ResultSet") || outNode.getClassID().contains("ResultSet"))
                            continue;

                        LinkedList<TaintEdge> inEdges = new LinkedList<TaintEdge>(fullGraph.getInEdges(inNode));
                        LinkedList<TaintEdge> outEdges = new LinkedList<TaintEdge>(fullGraph.getOutEdges(outNode));

                        boolean alreadyConnected = false;
                        for (TaintEdge checkEdge : fullGraph.getOutEdges(inNode)) {
                            TaintNode destNode = fullGraph.getDest(checkEdge);
                            if (destNode == outNode) {
                                alreadyConnected = true;
                                break;
                            }
                        }
                        if (alreadyConnected)
                            continue;

                        for (TaintEdge inEdge : inEdges) {
                            for (TaintEdge outEdge : outEdges) {
                                if (outEdge.getCounter() > inEdge.getCounter()) {
                                    HashSet<String> inTaintIDs = getPropagatedTaintIDs(inEdge.getAllTaintIDs(), propagationMap);
                                    HashSet<String> outTaintIDs = outEdge.getAllTaintIDs();
                                    
                                    boolean match = false;
                                    for (String outTaintID : outTaintIDs) {
                                        if (inTaintIDs.contains(outTaintID)) {
                                            match = true;
                                            break;
                                        }
                                    }   
                                    
                                    if (match) {
                                        TaintEdge newEdge = new TaintEdge();
                                        newEdge.setType("SUPPLEMENTARY");
                                        newEdge.setRequestCounter(inEdge.getRequestCounter());
                                        newEdge.setRequestURI(inEdge.getRequestURI());
                                        newEdge.setSrcClass(inEdge.getSrcClass());
                                        newEdge.setSrcMethod(inEdge.getSrcMethod());
                                        newEdge.setDestClass(inEdge.getDestClass());
                                        newEdge.setDestMethod(inEdge.getDestMethod());
                                        newEdge.setAdviceType("SUPPLEMENTARY");

                                        for (TaintedObject taintedObject : inEdge.getTaintedObjects())
                                            newEdge.addTaintedObject(taintedObject);
                                        
                                        newEdge.setOutputObject(inEdge.getOutputObject());
                                        newEdge.setCallingObject(inEdge.getCallingObject());
                                        newEdge.setCalledObject(inEdge.getCalledObject());
                                        newEdge.setSourceObject(inEdge.getSourceObject());
                                        newEdge.setDestObject(inEdge.getDestObject());
                                        newEdge.setCallingNode(inNode);
                                        newEdge.setCalledNode(outNode);

                                        newEdge.setInputContextCounter(inEdge.getInputContextCounter());
                                        newEdge.setOutputContextCounter(inEdge.getOutputContextCounter());
                                        
                                        edgeList.add(newEdge);
//                                        System.out.println("SPL EDGE: " + inNode + " to " + outNode);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }



    private HashSet<String> getPropagatedTaintIDs(HashSet<String> taintIDs, HashMap<String, LinkedList<String>> propagationMap) {
        HashSet<String> output = new HashSet<String>(taintIDs);
        for (String taintID : taintIDs) {
            LinkedList<String> propagations = propagationMap.get(taintID);
            addPropagationHelper(output, propagationMap, propagations, new HashSet<String>());
        }

        return output;
    }

    private void addPropagationHelper(HashSet<String> output, HashMap<String, LinkedList<String>> propagationMap, LinkedList<String> propagations, HashSet<String> visited) {
        if (propagations != null) {
            output.addAll(propagations);
            for (String propagation : propagations) {
                if (visited.contains(propagation))
                    continue;
                visited.add(propagation);
                LinkedList<String> deeperPropagations = propagationMap.get(propagation);
                addPropagationHelper(output, propagationMap, deeperPropagations, visited);
            }
        }
    }

    private void fillEdgeListFromFile(File input) {
        TaintEdge.resetCounter();
        edgeList.clear();
        nodeMap.clear();
        taintIDs.clear();
        taintIDPropagations.clear();
        requestCounters.clear();
        taintIDs.add("");

        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(input);

            Element docRoot = doc.getDocumentElement();
            NodeList childNodes = docRoot.getChildNodes();

            // Iterate through taintlog records
            ProgressMonitor monitor = new ProgressMonitor(AnalysisMainWindow.mainWindow, "Building Graph", "Loading Edges", 0, childNodes.getLength() * 2);
            monitor.setMillisToDecideToPopup(10);
            monitor.setMillisToPopup(10);
            int progCounter = 0;
            for (int i = 0; i < childNodes.getLength(); i++ ) {
                if (progCounter++ % 1000 == 0)
                    monitor.setProgress(progCounter);
                if (childNodes.item(i) instanceof Element) {
                    TaintEdge taintEdge = new TaintEdge();
                    Element taintLogElem = (Element) childNodes.item(i);
                    taintEdge.setType(taintLogElem.getAttribute("type"));
                    taintEdge.setExecutionTime(taintLogElem.getAttribute("executionTime"));

                    NodeList taintLogChildNodes = taintLogElem.getChildNodes();
                    for (int j = 0; j < taintLogChildNodes.getLength(); j++) {
                        Element taintLogChildElem = (Element) taintLogChildNodes.item(j);
                        if (taintLogChildElem.getNodeName().equals("location")) {
                            taintEdge.setSrcClass(taintLogChildElem.getAttribute("srcClass"));
                            taintEdge.setSrcMethod(taintLogChildElem.getAttribute("srcMethod"));
                            taintEdge.setDestClass(taintLogChildElem.getAttribute("destClass"));
                            taintEdge.setDestMethod(taintLogChildElem.getAttribute("destMethod"));
                            taintEdge.setCallerContextCounter(taintLogChildElem.getAttribute("callerContextCounter"));
                            taintEdge.setCalledContextCounter(taintLogChildElem.getAttribute("calledContextCounter"));
                            taintEdge.setAdviceType(taintLogChildElem.getAttribute("adviceType"));
                            String requestCounter = taintLogChildElem.getAttribute("requestCounter");
                            taintEdge.setRequestCounter(requestCounter);
                            taintEdge.setRequestURI(taintLogChildElem.getAttribute("requestURI"));
                            taintEdge.setRequestRemoteAddr(taintLogChildElem.getAttribute("requestRemoteAddr"));
                        }
                        else if (taintLogChildElem.getNodeName().equals("taintedObject")) {
                            TaintedObject taintedObject = new TaintedObject();
                            taintedObject.setObjectID(taintLogChildElem.getAttribute("objectID"));
                            String taintID = taintLogChildElem.getAttribute("taintID");
                            taintedObject.setTaintID(taintID);
                            taintIDs.add(taintID);
                            taintedObject.setType(taintLogChildElem.getAttribute("type"));
                            taintedObject.setValue(taintLogChildElem.getAttribute("value"));
                            taintedObject.setTaintRecord(taintLogChildElem.getAttribute("taintRecord"));

                            NodeList taintLogChildChildNodes = taintLogChildElem.getChildNodes();
                            for (int k = 0; k < taintLogChildChildNodes.getLength(); k++) {
                                Element taintLogChildChildElem = (Element) taintLogChildChildNodes.item(k);
                                if (taintLogChildChildElem.getNodeName().equals("subTaintedObject")) {
                                    TaintedObject subTaintedObject = new TaintedObject();
                                    subTaintedObject.setObjectID(taintLogChildChildElem.getAttribute("objectID"));
                                    String subTaintID= taintLogChildChildElem.getAttribute("taintID");
                                    subTaintedObject.setTaintID(subTaintID);
                                    taintIDs.add(subTaintID);
                                    subTaintedObject.setType(taintLogChildChildElem.getAttribute("type"));
                                    subTaintedObject.setValue(taintLogChildChildElem.getAttribute("value"));
                                    subTaintedObject.setTaintRecord(taintLogChildChildElem.getAttribute("taintRecord"));
                                    taintedObject.addTaintedObject(subTaintedObject);
                                }
                            }

                            taintEdge.addTaintedObject(taintedObject);
                        }
                        else if (taintLogChildElem.getNodeName().equals("composedObjects")) {
                            NodeList taintLogChildChildNodes = taintLogChildElem.getChildNodes();
                            for (int k = 0; k < taintLogChildChildNodes.getLength(); k++) {
                                Element taintLogChildChildElem = (Element) taintLogChildChildNodes.item(k);
                                if (taintLogChildChildElem.getNodeName().equals("composedObject")) {
                                    TaintedObject composedObject = new TaintedObject();
                                    composedObject.setObjectID(taintLogChildChildElem.getAttribute("objectID"));
                                    String subTaintID= taintLogChildChildElem.getAttribute("taintID");
                                    composedObject.setTaintID(subTaintID);
                                    taintIDs.add(subTaintID);
                                    composedObject.setType(taintLogChildChildElem.getAttribute("type"));
                                    composedObject.setValue(taintLogChildChildElem.getAttribute("value"));
                                    composedObject.setTaintRecord(taintLogChildChildElem.getAttribute("taintRecord"));
                                    taintEdge.addComposedObject(composedObject);
                                }
                            }

                        }
                        else if (taintLogChildElem.getNodeName().equals("associatedObjects")) {
                            NodeList taintLogChildChildNodes = taintLogChildElem.getChildNodes();
                            for (int k = 0; k < taintLogChildChildNodes.getLength(); k++) {
                                Element taintLogChildChildElem = (Element) taintLogChildChildNodes.item(k);
                                if (taintLogChildChildElem.getNodeName().equals("composedObject")) {
                                    TaintedObject associatedObject = new TaintedObject();
                                    associatedObject.setObjectID(taintLogChildChildElem.getAttribute("objectID"));
                                    String subTaintID= taintLogChildChildElem.getAttribute("taintID");
                                    associatedObject.setTaintID(subTaintID);
                                    taintIDs.add(subTaintID);
                                    associatedObject.setType(taintLogChildChildElem.getAttribute("type"));
                                    associatedObject.setValue(taintLogChildChildElem.getAttribute("value"));
                                    associatedObject.setTaintRecord(taintLogChildChildElem.getAttribute("taintRecord"));
                                    taintEdge.addTaintedObject(associatedObject);
                                }
                            }

                        }
                        else if (taintLogChildElem.getNodeName().equals("sourceObject")) {
                            TaintedObject taintedObject = new TaintedObject();
                            taintedObject.setObjectID(taintLogChildElem.getAttribute("objectID"));
                            String taintID = taintLogChildElem.getAttribute("taintID");
                            taintedObject.setTaintID(taintID);
                            taintIDs.add(taintID);
                            taintedObject.setType(taintLogChildElem.getAttribute("type"));
                            taintedObject.setValue(taintLogChildElem.getAttribute("value"));
                            taintedObject.setTaintRecord(taintLogChildElem.getAttribute("taintRecord"));

                            taintEdge.setSourceObject(taintedObject);
                        }
                        else if (taintLogChildElem.getNodeName().equals("destObject")) {
                            TaintedObject taintedObject = new TaintedObject();
                            taintedObject.setObjectID(taintLogChildElem.getAttribute("objectID"));
                            String taintID = taintLogChildElem.getAttribute("taintID");
                            taintedObject.setTaintID(taintID);
                            taintIDs.add(taintID);
                            taintedObject.setType(taintLogChildElem.getAttribute("type"));
                            taintedObject.setValue(taintLogChildElem.getAttribute("value"));
                            taintedObject.setTaintRecord(taintLogChildElem.getAttribute("taintRecord"));

                            taintEdge.setDestObject(taintedObject);
                        }
                        else if (taintLogChildElem.getNodeName().equals("outputObject")) {
                            TargetObject targetObject = new TargetObject();
                            targetObject.setObjectID(taintLogChildElem.getAttribute("objectID"));
                            targetObject.setType(taintLogChildElem.getAttribute("type"));
                            targetObject.setValue(taintLogChildElem.getAttribute("value"));
                            taintEdge.setOutputObject(targetObject);
                        }
                        else if (taintLogChildElem.getNodeName().equals("callingObject")) {
                            TargetObject targetObject = new TargetObject();
                            targetObject.setObjectID(taintLogChildElem.getAttribute("objectID"));
                            targetObject.setType(taintLogChildElem.getAttribute("type"));
                            targetObject.setValue(taintLogChildElem.getAttribute("value"));
                            taintEdge.setCallingObject(targetObject);
                        }
                        else if (taintLogChildElem.getNodeName().equals("calledObject")) {
                            TargetObject targetObject = new TargetObject();
                            targetObject.setObjectID(taintLogChildElem.getAttribute("objectID"));
                            targetObject.setType(taintLogChildElem.getAttribute("type"));
                            targetObject.setValue(taintLogChildElem.getAttribute("value"));
                            taintEdge.setCalledObject(targetObject);
                        }
                        else if (taintLogChildElem.getNodeName().equals("field")) {
                            TaintedField taintedField = new TaintedField();
                            taintedField.setTargetClass(taintLogChildElem.getAttribute("targetClass"));
                            taintedField.setTargetField(taintLogChildElem.getAttribute("targetField"));
                            taintEdge.setField(taintedField);
                        }
                    }


                    if (taintEdge.getSimpleSource() == null)
                        continue;

                    String srcName = taintEdge.getTargettedSource();
                    String destName = taintEdge.getTargettedDest();
                    String fieldName = taintEdge.getFieldName();

                    if (taintEdge.getType().equals("RETURNINGINPUT")) {
                        System.out.println("Relabeling " + destName + " to " + taintEdge.getDataSourceDest());
                        destName = taintEdge.getDataSourceDest();
                    }

                    if (!nodeMap.containsKey(srcName))
                        nodeMap.put(srcName, new TaintNode(taintEdge.getSrcClass(), taintEdge.getSrcMethod(), taintEdge.getSourceID()));
                    // Consider adding targetted objects later

                    if (!nodeMap.containsKey(destName)) {
                        if (taintEdge.getType().equals("RETURNINGINPUT"))
                            nodeMap.put(destName, new TaintNode(taintEdge.getDataSourceDest()));
                        else
                            nodeMap.put(destName, new TaintNode(taintEdge.getDestClass(), taintEdge.getDestMethod(), taintEdge.getDestID()));

                    }
                        
                    if (fieldName != null && !nodeMap.containsKey(fieldName)) {
                        nodeMap.put(fieldName, new TaintNode(fieldName));
                    }
                    edgeList.add(taintEdge);

                    if (taintEdge.getAdviceType().startsWith("NONTAINTRETURN"))
                        TaintEdge.decrementCounter();
                    if (taintEdge.getType().equals("PROPAGATION")) {
                        TaintIDPropagationPair pair = new TaintIDPropagationPair(taintEdge.getSourceObject().getTaintID(),
                                taintEdge.getSourceObject().getValue(),
                                taintEdge.getDestObject().getTaintID(),
                                taintEdge.getDestObject().getValue());
                        taintIDPropagations.add(pair);
                    }
                }
            }

            postProcessTaintIDPropagations();

            for (TaintEdge edge : edgeList) {
                if (progCounter++ % 1000 == 0)
                    monitor.setProgress(progCounter);
                TaintNode callingNode = null;
                TaintNode calledNode = null;

                if (edge.getType().equals("CALLING")) {
                    callingNode = nodeMap.get(edge.getTargettedSource());
                    calledNode = nodeMap.get(edge.getTargettedDest());
                    edge.setInputContextCounter(edge.getCalledContextCounter());
                    edge.setOutputContextCounter(edge.getCallerContextCounter());
                }
                else if (edge.getType().equals("OUTPUT"))  {
                    callingNode = nodeMap.get(edge.getTargettedSource());
                    calledNode = nodeMap.get(edge.getTargettedDest());
                    edge.setInputContextCounter(edge.getCalledContextCounter());
                    edge.setOutputContextCounter(edge.getCallerContextCounter());
                }
                else if (edge.getType().equals("RETURNING")) {
                    callingNode = nodeMap.get(edge.getTargettedDest());
                    calledNode = nodeMap.get(edge.getTargettedSource());
                    edge.setInputContextCounter(edge.getCallerContextCounter());
                    edge.setOutputContextCounter(edge.getCalledContextCounter());
                }
                else if (edge.getType().equals("RETURNINGINPUT")) {
                    callingNode = nodeMap.get(edge.getDataSourceDest());
//                    callingNode = nodeMap.get(edge.getTargettedDest());
                    calledNode = nodeMap.get(edge.getTargettedSource());
                    edge.setInputContextCounter(edge.getCallerContextCounter());
                    edge.setOutputContextCounter(edge.getCalledContextCounter());

                    taintIDToInputMap.put(edge.getTaintedObjects().getFirst().getTaintID(), callingNode);
                }
                else if (edge.getType().equals("STATICFIELDSTORE")) {
                    callingNode = nodeMap.get(edge.getTargettedDest());
                    calledNode = nodeMap.get(edge.getFieldName());
                    edge.setInputContextCounter(edge.getCalledContextCounter());
                    edge.setOutputContextCounter(edge.getCalledContextCounter());
                }
                else if (edge.getType().equals("FIELDSET")) {
                    callingNode = nodeMap.get(edge.getTargettedDest());
                    calledNode = nodeMap.get(edge.getFieldName());
                    edge.setInputContextCounter(edge.getCalledContextCounter());
                    edge.setOutputContextCounter(edge.getCalledContextCounter());
                }
                else if (edge.getType().equals("FIELDGET")) {
                    callingNode = nodeMap.get(edge.getFieldName());
                    calledNode = nodeMap.get(edge.getTargettedDest());
                    edge.setInputContextCounter(edge.getCalledContextCounter());
                    edge.setOutputContextCounter(edge.getCalledContextCounter());
                }
                else if (edge.getType().equals("JAVAFIELDSET")) {
                    callingNode = nodeMap.get(edge.getTargettedDest());
                    calledNode = nodeMap.get(edge.getFieldName());
                    edge.setInputContextCounter(edge.getCalledContextCounter());
                    edge.setOutputContextCounter(edge.getCalledContextCounter());
                }
                else if (edge.getType().equals("JAVAFIELDGET")) {
                    callingNode = nodeMap.get(edge.getFieldName());
                    calledNode = nodeMap.get(edge.getTargettedDest());
                    edge.setInputContextCounter(edge.getCalledContextCounter());
                    edge.setOutputContextCounter(edge.getCalledContextCounter());
                }
                else if (edge.getType().equals("FUZZYPROPAGATION")) {
                    callingNode = nodeMap.get(edge.getTargettedDest());
                    calledNode = nodeMap.get(edge.getTargettedDest());
                    //TODO: Not sure about this input context
                    edge.setInputContextCounter(edge.getCalledContextCounter());
                    edge.setOutputContextCounter(edge.getCalledContextCounter());
                }
                else if (edge.getType().equals("COMPOSITION")) {
                    callingNode = nodeMap.get(edge.getTargettedSource());
                    calledNode = nodeMap.get(edge.getTargettedDest());
                    edge.setInputContextCounter(edge.getCalledContextCounter());
                    edge.setOutputContextCounter(edge.getCallerContextCounter());
                    callingNode.addDataMixingEdge(edge);
                }
                else if (edge.getType().equals("ASSOCIATION")) {
                    callingNode = nodeMap.get(edge.getTargettedSource());
                    calledNode = nodeMap.get(edge.getTargettedDest());
                    edge.setInputContextCounter(edge.getCalledContextCounter());
                    edge.setOutputContextCounter(edge.getCallerContextCounter());
                    callingNode.addDataMixingEdge(edge);
                }

                if (callingNode != null && calledNode != null) {
                    if (!edge.getAdviceType().startsWith("NONTAINTRETURN")) {
                        RequestCounterURIPair counterURI = new RequestCounterURIPair(edge.getRequestCounter(), edge.getRequestURI());
                        requestCounters.put(counterURI.toString(), counterURI);
                    }
                    edge.setCallingNode(callingNode);
                    edge.setCalledNode(calledNode);
                }
            }
            monitor.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public HashSet<TaintEdge> getEdgeList() {
        return this.edgeList;
    }

    public LinkedList<TaintEdge> getOrderedEdgeList() {
        LinkedList<TaintEdge> output = new LinkedList<TaintEdge>(this.edgeList);
        Collections.sort(output);
        return output;
    }

    public LinkedList<TaintEdge> getPropagationEdges() {
        LinkedList<TaintEdge> propEdges = new LinkedList<TaintEdge>();

        for (TaintEdge edge : edgeList) {
            if (edge.getType().equals("PROPAGATION"))
                propEdges.add(edge);
        }

        return propEdges;
    }

    public HashSet<TaintNode> getInputNodesFromTaintIDs(HashSet<String> taintIDs) {
        HashSet<TaintNode> output = new HashSet<TaintNode>();

        for (String taintID : taintIDs) {
            output.add(this.taintIDToInputMap.get(taintID));
        }

        return output;
    }

    public HashSet<TaintNode> getInputNodes() {
        HashSet<TaintNode> output = new HashSet<TaintNode>();

        output.addAll(taintIDToInputMap.values());

        return output;
    }

    public HashMap<String, RequestCounterURIPair> getRequestCounters() {
        return requestCounters;
    }

    public LinkedHashSet<String> getTaintIDs() {
        return taintIDs;
    }

    public LinkedList<TaintIDPropagationPair> getTaintIDPropagations() {
        return taintIDPropagations;
    }

    public Graph<TaintNode, TaintEdge> getGraph() {
        if (unfilteredGraph != null)
            return unfilteredGraph;

        unfilteredGraph = getGraph(null);
        return unfilteredGraph;
    }

    public Graph<TaintNode, TaintEdge> getMultiGraph() {
//        if (unfilteredMultiGraph != null)
//            return unfilteredMultiGraph;

//        unfilteredMultiGraph = ;
        return getMultiGraph(new LinkedList<EdgeFilter>());
    }

    public Graph<TaintNode, TaintEdge> getLightMultiGraph() {
        if (unfilteredLightMultiGraph != null)
            return unfilteredLightMultiGraph;
        LinkedList<EdgeFilter> filters = new LinkedList<EdgeFilter>();
        
        filters.add(new FilterByIsUniqueEdge());
        unfilteredLightMultiGraph = getMultiGraph(filters);
        return unfilteredLightMultiGraph;
    }

    public Graph<TaintNode, TaintEdge> getLightMultiGraph(LinkedList<EdgeFilter> filters) {
        if (filters == null)
            filters = new LinkedList<EdgeFilter>();
        filters.add(new FilterByIsUniqueEdge());
        return getMultiGraph(filters);
    }

    public Graph<TaintNode, TaintEdge> getGraph(LinkedList<EdgeFilter> filters) {
        Graph<TaintNode, TaintEdge> g = new DirectedSparseGraph<TaintNode, TaintEdge>();
        loadGraph(g, filters);

        return g;
    }

    public Graph<TaintNode, TaintEdge> getMultiGraph(LinkedList<EdgeFilter> filters) {
        filters.add(new FilterByRINRETExclude());
        
        Graph<TaintNode, TaintEdge> g = new DirectedSparseMultigraph<TaintNode, TaintEdge>();
        loadGraph(g, filters);
        return g;
    }

    public void generateTaintIDPropagationsAndRequestCounters() {
        LinkedList<TaintEdge> sortedEdgeList = new LinkedList<TaintEdge>(edgeList);
        Collections.sort(sortedEdgeList);
        for (TaintEdge edge : sortedEdgeList) {
            if (edge.getType().equals("PROPAGATION")) {
                taintIDPropagations.add(new TaintIDPropagationPair(edge.getSourceObject().getTaintID(),
                        edge.getSourceObject().getValue(),
                        edge.getDestObject().getTaintID(),
                        edge.getDestObject().getValue()));
            }
            taintIDs.addAll(edge.getAllTaintIDs());

            if (edge.getCallingNode() != null && edge.getCalledNode() != null) {
                if (!edge.getAdviceType().startsWith("NONTAINTRETURN")) {
                    RequestCounterURIPair counterURI = new RequestCounterURIPair(edge.getRequestCounter(), edge.getRequestURI());
                    requestCounters.put(counterURI.toString(), counterURI);
                }
            }
        }
    }

    // Run a propagation list through this to fix up problems when multiple taint id sources are combined
    public void postProcessTaintIDPropagations() {
        HashMap<String, LinkedList<TaintIDPropagationPair>> proppedTo = new HashMap<String, LinkedList<TaintIDPropagationPair>>();

        LinkedList<TaintIDPropagationPair> toAdd = new LinkedList<TaintIDPropagationPair>();
        for (TaintIDPropagationPair pair : taintIDPropagations) {
            String destObjectId = pair.getDestID().substring(pair.getDestID().lastIndexOf('[') + 1, pair.getDestID().lastIndexOf(']'));
//            System.out.println("DESTOBJECTID: " + destObjectId);
            LinkedList<TaintIDPropagationPair> mappedPairs = proppedTo.get(destObjectId);
            if (mappedPairs == null) {
                mappedPairs = new LinkedList<TaintIDPropagationPair>();
                proppedTo.put(destObjectId, mappedPairs);
            }
            else {
                for (TaintIDPropagationPair mappedPair : mappedPairs) {
                    if (mappedPair.getDestID().equals(pair.getDestID()) && mappedPair.getDestValue().equals(pair.getDestValue()))
                        continue;
                    TaintIDPropagationPair newPair = new TaintIDPropagationPair(mappedPair.getDestID(), mappedPair.getDestValue(),
                                                                                pair.getDestID(), pair.getDestValue());
//                    System.out.println("ADDING NEW PAIR: " + newPair);
                    newPair.setIsPostProcessed();
                    toAdd.add(newPair);
                }
            }
            mappedPairs.add(pair);
        }

        taintIDPropagations.addAll(toAdd);
    }

    private void loadGraph(Graph<TaintNode, TaintEdge> g, LinkedList<EdgeFilter> filters) {
        // Want this filter in all graphs for now
        int skipCount = 0;

        LinkedList<TaintEdge> sortedEdgeList = this.getOrderedEdgeList();
        for (TaintEdge edge : sortedEdgeList) {
            if (edge.getAdviceType().equals("NONTAINTRETURN") || edge.getType().equals("COMPOSITION") ||
                    edge.getType().equals("ASSOCIATION")) {
                skipCount++;
                continue;
            }

            TaintNode callingNode = edge.getCallingNode();
            TaintNode calledNode = edge.getCalledNode();
            if (callingNode != null && calledNode != null) {
                if (filters != null) {

                    boolean pass = true;
                    for (EdgeFilter filter : filters) {
                        if (!filter.pass(edge)) {
                            pass = false;
                            break;
                        }
                    }
                    if (pass) {
                        g.addEdge(edge, callingNode, calledNode);
                    }
                }
                else {
                    g.addEdge(edge, callingNode, calledNode);
                }
            }
        }
    }

    public static Graph<TaintNode, TaintEdge> copyGraph(Graph<TaintNode, TaintEdge> fullInput) {
        Graph<TaintNode, TaintEdge> derivedGraph = null;
        try {
            derivedGraph = fullInput.getClass().newInstance();

            for (TaintNode node : fullInput.getVertices()) {
                derivedGraph.addVertex(node);
            }
            for (TaintEdge edge : fullInput.getEdges()) {
                derivedGraph.addEdge(edge, fullInput.getSource(edge), fullInput.getDest(edge));
            }
        } catch (InstantiationException ex) {
            Logger.getLogger(GraphBuilder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(GraphBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }

        return derivedGraph;
    }

    public static GraphBuilder copyGraphBuilder(GraphBuilder input) {
        GraphBuilder output = new GraphBuilder(input);

        output.generateTaintIDPropagationsAndRequestCounters();
        output.postProcessTaintIDPropagations();

        return output;
    }

    public static GraphBuilder pruneToOnlyStableGraphBuilder(GraphBuilder input, DataSourceInfoBuilder dsib) {
        GraphBuilder output = new GraphBuilder(input);

        Graph<TaintNode, TaintEdge> inputGraph = input.getMultiGraph();

        LinkedList<TaintEdge> edges = new LinkedList<TaintEdge>(output.edgeList);
        for (TaintEdge edge : edges) {
            for (String record : edge.getAllTaintRecords()) {
                if (!dsib.checkTaintRecordMatchesVariability(record, "STABLE")) {
                    output.edgeList.remove(edge);
                    Collection<TaintEdge> calledEdges = inputGraph.getIncidentEdges(edge.getCalledNode());
                    if (calledEdges != null) {
                        for (TaintEdge removeEdge : calledEdges) {
                            output.edgeList.remove(removeEdge);
                        }
                    }
                    Collection<TaintEdge> callingEdges = inputGraph.getIncidentEdges(edge.getCallingNode());
                    if (callingEdges != null) {
                        for (TaintEdge removeEdge : callingEdges) {
                            output.edgeList.remove(removeEdge);
                        }
                    }
                }
            }
        }

        output.generateTaintIDPropagationsAndRequestCounters();
        output.postProcessTaintIDPropagations();

        return output;
    }

    public static GraphBuilder pruneToPredictableGraphBuilder(GraphBuilder input, DataSourceInfoBuilder dsib, AnalysisMainWindow window) {
        Graph<TaintNode, TaintEdge> inputGraph = input.getMultiGraph();
        GraphBuilder output = new GraphBuilder(input);
        GraphBuilder onlyStable = GraphBuilder.pruneToOnlyStableGraphBuilder(input, dsib);

        LinkedList<TaintEdge> edges = new LinkedList<TaintEdge>(output.edgeList);
        for (TaintEdge edge : edges) {
            for (String record : edge.getAllTaintRecords()) {
                if (!dsib.checkTaintRecordMatchesVariability(record, "PREDICTABLE") && !dsib.checkTaintRecordMatchesVariability(record, "STABLE")) {
                    Collection<TaintEdge> calledEdges = inputGraph.getIncidentEdges(edge.getCalledNode());
                    if (calledEdges != null) {
                        for (TaintEdge removeEdge : calledEdges) {
                            output.edgeList.remove(removeEdge);
                        }
                    }
                    Collection<TaintEdge> callingEdges = inputGraph.getIncidentEdges(edge.getCallingNode());
                    if (callingEdges != null) {
                        for (TaintEdge removeEdge : callingEdges) {
                            output.edgeList.remove(removeEdge);
                        }
                    }
                }
            }
        }

        // Changed from using inputGraph to remove edges (getIncidentEdges called on input instead of stable), to using stable
        Graph<TaintNode, TaintEdge> stableGraph = onlyStable.getMultiGraph();
        for (TaintNode node : stableGraph.getVertices()) {
            for (TaintEdge removeEdge : stableGraph.getIncidentEdges(node)) {
                output.edgeList.remove(removeEdge);
            }
        }

        output.generateTaintIDPropagationsAndRequestCounters();
        output.postProcessTaintIDPropagations();

        return output;
    }

//    public static Graph<TaintNode, TaintEdge> pruneToNotRandomGraph(Graph<TaintNode, TaintEdge> fullInput, DataSourceInfoBuilder dsib) {
//        Graph<TaintNode, TaintEdge> outputGraph = GraphBuilder.copyGraph(fullInput);
//
//        LinkedList<TaintEdge> edges = new LinkedList<TaintEdge>(outputGraph.getEdges());
//        for (TaintEdge edge : edges) {
//            for (String record : edge.getAllTaintRecords()) {
//                if (dsib.checkTaintRecordMatchesVariability(record, "RANDOM")) {
//                    TaintNode source = outputGraph.getSource(edge);
//                    TaintNode dest = outputGraph.getDest(edge);
//                    outputGraph.removeEdge(edge);
//                    outputGraph.removeVertex(source);
//                    outputGraph.removeVertex(dest);
//                }
//            }
//        }
//
//        removeOrphanNodes(outputGraph);
//        return outputGraph;
//    }

//    public static Graph<TaintNode, TaintEdge> pruneToOnlyRandomGraph(Graph<TaintNode, TaintEdge> fullInput, DataSourceInfoBuilder dsib) {
//        Graph<TaintNode, TaintEdge> outputGraph = GraphBuilder.copyGraph(fullInput);
//
//        LinkedList<TaintEdge> edges = new LinkedList<TaintEdge>(outputGraph.getEdges());
//        for (TaintEdge edge : edges) {
//            for (String record : edge.getAllTaintRecords()) {
//                if (!dsib.checkTaintRecordMatchesVariability(record, "RANDOM")) {
//                    TaintNode source = outputGraph.getSource(edge);
//                    TaintNode dest = outputGraph.getDest(edge);
//                    outputGraph.removeEdge(edge);
//                    outputGraph.removeVertex(source);
//                    outputGraph.removeVertex(dest);
//                }
//            }
//        }
//
//        removeOrphanNodes(outputGraph);
//        return outputGraph;
//    }

    public static GraphBuilder pruneToRandomGraphBuilder(GraphBuilder input, DataSourceInfoBuilder dsib) {
        GraphBuilder output = new GraphBuilder(input);

        Graph<TaintNode, TaintEdge> inputGraph = input.getMultiGraph();

        LinkedList<TaintEdge> edges = new LinkedList<TaintEdge>(output.edgeList);
        for (TaintEdge edge : edges) {
            for (String record : edge.getAllTaintRecords()) {
                if (!dsib.checkTaintRecordMatchesVariability(record, "RANDOM")) {
                    output.edgeList.remove(edge);
                }
            }
        }

        output.generateTaintIDPropagationsAndRequestCounters();
        output.postProcessTaintIDPropagations();

        return output;
    }

//    private static void removeOrphanNodes(Graph<TaintNode, TaintEdge> input) {
//        LinkedList<TaintNode> nodes = new LinkedList<TaintNode>(input.getVertices());
//
//        for (TaintNode node : nodes) {
//            if (input.getIncidentEdges(node) == null || input.getIncidentEdges(node).isEmpty())
//                input.removeVertex(node);
//        }
//    }


    public static HashMap<GraphBuilder, String> getByRequestGraphBuilders(GraphBuilder input) {
        HashMap<GraphBuilder, String> outputs = new HashMap<GraphBuilder, String>();

        for (String key : input.getRequestCounters().keySet()) {
            RequestCounterURIPair pair = input.getRequestCounters().get(key);
            String counter = pair.getRequestCounter();

            GraphBuilder output = new GraphBuilder(input);

            LinkedList<TaintEdge> edges = new LinkedList<TaintEdge>(output.edgeList);
            for (TaintEdge edge : edges) {
                if (!edge.getRequestCounter().equals(counter)) {
                    output.edgeList.remove(edge);
                }
            }

            output.generateTaintIDPropagationsAndRequestCounters();
            output.postProcessTaintIDPropagations();

            outputs.put(output, counter);
        }

        return outputs;
    }

    public static LinkedList<GraphBuilder> getConnectedSubGraphBuilders(GraphBuilder input) {
        LinkedList<GraphBuilder> outputs = new LinkedList<GraphBuilder>();

        // Get graph, split graph. Use graph to generate new edge lists, and taint ID lists.
        Graph<TaintNode, TaintEdge> inputGraph = input.getMultiGraph();
        LinkedList<TaintNode> inputNodes = new LinkedList<TaintNode>(inputGraph.getVertices());

        HashSet<TaintNode> visited = new HashSet<TaintNode>();
        for (TaintNode node : inputNodes) {
            Graph<TaintNode, TaintEdge> subGraph = null;
            if (visited.contains(node))
                continue;
            subGraph = new DirectedSparseMultigraph<TaintNode, TaintEdge>();

            LinkedList<TaintNode> toVisit = new LinkedList<TaintNode>();
            toVisit.add(node);

            while (!toVisit.isEmpty()) {
                TaintNode visit = toVisit.removeFirst();
                if (visited.contains(visit))
                    continue;
                visited.add(visit);

                subGraph.addVertex(visit);
                if (inputGraph.getIncidentEdges(visit) != null) {
                    for (TaintEdge edge : inputGraph.getIncidentEdges(visit)) {
                        subGraph.addEdge(edge, inputGraph.getSource(edge), inputGraph.getDest(edge));
                        toVisit.add(inputGraph.getSource(edge));
                        toVisit.add(inputGraph.getDest(edge));
                    }
                }
            }
            
            if (subGraph.getVertexCount() > 0) {
                GraphBuilder output = new GraphBuilder(input, true);
                output.edgeList.addAll(subGraph.getEdges());

                for (TaintEdge edge : output.edgeList) {
                    output.taintIDs.addAll(edge.getAllTaintIDs());

                    if (edge.getCallingNode() != null && edge.getCalledNode() != null) {
                        if (!edge.getAdviceType().startsWith("NONTAINTRETURN")) {
                            RequestCounterURIPair counterURI = new RequestCounterURIPair(edge.getRequestCounter(), edge.getRequestURI());
                            output.requestCounters.put(counterURI.toString(), counterURI);
                        }
                    }
                }

                LinkedList<TaintEdge> sortedEdges = new LinkedList<TaintEdge>(input.getPropagationEdges());
                Collections.sort(sortedEdges);
                for (TaintEdge edge : sortedEdges) {
                    // No propagation edges moved over. No constructor copies full propagation list. It's added from edges. Propagation
                    // edges are never filtered out by taintRecord.
                    if (output.taintIDs.contains(edge.getSourceObject().getTaintID()) &&
                            output.taintIDs.contains(edge.getDestObject().getTaintID())) {
                        output.taintIDPropagations.add(new TaintIDPropagationPair(edge.getSourceObject().getTaintID(),
                                edge.getSourceObject().getValue(),
                                edge.getDestObject().getTaintID(),
                                edge.getDestObject().getValue()));
                    }
                }
                output.postProcessTaintIDPropagations();
                
                outputs.add(output);
            }
        }

        return outputs;
    }

    public static HashSet<TaintNode> getNonCoyoteOutputs(GraphBuilder input) {
        HashSet<TaintNode> output = new HashSet<TaintNode>();

        Graph<TaintNode, TaintEdge> fullGraph = input.getMultiGraph();

        for (TaintEdge edge : fullGraph.getEdges()) {
            if (edge.getType().equals("OUTPUT")) {
                TaintNode outputNode = fullGraph.getDest(edge);
                if (!outputNode.getName().contains("CoyoteWriter"))
                    output.add(outputNode);
            }
        }

        return output;
    }

    // Compares a subset graph with a superset to find all outputs from the subset into the superset
    public static HashMap<TaintNode, LinkedList<TaintEdge>> getOutputs(Graph<TaintNode, TaintEdge> subset, Graph<TaintNode, TaintEdge> superset) {
        HashMap<TaintNode, LinkedList<TaintEdge>> outputs = new HashMap<TaintNode, LinkedList<TaintEdge>>();

        for (TaintNode node : subset.getVertices()) {
            for (TaintEdge edge : superset.getOutEdges(node)) {
                TaintNode outNode = superset.getDest(edge);
                if (!subset.containsVertex(outNode)) {
                    LinkedList<TaintEdge> outEdges = outputs.get(node);
                    if (outEdges == null) {
                        outEdges = new LinkedList<TaintEdge>();
                        outputs.put(node, outEdges);
                    }
                    outEdges.add(edge);
                }
            }
        }

        return outputs;
    }

//    public static HashMap<TaintNode, LinkedList<TaintEdge>> getInputs(Graph<TaintNode, TaintEdge> graph, TaintNode startNode) {
//        HashMap<TaintNode, LinkedList<TaintEdge>> inputs = new HashMap<TaintNode, LinkedList<TaintEdge>>();
//
//        HashSet<TaintNode> inputsNodes = new HashSet<TaintNode>();
//        getInputsHelper(graph, inputsNodes, new HashSet<TaintNode>(), startNode);
//
//
//        return inputs;
//    }
//
//    public static void getInputsHelper(Graph<TaintNode, TaintEdge> graph, HashSet<TaintNode> inputs, HashSet<TaintNode> visited, TaintNode currentNode) {
//        if (visited.contains(currentNode))
//            return;
//        visited.add(currentNode);
//
//        boolean hasInputs = false;
//        for (TaintEdge inEdge : graph.getInEdges(currentNode)) {
//            hasInputs = true;
//            getInputsHelper(graph, inputs, visited, graph.getSource(inEdge));
//        }
//
//        if (!hasInputs) {
//            inputs.add(currentNode);
//        }
//    }

    // Compares a subset graph with a superset to find all inputs from the superset into the subset
    public static HashMap<TaintNode, LinkedList<TaintEdge>> getInputs(Graph<TaintNode, TaintEdge> subset, Graph<TaintNode, TaintEdge> superset) {
        HashMap<TaintNode, LinkedList<TaintEdge>> inputs = new HashMap<TaintNode, LinkedList<TaintEdge>>();

        for (TaintNode subNode : subset.getVertices()) {
            for (TaintEdge inEdge : superset.getInEdges(subNode)) {
                TaintNode inNode = superset.getSource(inEdge);
                if (!subset.containsVertex(inNode)) {
                    LinkedList<TaintEdge> inEdges = inputs.get(subNode);
                    if (inEdges == null) {
                        inEdges = new LinkedList<TaintEdge>();
                        inputs.put(subNode, inEdges);
                    }
                    inEdges.add(inEdge);
                }
            }
        }

        return inputs;
    }

    public static LinkedList<TaintNode> getInputs(Graph<TaintNode, TaintEdge> fullInput) {
        LinkedList<TaintNode> inputs = new LinkedList<TaintNode>();

//        for (TaintNode node : fullInput.getVertices()) {
//            if (fullInput.getInEdges(node) == null || fullInput.getInEdges(node).isEmpty()) {
//                inputs.add(node);
//            }
//        }

        if (inputs.isEmpty()) {
            LinkedList<TaintEdge> edges = new LinkedList<TaintEdge>(fullInput.getEdges());
            Collections.sort(edges);

            inputs.add(fullInput.getSource(edges.getFirst()));
        }

        return inputs;
    }

}
