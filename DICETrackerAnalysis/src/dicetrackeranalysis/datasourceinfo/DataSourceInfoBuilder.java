/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dicetrackeranalysis.datasourceinfo;

import dicetrackeranalysis.graphhandling.AnalysisMainWindow;
import dicetrackeranalysis.graphhandling.EdgeFilter;
import dicetrackeranalysis.graphhandling.FilterByIsUniqueEdge;
import dicetrackeranalysis.graphhandling.RequestCounterURIPair;
import dicetrackeranalysis.graphhandling.TaintEdge;
import dicetrackeranalysis.graphhandling.TaintIDPropagationPair;
import dicetrackeranalysis.graphhandling.TaintNode;
import dicetrackeranalysis.graphhandling.TaintedField;
import dicetrackeranalysis.graphhandling.TaintedObject;
import dicetrackeranalysis.graphhandling.TargetObject;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.Graph;
import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import javax.swing.ProgressMonitor;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author lee
 */
public class DataSourceInfoBuilder {
    public LinkedList<DataSourceInfo> dataSourceInfoList;

    public DataSourceInfoBuilder(File input) {
        dataSourceInfoList = new LinkedList<DataSourceInfo>();
        fillDataSourceInfoList(input);
    }

    private void fillDataSourceInfoList(File input) {
        dataSourceInfoList.clear();

        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(input);

            Element docRoot = doc.getDocumentElement();
            NodeList childNodes = docRoot.getChildNodes();

            // Iterate through taintlog records
            for (int i = 0; i < childNodes.getLength(); i++ ) {
                if (childNodes.item(i) instanceof Element) {
                    DataSourceInfo dataSourceInfo = null;
                    Element taintLogElem = (Element) childNodes.item(i);
                    if (taintLogElem.getNodeName().equals("databasesourceinfo")) {
                        dataSourceInfo = new DataBaseSourceInfo(taintLogElem.getAttribute("catalog"),
                                taintLogElem.getAttribute("table"),
                                taintLogElem.getAttribute("column"),
                                taintLogElem.getAttribute("variability"));
                    }
                    else if (taintLogElem.getNodeName().equals("requestsourceinfo")) {
                        dataSourceInfo = new RequestSourceInfo(taintLogElem.getAttribute("uri"),
                                taintLogElem.getAttribute("parameter"),
                                taintLogElem.getAttribute("variability"));
                    }

                    if (dataSourceInfo != null)
                        dataSourceInfoList.add(dataSourceInfo);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public LinkedList<DataSourceInfo> getDataSourceInfoList() {
        return this.dataSourceInfoList;
    }

    public boolean checkTaintRecordIsRandom(String taintRecord) {
        for (DataSourceInfo infoItem : dataSourceInfoList) {
            if (infoItem.isRandom() && infoItem.match(taintRecord))
                return true;
        }
        return false;
    }

    public boolean checkTaintRecordMatchesVariability(String taintRecord, String variability) {
        for (DataSourceInfo infoItem : dataSourceInfoList) {
            if (infoItem.matchesVariability(variability) && infoItem.match(taintRecord))
                return true;
        }
        return false;
    }
    

}
