/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dicetrackeranalysis.graphhandling;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JProgressBar;

/**
 *
 * @author lee
 */
public class MemMonitor implements Runnable {

    Runtime rt = Runtime.getRuntime();
    JProgressBar pb;

    public MemMonitor(JProgressBar progressBar) {
        pb = progressBar;
        pb.setMaximum((int)(rt.totalMemory() / 1024));
        pb.setMinimum(0);
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);

                pb.setValue((int)((rt.totalMemory() - rt.freeMemory()) / 1024));
            } catch (Exception ex) {
                Logger.getLogger(MemMonitor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
