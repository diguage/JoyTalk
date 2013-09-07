package com.diguage.joytalk;

/**
 * @author D瓜哥，http://www.diguage.com/
 *
 * Fig. 14.19: GridBagDemo.java
 * Demonstrating GridBagLayout.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterFrame extends JFrame implements ActionListener {
    private Container container;

    private GridBagLayout layout;

    private GridBagConstraints constraints;

    JLabel userNameLabel;

    JLabel userNameTip;

    JLabel userPwdLabel;

    JLabel userPwdLabel2;

    JLabel userSexLabel;

    JLabel userAgeLabel;

    JLabel userAddressLabel;

    JLabel userEmailLabel;

    JTextField userNameField;

    JPasswordField userPasswordField;

    JPasswordField userPasswordField2;

    JSpinner ageSpinner;

    JTextField userAddressField;

    JTextField userEmailField;

    JComboBox addressComboBox;

    JComboBox sexComboBox;

    JPanel buttonPanel;

    JButton concelButton;

    JButton repeatButton;

    JButton registButton;

    public RegisterFrame() {
        super("欢迎加入JoyTalk");
        String lnfName = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
        try {
            UIManager.setLookAndFeel(lnfName);
        } catch (ClassNotFoundException e) {
            // TODO 自动生成 catch 块
            e.printStackTrace();
        } catch (InstantiationException e) {
            // TODO 自动生成 catch 块
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO 自动生成 catch 块
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            // TODO 自动生成 catch 块
            e.printStackTrace();
        }
        SwingUtilities.updateComponentTreeUI(this);
        container = getContentPane();
        layout = new GridBagLayout();
        container.setLayout(layout);
        constraints = new GridBagConstraints();
        userNameLabel = new JLabel("用户名：");
        constraints.fill = GridBagConstraints.BOTH;
        addComponent(userNameLabel, 0, 0, 1, 1);
        userNameTip = new JLabel("  只是字母加数字！密码最少六位！");
        addComponent(userNameTip, 1, 0, 3, 1);
        userPwdLabel = new JLabel("密码：");
        constraints.fill = GridBagConstraints.BOTH;
        addComponent(userPwdLabel, 2, 0, 1, 1);
        userPwdLabel2 = new JLabel("确认密码：");
        constraints.fill = GridBagConstraints.BOTH;
        addComponent(userPwdLabel2, 3, 0, 1, 1);
        userSexLabel = new JLabel("性别：");
        constraints.fill = GridBagConstraints.BOTH;
        addComponent(userSexLabel, 4, 0, 1, 1);
        userAgeLabel = new JLabel("年龄：");
        constraints.fill = GridBagConstraints.BOTH;
        addComponent(userAgeLabel, 5, 0, 1, 1);
        userAddressLabel = new JLabel("地址：");
        constraints.fill = GridBagConstraints.BOTH;
        addComponent(userAddressLabel, 6, 0, 1, 1);
        userEmailLabel = new JLabel("邮箱：");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        addComponent(userEmailLabel, 7, 0, 1, 1);
        userNameField = new JTextField(10);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        addComponent(userNameField, 0, 1, 2, 1);
        userPasswordField = new JPasswordField(10);
        constraints.fill = GridBagConstraints.BOTH;
        addComponent(userPasswordField, 2, 1, 2, 1);
        userPasswordField2 = new JPasswordField(10);
        constraints.fill = GridBagConstraints.BOTH;
        addComponent(userPasswordField2, 3, 1, 2, 1);
        String sexes[] = {"男", "女"};
        sexComboBox = new JComboBox(sexes);
        addComponent(sexComboBox, 4, 1, 1, 1);
        ageSpinner = new JSpinner(new SpinnerNumberModel(18, 0, 99, 1));
        constraints.fill = GridBagConstraints.BOTH;
        addComponent(ageSpinner, 5, 1, 1, 1);
        String addresses[] = {"河南", "浙江", "江西", "湖北", "四川", "内蒙古", "黑龙江",
                "江苏", "北京"};
        addressComboBox = new JComboBox(addresses);
        addressComboBox.setMaximumRowCount(5);
        addComponent(addressComboBox, 6, 1, 1, 1);
        userEmailField = new JTextField(10);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        addComponent(userEmailField, 7, 1, 2, 1);
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        concelButton = new JButton("放弃");
        concelButton.addActionListener(this);
        buttonPanel.add(concelButton);
        repeatButton = new JButton("重填");
        repeatButton.addActionListener(this);
        buttonPanel.add(repeatButton);
        registButton = new JButton("注册");
        registButton.addActionListener(this);
        buttonPanel.add(registButton);
        addComponent(buttonPanel, 8, 0, 3, 1);
        // this.setLocation(600, 200);
        setSize(300, 300);
        setVisible(true);
        this.setLocationRelativeTo(null); // 设为null， 将窗口置于屏幕的中央
    } // end constructor

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == concelButton) {
            int i = JOptionPane.showConfirmDialog(null, "你确定退出吗？", "退出",
                    JOptionPane.OK_CANCEL_OPTION);
            if (i == JOptionPane.YES_NO_OPTION)
                System.exit(0);
        } else if (event.getSource() == repeatButton) {
            userNameField.setText(null);
            userPasswordField.setText(null);
            userPasswordField2.setText(null);
            sexComboBox.setSelectedIndex(0);
            ageSpinner.setValue(new Integer(18));
            addressComboBox.setSelectedIndex(0);
            userEmailField.setText(null);
        } else if (event.getSource() == registButton) {
            new TalkingFrame();
            dispose();
        }
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
        container.add(component);
    }

//    public static void main(String args[]) {
//        RegisterFrame application = new RegisterFrame();
//        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    }
} // end class GridBagDemo
