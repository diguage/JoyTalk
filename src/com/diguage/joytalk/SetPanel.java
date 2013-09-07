package com.diguage.joytalk;

/**
 * @author D瓜哥，http://www.diguage.com/
 *
 * Date: 2008-6-17 9:12:24
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetPanel extends JPanel {
    private GridBagLayout layout;

    private GridBagConstraints constraints;

    Frame frame;

    JLabel setLabel;

    JLabel sortLabel;

    JLabel styleLabel;

    int labelFontSize;

    String labelFontStyle;

    JTextField sortField;

    JButton randomButton;

    String styleString;

    StyleCombo styleCombo;

    public SetPanel(Frame frame) {
        this.frame = frame;
        layout = new GridBagLayout();
        this.setLayout(layout);
        constraints = new GridBagConstraints();
        setLabel = new JLabel(new ImageIcon("images/set.gif"));
        constraints.fill = GridBagConstraints.BOTH;
        addComponent(setLabel, 0, 0, 1, 2);
        labelFontSize = 15;
        labelFontStyle = "SansSerif";
        sortLabel = new JLabel("   端口：");
        sortLabel.setFont(new Font(labelFontStyle, Font.PLAIN, labelFontSize));
        constraints.fill = GridBagConstraints.BOTH;
        addComponent(sortLabel, 0, 1, 1, 1);
        styleLabel = new JLabel("   风格：");
        styleLabel.setFont(new Font(labelFontStyle, Font.PLAIN, labelFontSize));
        constraints.fill = GridBagConstraints.BOTH;
        addComponent(styleLabel, 1, 1, 1, 1);
        sortField = new JTextField("4444", 6);
        constraints.fill = GridBagConstraints.BOTH;
        addComponent(sortField, 0, 2, 1, 1);
        randomButton = new JButton("随机生成");
        // randomButton.setText("随机生成");
        constraints.fill = GridBagConstraints.NONE;
        addComponent(randomButton, 0, 3, 1, 1);
        randomButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Integer iSort = new Integer((int) (Math.random() * 65536));
                sortField.setText(iSort.toString());
            }
        });
        styleCombo = new StyleCombo(this.frame);
        constraints.fill = GridBagConstraints.BOTH;
        addComponent(styleCombo.styleCombo, 1, 2, 2, 1);
    }

    // method to set constraints on
    private void addComponent(Component component, int row, int column,
                              int width, int height) {
        // set gridx and gridy
        constraints.gridx = column;
        constraints.gridy = row;
        // set gridwidth and gridheight
        constraints.gridwidth = width;
        constraints.gridheight = height;
        // set constraints and add component
        layout.setConstraints(component, constraints);
        this.add(component);
    }

    public static void main(String[] args) {
        // TODO 自动生成方法存根
        JFrame frame = new JFrame();
        JPanel panel = new SetPanel(frame);
        frame.add(panel);
        frame.setVisible(true);
        frame.setSize(280, 90);
    }
}
