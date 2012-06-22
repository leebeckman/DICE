/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dicetrackeranalysis.graphanalysis;

import dicetrackeranalysis.graphhandling.GraphBuilder;
import dicetrackeranalysis.graphhandling.TaintNode;
import java.util.HashSet;
import java.util.LinkedList;

/**
 *
 * @author lee
 */
public class PartitionSimulator {

    private static int partitionCounter = 0;
    private static LinkedList<HashSet<TaintNode>> partitions = new LinkedList<HashSet<TaintNode>>();

    public static void addPartition(HashSet<TaintNode> partitionNodes, GraphBuilder gb) {
        for (TaintNode node : partitionNodes) {
            node.setPartitionID(partitionCounter);
            gb.colorNode(node, partitionCounter + 1);
        }

        partitions.add(partitionNodes);

        partitionCounter++;
    }

    public static void resetPartitions() {
        partitions.clear();
        partitionCounter = 0;
    }

    public static LinkedList<HashSet<TaintNode>> getPartitions() {
        return partitions;
    }

    public static int getPartitionCount() {
        return partitionCounter;
    }

}
