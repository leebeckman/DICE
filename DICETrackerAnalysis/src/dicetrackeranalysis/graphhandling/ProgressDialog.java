/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dicetrackeranalysis.graphhandling;

/**
 *
 * @author lee
 */
import javax.swing.JDialog;
import javax.swing.JFrame;

public class ProgressDialog extends JDialog {
    private ProgressPanel myPanel = null;

    public ProgressDialog(JFrame frame, String description, int maxValue) {
        super(frame, false);
        myPanel = new ProgressPanel(description, maxValue);
        getContentPane().add(myPanel);
        pack();
        setLocationRelativeTo(frame);
        setVisible(true);
    }

    public void setValue(int value) {
        myPanel.setValue(value);
    }

    public int getValue() {
        return myPanel.getValue();
    }

    public void setPortion(double portion) {
        myPanel.setPortion(portion);
    }

    public void finish() {
        setVisible(false);
    }
}
