package ui;

import java.awt.*;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.*;

public class GBLWeightTest extends JFrame {

    private static final Integer[] weights = new Integer[]{0, 1, 2, 5, 10};
    private GridBagConstraints leftGBC;
    private GridBagConstraints rightGBC;
    private GridBagLayout layout;

    public GBLWeightTest() {
        super("GridBagLayout – weightx test");
        layout = new GridBagLayout();
        final JPanel content = new JPanel(layout);
        final JComboBox cbxLeft = new JComboBox(weights);
        cbxLeft.setSelectedIndex(0);
        final JComboBox cbxRight = new JComboBox(weights);
        cbxRight.setSelectedIndex(0);
        //cbxRight.setPreferredSize(new Dimension(200, cbxRight.getPreferredSize().height));
        leftGBC = new GridBagConstraints(0, GridBagConstraints.RELATIVE, 1, 1, 20, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0, 0);
        rightGBC = new GridBagConstraints(1, GridBagConstraints.RELATIVE, 1, 1, 20, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0, 0);
        layout.setConstraints(cbxLeft, leftGBC);
        layout.setConstraints(cbxRight, leftGBC);
        content.add(cbxLeft);
        content.add(cbxRight);
        cbxLeft.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED){
                    leftGBC.weightx = (Integer)cbxLeft.getSelectedItem();
                    layout.setConstraints(cbxLeft, leftGBC);
                    content.doLayout();
                    cbxLeft.doLayout();
                    cbxRight.doLayout();
                }
            }
        });
        cbxRight.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED){
                    rightGBC.weightx = (Integer)cbxRight.getSelectedItem();
                    layout.setConstraints(cbxRight, rightGBC);
                    content.doLayout();
                    cbxLeft.doLayout();
                    cbxRight.doLayout();
                }
            }
        });
        setSize(420, 80);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().add(content, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        new GBLWeightTest().setVisible(true);
    }
}