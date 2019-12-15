package UI;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

/**
 * UI.FlowLayoutTest
 *
 * @author Eugene Matyushkin
 */
public class FlowLayoutTest extends JFrame{

    private FlowLayout layout;

    private static final Alignment[] ALIGNMENTS =
            new Alignment[]{new Alignment(FlowLayout.LEFT, "FlowLayout.LEFT"),
                    new Alignment(FlowLayout.CENTER, "FlowLayout.CENTER"),
                    new Alignment(FlowLayout.RIGHT, "FlowLayout.RIGHT"),
                    new Alignment(FlowLayout.LEADING, "FlowLayout.LEADING"),
                    new Alignment(FlowLayout.TRAILING, "FlowLayout.TRAILING")};

    public FlowLayoutTest(){
        super("FlowLayout");
        layout = new FlowLayout(FlowLayout.LEFT);
        final JPanel content = new JPanel();
        content.setLayout(layout);
        JLabel lbl = new JLabel("Label");
        lbl.setFont(lbl.getFont().deriveFont(30.f));
        lbl.setPreferredSize(new Dimension(100, 50));
        lbl.setHorizontalAlignment(JLabel.CENTER);
        lbl.setBorder(BorderFactory.createLineBorder(Color.blue, 1));
        content.add(lbl);
        JButton btn = new JButton("Button");
        btn.setFont(btn.getFont().deriveFont(20.f));
        content.add(btn);
        content.add(new JTextField("Some text field", 10));
        content.setBorder(BorderFactory.createLineBorder(Color.red));
        final JCheckBox chkBaseLine = new JCheckBox("Align on baseline");
        final JCheckBox chkOrientation = new JCheckBox("Right-to-left orientation");
        final JComboBox cbxAlignments = new JComboBox(ALIGNMENTS);
        cbxAlignments.setSelectedIndex(0);
        cbxAlignments.setBorder(BorderFactory.createEmptyBorder(0,5,0,5));
        JPanel controls = new JPanel(new GridLayout(4,1));
        JLabel lblAlignment = new JLabel("Alignment:");

        lblAlignment.setVerticalAlignment(JLabel.BOTTOM);
        lblAlignment.setBorder(BorderFactory.createEmptyBorder(0,5,0,5));
        controls.add(lblAlignment);
        controls.add(cbxAlignments);
        controls.add(chkOrientation);
        controls.add(chkBaseLine);
        controls.setBorder(BorderFactory.createLineBorder(Color.orange));

        getContentPane().add(content, BorderLayout.CENTER);

        getContentPane().add(controls, BorderLayout.SOUTH);


        chkBaseLine.addChangeListener(new ChangeListener(){
            public void stateChanged(ChangeEvent e){
                layout.setAlignOnBaseline(chkBaseLine.isSelected());
                content.doLayout();
            }
        });
        chkOrientation.addChangeListener(new ChangeListener(){
            public void stateChanged(ChangeEvent e){
                content.setComponentOrientation( chkOrientation.isSelected() ?
                        ComponentOrientation.RIGHT_TO_LEFT :
                        ComponentOrientation.LEFT_TO_RIGHT);
                content.doLayout();
            }
        });
        cbxAlignments.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e){
                if (e.getStateChange() == ItemEvent.SELECTED ){
                    layout.setAlignment(((Alignment)cbxAlignments.getSelectedItem()).alignment);
                    content.doLayout();
                }
            }
        });
        setSize(410, 220);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args){
            new FlowLayoutTest().setVisible(true);
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