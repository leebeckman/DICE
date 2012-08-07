/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datamanagement;

import java.io.File;
import java.util.LinkedList;
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
                                taintLogElem.getAttribute("inttracking"));
                    }
                    else if (taintLogElem.getNodeName().equals("requestsourceinfo")) {
                        dataSourceInfo = new RequestSourceInfo(taintLogElem.getAttribute("uri"),
                                taintLogElem.getAttribute("parameter"),
                                taintLogElem.getAttribute("inttracking"));
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

    public DataSourceInfo getMatchingInfo(String catalog, String table, String column) {
        for (DataSourceInfo infoItem : dataSourceInfoList) {
            if (infoItem instanceof DataBaseSourceInfo) {
            	if (((DataBaseSourceInfo)infoItem).match(catalog, table, column)) {
            		return infoItem;
            	}
            }
            else if (infoItem instanceof RequestSourceInfo) {
            	
            }
        }
        
        return null;
    }
    
    public DataSourceInfo getMatchingInfo(String uri, String parameter) {
        for (DataSourceInfo infoItem : dataSourceInfoList) {
            if (infoItem instanceof RequestSourceInfo) {
            	if (((RequestSourceInfo)infoItem).match(uri, parameter)) {
            		return infoItem;
            	}
            }
        }
        
        return null;
    }
    
    public DataSourceInfo getMatchingInfo(String uriParameterString) {
    	String parameter = uriParameterString.substring(uriParameterString.lastIndexOf(":") + 1);
    	String uri = uriParameterString.substring(4, uriParameterString.lastIndexOf(":"));
        for (DataSourceInfo infoItem : dataSourceInfoList) {
            if (infoItem instanceof RequestSourceInfo) {
            	if (((RequestSourceInfo)infoItem).match(uri, parameter)) {
            		return infoItem;
            	}
            }
        }
        
        return null;
    }
    

}
