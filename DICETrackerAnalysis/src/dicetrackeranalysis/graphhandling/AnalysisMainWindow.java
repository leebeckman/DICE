/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AnalysisMainWindow.java
 *
 * Created on 11-Mar-2012, 10:25:14 PM
 */

package dicetrackeranalysis.graphhandling;

import com.sun.java.swing.plaf.gtk.GTKLookAndFeel;
import dicetrackeranalysis.datasourceinfo.DataSourceInfoBuilder;
import dicetrackeranalysis.graphanalysis.AccessPathAnalysis;
import dicetrackeranalysis.graphanalysis.PartitionSimulator;
import dicetrackeranalysis.graphanalysis.PostcompAnalysis;
import dicetrackeranalysis.graphanalysis.PrecompAnalysis;
import dicetrackeranalysis.graphanalysis.StaticStateAnalysis;
import dicetrackeranalysis.graphanalysis.UselessCommAnalysis;
import dicetrackeranalysis.graphanalysis.UserstateAnalysis;
import edu.uci.ics.jung.algorithms.layout.ISOMLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.RenderContext.DirectedEdgeArrowPredicate;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Paint;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.text.DefaultCaret;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import org.apache.commons.collections15.Transformer;

/**
 *
 * @author lee
 */
public class AnalysisMainWindow extends javax.swing.JFrame {

    private DataSourceInfoBuilder dsib;
    private DefaultMutableTreeNode mainTaintIDsRoot;
    private DefaultMutableTreeNode taintIDsRoot;
    private DefaultMutableTreeNode selectedTaintNode;
    public static JFrame mainWindow;
    private GraphBuilder mainGraphBuilder;

    private String graphFileName;

    HashMap<String, GraphBuilder> tabToBuilderMap;
    HashMap<String, VisualizationViewer<TaintNode, TaintEdge>> tabToViewerMap;
    HashMap<String, JPanel> tabToViewPanelMap;
    HashMap<String, JButton> tabToDetailsButtonMap;
    HashMap<String, Graph<TaintNode, TaintEdge>> tabToGraphMap;

    private boolean holdTaintIDSelections;
    private HashSet<DefaultMutableTreeNode> heldTaintIDSelections;

    private HashSet<TaintNode> selectionFilterNodes;
    private HashSet<TaintNode> connectBlockedNodes;

    private HashSet<TaintNode> inputNodes;
    private HashSet<TaintNode> outputNodes;

//    private Object[] selectedTaintPath;

    private enum GraphMode {
        MULTIMODE,
        MULTILIGHTMODE,
        SIMPLEMODE
    }
    private GraphMode graphMode = GraphMode.MULTILIGHTMODE;

    /** Creates new form AnalysisMainWindow */
    public AnalysisMainWindow() {
        tabToBuilderMap = new HashMap<String, GraphBuilder>();
        tabToViewerMap = new HashMap<String, VisualizationViewer<TaintNode, TaintEdge>>();
        tabToViewPanelMap = new HashMap<String, JPanel>();
        tabToDetailsButtonMap = new HashMap<String, JButton>();
        tabToGraphMap = new HashMap<String, Graph<TaintNode, TaintEdge>>();
        heldTaintIDSelections = new HashSet<DefaultMutableTreeNode>();
        selectionFilterNodes = new HashSet<TaintNode>();
        connectBlockedNodes = new HashSet<TaintNode>();

        inputNodes = new HashSet<TaintNode>();
        outputNodes = new HashSet<TaintNode>();

        try {
            UIManager.setLookAndFeel(new GTKLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(AnalysisMainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        initComponents();

        taintIDsRoot = new DefaultMutableTreeNode("All");
        taintIDTree.setModel(new DefaultTreeModel(taintIDsRoot));
        
        requestCounters.removeAllItems();
        jungViewPanel.setLayout(new FlowLayout());
        DefaultCaret caret = (DefaultCaret)analysisText.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        analysisText.setTabSize(3);
        analysisText.setLineWrap(true);
        Thread memMon = new Thread(new MemMonitor(memBar));
        memMon.start();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        loadTracking = new javax.swing.JButton();
        fileNameField = new javax.swing.JTextField();
        quickLoadButton = new javax.swing.JButton();
        sourceFileName = new javax.swing.JTextField();
        loadSource = new javax.swing.JButton();
        tabView = new javax.swing.JTabbedPane();
        jungViewPanel = new javax.swing.JPanel();
        analysisPanel = new javax.swing.JPanel();
        analysisScroller = new javax.swing.JScrollPane();
        analysisText = new javax.swing.JTextArea();
        analysisClearButton = new javax.swing.JButton();
        screenCapButton = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        analysisButtonsPanel = new javax.swing.JPanel();
        staticStateAnalyzeButton = new javax.swing.JButton();
        resetAnalysisButton = new javax.swing.JButton();
        cachingAnalysisButton = new javax.swing.JButton();
        aprAnalysisButton = new javax.swing.JButton();
        postcomputationAnalyzeButton = new javax.swing.JButton();
        partitionButton = new javax.swing.JButton();
        resetPartitionsButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        userStateAnalysisButton = new javax.swing.JButton();
        fullOutputButton = new javax.swing.JButton();
        uselessCommButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        multiGraphButton = new javax.swing.JButton();
        multiLightGraphButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        requestCounters = new javax.swing.JComboBox();
        noSBCheckbox = new javax.swing.JCheckBox();
        hideCheckBox = new javax.swing.JCheckBox();
        showInputsButton = new javax.swing.JButton();
        noGCCheckBox = new javax.swing.JCheckBox();
        showOnlySelectedButton = new javax.swing.JButton();
        showConnectedButton = new javax.swing.JButton();
        resetSelectionFilterButton = new javax.swing.JButton();
        blockButton = new javax.swing.JButton();
        fConnectedButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        highlightField = new javax.swing.JTextField();
        highlightButton = new javax.swing.JButton();
        showOutputButton = new javax.swing.JButton();
        showPathButton = new javax.swing.JButton();
        hideUnusedBox = new javax.swing.JCheckBox();
        linkColoredButton = new javax.swing.JButton();
        setInputsButton = new javax.swing.JButton();
        setOutputsButton = new javax.swing.JButton();
        linkIOButton = new javax.swing.JButton();
        showOnlyButton = new javax.swing.JButton();
        taintFlowConnectedButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        edgeID = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        getEdgeDataButton = new javax.swing.JButton();
        getForwardGraphButton = new javax.swing.JButton();
        treePanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taintIDTree = new javax.swing.JTree();
        deepTaintCheckBox = new javax.swing.JCheckBox();
        holdTaintIDButton = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        memBar = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        loadTracking.setText("Load Tracking Log");
        loadTracking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadTrackingActionPerformed(evt);
            }
        });

        fileNameField.setEditable(false);

        quickLoadButton.setText("Quick Load");
        quickLoadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quickLoadButtonActionPerformed(evt);
            }
        });

        sourceFileName.setEditable(false);

        loadSource.setText("Load Source Info");
        loadSource.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadSourceActionPerformed(evt);
            }
        });

        tabView.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tabViewStateChanged(evt);
            }
        });

        jungViewPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Dataflow View"));

        javax.swing.GroupLayout jungViewPanelLayout = new javax.swing.GroupLayout(jungViewPanel);
        jungViewPanel.setLayout(jungViewPanelLayout);
        jungViewPanelLayout.setHorizontalGroup(
            jungViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1017, Short.MAX_VALUE)
        );
        jungViewPanelLayout.setVerticalGroup(
            jungViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 701, Short.MAX_VALUE)
        );

        tabView.addTab("Graph", jungViewPanel);

        analysisPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Analysis"));

        analysisText.setColumns(20);
        analysisText.setRows(5);
        analysisScroller.setViewportView(analysisText);

        analysisClearButton.setText("Clear");
        analysisClearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                analysisClearButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout analysisPanelLayout = new javax.swing.GroupLayout(analysisPanel);
        analysisPanel.setLayout(analysisPanelLayout);
        analysisPanelLayout.setHorizontalGroup(
            analysisPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, analysisPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(analysisClearButton))
            .addComponent(analysisScroller, javax.swing.GroupLayout.DEFAULT_SIZE, 1017, Short.MAX_VALUE)
        );
        analysisPanelLayout.setVerticalGroup(
            analysisPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, analysisPanelLayout.createSequentialGroup()
                .addComponent(analysisScroller, javax.swing.GroupLayout.DEFAULT_SIZE, 665, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(analysisClearButton))
        );

        tabView.addTab("Analysis", analysisPanel);

        screenCapButton.setText("Capture");
        screenCapButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                screenCapButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(loadSource, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(loadTracking, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sourceFileName, javax.swing.GroupLayout.DEFAULT_SIZE, 786, Short.MAX_VALUE)
                            .addComponent(fileNameField, javax.swing.GroupLayout.DEFAULT_SIZE, 786, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(screenCapButton)
                            .addComponent(quickLoadButton)))
                    .addComponent(tabView, javax.swing.GroupLayout.DEFAULT_SIZE, 1037, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loadTracking)
                    .addComponent(fileNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(quickLoadButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loadSource)
                    .addComponent(sourceFileName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(screenCapButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabView, javax.swing.GroupLayout.DEFAULT_SIZE, 766, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("View", jPanel4);

        analysisButtonsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Analyze"));

        staticStateAnalyzeButton.setText("Static State");
        staticStateAnalyzeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                staticStateAnalyzeButtonActionPerformed(evt);
            }
        });

        resetAnalysisButton.setText("Reset");
        resetAnalysisButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetAnalysisButtonActionPerformed(evt);
            }
        });

        cachingAnalysisButton.setText("Precomputation");
        cachingAnalysisButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cachingAnalysisButtonActionPerformed(evt);
            }
        });

        aprAnalysisButton.setText("APR");
        aprAnalysisButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aprAnalysisButtonActionPerformed(evt);
            }
        });

        postcomputationAnalyzeButton.setText("Postcomputation");
        postcomputationAnalyzeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                postcomputationAnalyzeButtonActionPerformed(evt);
            }
        });

        partitionButton.setText("Partition");
        partitionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                partitionButtonActionPerformed(evt);
            }
        });

        resetPartitionsButton.setText("Reset Partitions");
        resetPartitionsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetPartitionsButtonActionPerformed(evt);
            }
        });

        jLabel3.setText("Access Path Refactoring:");

        userStateAnalysisButton.setText("User State");
        userStateAnalysisButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userStateAnalysisButtonActionPerformed(evt);
            }
        });

        fullOutputButton.setText("Full Output");
        fullOutputButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fullOutputButtonActionPerformed(evt);
            }
        });

        uselessCommButton.setText("Useless Comm");
        uselessCommButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uselessCommButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout analysisButtonsPanelLayout = new javax.swing.GroupLayout(analysisButtonsPanel);
        analysisButtonsPanel.setLayout(analysisButtonsPanelLayout);
        analysisButtonsPanelLayout.setHorizontalGroup(
            analysisButtonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(analysisButtonsPanelLayout.createSequentialGroup()
                .addGroup(analysisButtonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(analysisButtonsPanelLayout.createSequentialGroup()
                        .addComponent(resetAnalysisButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(staticStateAnalyzeButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(userStateAnalysisButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(postcomputationAnalyzeButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cachingAnalysisButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fullOutputButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(uselessCommButton))
                    .addComponent(jLabel3)
                    .addGroup(analysisButtonsPanelLayout.createSequentialGroup()
                        .addComponent(partitionButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(aprAnalysisButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(resetPartitionsButton)))
                .addContainerGap(290, Short.MAX_VALUE))
        );
        analysisButtonsPanelLayout.setVerticalGroup(
            analysisButtonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(analysisButtonsPanelLayout.createSequentialGroup()
                .addGroup(analysisButtonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(staticStateAnalyzeButton)
                    .addComponent(resetAnalysisButton)
                    .addComponent(userStateAnalysisButton)
                    .addComponent(postcomputationAnalyzeButton)
                    .addComponent(cachingAnalysisButton)
                    .addComponent(fullOutputButton)
                    .addComponent(uselessCommButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(analysisButtonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(partitionButton)
                    .addComponent(aprAnalysisButton)
                    .addComponent(resetPartitionsButton))
                .addGap(36, 36, 36))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Graph View / Request Filter"));

        multiGraphButton.setText("Multi");
        multiGraphButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                multiGraphButtonActionPerformed(evt);
            }
        });

        multiLightGraphButton.setText("Multi-Light");
        multiLightGraphButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                multiLightGraphButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Request:");

        requestCounters.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        requestCounters.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                requestCountersActionPerformed(evt);
            }
        });

        noSBCheckbox.setText("No SB");
        noSBCheckbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noSBCheckboxActionPerformed(evt);
            }
        });

        hideCheckBox.setText("Hide");

        showInputsButton.setText("Show In");
        showInputsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showInputsButtonActionPerformed(evt);
            }
        });

        noGCCheckBox.setText("No GC");
        noGCCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noGCCheckBoxActionPerformed(evt);
            }
        });

        showOnlySelectedButton.setText("Selected");
        showOnlySelectedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showOnlySelectedButtonActionPerformed(evt);
            }
        });

        showConnectedButton.setText("Connected");
        showConnectedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showConnectedButtonActionPerformed(evt);
            }
        });

        resetSelectionFilterButton.setText("Reset");
        resetSelectionFilterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetSelectionFilterButtonActionPerformed(evt);
            }
        });

        blockButton.setText("Block");
        blockButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blockButtonActionPerformed(evt);
            }
        });

        fConnectedButton.setText("F-Connected");
        fConnectedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fConnectedButtonActionPerformed(evt);
            }
        });

        jLabel4.setText("Highlight:");

        highlightButton.setText("Highlight");
        highlightButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                highlightButtonActionPerformed(evt);
            }
        });

        showOutputButton.setText("Show Out");
        showOutputButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showOutputButtonActionPerformed(evt);
            }
        });

        showPathButton.setText("Show Path");
        showPathButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showPathButtonActionPerformed(evt);
            }
        });

        hideUnusedBox.setText("Hide Unused");
        hideUnusedBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hideUnusedBoxActionPerformed(evt);
            }
        });

        linkColoredButton.setText("Link Colored");
        linkColoredButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linkColoredButtonActionPerformed(evt);
            }
        });

        setInputsButton.setText("Set Inputs");
        setInputsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setInputsButtonActionPerformed(evt);
            }
        });

        setOutputsButton.setText("Set Outputs");
        setOutputsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setOutputsButtonActionPerformed(evt);
            }
        });

        linkIOButton.setText("Link IO");
        linkIOButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linkIOButtonActionPerformed(evt);
            }
        });

        showOnlyButton.setText("Show Only");
        showOnlyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showOnlyButtonActionPerformed(evt);
            }
        });

        taintFlowConnectedButton.setText("Taint Flow Connected");
        taintFlowConnectedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                taintFlowConnectedButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(highlightField, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(highlightButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(showOnlyButton))
                            .addComponent(requestCounters, 0, 910, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(multiGraphButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(multiLightGraphButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(noGCCheckBox)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(noSBCheckbox)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(hideCheckBox)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(hideUnusedBox))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(showInputsButton)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(showOnlySelectedButton))
                                    .addComponent(showOutputButton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(fConnectedButton)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(taintFlowConnectedButton))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(showConnectedButton)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(blockButton)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(resetSelectionFilterButton)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(setInputsButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(setOutputsButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(linkIOButton))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(showPathButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(linkColoredButton)))
                        .addGap(261, 261, 261)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(multiGraphButton)
                    .addComponent(multiLightGraphButton)
                    .addComponent(noGCCheckBox)
                    .addComponent(noSBCheckbox)
                    .addComponent(hideCheckBox)
                    .addComponent(hideUnusedBox)
                    .addComponent(showPathButton)
                    .addComponent(linkColoredButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(showInputsButton)
                    .addComponent(showOnlySelectedButton)
                    .addComponent(showConnectedButton)
                    .addComponent(blockButton)
                    .addComponent(resetSelectionFilterButton)
                    .addComponent(setInputsButton)
                    .addComponent(setOutputsButton)
                    .addComponent(linkIOButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fConnectedButton)
                    .addComponent(showOutputButton)
                    .addComponent(taintFlowConnectedButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(highlightField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(highlightButton)
                    .addComponent(showOnlyButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(requestCounters, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Edge Data"));

        edgeID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edgeIDActionPerformed(evt);
            }
        });

        jLabel2.setText("Edge ID:");

        getEdgeDataButton.setText("Get Data");
        getEdgeDataButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getEdgeDataButtonActionPerformed(evt);
            }
        });

        getForwardGraphButton.setText("Get Forward Graph");
        getForwardGraphButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getForwardGraphButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(edgeID, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(getEdgeDataButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(getForwardGraphButton)
                .addContainerGap(609, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(edgeID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel2)
                .addComponent(getEdgeDataButton)
                .addComponent(getForwardGraphButton))
        );

        treePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("TaintID Filter"));

        jScrollPane1.setMaximumSize(new java.awt.Dimension(32767, 500));

        taintIDTree.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                taintIDTreeValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(taintIDTree);

        deepTaintCheckBox.setText("Deep");
        deepTaintCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deepTaintCheckBoxActionPerformed(evt);
            }
        });

        holdTaintIDButton.setText("Hold");
        holdTaintIDButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                holdTaintIDButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout treePanelLayout = new javax.swing.GroupLayout(treePanel);
        treePanel.setLayout(treePanelLayout);
        treePanelLayout.setHorizontalGroup(
            treePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(treePanelLayout.createSequentialGroup()
                .addComponent(deepTaintCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(holdTaintIDButton)
                .addGap(121, 121, 121))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1013, Short.MAX_VALUE)
        );
        treePanelLayout.setVerticalGroup(
            treePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(treePanelLayout.createSequentialGroup()
                .addGroup(treePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deepTaintCheckBox)
                    .addComponent(holdTaintIDButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Mem Usage"));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(memBar, javax.swing.GroupLayout.DEFAULT_SIZE, 989, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(memBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(treePanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(analysisButtonsPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(analysisButtonsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(treePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Control", jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addRequestCounter(String requestCounter) {
        this.requestCounters.addItem(requestCounter);
    }

//    private void addTaintID(String taintID) {
//        this.taintIDs.addItem(taintID);
//    }

    private void resetRequestCounters() {
        requestCounters.removeAllItems();
        requestCounters.revalidate();
        requestCounters.repaint();
    }

    private void resetTaintIDs() {
        taintIDsRoot.removeAllChildren();
        taintIDTree.removeAll();
        taintIDTree.revalidate();
        taintIDTree.repaint();
    }

    private void loadTrackingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadTrackingActionPerformed
        JFileChooser chooser = new JFileChooser();
        int returnVal = chooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            resetAnalysisGraphs();
            File file = chooser.getSelectedFile();
            graphFileName = file.getName();
            loadTrackingFile(file);

        } else {
            System.out.println("File access cancelled by user.");
        }
    }//GEN-LAST:event_loadTrackingActionPerformed

    private void loadTrackingFile(File file) {
        tabView.setSelectedIndex(0);
        fileNameField.setText(file.getAbsolutePath());

        GraphBuilder gb = new GraphBuilder(file);

        regenerateGraphFilters(gb, true);

        Graph<TaintNode, TaintEdge> graph = gb.getLightMultiGraph();
        VisualizationViewer<TaintNode, TaintEdge> vv = getVisualizationViewer(graph, 0);

        mainGraphBuilder = gb;

        tabToBuilderMap.clear();
        tabToViewerMap.clear();
        tabToViewPanelMap.clear();
        tabToGraphMap.clear();
        
        tabToBuilderMap.put("Graph", gb);
        tabToViewPanelMap.put("Graph", jungViewPanel);
        tabToViewerMap.put("Graph", vv);

        redrawGraph();
//        jungViewPanel.removeAll();
//        jungViewPanel.add(vv);
//        jungViewPanel.validate();
//        jungViewPanel.repaint();
    }

    public void regenerateGraphFilters(GraphBuilder gb) {
        regenerateGraphFilters(gb, false);
    }

    public void regenerateGraphFilters(GraphBuilder gb, boolean main) {
        resetRequestCounters();
        resetTaintIDs();
        requestCounters.addItem(null);
        for (RequestCounterURIPair item : gb.getRequestCounters().values()) {
            requestCounters.addItem(item);
        }

        LinkedHashMap<String, String> taintIDList = gb.getTaintIDsWithTypes();
        HashMap<String, DefaultMutableTreeNode> propagationMap = new HashMap<String, DefaultMutableTreeNode>();
        for (TaintIDPropagationPair propagation : gb.getTaintIDPropagations()) {
            // Get all taintIDs IN ORDER
            // If you haven't seen one yet, add it at root level
            /*
             * Have a map of id -> treenode
             * go through propagations and add appropriately
             */
            DefaultMutableTreeNode sourceNode = propagationMap.get(propagation.getSourceID());
            DefaultMutableTreeNode destNode = propagationMap.get(propagation.getDestID());

            if (sourceNode == null) {
                sourceNode = new DefaultMutableTreeNode(new TaintIDTreeNode(propagation.getSourceID(), propagation.getSourceValue(), propagation.getSourceType()));
                propagationMap.put(propagation.getSourceID(), sourceNode);
                taintIDsRoot.add(sourceNode);
                taintIDList.remove(propagation.getSourceID());
                if (destNode == null) {
                    destNode = new DefaultMutableTreeNode(new TaintIDTreeNode(propagation.getDestID(), propagation.getDestValue(), propagation.getDestType()));
                    propagationMap.put(propagation.getDestID(), destNode);
                    sourceNode.add(destNode);
                    taintIDList.remove(propagation.getDestID());
                }
            }
            else if (destNode == null) {
                destNode = new DefaultMutableTreeNode(new TaintIDTreeNode(propagation.getDestID(), propagation.getDestValue(), propagation.getDestType()));
                propagationMap.put(propagation.getDestID(), destNode);
                sourceNode.add(destNode);
                taintIDList.remove(propagation.getDestID());
            }
            else if (propagation.isPostProcessed()) { // Both nodes have been added to tree, this is probably one of the post-processed composition propagations
                DefaultMutableTreeNode newDestNode = (DefaultMutableTreeNode)destNode.clone();
                cloneTree(destNode, newDestNode);

                sourceNode.add(newDestNode);
            }
        }
        for (Entry<String, String> taintID : taintIDList.entrySet()) {
            if (taintID.getKey() != null && !taintID.getKey().isEmpty()) {
                DefaultMutableTreeNode sourceNode = new DefaultMutableTreeNode(new TaintIDTreeNode(taintID.getKey(), null, taintID.getValue()));
                taintIDsRoot.add(sourceNode);
            }
        }

        ((DefaultTreeModel)taintIDTree.getModel()).setRoot(taintIDsRoot);

        if (main) {
            mainTaintIDsRoot = (DefaultMutableTreeNode)taintIDsRoot.clone();
            cloneTree(taintIDsRoot, mainTaintIDsRoot);
        }
    }

    private void cloneTree(DefaultMutableTreeNode sourceRoot, DefaultMutableTreeNode destRoot) {
        for (int i = 0; i < sourceRoot.getChildCount(); i++) {
            DefaultMutableTreeNode sourceChild = (DefaultMutableTreeNode)sourceRoot.getChildAt(i);
            DefaultMutableTreeNode destChild = (DefaultMutableTreeNode)sourceChild.clone();
            destRoot.add(destChild);

            cloneTree(sourceChild, destChild);
        }
    }

    public void addAnalysisGraphBuilder(GraphBuilder analysisGraphBuilder, String tabName, String analysisText) {
        JPanel analysisGraphPanel = new javax.swing.JPanel();
        analysisGraphPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Dataflow Analysis View"));
        tabView.addTab(tabName, analysisGraphPanel);
        analysisGraphPanel.setLayout(new FlowLayout());

        tabToViewPanelMap.put(tabName, analysisGraphPanel);
        tabToBuilderMap.put(tabName, analysisGraphBuilder);

        VisualizationViewer<TaintNode, TaintEdge> newViewer = getVisualizationViewer(analysisGraphBuilder.getLightMultiGraph(), 100);
        if (newViewer != null) {
            tabToViewerMap.put(tabName, newViewer);
//            analysisGraphPanel.removeAll();

            JButton detailsButton = new JButton("Details");
            final String analysisTextArg = analysisText;
            detailsButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    TextFrame.showText(analysisTextArg);
                }
            });
            tabToDetailsButtonMap.put(tabName, detailsButton);
//            analysisGraphPanel.add(detailsButton, FlowLayout.LEFT);
//            analysisGraphPanel.add(newViewer);

//            analysisGraphPanel.validate();
//            analysisGraphPanel.repaint();
        }
    }

    private void resetAnalysisGraphs() {
        if (mainGraphBuilder != null) {
            for (TaintNode node : mainGraphBuilder.getMultiGraph().getVertices()) {
                node.colorValue = 0;
            }
        }
        while (tabView.getTabCount() > 2) {
            tabView.removeTabAt(2);
        }
    }
    
    private final Color[] palette = {Color.BLUE, Color.GREEN, Color.RED, Color.ORANGE, Color.BLACK,
                                            Color.CYAN, Color.GRAY, Color.MAGENTA, Color.PINK, Color.WHITE, Color.YELLOW};

    private VisualizationViewer<TaintNode, TaintEdge> getVisualizationViewer(Graph<TaintNode, TaintEdge> g, int extraRoom) {
        if (g.getEdgeCount() < 1)
            return null;

//        System.out.println("GET VIS VIEWER " + g.getVertexCount());
        
        DirectedEdgeArrowPredicate<TaintNode, TaintEdge> arrowPred = new DirectedEdgeArrowPredicate<TaintNode, TaintEdge>();

        Layout<TaintNode, TaintEdge> layout = new ISOMLayout<TaintNode, TaintEdge>(g);
        layout.setSize(new Dimension(jungViewPanel.getWidth(), jungViewPanel.getHeight() - extraRoom));

        VisualizationViewer<TaintNode, TaintEdge> vs = new VisualizationViewer<TaintNode, TaintEdge>(layout);
        vs.setPreferredSize(new Dimension(jungViewPanel.getWidth(), jungViewPanel.getHeight() - extraRoom));
        vs.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
        vs.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
//                vs.getRenderContext().setEdgeArrowTransformer(edgeArrowTransformer);
        vs.getRenderContext().setEdgeArrowPredicate(arrowPred);
        Transformer<TaintNode, Paint> vertexPaint = new Transformer<TaintNode, Paint>() {
            public Paint transform(TaintNode node) {
                return palette[node.colorValue];
            }
        };
        Transformer<TaintEdge, Paint> edgePaint = new Transformer<TaintEdge, Paint>() {
            public Paint transform(TaintEdge node) {
                return palette[node.colorValue];
            }
        };
        vs.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
        vs.getRenderContext().setEdgeDrawPaintTransformer(edgePaint);

        DefaultModalGraphMouse gm = new DefaultModalGraphMouse();
        gm.setMode(ModalGraphMouse.Mode.TRANSFORMING);
        vs.addKeyListener(gm.getModeKeyListener());
        vs.setGraphMouse(gm);

        return vs;
    }

    private void redrawGraph() {
        if (tabView.getTitleAt(tabView.getSelectedIndex()).equals("Analysis"))
            return;

        GraphBuilder gb = tabToBuilderMap.get(tabView.getTitleAt(tabView.getSelectedIndex()));
        JPanel viewPanel = tabToViewPanelMap.get(tabView.getTitleAt(tabView.getSelectedIndex()));

        if (hideCheckBox.isSelected()) {
            viewPanel.removeAll();
            return;
        }

        if (gb == null)
            return;

        LinkedList<EdgeFilter> filters = new LinkedList<EdgeFilter>();
        Object selectedReq = requestCounters.getSelectedItem();
        if (selectedReq != null && !selectedReq.toString().isEmpty()) {
            if (selectedReq instanceof RequestCounterURIPair) {
                filters.add(new FilterByRequestCounter(((RequestCounterURIPair)selectedReq).getRequestCounter()));
            }
        }
        if (selectedTaintNode != null && selectedTaintNode.getUserObject() instanceof TaintIDTreeNode) {
            HashSet<String> taintIDs = new HashSet<String>();
            if (holdTaintIDSelections) {
                for (DefaultMutableTreeNode heldTaintNode : heldTaintIDSelections) {
                    TaintIDTreeNode taintNode = (TaintIDTreeNode) heldTaintNode.getUserObject();
                    if (deepTaintCheckBox.isSelected()) {
                        taintIDs.add(taintNode.getTaintID());
                        Enumeration<DefaultMutableTreeNode> childNodes = heldTaintNode.depthFirstEnumeration();
                        while (childNodes.hasMoreElements()) {
                            DefaultMutableTreeNode childNode = childNodes.nextElement();
                            taintIDs.add(((TaintIDTreeNode)childNode.getUserObject()).getTaintID());
                        }
                    }
                    else {
                        taintIDs.add(taintNode.getTaintID());
                    }
                }
            }
            else {
                TaintIDTreeNode taintNode = (TaintIDTreeNode) selectedTaintNode.getUserObject();
                if (deepTaintCheckBox.isSelected()) {
                    taintIDs.add(taintNode.getTaintID());
                    Enumeration<DefaultMutableTreeNode> childNodes = selectedTaintNode.depthFirstEnumeration();
                    while (childNodes.hasMoreElements()) {
                        DefaultMutableTreeNode childNode = childNodes.nextElement();
                        taintIDs.add(((TaintIDTreeNode)childNode.getUserObject()).getTaintID());
                    }
                }
                else {
                    taintIDs.add(taintNode.getTaintID());
                }
            }
            filters.add(new FilterByTaintID(taintIDs));
            if (hideUnusedBox.isSelected())
                filters.add(new FilterUnused(taintIDs));
        }
        filters.add(new FilterAllUnused());

        JButton detailsButton = tabToDetailsButtonMap.get(tabView.getTitleAt(tabView.getSelectedIndex()));
        int offset = 0;
        if (detailsButton != null)
            offset = 60;

        Graph<TaintNode, TaintEdge> graph = null;
        if (graphMode == GraphMode.MULTILIGHTMODE) {
//            System.out.println("MULTILIGHTMODE");
            graph = gb.getLightMultiGraph(filters);
        }
        else if(graphMode == GraphMode.MULTIMODE) {
//            System.out.println("MULTIMODE");
            graph = gb.getMultiGraph(filters);
        }
        else if (graphMode == GraphMode.SIMPLEMODE) {
//            System.out.println("SIMPLEMODE");
            graph = gb.getGraph(filters);
        }

//        for (TaintEdge edge : graph.getEdges()) {
//            if (edge.getCallingNode().toString().contains("fillUser") && edge.getCalledNode().toString().contains("fillUser")) {
//                System.out.println("DRAWING fillUser EDGE: " + edge + " from " + graph.getSource(edge) + " to " + graph.getDest(edge));
//            }
//        }
//        System.out.println("REDRAWING GRAPH PRE: " + gb.getLightMultiGraph().getVertexCount() + " next: " + graph.getVertexCount());
//        for (EdgeFilter filt : filters) {
//            System.out.println("\tFILTER: " + filt);
//        }
        
        if (noSBCheckbox.isSelected()) {
            LinkedList<TaintNode> nodes = new LinkedList<TaintNode>(graph.getVertices());
            for (TaintNode node : nodes) {
                if (node.toString().contains("StringBuilder:toString") ||
                        node.toString().contains("StringBuilder:append"))
                    graph.removeVertex(node);
            }
        }
        if (noGCCheckBox.isSelected()) {
            LinkedList<TaintNode> nodes = new LinkedList<TaintNode>(graph.getVertices());
            for (TaintNode node : nodes) {
                if (node.toString().contains("String:getChars"))
                    graph.removeVertex(node);
            }
        }
        if (!selectionFilterNodes.isEmpty()) {
            LinkedList<TaintNode> nodes = new LinkedList<TaintNode>(graph.getVertices());
            for (TaintNode node : nodes) {
                if (!selectionFilterNodes.contains(node))
                    graph.removeVertex(node);
//                else
//                    System.out.println("ALLOWING SELECTION NODE: " + node);
            }
        }

        tabToGraphMap.put(tabView.getTitleAt(tabView.getSelectedIndex()), graph);

        VisualizationViewer<TaintNode, TaintEdge> vv = getVisualizationViewer(graph, offset);
        tabToViewerMap.put(tabView.getTitleAt(tabView.getSelectedIndex()), vv);

        viewPanel.removeAll();

        if (vv != null)
            viewPanel.add(vv);
        if (detailsButton != null)
            viewPanel.add(detailsButton);
        
        viewPanel.validate();
        viewPanel.repaint();
    }

    private void redrawGraphNextConnected(boolean extend, boolean applyFilters, HashSet<TaintNode> selected) {
        if (tabView.getTitleAt(tabView.getSelectedIndex()).equals("Analysis"))
            return;

        GraphBuilder gb = tabToBuilderMap.get(tabView.getTitleAt(tabView.getSelectedIndex()));
        JPanel viewPanel = tabToViewPanelMap.get(tabView.getTitleAt(tabView.getSelectedIndex()));

        if (hideCheckBox.isSelected()) {
            viewPanel.removeAll();
            return;
        }

        if (gb == null)
            return;

        LinkedList<EdgeFilter> filters = new LinkedList<EdgeFilter>();
        Object selectedReq = requestCounters.getSelectedItem();
        if (selectedReq != null && !selectedReq.toString().isEmpty()) {
            if (selectedReq instanceof RequestCounterURIPair) {
                filters.add(new FilterByRequestCounter(((RequestCounterURIPair)selectedReq).getRequestCounter()));
            }
        }
        if (applyFilters) {
            if (selectedTaintNode != null && selectedTaintNode.getUserObject() instanceof TaintIDTreeNode) {
                HashSet<String> taintIDs = new HashSet<String>();
                if (holdTaintIDSelections) {
                    for (DefaultMutableTreeNode heldTaintNode : heldTaintIDSelections) {
                        TaintIDTreeNode taintNode = (TaintIDTreeNode) heldTaintNode.getUserObject();
                        if (deepTaintCheckBox.isSelected()) {
                            taintIDs.add(taintNode.getTaintID());
                            Enumeration<DefaultMutableTreeNode> childNodes = heldTaintNode.depthFirstEnumeration();
                            while (childNodes.hasMoreElements()) {
                                DefaultMutableTreeNode childNode = childNodes.nextElement();
                                taintIDs.add(((TaintIDTreeNode)childNode.getUserObject()).getTaintID());
                            }
                        }
                        else {
                            taintIDs.add(taintNode.getTaintID());
                        }
                    }
                }
                else {
                    TaintIDTreeNode taintNode = (TaintIDTreeNode) selectedTaintNode.getUserObject();
                    if (deepTaintCheckBox.isSelected()) {
                        taintIDs.add(taintNode.getTaintID());
                        Enumeration<DefaultMutableTreeNode> childNodes = selectedTaintNode.depthFirstEnumeration();
                        while (childNodes.hasMoreElements()) {
                            DefaultMutableTreeNode childNode = childNodes.nextElement();
                            taintIDs.add(((TaintIDTreeNode)childNode.getUserObject()).getTaintID());
                        }
                    }
                    else {
                        taintIDs.add(taintNode.getTaintID());
                    }
                }
                filters.add(new FilterByTaintID(taintIDs));
                if (hideUnusedBox.isSelected())
                    filters.add(new FilterUnused(taintIDs));
            }
        }
        filters.add(new FilterAllUnused());

        JButton detailsButton = tabToDetailsButtonMap.get(tabView.getTitleAt(tabView.getSelectedIndex()));
        int offset = 0;
        if (detailsButton != null)
            offset = 60;

        Graph<TaintNode, TaintEdge> graph = null;
        graph = gb.getLightMultiGraph(filters);

        if (noSBCheckbox.isSelected()) {
            LinkedList<TaintNode> nodes = new LinkedList<TaintNode>(graph.getVertices());
            for (TaintNode node : nodes) {
                if (node.toString().contains("StringBuilder:toString") ||
                        node.toString().contains("StringBuilder:append"))
                    graph.removeVertex(node);
            }
        }
        if (noGCCheckBox.isSelected()) {
            LinkedList<TaintNode> nodes = new LinkedList<TaintNode>(graph.getVertices());
            for (TaintNode node : nodes) {
                if (node.toString().contains("String:getChars"))
                    graph.removeVertex(node);
            }
        }

        if (!selectionFilterNodes.isEmpty()) {
            if (extend) {
                HashSet<TaintNode> toExtend = new HashSet<TaintNode>();
                for (TaintNode node : selectionFilterNodes) {
                    if (!connectBlockedNodes.contains(node)) {
                        if (selected != null && !selected.isEmpty()) {
                            if (selected.contains(node))
                                toExtend.addAll(graph.getNeighbors(node));
                        } else
                            toExtend.addAll(graph.getNeighbors(node));
                    }
                }
                selectionFilterNodes.addAll(toExtend);
            }

            LinkedList<TaintNode> nodes = new LinkedList<TaintNode>(graph.getVertices());
            for (TaintNode node : nodes) {
                if (!selectionFilterNodes.contains(node))
                    graph.removeVertex(node);
            }
        }

        VisualizationViewer<TaintNode, TaintEdge> vv = getVisualizationViewer(graph, offset);
        tabToViewerMap.put(tabView.getTitleAt(tabView.getSelectedIndex()), vv);
        
        viewPanel.removeAll();

        if (vv != null)
            viewPanel.add(vv);
        if (detailsButton != null)
            viewPanel.add(detailsButton);

        viewPanel.validate();
        viewPanel.repaint();
    }

    private void requestCountersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_requestCountersActionPerformed
        redrawGraph();
    }//GEN-LAST:event_requestCountersActionPerformed

    private void multiGraphButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_multiGraphButtonActionPerformed
        graphMode = GraphMode.MULTIMODE;
        redrawGraph();
    }//GEN-LAST:event_multiGraphButtonActionPerformed

    private void multiLightGraphButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_multiLightGraphButtonActionPerformed
        graphMode = GraphMode.MULTILIGHTMODE;
        redrawGraph();
    }//GEN-LAST:event_multiLightGraphButtonActionPerformed

    private void taintIDTreeValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_taintIDTreeValueChanged
        if (evt.getNewLeadSelectionPath() != null) {
            if (evt.getNewLeadSelectionPath().getLastPathComponent() instanceof DefaultMutableTreeNode) {
                selectedTaintNode = (DefaultMutableTreeNode) evt.getNewLeadSelectionPath().getLastPathComponent();
                if (holdTaintIDSelections) {
                    heldTaintIDSelections.add(selectedTaintNode);
                }
            }
            else {
                selectedTaintNode = null;
            }
        }

        redrawGraph();
    }//GEN-LAST:event_taintIDTreeValueChanged

    private void deepTaintCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deepTaintCheckBoxActionPerformed
        redrawGraph();
    }//GEN-LAST:event_deepTaintCheckBoxActionPerformed

    private void staticStateAnalyzeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_staticStateAnalyzeButtonActionPerformed
        resetAnalysisGraphs();
        StaticStateAnalysis analysis = new StaticStateAnalysis(mainGraphBuilder, this, mainTaintIDsRoot, analysisText);
        analysis.analyze();
    }//GEN-LAST:event_staticStateAnalyzeButtonActionPerformed

    private void resetAnalysisButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetAnalysisButtonActionPerformed
        resetAnalysisGraphs();
    }//GEN-LAST:event_resetAnalysisButtonActionPerformed

    private void loadSourceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadSourceActionPerformed
        JFileChooser chooser = new JFileChooser();
        int returnVal = chooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            
            loadSourceFile(file);
        } else {
            System.out.println("File access cancelled by user.");
        }
    }//GEN-LAST:event_loadSourceActionPerformed

    private void loadSourceFile(File file) {
        sourceFileName.setText(file.getAbsolutePath());

        dsib = new DataSourceInfoBuilder(file);
    }

    private void postcomputationAnalyzeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_postcomputationAnalyzeButtonActionPerformed
        resetAnalysisGraphs();
        PostcompAnalysis analysis = new PostcompAnalysis(mainGraphBuilder, dsib, analysisText, mainTaintIDsRoot, this);
        analysis.analyze();
    }//GEN-LAST:event_postcomputationAnalyzeButtonActionPerformed

    private void cachingAnalysisButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cachingAnalysisButtonActionPerformed
        resetAnalysisGraphs();
        PrecompAnalysis analysis = new PrecompAnalysis(mainGraphBuilder, dsib, analysisText, this);
        analysis.analyze();
    }//GEN-LAST:event_cachingAnalysisButtonActionPerformed

    private void analysisClearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_analysisClearButtonActionPerformed
        analysisText.setText("");
    }//GEN-LAST:event_analysisClearButtonActionPerformed

    private void quickLoadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quickLoadButtonActionPerformed
        File trackerFile = new File("/home/lee/DICE/DATA_jgossip_caching_ShowForum_5.xml");
        File sourceFile = new File("/home/lee/DICE/jgossipDataInfo.xml");

        loadTrackingFile(trackerFile);
        loadSourceFile(sourceFile);
    }//GEN-LAST:event_quickLoadButtonActionPerformed

    private void aprAnalysisButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aprAnalysisButtonActionPerformed
        resetAnalysisGraphs();
        AccessPathAnalysis analysis = new AccessPathAnalysis(mainGraphBuilder, dsib, analysisText, mainTaintIDsRoot, this, "edu.rice");
        analysis.analyze();
    }//GEN-LAST:event_aprAnalysisButtonActionPerformed

    private void tabViewStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tabViewStateChanged
        GraphBuilder changedBuilder = tabToBuilderMap.get(tabView.getTitleAt(tabView.getSelectedIndex()));
        if (changedBuilder != null)
            regenerateGraphFilters(changedBuilder);
//        redrawGraph();
    }//GEN-LAST:event_tabViewStateChanged

    private void holdTaintIDButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_holdTaintIDButtonActionPerformed
        heldTaintIDSelections.clear();
        holdTaintIDSelections = holdTaintIDButton.isSelected();
        redrawGraph();
    }//GEN-LAST:event_holdTaintIDButtonActionPerformed

    private void userStateAnalysisButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userStateAnalysisButtonActionPerformed
        resetAnalysisGraphs();
        UserstateAnalysis analysis = new UserstateAnalysis(mainGraphBuilder, mainTaintIDsRoot, this, analysisText);
        analysis.analyze();
    }//GEN-LAST:event_userStateAnalysisButtonActionPerformed

    private void noSBCheckboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noSBCheckboxActionPerformed
        redrawGraph();
    }//GEN-LAST:event_noSBCheckboxActionPerformed

    private void edgeIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edgeIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edgeIDActionPerformed

    private void getEdgeDataButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getEdgeDataButtonActionPerformed
        if (edgeID.getText() != null && !edgeID.getText().isEmpty()) {
            int edgeCounter = Integer.valueOf(edgeID.getText());
            GraphBuilder gb = tabToBuilderMap.get(tabView.getTitleAt(tabView.getSelectedIndex()));

            HashSet<TaintEdge> edges = gb.getEdgeList();
            for (TaintEdge edge : edges) {
                if (edge.getCounter() == edgeCounter && !edge.getAdviceType().startsWith("NONTAINTRETURN")) {
                    System.out.println("Matched: " + edge.getCounter());
                    TextFrame.showText(edge.toDebugString());
                    break;
                }
            }
        }
    }//GEN-LAST:event_getEdgeDataButtonActionPerformed

    private void resetPartitionsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetPartitionsButtonActionPerformed
        PartitionSimulator.resetPartitions();
        VisualizationViewer<TaintNode, TaintEdge> mainVV = tabToViewerMap.get("Graph");
        GraphBuilder mainGB = tabToBuilderMap.get("Graph");
        mainGB.resetNodeColors();
        mainGB.getMultiGraph();
        mainVV.repaint();
    }//GEN-LAST:event_resetPartitionsButtonActionPerformed

    private void partitionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_partitionButtonActionPerformed
        VisualizationViewer<TaintNode, TaintEdge> mainVV = tabToViewerMap.get("Graph");
        GraphBuilder mainGB = tabToBuilderMap.get("Graph");

        HashSet<TaintNode> picked = new HashSet<TaintNode>(mainVV.getPickedVertexState().getPicked());
        PartitionSimulator.addPartition(picked, mainGB);

        analysisText.append("Partition " + PartitionSimulator.getPartitionCount() + " is " + palette[PartitionSimulator.getPartitionCount() + 1] + "\n");

        mainGB.getMultiGraph();
        mainVV.repaint();
    }//GEN-LAST:event_partitionButtonActionPerformed

    private void showInputsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showInputsButtonActionPerformed
        GraphBuilder activeBuilder = tabToBuilderMap.get(tabView.getTitleAt(tabView.getSelectedIndex()));
        activeBuilder.colorInputs();
        redrawGraph();
    }//GEN-LAST:event_showInputsButtonActionPerformed

    private void noGCCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noGCCheckBoxActionPerformed
        redrawGraph();
    }//GEN-LAST:event_noGCCheckBoxActionPerformed

    private void showOnlySelectedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showOnlySelectedButtonActionPerformed
        VisualizationViewer<TaintNode, TaintEdge> vv = tabToViewerMap.get(tabView.getTitleAt(tabView.getSelectedIndex()));

        HashSet<TaintNode> picked = new HashSet<TaintNode>(vv.getPickedVertexState().getPicked());
        selectionFilterNodes.clear();
        selectionFilterNodes.addAll(picked);
        redrawGraph();
    }//GEN-LAST:event_showOnlySelectedButtonActionPerformed

    private void resetSelectionFilterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetSelectionFilterButtonActionPerformed
        selectionFilterNodes.clear();
        connectBlockedNodes.clear();
        redrawGraph();
    }//GEN-LAST:event_resetSelectionFilterButtonActionPerformed

    private void showConnectedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showConnectedButtonActionPerformed
        VisualizationViewer<TaintNode, TaintEdge> vv = tabToViewerMap.get(tabView.getTitleAt(tabView.getSelectedIndex()));

        HashSet<TaintNode> picked = null;
        if (vv != null && vv.getPickedVertexState() != null)
            picked = new HashSet<TaintNode>(vv.getPickedVertexState().getPicked());

        redrawGraphNextConnected(true, false, picked);
    }//GEN-LAST:event_showConnectedButtonActionPerformed

    private void blockButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_blockButtonActionPerformed
        VisualizationViewer<TaintNode, TaintEdge> vv = tabToViewerMap.get(tabView.getTitleAt(tabView.getSelectedIndex()));
        GraphBuilder gb = tabToBuilderMap.get(tabView.getTitleAt(tabView.getSelectedIndex()));

        HashSet<TaintNode> picked = new HashSet<TaintNode>(vv.getPickedVertexState().getPicked());
        connectBlockedNodes.addAll(picked);
        for (TaintNode blockedNode : picked) {
            gb.colorNode(blockedNode, 10);
        }

        redrawGraphNextConnected(false, false, null);
    }//GEN-LAST:event_blockButtonActionPerformed

    private void fConnectedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fConnectedButtonActionPerformed
        redrawGraphNextConnected(true, true, null);
    }//GEN-LAST:event_fConnectedButtonActionPerformed

    private void highlightButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_highlightButtonActionPerformed
        GraphBuilder gb = tabToBuilderMap.get(tabView.getTitleAt(tabView.getSelectedIndex()));
        Graph<TaintNode, TaintEdge> fullGraph = gb.getMultiGraph();

        String matchtext = highlightField.getText();

        for (TaintNode node : fullGraph.getVertices()) {
            if (node.toString().contains(matchtext))
                gb.colorNode(node, 10);
            else
                gb.colorNode(node, 0);
        }
        redrawGraph();
    }//GEN-LAST:event_highlightButtonActionPerformed

    private void showOutputButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showOutputButtonActionPerformed
        GraphBuilder activeBuilder = tabToBuilderMap.get(tabView.getTitleAt(tabView.getSelectedIndex()));
        activeBuilder.colorOutputs();
        redrawGraph();
    }//GEN-LAST:event_showOutputButtonActionPerformed

    private void showPathButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showPathButtonActionPerformed
        VisualizationViewer<TaintNode, TaintEdge> vv = tabToViewerMap.get(tabView.getTitleAt(tabView.getSelectedIndex()));
        GraphBuilder gb = tabToBuilderMap.get(tabView.getTitleAt(tabView.getSelectedIndex()));
        Graph<TaintNode, TaintEdge> filteredGraph = tabToGraphMap.get(tabView.getTitleAt(tabView.getSelectedIndex()));


        TaintNode start = null;
        TaintNode end = null;

        HashSet<TaintNode> picked = new HashSet<TaintNode>(vv.getPickedVertexState().getPicked());
        for (TaintNode pickedNode : picked) {
            gb.colorNode(pickedNode, 10);
            if (start == null) {
                start = pickedNode;
                continue;
            }
            end = pickedNode;
            break;
        }

        gb.colorPathBetween(filteredGraph, start, end);
        gb.colorPathBetween(filteredGraph, end, start);
        redrawGraph();
    }//GEN-LAST:event_showPathButtonActionPerformed

    private void hideUnusedBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hideUnusedBoxActionPerformed
        redrawGraph();
    }//GEN-LAST:event_hideUnusedBoxActionPerformed

    private void fullOutputButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fullOutputButtonActionPerformed
        GraphBuilder gb = tabToBuilderMap.get("Graph");
        String output = gb.getFullOutput();

        analysisText.append(output);
    }//GEN-LAST:event_fullOutputButtonActionPerformed

    private void getForwardGraphButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getForwardGraphButtonActionPerformed
        if (edgeID.getText() != null && !edgeID.getText().isEmpty()) {
            int edgeCounter = Integer.valueOf(edgeID.getText());
            GraphBuilder gb = tabToBuilderMap.get(tabView.getTitleAt(tabView.getSelectedIndex()));

            HashSet<TaintEdge> edges = gb.getEdgeList();
            for (TaintEdge edge : edges) {
                if (edge.getCounter() == edgeCounter && !edge.getAdviceType().startsWith("NONTAINTRETURN")) {
                    System.out.println("Matched: " + edge.getCounter());
                    GraphBuilder forwardBuilder = gb.getForwardTaintContextGraphBuilder(edge);
                    forwardBuilder.colorNode(edge.getCallingNode(), 5);
                    addAnalysisGraphBuilder(forwardBuilder, edge.toString(), "NONE");
                    break;
                }
            }
        }
    }//GEN-LAST:event_getForwardGraphButtonActionPerformed

    private void screenCapButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_screenCapButtonActionPerformed
        String outFileName = "/home/lee/DICE/thesiswriting/figs/" + graphFileName + ".png";
        writeToImageFile(outFileName);
    }//GEN-LAST:event_screenCapButtonActionPerformed

    private void uselessCommButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uselessCommButtonActionPerformed
        resetAnalysisGraphs();
        UselessCommAnalysis analysis = new UselessCommAnalysis(mainGraphBuilder, this, analysisText);
        analysis.analyze();
    }//GEN-LAST:event_uselessCommButtonActionPerformed

    private void linkColoredButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linkColoredButtonActionPerformed
        VisualizationViewer<TaintNode, TaintEdge> vv = tabToViewerMap.get(tabView.getTitleAt(tabView.getSelectedIndex()));
        GraphBuilder gb = tabToBuilderMap.get(tabView.getTitleAt(tabView.getSelectedIndex()));
        Graph<TaintNode, TaintEdge> filteredGraph = tabToGraphMap.get(tabView.getTitleAt(tabView.getSelectedIndex()));


        LinkedList<TaintNode> coloredNodes = new LinkedList<TaintNode>();
        for (TaintNode node : filteredGraph.getVertices()) {
            if (node.colorValue != 0) {
                coloredNodes.add(node);
            }
        }

        HashSet<TaintNode> linkNodes = new HashSet<TaintNode>();

        for (int i = 0; i < coloredNodes.size(); i++) {
            for (int j = i + 1; j < coloredNodes.size(); j++) {
                TaintNode start = coloredNodes.get(i);
                TaintNode end = coloredNodes.get(j);

                HashSet<TaintNode> path = gb.getPathBetween(filteredGraph, start, end);
                HashSet<TaintNode> backpath = gb.getPathBetween(filteredGraph, start, end);

                linkNodes.addAll(path);
                linkNodes.addAll(backpath);
            }
        }

        selectionFilterNodes.clear();
        selectionFilterNodes.addAll(linkNodes);

        redrawGraph();
    }//GEN-LAST:event_linkColoredButtonActionPerformed

    private void setInputsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setInputsButtonActionPerformed
        VisualizationViewer<TaintNode, TaintEdge> vv = tabToViewerMap.get(tabView.getTitleAt(tabView.getSelectedIndex()));

        HashSet<TaintNode> picked = new HashSet<TaintNode>(vv.getPickedVertexState().getPicked());
        inputNodes.clear();
        inputNodes.addAll(picked);
        redrawGraph();
    }//GEN-LAST:event_setInputsButtonActionPerformed

    private void setOutputsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setOutputsButtonActionPerformed
        VisualizationViewer<TaintNode, TaintEdge> vv = tabToViewerMap.get(tabView.getTitleAt(tabView.getSelectedIndex()));

        HashSet<TaintNode> picked = new HashSet<TaintNode>(vv.getPickedVertexState().getPicked());
        outputNodes.clear();
        outputNodes.addAll(picked);
        redrawGraph();
    }//GEN-LAST:event_setOutputsButtonActionPerformed

    private void linkIOButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linkIOButtonActionPerformed
        GraphBuilder gb = tabToBuilderMap.get(tabView.getTitleAt(tabView.getSelectedIndex()));
        Graph<TaintNode, TaintEdge> filteredGraph = tabToGraphMap.get(tabView.getTitleAt(tabView.getSelectedIndex()));
        
        HashSet<TaintNode> linkNodes = new HashSet<TaintNode>();

        for (TaintNode inputNode : inputNodes) {
            for (TaintNode outputNode : outputNodes) {
                HashSet<TaintNode> path = gb.getPathBetween(filteredGraph, inputNode, outputNode);

                linkNodes.addAll(path);
            }
        }

        selectionFilterNodes.clear();
        selectionFilterNodes.addAll(linkNodes);

        redrawGraph();
    }//GEN-LAST:event_linkIOButtonActionPerformed

    private void showOnlyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showOnlyButtonActionPerformed
        GraphBuilder gb = tabToBuilderMap.get(tabView.getTitleAt(tabView.getSelectedIndex()));
        Graph<TaintNode, TaintEdge> fullGraph = gb.getMultiGraph();

        String matchtext = highlightField.getText();

        selectionFilterNodes.clear();

        for (TaintNode node : fullGraph.getVertices()) {
            if (node.toString().contains(matchtext)) {
                selectionFilterNodes.add(node);
                gb.colorNode(node, 10);
                selectionFilterNodes.addAll(fullGraph.getNeighbors(node));
            }
        }

        redrawGraph();
    }//GEN-LAST:event_showOnlyButtonActionPerformed

    private void taintFlowConnectedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_taintFlowConnectedButtonActionPerformed
        VisualizationViewer<TaintNode, TaintEdge> vv = tabToViewerMap.get(tabView.getTitleAt(tabView.getSelectedIndex()));
        GraphBuilder gb = tabToBuilderMap.get(tabView.getTitleAt(tabView.getSelectedIndex()));

        TaintEdge picked = new LinkedList<TaintEdge>(vv.getPickedEdgeState().getPicked()).getFirst();

        selectionFilterNodes.addAll(gb.getForwardTaintContextNodes(picked));
        redrawGraph();
    }//GEN-LAST:event_taintFlowConnectedButtonActionPerformed

    private void writeToImageFile(String imageFileName) {
        BufferedImage bufImage = ScreenImage.createImage((JComponent) tabToViewPanelMap.get(tabView.getTitleAt(tabView.getSelectedIndex())));
        try {
            File outFile = new File(imageFileName);
            ImageIO.write(bufImage, "png", outFile);
            System.out.println("wrote image to " + imageFileName);
        } catch (Exception e) {
            System.out.println("writeToImageFile(): " + e.getMessage());
        }
    }

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AnalysisMainWindow.mainWindow = new AnalysisMainWindow();
                AnalysisMainWindow.mainWindow.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel analysisButtonsPanel;
    private javax.swing.JButton analysisClearButton;
    private javax.swing.JPanel analysisPanel;
    private javax.swing.JScrollPane analysisScroller;
    private javax.swing.JTextArea analysisText;
    private javax.swing.JButton aprAnalysisButton;
    private javax.swing.JButton blockButton;
    private javax.swing.JButton cachingAnalysisButton;
    private javax.swing.JCheckBox deepTaintCheckBox;
    private javax.swing.JTextField edgeID;
    private javax.swing.JButton fConnectedButton;
    private javax.swing.JTextField fileNameField;
    private javax.swing.JButton fullOutputButton;
    private javax.swing.JButton getEdgeDataButton;
    private javax.swing.JButton getForwardGraphButton;
    private javax.swing.JCheckBox hideCheckBox;
    private javax.swing.JCheckBox hideUnusedBox;
    private javax.swing.JButton highlightButton;
    private javax.swing.JTextField highlightField;
    private javax.swing.JCheckBox holdTaintIDButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel jungViewPanel;
    private javax.swing.JButton linkColoredButton;
    private javax.swing.JButton linkIOButton;
    private javax.swing.JButton loadSource;
    private javax.swing.JButton loadTracking;
    private javax.swing.JProgressBar memBar;
    private javax.swing.JButton multiGraphButton;
    private javax.swing.JButton multiLightGraphButton;
    private javax.swing.JCheckBox noGCCheckBox;
    private javax.swing.JCheckBox noSBCheckbox;
    private javax.swing.JButton partitionButton;
    private javax.swing.JButton postcomputationAnalyzeButton;
    private javax.swing.JButton quickLoadButton;
    private javax.swing.JComboBox requestCounters;
    private javax.swing.JButton resetAnalysisButton;
    private javax.swing.JButton resetPartitionsButton;
    private javax.swing.JButton resetSelectionFilterButton;
    private javax.swing.JButton screenCapButton;
    private javax.swing.JButton setInputsButton;
    private javax.swing.JButton setOutputsButton;
    private javax.swing.JButton showConnectedButton;
    private javax.swing.JButton showInputsButton;
    private javax.swing.JButton showOnlyButton;
    private javax.swing.JButton showOnlySelectedButton;
    private javax.swing.JButton showOutputButton;
    private javax.swing.JButton showPathButton;
    private javax.swing.JTextField sourceFileName;
    private javax.swing.JButton staticStateAnalyzeButton;
    private javax.swing.JTabbedPane tabView;
    private javax.swing.JButton taintFlowConnectedButton;
    private javax.swing.JTree taintIDTree;
    private javax.swing.JPanel treePanel;
    private javax.swing.JButton uselessCommButton;
    private javax.swing.JButton userStateAnalysisButton;
    // End of variables declaration//GEN-END:variables

}
