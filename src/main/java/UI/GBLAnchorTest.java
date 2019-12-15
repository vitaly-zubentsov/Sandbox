package UI;

import java.awt.*;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

/**
 * GBLAnchorTest
 *
 * @author Eugene Matyushkin
 */
public class GBLAnchorTest extends JFrame {

    private GridBagLayout layout;
    private JComponent component;
    private GridBagConstraints constraints;

    private static final Alignment[] ALIGNMENTS = new Alignment[]{
            new Alignment(GridBagConstraints.CENTER, "GridBagConstraints.CENTER"),
            new Alignment(GridBagConstraints.NORTHWEST, "GridBagConstraints.NORTHWEST"),
            new Alignment(GridBagConstraints.NORTH, "GridBagConstraints.NORTH"),
            new Alignment(GridBagConstraints.NORTHEAST, "GridBagConstraints.NORTHEAST"),
            new Alignment(GridBagConstraints.EAST, "GridBagConstraints.EAST"),
            new Alignment(GridBagConstraints.SOUTHEAST, "GridBagConstraints.SOUTHEAST"),
            new Alignment(GridBagConstraints.SOUTH, "GridBagConstraints.SOUTH"),
            new Alignment(GridBagConstraints.SOUTHWEST, "GridBagConstraints.SOUTHWEST"),
            new Alignment(GridBagConstraints.WEST, "GridBagConstraints.WEST"),
            new Alignment(GridBagConstraints.FIRST_LINE_START, "GridBagConstraints.FIRST_LINE_START"),
            new Alignment(GridBagConstraints.PAGE_START, "GridBagConstraints.PAGE_START"),
            new Alignment(GridBagConstraints.FIRST_LINE_END, "GridBagConstraints.FIRST_LINE_END"),
            new Alignment(GridBagConstraints.LINE_END, "GridBagConstraints.LINE_END"),
            new Alignment(GridBagConstraints.LAST_LINE_END, "GridBagConstraints.LAST_LINE_END"),
            new Alignment(GridBagConstraints.PAGE_END, "GridBagConstraints.PAGE_END"),
            new Alignment(GridBagConstraints.LAST_LINE_START, "GridBagConstraints.LAST_LINE_START"),
            new Alignment(GridBagConstraints.LINE_START, "GridBagConstraints.LINE_START"),
            new Alignment(GridBagConstraints.BASELINE, "GridBagConstraints.BASELINE"),
            new Alignment(GridBagConstraints.BASELINE_LEADING, "GridBagConstraints.BASELINE_LEADING"),
            new Alignment(GridBagConstraints.BASELINE_TRAILING, "GridBagConstraints.BASELINE_TRAILING"),
            new Alignment(GridBagConstraints.ABOVE_BASELINE, "GridBagConstraints.ABOVE_BASELINE"),
            new Alignment(GridBagConstraints.ABOVE_BASELINE_LEADING, "GridBagConstraints.ABOVE_BASELINE_LEADING"),
            new Alignment(GridBagConstraints.ABOVE_BASELINE_TRAILING, "GridBagConstraints.ABOVE_BASELINE_TRAILING"),
            new Alignment(GridBagConstraints.BELOW_BASELINE, "GridBagConstraints.BELOW_BASELINE"),
            new Alignment(GridBagConstraints.BELOW_BASELINE_LEADING, "GridBagConstraints.BELOW_BASELINE_LEADING"),
            new Alignment(GridBagConstraints.BELOW_BASELINE_TRAILING, "GridBagConstraints.BELOW_BASELINE_TRAILING"),
    };


    public GBLAnchorTest(){
        super("GridBagLayout – anchor test");
        layout = new GridBagLayout();
        final JPanel content = new JPanel(layout);

        JLabel lblFirst = new JLabel("Label");
        lblFirst.setFont(lblFirst.getFont().deriveFont(30f));
        content.add(lblFirst, new GridBagConstraints(0,0,1,1,0,1,
                GridBagConstraints.BASELINE, GridBagConstraints.HORIZONTAL,
                new Insets(5,5,5,5),0,0));
        component = new JButton("Button");
        constraints = new GridBagConstraints(1,0,1,2,1,1,
                GridBagConstraints.CENTER, GridBagConstraints.NONE,
                new Insets(5,5,5,5),0,0);
        content.add(component, constraints);
        content.setBorder(BorderFactory.createLineBorder(Color.red));
        content.add(new JLabel("Second label"), new GridBagConstraints(0,1,1,1,0,1,
                GridBagConstraints.BASELINE, GridBagConstraints.HORIZONTAL,
                new Insets(5,5,5,5),0,0));
        final JCheckBox chkOrientation = new JCheckBox("Right-to-left orientation");
        final JComboBox cbxAlignments = new JComboBox(ALIGNMENTS);
        cbxAlignments.setSelectedIndex(0);
        cbxAlignments.setBorder(BorderFactory.createEmptyBorder(0,5,0,5));
        JPanel controls = new JPanel(new GridLayout(3,1));
        JLabel lblAlignment = new JLabel("Alignment:");
        lblAlignment.setVerticalAlignment(JLabel.BOTTOM);
        lblAlignment.setBorder(BorderFactory.createEmptyBorder(0,5,0,5));
        controls.add(lblAlignment);
        controls.add(cbxAlignments);
        controls.add(chkOrientation);
        controls.setBorder(BorderFactory.createLineBorder(Color.orange));
        getContentPane().add(content, BorderLayout.CENTER);
        getContentPane().add(controls, BorderLayout.SOUTH);
        chkOrientation.addChangeListener(new ChangeListener(){
            public void stateChanged(ChangeEvent e){
                content.setComponentOrientation( chkOrientation.isSelected() ?
                        ComponentOrientation.RIGHT_TO_LEFT :
                        ComponentOrientation.LEFT_TO_RIGHT );
                content.doLayout();
            }
        });
        cbxAlignments.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e){
                if (e.getStateChange() == ItemEvent.SELECTED ){
                    constraints.anchor = ((Alignment)cbxAlignments.getSelectedItem()).alignment;
                    layout.setConstraints(component, constraints);
                    content.doLayout();
                }
            }
        });
        setSize(410, 220);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args){
        JFrame.setDefaultLookAndFeelDecorated(true);
        new GBLAnchorTest().setVisible(true);
    }

    private static class Alignment{

        private int alignment;
        private String description;

        Alignment(int alignment, String description){
            this.alignment = alignment;
            this.description = description;
        }

        @Override
        public String toString(){
            return description;
        }
    }
}