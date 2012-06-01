/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dicetrackeranalysis.graphhandling;

import com.sun.org.apache.bcel.internal.generic.SIPUSH;
import dicetrackeranalysis.datasourceinfo.DataSourceInfoBuilder;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.Graph;
import java.io.File;
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
import org.w3c.dom.NodeList;

/**
 *
 * @author lee
 */
public class GraphBuilder {

    public LinkedList<TaintEdge> edgeList;
    public HashMap<String, TaintNode> nodeMap;
    public LinkedHashSet<String> taintIDs;
    public LinkedList<TaintIDPropagationPair> taintIDPropagations;
    public HashMap<String, RequestCounterURIPair> requestCounters;

    public GraphBuilder(File input) {
        edgeList = new LinkedList<TaintEdge>();
        nodeMap = new HashMap<String, TaintNode>();
        taintIDs = new LinkedHashSet<String>();
        taintIDPropagations = new LinkedList<TaintIDPropagationPair>();
        requestCounters = new HashMap<String, RequestCounterURIPair>();
        fillEdgeList(input);
    }

    private void fillEdgeList(File input) {
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
                            taintEdge.setAdviceType(taintLogChildElem.getAttribute("adviceType"));
                            String requestCounter = taintLogChildElem.getAttribute("requestCounter");
                            taintEdge.setRequestCounter(requestCounter);
                            taintEdge.setRequestURI(taintLogChildElem.getAttribute("requestURI"));
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

                    if (!nodeMap.containsKey(srcName))
                        nodeMap.put(srcName, new TaintNode(taintEdge.getSimpleSource(), taintEdge.getSourceID()));
                    // Consider adding targetted objects later
                    if (!nodeMap.containsKey(destName))
                        nodeMap.put(destName, new TaintNode(taintEdge.getSimpleDest(), taintEdge.getDestID()));
                    if (fieldName != null && !nodeMap.containsKey(fieldName)) {
                        nodeMap.put(fieldName, new TaintNode(fieldName));
                    }
                    edgeList.add(taintEdge);
                    if (taintEdge.getAdviceType().startsWith("NONTAINTRETURN"))
                        TaintEdge.decrementCounter();
                    if (taintEdge.getType().equals("PROPAGATION")) {
                        taintIDPropagations.add(new TaintIDPropagationPair(taintEdge.getSourceObject().getTaintID(),
                                taintEdge.getSourceObject().getValue(),
                                taintEdge.getDestObject().getTaintID(),
                                taintEdge.getDestObject().getValue()));
                    }
                }
            }

            for (TaintEdge edge : edgeList) {
                if (progCounter++ % 1000 == 0)
                    monitor.setProgress(progCounter);
                TaintNode callingNode = null;
                TaintNode calledNode = null;

                if (edge.getType().equals("CALLING")) {
                    callingNode = nodeMap.get(edge.getTargettedSource());
                    calledNode = nodeMap.get(edge.getTargettedDest());
                }
                else if (edge.getType().equals("OUTPUT"))  {
                    callingNode = nodeMap.get(edge.getTargettedSource());
                    calledNode = nodeMap.get(edge.getTargettedDest());
                }
                else if (edge.getType().equals("RETURNING")) {
                    callingNode = nodeMap.get(edge.getTargettedDest());
                    calledNode = nodeMap.get(edge.getTargettedSource());
                }
                else if (edge.getType().equals("RETURNINGINPUT")) {
                    callingNode = nodeMap.get(edge.getTargettedDest());
                    calledNode = nodeMap.get(edge.getTargettedSource());
                }
                else if (edge.getType().equals("STATICFIELDSTORE")) {
                    callingNode = nodeMap.get(edge.getTargettedDest());
                    calledNode = nodeMap.get(edge.getFieldName());
                }
                else if (edge.getType().equals("FIELDSET")) {
                    callingNode = nodeMap.get(edge.getTargettedDest());
                    calledNode = nodeMap.get(edge.getFieldName());
                }
                else if (edge.getType().equals("FIELDGET")) {
                    callingNode = nodeMap.get(edge.getFieldName());
                    calledNode = nodeMap.get(edge.getTargettedDest());
                }
                else if (edge.getType().equals("JAVAFIELDSET")) {
                    callingNode = nodeMap.get(edge.getTargettedDest());
                    calledNode = nodeMap.get(edge.getFieldName());
                }
                else if (edge.getType().equals("JAVAFIELDGET")) {
                    callingNode = nodeMap.get(edge.getFieldName());
                    calledNode = nodeMap.get(edge.getTargettedDest());
                }
                else if (edge.getType().equals("FUZZYPROPAGATION")) {
                    callingNode = nodeMap.get(edge.getTargettedDest());
                    calledNode = nodeMap.get(edge.getTargettedDest());
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
        return getGraph(null);
    }

    public Graph<TaintNode, TaintEdge> getMultiGraph() {
        return getMultiGraph(null);
    }

    public Graph<TaintNode, TaintEdge> getLightMultiGraph() {
        LinkedList<EdgeFilter> filters = new LinkedList<EdgeFilter>();
        
        filters.add(new FilterByIsUniqueEdge());
        return getMultiGraph(filters);
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
        Graph<TaintNode, TaintEdge> g = new DirectedSparseMultigraph<TaintNode, TaintEdge>();
        loadGraph(g, filters);

        return g;
    }

    private void loadGraph(Graph<TaintNode, TaintEdge> g, LinkedList<EdgeFilter> filters) {
        int skipCount = 0;
        for (TaintEdge edge : edgeList) {
            if (edge.getAdviceType().equals("NONTAINTRETURN")) {
                skipCount++;
                continue;
            }

            TaintNode callingNode = edge.getCallingNode();
            TaintNode calledNode = edge.getCalledNode();
            if (callingNode != null && calledNode != null) {
                edge.setCallingNode(callingNode);
                edge.setCalledNode(calledNode);
                if (filters != null) {
                    boolean pass = true;
                    for (EdgeFilter filter : filters) {
                        if (!filter.pass(edge)) {
                            pass = false;
                            break;
                        }
                    }
                    if (pass)
                        g.addEdge(edge, callingNode, calledNode);
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

    // For friendler display
    public static Graph<TaintNode, TaintEdge> convertToLightMultiGraph(Graph<TaintNode, TaintEdge> fullInput) {
        Graph<TaintNode, TaintEdge> derivedGraph = new DirectedSparseMultigraph<TaintNode, TaintEdge>();

        for (TaintNode node : fullInput.getVertices()) {
            derivedGraph.addVertex(node);
        }
        
        EdgeFilter uniqueFilter = new FilterByIsUniqueEdge();
        for (TaintEdge edge : fullInput.getEdges()) {
            if (uniqueFilter.pass(edge)) {
                derivedGraph.addEdge(edge, fullInput.getSource(edge), fullInput.getDest(edge));
            }
        }

        return derivedGraph;
    }

    public static Graph<TaintNode, TaintEdge> pruneToOnlyStableGraph(Graph<TaintNode, TaintEdge> fullInput, DataSourceInfoBuilder dsib) {
        Graph<TaintNode, TaintEdge> outputGraph = GraphBuilder.copyGraph(fullInput);

        LinkedList<TaintEdge> edges = new LinkedList<TaintEdge>(outputGraph.getEdges());
        for (TaintEdge edge : edges) {
            for (String record : edge.getAllTaintRecords()) {
                if (!dsib.checkTaintRecordMatchesVariability(record, "STABLE")) {
                    TaintNode source = outputGraph.getSource(edge);
                    TaintNode dest = outputGraph.getDest(edge);
                    outputGraph.removeEdge(edge);
                    outputGraph.removeVertex(source);
                    outputGraph.removeVertex(dest);
                }
            }
        }
        removeOrphanNodes(outputGraph);
        return outputGraph;
    }

    public static Graph<TaintNode, TaintEdge> pruneToPredictableGraph(Graph<TaintNode, TaintEdge> fullInput, DataSourceInfoBuilder dsib) {
        Graph<TaintNode, TaintEdge> onlyStable = GraphBuilder.pruneToOnlyStableGraph(fullInput, dsib);
        Graph<TaintNode, TaintEdge> outputGraph = GraphBuilder.copyGraph(fullInput);

        LinkedList<TaintEdge> edges = new LinkedList<TaintEdge>(outputGraph.getEdges());
        for (TaintEdge edge : edges) {
            for (String record : edge.getAllTaintRecords()) {
                if (!dsib.checkTaintRecordMatchesVariability(record, "PREDICTABLE") && !dsib.checkTaintRecordMatchesVariability(record, "STABLE")) {
                    TaintNode source = outputGraph.getSource(edge);
                    TaintNode dest = outputGraph.getDest(edge);
                    outputGraph.removeEdge(edge);
                    outputGraph.removeVertex(source);
                    outputGraph.removeVertex(dest);
                }
            }
        }

        for (TaintNode node : onlyStable.getVertices()) {
            if (outputGraph.containsVertex(node)) {
                outputGraph.removeVertex(node);
            }
        }

        removeOrphanNodes(outputGraph);
        return outputGraph;
    }

    public static Graph<TaintNode, TaintEdge> pruneToNotRandomGraph(Graph<TaintNode, TaintEdge> fullInput, DataSourceInfoBuilder dsib) {
        Graph<TaintNode, TaintEdge> outputGraph = GraphBuilder.copyGraph(fullInput);

        LinkedList<TaintEdge> edges = new LinkedList<TaintEdge>(outputGraph.getEdges());
        for (TaintEdge edge : edges) {
            for (String record : edge.getAllTaintRecords()) {
                if (dsib.checkTaintRecordMatchesVariability(record, "RANDOM")) {
                    TaintNode source = outputGraph.getSource(edge);
                    TaintNode dest = outputGraph.getDest(edge);
                    outputGraph.removeEdge(edge);
                    outputGraph.removeVertex(source);
                    outputGraph.removeVertex(dest);
                }
            }
        }

        removeOrphanNodes(outputGraph);
        return outputGraph;
    }

    public static Graph<TaintNode, TaintEdge> pruneToOnlyRandomGraph(Graph<TaintNode, TaintEdge> fullInput, DataSourceInfoBuilder dsib) {
        Graph<TaintNode, TaintEdge> outputGraph = GraphBuilder.copyGraph(fullInput);

        LinkedList<TaintEdge> edges = new LinkedList<TaintEdge>(outputGraph.getEdges());
        for (TaintEdge edge : edges) {
            for (String record : edge.getAllTaintRecords()) {
                if (!dsib.checkTaintRecordMatchesVariability(record, "RANDOM")) {
                    TaintNode source = outputGraph.getSource(edge);
                    TaintNode dest = outputGraph.getDest(edge);
                    outputGraph.removeEdge(edge);
                    outputGraph.removeVertex(source);
                    outputGraph.removeVertex(dest);
                }
            }
        }

        removeOrphanNodes(outputGraph);
        return outputGraph;
    }

    public static Graph<TaintNode, TaintEdge> pruneToRandomGraph(Graph<TaintNode, TaintEdge> fullInput, DataSourceInfoBuilder dsib) {
        Graph<TaintNode, TaintEdge> outputGraph = GraphBuilder.copyGraph(fullInput);

        LinkedList<TaintEdge> edges = new LinkedList<TaintEdge>(outputGraph.getEdges());
        for (TaintEdge edge : edges) {
            for (String record : edge.getAllTaintRecords()) {
                if (!dsib.checkTaintRecordMatchesVariability(record, "RANDOM")) {
                    outputGraph.removeEdge(edge);
                }
            }
        }

        removeOrphanNodes(outputGraph);
        return outputGraph;
    }

    private static void removeOrphanNodes(Graph<TaintNode, TaintEdge> input) {
        LinkedList<TaintNode> nodes = new LinkedList<TaintNode>(input.getVertices());

        for (TaintNode node : nodes) {
            if (input.getIncidentEdges(node) == null || input.getIncidentEdges(node).isEmpty())
                input.removeVertex(node);
        }
    }

    public static LinkedList<Graph<TaintNode, TaintEdge>> getConnectedSubGraphs(Graph<TaintNode, TaintEdge> fullInput) {
        Graph<TaintNode, TaintEdge> outputGraph = GraphBuilder.copyGraph(fullInput);
        LinkedList<Graph<TaintNode, TaintEdge>> output = new LinkedList<Graph<TaintNode, TaintEdge>>();

        HashSet<TaintNode> visited = new HashSet<TaintNode>();
        for (TaintNode node : fullInput.getVertices()) {
            Graph<TaintNode, TaintEdge> subGraph = null;
            if (visited.contains(node))
                continue;
            try {
                subGraph = fullInput.getClass().newInstance();
            } catch (InstantiationException ex) {
                Logger.getLogger(GraphBuilder.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(GraphBuilder.class.getName()).log(Level.SEVERE, null, ex);
            }

            LinkedList<TaintNode> toVisit = new LinkedList<TaintNode>();
            toVisit.add(node);

            while (!toVisit.isEmpty()) {
                TaintNode visit = toVisit.removeFirst();
                if (visited.contains(visit))
                    continue;
                visited.add(visit);

                subGraph.addVertex(visit);
                if (fullInput.getIncidentEdges(visit) != null) {
                    for (TaintEdge edge : fullInput.getIncidentEdges(visit)) {
                        subGraph.addEdge(edge, fullInput.getSource(edge), fullInput.getDest(edge));
                        toVisit.add(fullInput.getSource(edge));
                        toVisit.add(fullInput.getDest(edge));
                    }
                }
            }
            
            if (subGraph.getVertexCount() > 0)
                output.add(subGraph);
        }

        return output;
    }

    public static LinkedList<TaintNode> getOutputs(Graph<TaintNode, TaintEdge> fullInput) {
        LinkedList<TaintNode> outputs = new LinkedList<TaintNode>();

        for (TaintNode node : fullInput.getVertices()) {
            if (fullInput.getOutEdges(node) == null || fullInput.getOutEdges(node).isEmpty()) {
                outputs.add(node);
            }
        }

        if (outputs.isEmpty()) {
            LinkedList<TaintEdge> edges = new LinkedList<TaintEdge>(fullInput.getEdges());
            Collections.sort(edges);

            outputs.add(fullInput.getDest(edges.getLast()));
        }

        return outputs;
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
